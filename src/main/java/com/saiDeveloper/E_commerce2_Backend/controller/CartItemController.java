package com.saiDeveloper.E_commerce2_Backend.controller;

import com.saiDeveloper.E_commerce2_Backend.exception.CartItemException;
import com.saiDeveloper.E_commerce2_Backend.exception.ProductException;
import com.saiDeveloper.E_commerce2_Backend.exception.UserException;
import com.saiDeveloper.E_commerce2_Backend.model.User;
import com.saiDeveloper.E_commerce2_Backend.request.AddItemRequest;
import com.saiDeveloper.E_commerce2_Backend.response.ApiResponse;
import com.saiDeveloper.E_commerce2_Backend.service.CartItemService;
import com.saiDeveloper.E_commerce2_Backend.service.CartService;
import com.saiDeveloper.E_commerce2_Backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cartItem")
@Tag(name = "Cart", description = "Cart Item Controller")
public class CartItemController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private CartService cartService;
    @DeleteMapping("/{cartItemId}")
    @Operation(
            summary = "Delete cart item based on CartItemId",
            description = "Delete cart item based on CartItemId. It finds the user through JWT and if all happend well, returns OK response",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cart item deleted successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Cart item not found")
            }
    )
    public ResponseEntity<ApiResponse> deleteCartItem(
            @PathVariable Long cartItemId,
            @RequestHeader("Authorization") String jwt) throws UserException, CartItemException {
        User user = userService.findByJWT(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse response = new ApiResponse();
        response.setMessage("Cart item deleted successfully");
        response.setStatus(true);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
    }


    @PutMapping("/add-item")
    @Operation(summary = "Add item to cart", description = "Add an item to the cart")
    public ResponseEntity<ApiResponse> addItemToCart(
            @RequestBody AddItemRequest req,
            @RequestHeader("Authorization") String jwt) throws UserException, ProductException, CartItemException {
                User user = userService.findByJWT(jwt);

                cartService.addCartItem(user.getId(), req);
                ApiResponse response = new ApiResponse();
                response.setMessage("Item added to cart successfully");
                response.setStatus(true);
                return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
            }


}


















