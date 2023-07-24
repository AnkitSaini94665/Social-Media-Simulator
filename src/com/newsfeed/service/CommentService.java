package com.newsfeed.service;

import com.newsfeed.exception.InvalidIdentifierException;
import com.newsfeed.model.Comment;
import com.newsfeed.model.Post;
import com.newsfeed.repository.CommentRepository;

import java.util.List;

public class CommentService {

    private CommentRepository commentRepository;
    private PostService postService;

    public CommentService(CommentRepository commentRepository, PostService postService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
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
        List<String> replies = comment.getReplies();
        replies.add(reply);
        comment.setReplies(replies);
    }

    public void addNewComment(String ID, String postData) throws InvalidIdentifierException {
        Comment comment = new Comment(postData);
        comment = commentRepository.createcomment(comment);
        Post post = postService.getPost(Long.parseLong(ID));
        List<Comment> comments = post.getComments();
        comments.add(comment);
        post.setComments(comments);
    }

}
