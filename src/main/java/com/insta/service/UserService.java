package com.insta.service;

import com.insta.modal.User;

import java.util.List;

public interface UserService {

    public User registerUser(User user);

    public List<User> getAllUsers();

    public User findUserById(Integer userId);

    public User findUserByEmail(String email);

    public User followUser(Integer userId1, Integer userId2);

    public User updateUser(User user, Integer userId);

    public List<User> searchUser(String query);

    public User getUserByJwtToken(String jwt);


}
