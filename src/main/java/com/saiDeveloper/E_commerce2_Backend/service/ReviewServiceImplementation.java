package com.saiDeveloper.E_commerce2_Backend.service;

import com.saiDeveloper.E_commerce2_Backend.exception.ProductException;
import com.saiDeveloper.E_commerce2_Backend.model.Product;
import com.saiDeveloper.E_commerce2_Backend.model.Review;
import com.saiDeveloper.E_commerce2_Backend.model.User;
import com.saiDeveloper.E_commerce2_Backend.repo.ReviewRepo;
import com.saiDeveloper.E_commerce2_Backend.request.ReviewRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ReviewServiceImplementation implements ReviewService {

    @Autowired
    private ReviewRepo repo;
    @Autowired
    private ProductService productService;


    @Override
    public Review createReview(ReviewRequest req, User user) throws ProductException {

        Product product = productService.findProductById(req.getProductId());
        Review review = new Review();

        review.setReview(req.getReview());
        review.setProduct(product);
        review.setUser(user);
        review.setCreatedAt(LocalDateTime.now());

        repo.save(review);
        log.info("Review created successfully with id:{}",review.getId());
        return review;
    }

    @Override
    public List<Review> getAllReviews(Long productId) {
        log.info("Getting all reviews for product with id:{}",productId);
        return repo.getAllReviews(productId);
    }
}
