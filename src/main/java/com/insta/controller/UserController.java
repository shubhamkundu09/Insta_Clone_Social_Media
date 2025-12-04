package com.insta.controller;


import com.insta.modal.User;
import com.insta.repo.UserRepo;
import com.insta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        return userService.registerUser(user);

    }

    @GetMapping("/get-all-user")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/get-user-by-id/{id}")
    public User getUserById(@PathVariable("id") Integer id){
        return userService.findUserById(id);
    }

    @GetMapping("/get-user-by-email/{email}")
    public User getUserByEmail(@PathVariable("email") String email){
        return userService.findUserByEmail(email);
    }

    @PutMapping("/update-user")
    public User updateUser(@RequestBody User user, @RequestHeader("Authorization") String jwt){
       User userbyjwt =  userService.getUserByJwtToken(jwt);


        return userService.updateUser(user, userbyjwt.getId());
    }

    @PutMapping("/follow/{userId2}")
    public User followUser(@RequestHeader("Authorization") String jwt, @PathVariable("userId2") Integer userId2){
        User userbyjwt= userService.getUserByJwtToken(jwt);
        return userService.followUser(userbyjwt.getId(), userId2);
    }


    @GetMapping("/search-user")
    public List<User> searchUser(@RequestParam("name") String name){
        return  userService.searchUser(name);
    }


    @GetMapping("/user-by-jwt")
    public User getUserByJwtToken(@RequestHeader("Authorization") String jwt){
        return userService.getUserByJwtToken(jwt);
    }


}
