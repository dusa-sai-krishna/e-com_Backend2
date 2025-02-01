package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.exception.CartItemException;
import com.saiDeveloper.E_commerce2_Backend.exception.UserException;
import com.saiDeveloper.E_commerce2_Backend.model.Cart;
import com.saiDeveloper.E_commerce2_Backend.model.CartItem;
import com.saiDeveloper.E_commerce2_Backend.model.Product;
import com.saiDeveloper.E_commerce2_Backend.repo.CartItemRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CartItemServiceImplementation implements CartItemService {

    @Autowired
    CartItemRepo repo;



    @Override
    public CartItem createCartItem(CartItem cartItem) {

        if (cartItem.getQuantity() == 0) {
            cartItem.setQuantity(1);
        }//by default cartItem quantity is 1
        cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());
        return repo.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException {

        CartItem existingCartItem = findCartItemById(id);



        // user can only update the quantity, then price and discounted Price are automatically updated
        if (existingCartItem.getUserId().equals(userId)) {
            log.info("Cart item with id:{} updation started", id);
            existingCartItem.setQuantity(cartItem.getQuantity());
            existingCartItem.setPrice(existingCartItem.getPrice() * existingCartItem.getQuantity());
            existingCartItem.setDiscountedPrice(existingCartItem.getDiscountedPrice() * existingCartItem.getQuantity());
        }

        return repo.save(cartItem);
    }

    //Primarily used to check whether a cartItem exists, then changes can be updated if not new cartItem is created!
    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        log.info("Checking for cartItem with cartId:{} productId:{} size:{} userId:{}", cart.getId(), product.getId(), size, userId);
        CartItem cartItem = repo.isCartItemExist(cart, product, size, userId).orElse(null);
        if (cartItem != null) {
            log.info("Cart Item found with cartItemId:{} cartId:{} productId:{} size:{} userId:{}", cartItem.getId(), cart.getId(), product.getId(), size, userId);
        }
        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long id) throws CartItemException, UserException {
        CartItem cartItem = findCartItemById(id);
        if (cartItem.getUserId().equals(userId)) {
            //delete from cart
            Cart cart = cartItem.getCart();
            cart.getCartItems().remove(cartItem);
          repo.deleteById(id);
            log.info("Cart item with id:{} deleted successfully", id);

        } else {
            log.info("You can't remove other Users Cart items");
            throw new UserException("You can't remove other Users items");

        }
    }

    @Override
    public CartItem findCartItemById(Long id) throws CartItemException {
        CartItem cartItem = repo.findById(id).orElse(null);
        if (cartItem != null) {
            return cartItem;
        }
        throw new CartItemException("Cart Item not found with id:" + id);
    }

    @Override
    public CartItem getCartItemById(Long cartItemId) throws CartItemException {

        CartItem cartItem = repo.findById(cartItemId).orElse(null);
        if (cartItem != null) {
            log.info("Cart Item found with id:{}", cartItemId);
        }
        else{
            throw new CartItemException("Cart Item not found with id:" + cartItemId);
        }

      return cartItem;
    }

}



















