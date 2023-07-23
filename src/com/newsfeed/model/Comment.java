package com.newsfeed.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Creating common class to reuse properties for comments and posts
public class Comment extends BaseModel {
    private int upvotes;
    private int downvotes;
    private String data;
    private Date createTime;
    private List<Comment> comments;

    public Comment(String data) {
        this.data = data;
        this.createTime = new Date();
        this.comments = new ArrayList<>();
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public String getData() {
        return data;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
