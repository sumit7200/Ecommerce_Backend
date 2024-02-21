package com.ecommerce.serviceImpl;

import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Product;
import com.ecommerce.model.Rating;
import com.ecommerce.model.User;
import com.ecommerce.repo.RatingRepository;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    ProductService productService;

    @Override
    public Rating createRating(Rating rating, User user) throws ProductException {
        Product product = productService.findProductById(rating.getProduct().getId());
        Rating rating1 = new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(rating.getRating());
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.saveAndFlush(rating);

    }

    @Override
    public List<Rating> getProductRating(Long productId) {
        return ratingRepository.getAllProductRating(productId);
    }

}
