package com.saiDeveloper.E_commerce2_Backend.repo;

import com.saiDeveloper.E_commerce2_Backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

    Optional<Category> findByName(String name);


    @Query("SELECT c FROM Category c WHERE c.name = :name AND c.parentCategory.name = :parentName")
    Optional<Category> findByNameAndParentName(@Param("name") String name, @Param("parentName") String parentName);
}
