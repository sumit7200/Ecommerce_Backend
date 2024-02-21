package com.ecommerce.service;


import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Product;
import com.ecommerce.request.CreateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    //only for Admin
    Product CreateProduct(CreateProductRequest request);

    String deleteProduct(Long productId) throws ProductException;

    Product updateProduct(Long productId, Product product) throws ProductException;

    List<Product> getAllProducts();

    //for user and admin both
    Product findProductById(Long id) throws ProductException;

    public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice,
                                       Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);

    List<Product> findProductByCategory(String category);

    List<Product> searchProduct(String query);

}
