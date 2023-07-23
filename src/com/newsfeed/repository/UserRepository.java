package com.newsfeed.repository;

import com.newsfeed.exception.InvalidIdentifierException;
import com.newsfeed.model.User;
import com.newsfeed.util.CommonConstants;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    //When connected to database, this class will be used to set up database connection and retrieve the
    //data from database. For now, we are storing data in a map to be used in memory.

    private static Map<String,User> userMap = new HashMap<>();
    private static long counter = 0L;

    public User getUser(String username) throws InvalidIdentifierException {
        if(userMap.containsKey(username)){
            return userMap.get(username);
        }else{
            throw new InvalidIdentifierException(CommonConstants.INVALID_USERNAME_MESSAGE);
        }
    }

    public boolean userExists(String username){
        return userMap.containsKey(username);
    }

    public User createUser(User user){
        counter++;
        user.setID(counter);
        userMap.put(user.getUsername(), user);
        return user;
    }
}
