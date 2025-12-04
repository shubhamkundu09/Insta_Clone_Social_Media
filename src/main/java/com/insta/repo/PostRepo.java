package com.insta.repo;

import com.insta.modal.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

    @Query("select p from Post p where p.user.id = :userId")
    List<Post> findPostsByUserId(@Param("userId") Integer userId);
}
