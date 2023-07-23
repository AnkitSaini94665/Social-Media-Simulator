package com.newsfeed.service;

import com.newsfeed.exception.InvalidIdentifierException;
import com.newsfeed.model.Comment;
import com.newsfeed.model.User;
import com.newsfeed.repository.CommentRepository;

import java.util.List;

public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void upvote(String ID) throws InvalidIdentifierException {
        Comment comment = commentRepository.getcomment(Long.parseLong(ID));
        comment.setUpvotes(comment.getUpvotes() + 1);
    }

    public void downvote(String ID) throws InvalidIdentifierException {
        Comment comment = commentRepository.getcomment(Long.parseLong(ID));
        comment.setDownvotes(comment.getDownvotes() + 1);
    }


    public void reply(String ID, String reply) throws InvalidIdentifierException {
        Comment comment = commentRepository.getcomment(Long.parseLong(ID));
        Comment newComment = new Comment(reply);
        newComment = commentRepository.createcomment(newComment);
        List<Comment> comments = comment.getComments();
        comments.add(newComment);
        comment.setComments(comments);
    }

    public void addNewPost(User user, String postData) {
        Comment post = new Comment(postData);
        post = commentRepository.createcomment(post);
        List<Comment> posts = user.getPosts();
        posts.add(post);
        user.setPosts(posts);
    }

}
