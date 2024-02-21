package com.ecommerce.serviceImpl;

import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Cart;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.repo.CartRepository;
import com.ecommerce.request.AddItemRequest;
import com.ecommerce.service.CartItemService;
import com.ecommerce.service.CartService;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    ProductService productService;

    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.saveAndFlush(cart);
    }


    @Override
    public Cart findUserCart(Long userID){
        Cart cart = cartRepository.findByUserId(userID);
        int totalPrice = 0;
        int totalDiscountPrice = 0;
        int totalItem=0;
        for (CartItem cartItem : cart.getCartItems()){
            totalPrice+= cartItem.getPrice();
            totalDiscountPrice+=cartItem.getDiscountedPrice();
            totalItem+=cartItem.getQuantity();
        }

        cart.setTotalPrice(totalPrice);
        cart.setTotalItem(cart.getCartItems().size());
        cart.setTotalDiscountPrice(totalDiscountPrice);
        cart.setDiscount(totalPrice-totalDiscountPrice);
        cart.setTotalItem(totalItem);

        return cartRepository.saveAndFlush(cart);
    }



    @Override
    public String addCartItem(Long userId, AddItemRequest request) throws ProductException {
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.findProductById(request.getProductId());
        CartItem isPresent = cartItemService.isCartItemExist(cart, product, request.getSize(), userId);
        if (isPresent == null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setUserId(userId);

            int price = request.getQuantity() * product.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setSize(request.getSize());
            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
        }
        return "item add to cart";
    }

}
