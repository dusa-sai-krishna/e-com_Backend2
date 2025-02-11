package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.exception.CartItemException;
import com.saiDeveloper.E_commerce2_Backend.exception.UserException;
import com.saiDeveloper.E_commerce2_Backend.model.Cart;
import com.saiDeveloper.E_commerce2_Backend.model.CartItem;
import com.saiDeveloper.E_commerce2_Backend.model.Product;

public interface CartItemService {

     CartItem createCartItem(CartItem cartItem) throws CartItemException;


    CartItem updateCartItem(Long userId, Long cartItemId, Integer quantity) throws CartItemException;

     CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

     void removeCartItem(Long userId, Long id) throws CartItemException,UserException;

     CartItem findCartItemById(Long id) throws CartItemException;

    CartItem getCartItemById(Long cartItemId) throws CartItemException;
}









