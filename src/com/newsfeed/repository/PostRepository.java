package com.newsfeed.repository;

import com.newsfeed.exception.InvalidIdentifierException;
import com.newsfeed.model.Post;
import com.newsfeed.util.CommonConstants;

import java.util.HashMap;
import java.util.Map;

public class PostRepository {
    private Map<Long, Post> postMap = new HashMap<>();
    private static long counter = 0L;

    public Post getPost(Long ID) throws InvalidIdentifierException {
        if (postMap.containsKey(ID)) {
            return postMap.get(ID);
        } else {
            throw new InvalidIdentifierException(CommonConstants.INVALID_ID_MESSAGE);
        }
    }

    public Post createPost(Post post) {
        counter++;
        post.setID(counter);
        postMap.put(counter, post);
        return post;
    }
}
