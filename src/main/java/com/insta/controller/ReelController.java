package com.insta.controller;

import com.insta.modal.Reel;
import com.insta.modal.User;
import com.insta.service.ReelService;
import com.insta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reel")
public class ReelController {

    @Autowired
    private ReelService reelService;

    @Autowired
    private UserService userService;

    @PostMapping("/create-reel")
    public Reel createReel(@RequestBody Reel reel, @RequestHeader("Authorization") String jwt){
        User user = userService.getUserByJwtToken(jwt);
        return reelService.createReel(reel, user);
    }

    @GetMapping("/all-reels")
    public List<Reel> findAllReels(){
        return reelService.findAllReels();
    }

    @GetMapping("/reel-by-userId/{userId}")
    public List<Reel> findUsersReel(@PathVariable("userId") Integer userId){
        return reelService.findUsersReel(userId);
    }

}
