package com.insta.service;

import com.insta.modal.Post;
import com.insta.modal.User;
import com.insta.repo.PostRepo;
import com.insta.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
     PostRepo postRepo;

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;


    @Override
    public Post createPost(Post post, Integer userId) {

        User user = userService.findUserById(userId);

        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setImage(post.getImage());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);

        postRepo.save(newPost);
        return newPost;
    }

    @Override
    public String deletePost(Integer postId, Integer userId) {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (post.getUser().getId()!=user.getId()){
            throw new RuntimeException("you can not delete this post......");
        }

        postRepo.deleteById(postId);

        return "Post deleted Successfully......";
    }

    @Override
    public List<Post> findPostsByUserId(Integer userId) {
        return postRepo.findPostsByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) {
       Optional<Post> optionalPost= postRepo.findById(postId);
       if (optionalPost.isEmpty()){
           throw new RuntimeException("Post not Found.....");
       }

        return optionalPost.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepo.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
        }else{
            user.getSavedPost().add(post);
        }

        userRepo.save(user);

        return postRepo.save(post);
    }

    @Override
    public Post likePost(Integer postId, Integer userId) {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (post.getLiked().contains(user)){
            post.getLiked().remove(user);
        }else {
            post.getLiked().add(user);
        }


       return postRepo.save(post);
    }
}
