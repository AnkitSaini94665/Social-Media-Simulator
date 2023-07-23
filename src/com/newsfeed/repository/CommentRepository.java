package com.newsfeed.repository;

import com.newsfeed.exception.InvalidIdentifierException;
import com.newsfeed.model.Comment;
import com.newsfeed.util.CommonConstants;

import java.util.HashMap;
import java.util.Map;

public class CommentRepository {
    //To store the posts and comments

    private Map<Long, Comment> commentMap = new HashMap<>();
    private static long counter = 0L;

    public Comment getcomment(Long ID) throws InvalidIdentifierException {
        if(commentMap.containsKey(ID)){
            return commentMap.get(ID);
        }else{
            throw new InvalidIdentifierException(CommonConstants.INVALID_ID_MESSAGE);
        }
    }

    public Comment createcomment(Comment comment){
        counter++;
        comment.setID(counter);
        commentMap.put(counter, comment);
        return comment;
    }
}
