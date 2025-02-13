package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.exception.CartException;
import com.saiDeveloper.E_commerce2_Backend.exception.CartItemException;
import com.saiDeveloper.E_commerce2_Backend.exception.ProductException;
import com.saiDeveloper.E_commerce2_Backend.model.Cart;
import com.saiDeveloper.E_commerce2_Backend.model.CartItem;
import com.saiDeveloper.E_commerce2_Backend.model.Product;
import com.saiDeveloper.E_commerce2_Backend.model.User;
import com.saiDeveloper.E_commerce2_Backend.repo.CartRepo;
import com.saiDeveloper.E_commerce2_Backend.request.AddItemRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CartServiceImplementation implements CartService{

    @Autowired
    private CartRepo repo;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ProductService productService;

    @Override
    public Cart createCart(User user) {

        Cart cart = new Cart();

        cart.setUser(user);
        log.info("Cart created successfully for user {}",user.getId());
        return repo.save(cart);
    }


    @Override
    public Cart addCartItem(Long userId, AddItemRequest req) throws ProductException, CartException, CartItemException {

        Cart cart = repo.findByUserId(userId).orElse(null);
        if(cart!=null){
            log.info("Cart found with user id:{}",userId);
            //find product
            Product product = productService.findProductById(req.getProductId());

            CartItem isPresent = cartItemService.isCartItemExist(cart,product,req.getSize(),userId);

            if(isPresent==null){
                log.info("CartItem is not present. Creating a new one");
                CartItem cartItem = new CartItem();

                cartItem.setCart(cart);
                cartItem.setProduct(product);
                cartItem.setSize(req.getSize());
                cartItem.setQuantity(req.getQuantity());
                cartItem.setUserId(userId);

                CartItem updatedCartitem = cartItemService.createCartItem(cartItem);
                cart.getCartItems().add(updatedCartitem);


                log.info("Item added to cart successfully");
               return repo.save(cart);
            }
            else {
                log.error("Item already exists in cart");
                throw new CartItemException("Item already exists in cart");
            }
        }
        else {
            log.error("Cart not found with user id:{}",userId);
            throw new CartException("Can't access Cart of another User");
        }
    }

    /**
     * Find the cart associated with the given user ID. If the cart does not exist, return null.
     * <p>
     * If the cart does exist, calculate the total price, total discounted price, and total item count of all items in
     * the cart. Set these values on the cart before returning it.
     * <p>
     * @param userId the ID of the user to find the cart for
     * @return the cart associated with the given user ID, or null if no such cart exists
     */
    @Override
    public Cart findCartByUserId(Long userId) throws CartException {

        Cart cart = repo.findByUserId(userId).orElse(null);

        if(cart!=null){
            log.info("Cart found with user id:{} and cart:{}",userId,cart);

            return aggregateCost(cart.getUser().getId());
        }
        log.error("Cart not found with user id:{}",userId);
            throw new CartException("Cart not found with user id:"+userId);


    }



    @Override
    public Cart aggregateCost(Long userId){
        Cart cart = repo.findByUserId(userId).orElse(null);

        if(cart!=null){
            log.info("Process of identifying total price and items has started for the cart with id:{}",cart.getId());
            int totalPrice = 0;
            int totalDiscountedPrice = 0;
            int totalItem = 0;

            for(CartItem cartItem : cart.getCartItems()){
                totalPrice += cartItem.getPrice();
                totalDiscountedPrice += cartItem.getDiscountedPrice();
                totalItem += cartItem.getQuantity();
            }
            cart.setTotalPrice(totalPrice);
            cart.setTotalItems(totalItem);
            cart.setTotalDiscountedPrice(totalDiscountedPrice);
            cart.setDiscount(cart.getTotalPrice()-cart.getTotalDiscountedPrice());
            log.info("Process of identifying total price and items has completed for the cart with id:{}",cart.getId());
        }

        return cart;
    }

}





























