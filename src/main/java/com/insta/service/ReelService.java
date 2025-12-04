package com.insta.service;

import com.insta.modal.Reel;
import com.insta.modal.User;

import java.util.List;

public interface ReelService {

    public Reel createReel(Reel reel, User user);

    public List<Reel> findAllReels();

    public List<Reel> findUsersReel(Integer userId);
}
