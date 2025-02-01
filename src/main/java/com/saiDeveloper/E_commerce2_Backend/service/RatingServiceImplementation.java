package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.exception.ProductException;
import com.saiDeveloper.E_commerce2_Backend.model.Product;
import com.saiDeveloper.E_commerce2_Backend.model.Rating;
import com.saiDeveloper.E_commerce2_Backend.model.User;
import com.saiDeveloper.E_commerce2_Backend.repo.RatingRepo;
import com.saiDeveloper.E_commerce2_Backend.request.RatingRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class RatingServiceImplementation implements RatingService {

    @Autowired
    private RatingRepo repo;
    @Autowired
    private ProductService productService;

    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {

        Product product = productService.findProductById(req.getProductId());

        Rating rating = new Rating();

        rating.setRating(req.getRating());
        rating.setProduct(product);
        rating.setUser(user);
        rating.setCreatedAt(LocalDateTime.now());

        repo.save(rating);
    log.info("Rating with id:{} created successfully",rating.getId());
        return rating;
    }

    @Override
    public List<Rating> getAllRatings(Long productId) throws ProductException {
        log.info("Getting all ratings for product with id:{}",productId);
        productService.findProductById(productId);//checks whether product exists or not
        return repo.getAllRatings(productId);
    }
}















