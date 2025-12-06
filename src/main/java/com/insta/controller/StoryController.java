package com.insta.controller;

import com.insta.modal.Story;
import com.insta.modal.User;
import com.insta.service.StoryServiceImpl;
import com.insta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/story")
public class StoryController {

    @Autowired
    private StoryServiceImpl storyService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt){
        User user = userService.getUserByJwtToken(jwt);
        return storyService.createStory(story,user);
    }

    @GetMapping("/get-by-userId/{userId}")
    public List<Story> getByUserId(@PathVariable("userId") Integer userId){
        return storyService.findStoryByUserId(userId);
    }

    // ✅ ADD THIS METHOD to get all stories
    @GetMapping("/all-stories")
    public List<Story> getAllStories(){
        return storyService.findAllStories();
    }

}
