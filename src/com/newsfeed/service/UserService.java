package com.newsfeed.service;

import com.newsfeed.dto.CreateUserRequestDTO;
import com.newsfeed.exception.InvalidIdentifierException;
import com.newsfeed.model.User;
import com.newsfeed.repository.UserRepository;
import com.newsfeed.util.NewsFeedGenerator;

import java.util.List;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDTO requestDTO) {
        User user = new User();
        user.setFirstName(requestDTO.getFirstName());
        user.setLastName(requestDTO.getLastName());
        user.setUsername(requestDTO.getUsername());
        user.setPassword(encrypt(requestDTO.getPassword()));
        user = userRepository.createUser(user);
        return user;
    }

    private String encrypt(String password) {
        //We can add logic here to encrypt the password
        return password.concat("##");
    }

    public boolean userExists(String username) {
        return userRepository.userExists(username);
    }

    public boolean checkPassword(String username, String password) {
        try {
            User user = userRepository.getUser(username);
            if (user.getPassword().equals(encrypt(password))) {
                return true;
            }
        } catch (InvalidIdentifierException e) {
            return false;
        }
        return false;
    }

    public void generateNewsFeed(User user) {
        NewsFeedGenerator newsFeedGenerator = new NewsFeedGenerator();
        newsFeedGenerator.generateNewsFeedForUser(user);
    }

    public void followUser(User self, String usernameOther) throws InvalidIdentifierException {
        User other = userRepository.getUser(usernameOther);
        if (other != null) {
            List<User> following = self.getFollowing();
            following.add(other);
            self.setFollowing(following);
        }
    }

    public User getUser(String username) throws InvalidIdentifierException {
        return userRepository.getUser(username);
    }
}
