package com.ecommerce.service;

import com.ecommerce.exception.UserException;
import com.ecommerce.model.User;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findUserById(Long userId) throws UserException;

    User findUserProfileByJwt(String jwt) throws UserException;
}
