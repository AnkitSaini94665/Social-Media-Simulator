package com.newsfeed.util;

import com.newsfeed.model.Comment;
import com.newsfeed.model.Post;
import com.newsfeed.model.User;

import java.util.*;

public class NewsFeedGenerator {

    public void generateNewsFeedForUser(User currentUser) {
        List<User> following = currentUser.getFollowing();
        List<Post> allPosts = new ArrayList<>(currentUser.getPosts());
        for (User user : following) {
            allPosts.addAll(user.getPosts());
        }
        allPosts = getSortedList(allPosts);
        for (Post post : allPosts) {
            System.out.println(post.getData());
            System.out.printf("ID = %d, Upvotes = %d, Downvotes = %d, posted %s ago\n", post.getID(), post.getUpvotes(), post.getDownvotes(), getTime(post.getCreateTime()));
            System.out.println("Comments on post - ");
            for (Comment comment : post.getComments()) {
                System.out.println(comment.getData());
                System.out.printf("ID = %d, Upvotes = %d, Downvotes = %d, posted %s ago\n", comment.getID(), comment.getUpvotes(), comment.getDownvotes(), getTime(post.getCreateTime()));
                System.out.println("Replies to this comment - ");
                for (String reply : comment.getReplies()) {
                    System.out.println(reply);
                }
            }
        }
    }

    private String getTime(Date createTime) {
        Date current = new Date();
        long ms = current.getTime() - createTime.getTime();
        long mins = ms / 60000;
        if (mins > 59) {
            return (mins / 60) + " h";
        }
        return mins + " m";
    }

    private List<Post> getSortedList(List<Post> allPosts) {
        Comparator<Post> comparator = (a, b) -> {
            int votingScore = (b.getUpvotes() - b.getDownvotes()) - (a.getUpvotes() - a.getDownvotes());
            if (votingScore != 0) {
                return votingScore;
            }
            int commentScore = b.getComments().size() - a.getComments().size();
            if (commentScore != 0) {
                return commentScore;
            }
            return b.getCreateTime().compareTo(a.getCreateTime());
        };
        Collections.sort(allPosts, comparator);
        return allPosts;
    }
}
