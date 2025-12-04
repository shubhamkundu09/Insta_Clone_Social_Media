package com.insta.service;

import com.insta.modal.Post;

import java.util.List;


public interface PostService {

    Post createPost(Post post, Integer userId);

    String deletePost(Integer postId, Integer userId);

    List<Post> findPostsByUserId(Integer userId);

    Post findPostById(Integer postId);

    List<Post> findAllPost();

    Post savedPost(Integer postId, Integer userId);

    Post likePost(Integer postId, Integer userId);


}
