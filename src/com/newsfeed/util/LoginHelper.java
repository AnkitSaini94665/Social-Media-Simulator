package com.newsfeed.util;

import com.newsfeed.registry.ObjectRegistry;
import com.newsfeed.registry.ResourceRegistry;
import com.newsfeed.exception.InvalidPasswordException;
import com.newsfeed.exception.InvalidIdentifierException;
import com.newsfeed.model.User;
import com.newsfeed.service.UserService;

import java.util.Scanner;

public class LoginHelper {

    public User login(Scanner scanner, ObjectRegistry objectRegistry) throws InvalidIdentifierException, InvalidPasswordException {
        UserService service = (UserService) objectRegistry.get(ResourceRegistry.USERSERVICE);
        System.out.println("Enter your username");
        String username = scanner.nextLine();
        verifyUsername(username, service);
        System.out.println("Enter your password");
        String password = scanner.nextLine();
        verifyPassword(username, password, service);
        System.out.println("Login Successful!");
        return service.getUser(username);
    }

    private void verifyPassword(String username, String password, UserService service) throws InvalidPasswordException {
        if (!service.checkPassword(username, password)) {
            throw new InvalidPasswordException(CommonConstants.INVALID_PASSWORD_MESSAGE);
        }
    }

    private void verifyUsername(String username, UserService service) throws InvalidIdentifierException {
        if (!service.userExists(username)) {
            throw new InvalidIdentifierException(CommonConstants.INVALID_USERNAME_MESSAGE);
        }
    }
}
