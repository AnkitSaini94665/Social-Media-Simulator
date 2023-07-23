package com.newsfeed.registry;

import com.newsfeed.controller.UserController;
import com.newsfeed.repository.CommentRepository;
import com.newsfeed.repository.UserRepository;
import com.newsfeed.service.CommentService;
import com.newsfeed.service.UserService;
import com.newsfeed.util.LoginHelper;
import com.newsfeed.util.NewsFeedHandler;
import com.newsfeed.util.SignUpHelper;

import java.util.HashMap;
import java.util.Map;

public class ObjectRegistry {
    private Map<ResourceRegistry, Object> objectsMap = new HashMap<>();

    public void register(ResourceRegistry name, Object object) {
        objectsMap.put(name, object);
    }

    public Object get(ResourceRegistry name) {
        return objectsMap.get(name);
    }

    public void registerObjects() {
        CommentRepository commentRepository = new CommentRepository();
        CommentService commentService = new CommentService(commentRepository);
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        UserController userController = new UserController(userService);
        LoginHelper loginHelper = new LoginHelper();
        SignUpHelper signUpHelper = new SignUpHelper();
        NewsFeedHandler newsFeedHandler = new NewsFeedHandler();
        this.register(ResourceRegistry.COMMENTSERVICE, commentService);
        this.register(ResourceRegistry.USERSERVICE, userService);
        this.register(ResourceRegistry.USERCONTROLLER, userController);
        this.register(ResourceRegistry.LOGINHELPER, loginHelper);
        this.register(ResourceRegistry.SIGNUPHELPER, signUpHelper);
        this.register(ResourceRegistry.NEWSFEEDHELPER, newsFeedHandler);
    }
}
