package com.newsfeed.controller;

import com.newsfeed.dto.CreateUserRequestDTO;
import com.newsfeed.dto.CreateUserResponseDTO;
import com.newsfeed.model.User;
import com.newsfeed.service.UserService;

public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public CreateUserResponseDTO createUser(CreateUserRequestDTO requestDTO) {
        CreateUserResponseDTO responseDTO = new CreateUserResponseDTO();
        User user = userService.createUser(requestDTO);
        if (user != null) {
            responseDTO.setSuccess(true);
        }
        return responseDTO;
    }

    public boolean checkUsername(String username) {
        return userService.userExists(username);
    }


}
