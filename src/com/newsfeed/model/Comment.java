package com.newsfeed.model;

import java.util.ArrayList;
import java.util.List;

public class Comment extends UserAction {
    private List<String> replies;

    public Comment(String data) {
        super(data);
        this.replies = new ArrayList<>();
    }

    public List<String> getReplies() {
        return replies;
    }

    public void setReplies(List<String> replies) {
        this.replies = replies;
    }
}
