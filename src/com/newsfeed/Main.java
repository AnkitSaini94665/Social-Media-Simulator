package com.newsfeed;

import com.newsfeed.exception.InvalidPasswordException;
import com.newsfeed.exception.InvalidIdentifierException;
import com.newsfeed.exception.SignUpFailedException;
import com.newsfeed.model.User;
import com.newsfeed.model.UserCommand;
import com.newsfeed.registry.ObjectRegistry;
import com.newsfeed.registry.ResourceRegistry;
import com.newsfeed.util.CommonConstants;
import com.newsfeed.util.LoginHelper;
import com.newsfeed.util.NewsFeedHandler;
import com.newsfeed.util.SignUpHelper;

import java.util.Scanner;

public class Main {
    private static ObjectRegistry objectRegistry;

    public static void main(String[] args) {
        objectRegistry = new ObjectRegistry();
        objectRegistry.registerObjects();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(CommonConstants.LOGIN_COMMAND);
                String command = scanner.nextLine();
                if (command.equalsIgnoreCase(UserCommand.EXIT.name())) {
                    break;
                }
                if (command.equalsIgnoreCase(UserCommand.SIGNUP.name())) {
                    SignUpHelper signUpHelper = (SignUpHelper)objectRegistry.get(ResourceRegistry.SIGNUPHELPER);
                    signUpHelper.signup(scanner, objectRegistry);
                } else if (command.equalsIgnoreCase(UserCommand.LOGIN.name())) {
                    LoginHelper loginHelper = (LoginHelper)objectRegistry.get(ResourceRegistry.LOGINHELPER);
                    User user = loginHelper.login(scanner, objectRegistry);
                    NewsFeedHandler newsFeedHandler = (NewsFeedHandler)objectRegistry.get(ResourceRegistry.NEWSFEEDHELPER);
                    newsFeedHandler.handleNewsFeed(scanner,objectRegistry,user);
                } else {
                    System.out.println(CommonConstants.INVALID_COMMAND);
                }
            } catch (SignUpFailedException | InvalidIdentifierException | InvalidPasswordException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
        System.out.println("Thank You!!!");
    }

}
