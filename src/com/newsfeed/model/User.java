package com.newsfeed.model;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseModel {
    private String username;
    private String password; //Should be stored as encrypted password or char[]
    private String firstName;
    private String lastName;
    private List<Comment> posts;
    private List<User> following;

    public User(){
        this.posts = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public List<Comment> getPosts() {
        return posts;
    }

    public void setPosts(List<Comment> posts) {
        this.posts = posts;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
