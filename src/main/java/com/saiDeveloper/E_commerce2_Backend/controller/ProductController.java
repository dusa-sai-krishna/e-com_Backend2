package com.saiDeveloper.E_commerce2_Backend.controller;

import com.saiDeveloper.E_commerce2_Backend.exception.ProductException;
import com.saiDeveloper.E_commerce2_Backend.model.Product;
import com.saiDeveloper.E_commerce2_Backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


//*
// This method, `findProductByCategoryHandler`, is a Spring Boot controller handler method. It handles HTTP requests to fetch a paginated list of products based on various filter and sorting criteria. Here's a detailed explanation:
//
//### **Key Features and Behavior**
//
//1. **Input Parameters**:
//   - **`String category`**: Filters products by category (e.g., "electronics").
//   - **`List<String> color`**: Filters products by a list of colors.
//   - **`List<String> size`**: Filters products by a list of sizes.
//   - **`Integer minPrice` and `Integer maxPrice`**: Defines a price range for filtering.
//   - **`Integer minDiscount`**: Filters products by a minimum discount percentage.
//   - **`String sort`**: Specifies the sorting criterion (e.g., "price_low", "price_high").
//   - **`String stock`**: Filters products based on stock availability (e.g., "in_stock").
//   - **`Integer pageNumber` and `Integer pageSize`**: Determines pagination, specifying the page to retrieve and the number of items per page.
//
//2. **Service Layer Call**:
//   - The method calls `productService.getAllProduct` with the provided filtering, sorting, and pagination parameters.
//   - The service layer is responsible for constructing and executing the query (likely involving JPA or Hibernate).
//
//3. **ResponseEntity and HTTP Response**:
//   - **`Page<Product>`**: The service returns a paginated result containing a subset of products that match the given criteria. This includes:
//     - The product data for the current page.
//     - Metadata about pagination (e.g., total pages, current page, total items).
//   - **`ResponseEntity`**: The method wraps the result in a `ResponseEntity` object, which provides HTTP status control.
//     - **`HttpStatus.ACCEPTED` (202)**: Indicates that the request has been accepted and processed successfully, but it’s commonly used when further processing may occur.
//
//4. **Logging**:
//   - **`System.out.println("Complete Products")`**: Logs a message for debugging, indicating that the process has completed. This is not recommended in production; use a proper logging framework like SLF4J.
//
//5. **Exception Handling**:
//   - **`ProductException`**: Declared in the method signature, this custom exception is likely thrown if something goes wrong during the query execution (e.g., invalid parameters, database issues).
//
//### **Example Usage**
//Suppose the method is mapped to `/products` in the controller. A client sends a request:
//
//**Request**:
//```http
//GET /products?category=electronics&color=red,blue&size=L&minPrice=100&maxPrice=500&sort=price_low&stock=in_stock&pageNumber=1&pageSize=10
//```
//
//**Processing**:
//- Filters for products in the "electronics" category, with colors red or blue, size L, priced between 100 and 500, with stock available, sorted by price (low to high), and retrieves the first page of 10 items.
//
//**Response**:
//- The method returns an HTTP 202 response with the paginated list of matching products in the body.
//
//### **Key Notes**:
//- The use of `ResponseEntity` allows flexibility in controlling HTTP status and headers.
//- Using pagination helps optimize performance and usability for large datasets.
//- Proper input validation and exception handling are crucial for production-ready implementations.
//
//
// *//
    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findProductByCategoryHandler(
            String category,
            List<String> color,
            List<String> size,
            Integer minPrice,
            Integer maxPrice,
            Integer minDiscount,
            String sort,
            String stock,
            Integer pageNumber,
            Integer pageSize
    ) throws ProductException {
        Page<Product> res = productService.getAllProduct(
                category, color, size, minPrice, maxPrice,
                minDiscount, sort, stock, pageNumber, pageSize);
        System.out.println("Complete Products");
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @GetMapping("/products/{productId}")
    public  ResponseEntity<Product> findProductByIdHandler(@PathVariable("productId") Long productId) throws ProductException {
        Product res = productService.findProductById(productId);
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }






}
















