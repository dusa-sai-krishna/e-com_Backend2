package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.exception.ProductException;
import com.saiDeveloper.E_commerce2_Backend.model.Product;
import com.saiDeveloper.E_commerce2_Backend.repo.CategoryRepo;
import com.saiDeveloper.E_commerce2_Backend.repo.ProductRepo;
import com.saiDeveloper.E_commerce2_Backend.request.createProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    public Product createProduct(createProductRequest req);

    public String deleteProduct(Long id) throws ProductException;

    public Product updateProduct(Long id, Product product) throws ProductException;

    public Product findProductById(Long id) throws ProductException;

    public List<Product> findProductByCategory(String category) throws ProductException;

    public Page<Product> getAllProduct(String category, List<String> colors,List<String> sizes, int minPrice,
    int maxPrice, int minDiscount, String sort, String stock, int pageNumber, int pageSize) throws ProductException;

}
