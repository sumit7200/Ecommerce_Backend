package com.ecommerce.repo;

import com.ecommerce.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.product.id = :productId")
    public List<Review> getAllProductReview(@Param("productId") Long productId);

//    @Query("SELECT r FROM Review r WHERE r.product.id = :productId")
//    public List<Review> getAllProductReviews(@Param("productId") Long productId);

}
