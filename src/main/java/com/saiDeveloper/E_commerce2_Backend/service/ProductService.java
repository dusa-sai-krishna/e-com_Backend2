package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.exception.ProductException;
import com.saiDeveloper.E_commerce2_Backend.model.Product;
import com.saiDeveloper.E_commerce2_Backend.request.createProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

   Product createProduct(createProductRequest req) throws ProductException;

  String deleteProduct(Long id) throws ProductException;

Product updateProduct(Long id, Product product) throws ProductException;

Product findProductById(Long id) throws ProductException;

Page<Product> getAllProduct(String category, List<String> colors,List<String> sizes, int minPrice,
    int maxPrice, int minDiscount, String sort, String stock, int pageNumber, int pageSize) throws ProductException;

}
