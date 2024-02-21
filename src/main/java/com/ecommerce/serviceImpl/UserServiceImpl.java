package com.ecommerce.serviceImpl;

import com.ecommerce.config.JwtProvider;
import com.ecommerce.exception.UserException;
import com.ecommerce.model.User;
import com.ecommerce.repo.UserRepository;
import com.ecommerce.service.UserService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserException("user not found with id" + userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        System.out.println("user service");
        String email = jwtProvider.getEmailFromToken(jwt);
        System.out.println("email" + email);
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserException("user not exist with email" + email);
        }
        System.out.println("email user" + user.getEmail());
        return user;
    }
}
