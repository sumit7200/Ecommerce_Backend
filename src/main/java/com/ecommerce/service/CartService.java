package com.ecommerce.service;

import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Cart;
import com.ecommerce.model.User;
import com.ecommerce.request.AddItemRequest;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    Cart createCart(User user);

    Cart findUserCart(Long userID);

    String addCartItem(Long userId, AddItemRequest request) throws ProductException;
}
