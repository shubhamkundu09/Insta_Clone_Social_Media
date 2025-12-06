package com.insta.service;

import com.insta.modal.Story;
import com.insta.modal.User;
import com.insta.repo.StoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class StoryServiceImpl implements StoryService{

    @Autowired
    private StoryRepo storyRepo;

    @Autowired UserService userService;

    @Override
    public Story createStory(Story story, User user) {

       Story newStory = new Story();

        newStory.setCaption(story.getCaption());
        newStory.setImage(story.getImage());
        newStory.setTimeStamp(LocalDateTime.now());
        newStory.setUser(user);



        return  storyRepo.save(newStory);
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) {
        User user = userService.findUserById(userId);
        return storyRepo.findByUserId(userId);
    }


    // ✅ ADD THIS METHOD
    @Override
    public List<Story> findAllStories() {
        return storyRepo.findAll();
    }
}
