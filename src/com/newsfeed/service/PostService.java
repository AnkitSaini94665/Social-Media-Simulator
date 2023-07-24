package com.newsfeed.service;

import com.newsfeed.exception.InvalidIdentifierException;
import com.newsfeed.model.Post;
import com.newsfeed.model.User;
import com.newsfeed.repository.PostRepository;

import java.util.List;

public class PostService {
    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public Post getPost(Long ID) throws InvalidIdentifierException {
        return postRepository.getPost(ID);
    }
    public void addNewPost(User user, String postData){
        Post post = new Post(postData);
        post = postRepository.createPost(post);
        List<Post> posts = user.getPosts();
        posts.add(post);
        user.setPosts(posts);
    }

    public void upvote(String ID) throws InvalidIdentifierException {
        Post post = postRepository.getPost(Long.parseLong(ID));
        post.setUpvotes(post.getUpvotes()+1);
    }

    public void downvote(String ID) throws InvalidIdentifierException {
        Post post = postRepository.getPost(Long.parseLong(ID));
        post.setUpvotes(post.getUpvotes()+1);
    }
}
