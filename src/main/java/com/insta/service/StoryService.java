package com.insta.service;

import com.insta.modal.Story;
import com.insta.modal.User;

import java.util.List;

public interface StoryService {

    public Story createStory(Story story, User user);

    public List<Story> findStoryByUserId(Integer userId);
}
