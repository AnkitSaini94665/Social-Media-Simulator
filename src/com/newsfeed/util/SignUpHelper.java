package com.newsfeed.util;

import com.newsfeed.registry.ObjectRegistry;
import com.newsfeed.registry.ResourceRegistry;
import com.newsfeed.controller.UserController;
import com.newsfeed.dto.CreateUserRequestDTO;
import com.newsfeed.exception.SignUpFailedException;
import com.newsfeed.exception.UserAlreadyExistsException;
import com.newsfeed.model.User;
import com.newsfeed.model.UserCommand;

import java.util.Scanner;

public class SignUpHelper {

    public void signup(Scanner scanner, ObjectRegistry objectRegistry) throws SignUpFailedException {
        boolean response = false;
        String username = "";
        while (!response) {
            System.out.println(CommonConstants.SIGNUP_COMMAND);
            username = scanner.nextLine();
            if (username.equalsIgnoreCase(UserCommand.EXIT.name())) {
                return;
            }
            try {
                UserController userController = (UserController) objectRegistry.get(ResourceRegistry.USERCONTROLLER);
                if (userController.checkUsername(username)) {
                    throw new UserAlreadyExistsException(CommonConstants.USER_ALREADY_EXISTS_MESSAGE);
                } else {
                    response = true;
                }
            } catch (UserAlreadyExistsException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Enter First Name");
        String firstName = scanner.nextLine();
        System.out.println("Enter Last Name");
        String lastName = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();

        UserController userController = (UserController) objectRegistry.get(ResourceRegistry.USERCONTROLLER);
        CreateUserRequestDTO requestDTO = new CreateUserRequestDTO(firstName, lastName, username, password);
        User user = userController.createUser(requestDTO);
        if (user != null) {
            System.out.println(CommonConstants.SIGNUP_SUCCESSFUL_MESSAGE);
        } else {
            throw new SignUpFailedException(CommonConstants.SIGNUP_FAILED_MESSAGE);
        }
    }
}
