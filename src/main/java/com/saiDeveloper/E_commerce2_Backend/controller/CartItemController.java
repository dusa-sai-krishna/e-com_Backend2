package com.saiDeveloper.E_commerce2_Backend.controller;

import com.saiDeveloper.E_commerce2_Backend.exception.CartException;
import com.saiDeveloper.E_commerce2_Backend.exception.CartItemException;
import com.saiDeveloper.E_commerce2_Backend.exception.ProductException;
import com.saiDeveloper.E_commerce2_Backend.exception.UserException;
import com.saiDeveloper.E_commerce2_Backend.model.Cart;
import com.saiDeveloper.E_commerce2_Backend.model.CartItem;
import com.saiDeveloper.E_commerce2_Backend.model.User;
import com.saiDeveloper.E_commerce2_Backend.request.AddItemRequest;
import com.saiDeveloper.E_commerce2_Backend.request.UpdateCartItemQuantityRequest;
import com.saiDeveloper.E_commerce2_Backend.service.CartItemService;
import com.saiDeveloper.E_commerce2_Backend.service.CartService;
import com.saiDeveloper.E_commerce2_Backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cartItem")
@Tag(name = "Cart", description = "Cart Item Controller")
@Slf4j
public class CartItemController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private CartService cartService;
    @DeleteMapping("/id/{cartItemId}")
    @Operation(
            summary = "Delete cart item based on CartItemId",
            description = "Delete cart item based on CartItemId. It finds the user through JWT and if all happened well, returns OK response",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cart item deleted successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Cart item not found")
            }
    )
    public ResponseEntity<Long> deleteCartItem(
            @PathVariable Long cartItemId,
            @RequestHeader("Authorization") String jwt) throws UserException, CartItemException {
        User user = userService.findByJWT(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);
        return new ResponseEntity<>(cartItemId, HttpStatus.OK);
    }


    @PutMapping("/add")
    @Operation(summary = "Add item to cart", description = "Add an item to the cart")
    public ResponseEntity<Cart> addItemToCart(@Valid  @RequestBody AddItemRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException, CartItemException, CartException {

       log.info("Request to add a cart Item received with AddItemRequest:{}",req);

        User user = userService.findByJWT(jwt);
        Cart cart= cartService.addCartItem(user.getId(), req);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/id/{cartItemId}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable("cartItemId") Long cartItemId) throws CartItemException {
    return new ResponseEntity<>(cartItemService.getCartItemById(cartItemId), HttpStatus.OK);
}


    @PutMapping("/")
    public ResponseEntity<CartItem> updateCartItemQuantity(@RequestBody @Valid UpdateCartItemQuantityRequest req,
                                                           @RequestHeader("Authorization") String jwt
                                                            ) throws UserException, CartItemException {
        User user = userService.findByJWT(jwt);
        return new ResponseEntity<>(cartItemService.updateCartItem(user.getId(),req.getCartItemId(),req.getQuantity())
        ,HttpStatus.OK);
    }


}


















