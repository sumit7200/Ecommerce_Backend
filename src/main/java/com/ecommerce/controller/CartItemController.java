package com.ecommerce.controller;

import com.ecommerce.exception.CartItemException;
import com.ecommerce.exception.UserException;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.User;
import com.ecommerce.response.ApiResponse;
import com.ecommerce.service.CartItemService;
import com.ecommerce.service.CartService;
import com.ecommerce.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@CrossOrigin
@RequestMapping("/api/cart_items")
public class CartItemController {


    @Autowired
    CartItemService cartItemService;

    @Autowired
    UserService userService;


    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItemHandler(@PathVariable Long cartItemId, @RequestHeader("Authorization") String jwt)
        throws CartItemException, UserException{

        User user = userService.findUserProfileByJwt(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);
        ApiResponse response = new ApiResponse("Item Remove From Cart",true);
        return new ResponseEntity<ApiResponse>(response,HttpStatus.ACCEPTED);
    }



    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItemHandler(@PathVariable Long cartItemId, @RequestBody CartItem cartItem, @RequestHeader
            ("Authorization")String jwt) throws CartItemException, UserException{

        User user = userService.findUserProfileByJwt(jwt);
        CartItem updateCartItem = cartItemService.updateCartItem(user.getId(),cartItemId, cartItem);

        return new ResponseEntity<>(updateCartItem, HttpStatus.ACCEPTED);
    }


}
