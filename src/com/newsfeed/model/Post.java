package com.newsfeed.model;

import java.util.ArrayList;
import java.util.List;

public class Post extends UserAction{

    private List<Comment> comments;
    public Post(String data) {
        super(data);
        this.comments = new ArrayList<>();
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
