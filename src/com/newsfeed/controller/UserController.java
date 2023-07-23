package com.newsfeed.controller;

import com.newsfeed.dto.CreateUserRequestDTO;
import com.newsfeed.model.User;
import com.newsfeed.service.UserService;

public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User createUser(CreateUserRequestDTO requestDTO) {
        return userService.createUser(requestDTO);
    }

    public boolean checkUsername(String username) {
        return userService.userExists(username);
    }


}
