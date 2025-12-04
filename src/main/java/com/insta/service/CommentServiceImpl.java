package com.insta.service;

import com.insta.modal.Comment;
import com.insta.modal.Post;
import com.insta.modal.User;
import com.insta.repo.CommentRepo;
import com.insta.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;


    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) {

        Post post = postService.findPostById(postId);
        if (post==null){
            throw new RuntimeException("Post Not Found");
        }
        User user = userService.findUserById(userId);
        if (user==null){
            throw new RuntimeException("User Not Found");
        }
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUser(user);
        comment.setContent(comment.getContent());
        Comment savedComment = commentRepo.save(comment);

        post.getComment().add(savedComment);

        postRepo.save(post);


        return savedComment;
    }

    @Override
    public Comment findCommentById(Integer commentId) {
        Optional<Comment> comment = commentRepo.findById(commentId);
        if (comment.isEmpty()){
            throw new BadCredentialsException("Comment Not Found");
        }
        return comment.get();
    }

    @Override
    public Comment likeComment(Integer commentId, Integer userId) {
        Comment comment = findCommentById(commentId);
        User user = userService.findUserById(userId);

        if (comment.getLiked().contains(user)){
            comment.getLiked().remove(user);
        }else {
            comment.getLiked().add(user);
        }
        return commentRepo.save(comment);

    }
}
