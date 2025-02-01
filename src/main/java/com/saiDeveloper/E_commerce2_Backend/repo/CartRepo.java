package com.saiDeveloper.E_commerce2_Backend.repo;

import com.saiDeveloper.E_commerce2_Backend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {


    @Query("Select c From Cart c Where c.user.id = :userId")
     Optional<Cart> findByUserId(@Param("userId") Long userId);




}
