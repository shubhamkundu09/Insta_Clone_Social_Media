package com.insta.repo;

import com.insta.modal.Reel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReelRepo extends JpaRepository<Reel, Integer> {

    public List<Reel> findByUserId(Integer userId);
}
