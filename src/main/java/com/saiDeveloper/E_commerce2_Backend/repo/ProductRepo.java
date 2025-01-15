package com.saiDeveloper.E_commerce2_Backend.repo;

import com.saiDeveloper.E_commerce2_Backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {








    @Query("Select p From Product p" +
            "Where (p.category.name = :category Or :category = '') " +
            "And ((:minPrice Is NULL And :maxPrice Is NULL) Or (p.discountedPrice Between :minPrice And :maxPrice))" +
            "And(:minDiscount Is Null Or p.discountPercentage>=:minDiscount)" +
            "Order By"+
            "Case When :sort='price_low' Then p.discountedPrice End ASC,"+
            "Case When :sort='price_high' Then p.discountedPrice End DESC")
    public List<Product> filterProducts(@Param("category") String category,
                                        @Param("minPrice") Integer minPrice,
                                        @Param("maxPrice") Integer maxPrice,
                                        @Param("minDiscount") Integer minDiscount,
                                        @Param("sort") String sort);

}
