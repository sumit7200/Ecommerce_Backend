package com.ecommerce.service;

import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Rating;
import com.ecommerce.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {

    Rating createRating(Rating rating, User user) throws ProductException;

    List<Rating> getProductRating(Long productId);
}
