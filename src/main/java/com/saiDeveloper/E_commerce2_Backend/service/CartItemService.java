package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.exception.CartItemException;
import com.saiDeveloper.E_commerce2_Backend.exception.UserException;
import com.saiDeveloper.E_commerce2_Backend.model.Cart;
import com.saiDeveloper.E_commerce2_Backend.model.CartItem;
import com.saiDeveloper.E_commerce2_Backend.model.Product;

public interface CartItemService {

    public CartItem createCartItem(CartItem cartItem) throws CartItemException;

    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem)throws UserException, CartItemException;

    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

    public void removeCartItem(Long userId, Long id) throws CartItemException,UserException;

    public CartItem findCartItemById(Long id) throws CartItemException;

    CartItem getCartItemById(Long cartItemId) throws CartItemException;
}









