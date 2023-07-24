package com.newsfeed.model;

import java.util.Date;

abstract public class UserAction extends BaseModel {
    protected int upvotes;
    protected int downvotes;
    protected String data;
    protected Date createTime;

    public UserAction(String data) {
        this.data = data;
        this.createTime = new Date();
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
