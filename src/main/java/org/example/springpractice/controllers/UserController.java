package org.example.springpractice.controllers;

import org.example.springpractice.dtos.UserRequestDto;
import org.example.springpractice.dtos.UserResponseDto;
import org.example.springpractice.models.user.User;
import org.example.springpractice.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/create")
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto)
    {
        User createdUser = userService.createUser(from(userRequestDto));
        return from(createdUser);
    }

    UserResponseDto from(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setId(user.getId());
        userResponseDto.setStatus(user.getStatus());
        return userResponseDto;
    }

    User from(UserRequestDto userRequestDto)
    {
        User user = new User();
        user.setId(userRequestDto.getId());
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        return user;
    }
}
