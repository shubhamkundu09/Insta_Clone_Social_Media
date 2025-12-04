package com.insta.service;

import com.insta.modal.Comment;

public interface CommentService {

    public Comment createComment(Comment comment, Integer postId, Integer userId);

    public Comment findCommentById(Integer commentId);

    public Comment likeComment(Integer commentId, Integer userId);
}
