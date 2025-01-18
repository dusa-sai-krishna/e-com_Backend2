package com.saiDeveloper.E_commerce2_Backend.repo;

import com.saiDeveloper.E_commerce2_Backend.model.Cart;
import com.saiDeveloper.E_commerce2_Backend.model.CartItem;
import com.saiDeveloper.E_commerce2_Backend.model.Product;
import com.saiDeveloper.E_commerce2_Backend.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem,Long> {


    @Query("Select ci From CartItem ci " +
            " Where ci.cart = :cart And ci.product = :product" +
            " And ci.size = :size And ci.userId = :userId")
    public Optional<CartItem> isCartItemExist(
            @Param("cart") Cart cart,
            @Param("product")Product product,
            @Param("size") String size,
            @Param("userId") Long userId
            );

}


















