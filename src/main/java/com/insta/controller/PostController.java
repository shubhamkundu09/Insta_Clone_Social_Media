package com.insta.controller;

import com.insta.modal.Post;
import com.insta.modal.User;
import com.insta.service.PostServiceImpl;
import com.insta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostServiceImpl postService;

    @Autowired
    UserService userService;

    @PostMapping("/create-post")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestHeader("Authorization") String jwt){

        User user = userService.getUserByJwtToken(jwt);

      Post createPost =   postService.createPost(post, user.getId());

        return new ResponseEntity<>(createPost, HttpStatus.CREATED);

    }

    @DeleteMapping("/delete-post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") Integer postId,
                                             @RequestHeader("Authorization") String jwt){
        User user = userService.getUserByJwtToken(jwt);
        postService.deletePost(postId, user.getId());
        return new ResponseEntity<>("Post Deleted", HttpStatus.OK);

    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable("postId") Integer postId){
        Post post = postService.findPostById(postId);
       return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/all-post")
    public ResponseEntity<List<Post>> findAllPostByUserId( @RequestHeader("Authorization") String jwt){
        User user = userService.getUserByJwtToken(jwt);
        List<Post> lp = postService.findPostsByUserId(user.getId());
        return new ResponseEntity<>(lp, HttpStatus.OK);
    }


    @GetMapping("/all-posts-of-users")
    public ResponseEntity<List<Post>> findAllPost(){
        List<Post> lp = postService.findAllPost();
        return new ResponseEntity<>(lp, HttpStatus.OK);
    }


    @PutMapping("/save-post/{postId}")
    public ResponseEntity<Post> savedPost(@PathVariable("postId") Integer postId,
                                          @RequestHeader("Authorization") String jwt){
        User user = userService.getUserByJwtToken(jwt);
        Post post = postService.savedPost(postId, user.getId());
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

    @PutMapping("/like-post/{postId}")
    public ResponseEntity<Post> likePost(@PathVariable("postId") Integer postId,
                                         @RequestHeader("Authorization") String jwt){
        User user = userService.getUserByJwtToken(jwt);
        Post post = postService.likePost(postId, user.getId());
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }


}
