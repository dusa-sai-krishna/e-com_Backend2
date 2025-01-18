package com.saiDeveloper.E_commerce2_Backend.repo;

import com.saiDeveloper.E_commerce2_Backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {


    @Query("SELECT o FROM Order o WHERE o.user.id = :userId" +
            " And (o.orderStatus IN ('PLACED','CONFIRMED','SHIPPED', 'DELIVERED'))")

    public List<Order> getAllOrdersByUserId(@Param("userId") Long userId);

}







