package com.insta.controller;

import com.insta.modal.Comment;
import com.insta.modal.User;
import com.insta.service.CommentServiceImpl;
import com.insta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private UserService userService;




    @PostMapping("/add-comment/{postId}")
    public Comment addComment(@RequestBody Comment comment, @PathVariable("postId") Integer postId,
                              @RequestHeader("Authorization") String jwt){

        User user = userService.getUserByJwtToken(jwt);
        return commentService.createComment(comment,postId, user.getId());
    }

    @GetMapping("/get-comment-by-id/{commentId}")
    public Comment getCommentById(@PathVariable("commentId") Integer commentId){
        return commentService.findCommentById(commentId);
    }


    @PutMapping("/like-comment/{commentId}")
    public Comment likeComment(@PathVariable("commentId") Integer commentId,
                               @RequestHeader("Authorization") String jwt){
        User user = userService.getUserByJwtToken(jwt);
        return commentService.likeComment(commentId, user.getId());
    }



}
