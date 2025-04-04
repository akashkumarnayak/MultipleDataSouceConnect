package org.example.springpractice.services;

import org.example.springpractice.models.user.User;
import org.example.springpractice.repositories.product.ProductRepo;
import org.example.springpractice.repositories.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserRepo userRepo;
    private final ProductRepo productRepo;

    @Autowired
    public UserService(UserRepo userRepo, ProductRepo productRepo) {

        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }


    public User createUser(User user) {
        userRepo.save(user);
        return user;
    }
}
