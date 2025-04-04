package org.example.springpractice.services;

import org.example.springpractice.models.user.User;
import org.example.springpractice.repositories.product.ProductRepo;
import org.example.springpractice.repositories.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {

        this.userRepo = userRepo;

    }


    public User createUser(User user) {
        userRepo.save(user);
        return user;
    }
}
