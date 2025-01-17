package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.exception.CartItemException;
import com.saiDeveloper.E_commerce2_Backend.exception.UserException;
import com.saiDeveloper.E_commerce2_Backend.model.Cart;
import com.saiDeveloper.E_commerce2_Backend.model.CartItem;
import com.saiDeveloper.E_commerce2_Backend.model.Product;
import com.saiDeveloper.E_commerce2_Backend.model.User;
import com.saiDeveloper.E_commerce2_Backend.repo.CartItemRepo;
import com.saiDeveloper.E_commerce2_Backend.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImplementation implements CartItemService{

    @Autowired
    CartItemRepo repo;

    @Autowired
    private UserService userService;

    @Autowired
    private CartRepo cartRepository;
    @Override
    public CartItem createCartItem(CartItem cartItem) {

        if(cartItem.getQuantity()==0){cartItem.setQuantity(1);}//by default cartItem quantity is 1
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());

        return repo.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws UserException, CartItemException {

        CartItem existingCartItem = findCartItemById(id);

        User orderedUser = userService.findById(existingCartItem.getUserId());

        // user can only update the quantity, then price and discounted Price are automatiically updated
        if(orderedUser.getId().equals(userId)){
            existingCartItem.setQuantity(cartItem.getQuantity());
            existingCartItem.setPrice(existingCartItem.getPrice()*existingCartItem.getQuantity());
            existingCartItem.setDiscountedPrice(existingCartItem.getDiscountedPrice()* existingCartItem.getQuantity());
        }

        return repo.save(cartItem);
    }

    //Primarily used to check whether a cartItem exists, then changes can be updated if not new cartItem is created!
    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {

        return repo.isCartItemExist(cart,product,size,userId).orElse(null);

    }

    @Override
    public void removeCartItem(Long userId, Long id) throws CartItemException, UserException {
        CartItem cartItem = findCartItemById(id);

        User user = userService.findById(cartItem.getUserId());

        if(user.getId().equals(userId)){
            repo.deleteById(id);
        }

        else{
            throw new UserException("You can't remove other Users items");
        }
    }

    @Override
    public CartItem findCartItemById(Long id) throws CartItemException {
        CartItem cartItem = repo.findById(id).orElse(null);
        if(cartItem!=null){
            return cartItem;
        }
        throw new CartItemException("Cart Item not found with id:"+id);
    }
}



















