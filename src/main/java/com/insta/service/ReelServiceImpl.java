package com.insta.service;

import com.insta.modal.Reel;
import com.insta.modal.User;
import com.insta.repo.ReelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelServiceImpl implements ReelService{

    @Autowired
    private ReelRepo reelRepo;

    @Autowired
    private UserService userService;

    @Override
    public Reel createReel(Reel reel, User user) {
        Reel createReel = new Reel();

        createReel.setVideo(reel.getVideo());
        createReel.setTitle(reel.getTitle());
        createReel.setUser(user);
        return reelRepo.save(createReel);
    }

    @Override
    public List<Reel> findAllReels() {
        return reelRepo.findAll();
    }

    @Override
    public List<Reel> findUsersReel(Integer userId) {
        userService.findUserById(userId);
        return reelRepo.findByUserId(userId);
    }
}
