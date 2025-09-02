package com.saiDeveloper.E_commerce2_Backend.repo;

import com.saiDeveloper.E_commerce2_Backend.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem,Long> {


    @Modifying
    @Query("Delete From OrderItem o Where o.product.id = :id")
    void deleteByProductId(Long id);

    @Query("Select o From OrderItem o Where o.product.id = :id")
    List<OrderItem> findByProductId(Long id);
}
