package com.newsfeed.util;

import com.newsfeed.registry.ObjectRegistry;
import com.newsfeed.registry.ResourceRegistry;
import com.newsfeed.model.PostCommand;
import com.newsfeed.model.User;
import com.newsfeed.model.UserCommand;
import com.newsfeed.service.CommentService;
import com.newsfeed.service.UserService;

import java.util.Scanner;

public class NewsFeedHandler {

    public void handleNewsFeed(Scanner scanner, ObjectRegistry objectRegistry, User user) {
        boolean response = true;
        UserService userService = (UserService) objectRegistry.get(ResourceRegistry.USERSERVICE);
        CommentService commentService = (CommentService) objectRegistry.get(ResourceRegistry.COMMENTSERVICE);
        while (response) {
            try {
                System.out.println(CommonConstants.NEWS_FEED_START_MESSAGE);
                String command = scanner.nextLine();
                if (command.equalsIgnoreCase(UserCommand.SHOWNEWSFEED.name())) {
                    userService.generateNewsFeed(user);
                } else if (command.equalsIgnoreCase(UserCommand.POST.name())) {
                    System.out.println("Enter your post");
                    String post = scanner.nextLine();
                    commentService.addNewPost(user, post);
                } else if (command.equalsIgnoreCase(UserCommand.FOLLOW.name())) {
                    System.out.println("Enter username of the user you wish to follow");
                    String username = scanner.nextLine();
                    userService.followUser(user, username);
                } else if (command.equalsIgnoreCase(PostCommand.UPVOTE.name())) {
                    System.out.println("Enter id of the post/comment you wish to upvote");
                    String ID = scanner.nextLine();
                    commentService.upvote(ID);
                } else if (command.equalsIgnoreCase(PostCommand.DOWNVOTE.name())) {
                    System.out.println("Enter id of the post/comment you wish to downvote");
                    String ID = scanner.nextLine();
                    commentService.downvote(ID);
                } else if (command.equalsIgnoreCase(PostCommand.REPLY.name())) {
                    System.out.println("Enter id of the post/comment you wish to add a reply on");
                    String ID = scanner.nextLine();
                    System.out.println("Enter your reply");
                    String reply = scanner.nextLine();
                    commentService.reply(ID, reply);
                } else if (command.equalsIgnoreCase(UserCommand.LOGOUT.name())) {
                    response = false;
                } else {
                    System.out.println(CommonConstants.INVALID_COMMAND);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
