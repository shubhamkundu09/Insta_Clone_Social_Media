package com.insta.repo;

import com.insta.modal.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepo extends JpaRepository<Story, Integer> {

     List<Story> findByUserId(Integer userId);
}
