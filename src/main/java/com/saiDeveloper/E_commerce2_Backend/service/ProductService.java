package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.exception.CartItemException;
import com.saiDeveloper.E_commerce2_Backend.exception.OrderItemException;
import com.saiDeveloper.E_commerce2_Backend.exception.ProductException;
import com.saiDeveloper.E_commerce2_Backend.exception.UserException;
import com.saiDeveloper.E_commerce2_Backend.model.Product;
import com.saiDeveloper.E_commerce2_Backend.request.createProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

   Product createProduct(createProductRequest req) throws ProductException;

  String deleteProduct(Long id) throws ProductException, OrderItemException, CartItemException, UserException;

Product updateProduct(Long id, Product product) throws ProductException;

Product findProductById(Long id) throws ProductException;

Page<Product> getAllProduct(String category, List<String> colors,List<String> sizes, Integer minPrice,
    Integer maxPrice, Integer minDiscount, String sort, String stock, int pageNumber, int pageSize) throws ProductException;

}
