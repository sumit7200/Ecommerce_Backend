package com.ecommerce.service;

import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Review;
import com.ecommerce.model.User;
import com.ecommerce.request.ReviewRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {

    Review createReview(ReviewRequest reviewRequest, User user) throws ProductException;

    List<Review> getAllReview(Long productId);
}
