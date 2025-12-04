package com.insta.service;

import com.insta.config.JwtProvider;
import com.insta.modal.User;
import com.insta.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Override
    public User registerUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
       return userRepo.findAll();
    }


    @Override
    public User findUserById(Integer userId) {
        Optional<User> ou = userRepo.findById(userId);
        if (ou.isEmpty()){
            throw new RuntimeException("User Not Found");
        }
        return ou.get();


    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);

    }

    @Override
    public User followUser(Integer userId1, Integer userId2) {

        User user1 = userRepo.findById(userId1)
                .orElseThrow(() -> new RuntimeException("User 1 Not Found"));

        User user2 = userRepo.findById(userId2)
                .orElseThrow(() -> new RuntimeException("User 2 Not Found"));

        // ✅ Prevent same user following themselves
        if (userId1.equals(userId2)) {
            throw new RuntimeException("You cannot follow yourself");
        }

        // ✅ FOLLOW / UNFOLLOW LOGIC
        if (user2.getFollowers().contains(user1.getId())) {
            // Already following → Unfollow
            user2.getFollowers().remove(user1.getId());
            user1.getFollowing().remove(user2.getId());
        } else {
            // Not following → Follow
            user2.getFollowers().add(user1.getId());
            user1.getFollowing().add(user2.getId());
        }

        userRepo.save(user2);
        userRepo.save(user1);

        return user1;
    }


    @Override
    public User updateUser(User user, Integer userId) {
        User existingUser = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getFirstName() != null) {
            existingUser.setFirstName(user.getFirstName());
        }

        if (user.getLastName() != null) {
            existingUser.setLastName(user.getLastName());
        }

        if (user.getEmail() != null && !user.getEmail().equals(existingUser.getEmail())) {
            User existingEmailUser = userRepo.findByEmail(user.getEmail());
            if (existingEmailUser != null) {
                throw new RuntimeException("Email already in use");
            }
            existingUser.setEmail(user.getEmail());
        }

        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }

        if (user.getGender() != null) {
            existingUser.setGender(user.getGender());
        }

        return userRepo.save(existingUser);
    }


    @Override
    public List<User> searchUser(String query) {
        return userRepo.searchUser(query);
    }

    @Override
    public User getUserByJwtToken(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        return userRepo.findByEmail(email);
    }
}
