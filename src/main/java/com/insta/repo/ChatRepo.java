package com.insta.repo;

import com.insta.modal.Chat;
import com.insta.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepo extends JpaRepository<Chat, Integer> {

    List<Chat> findByUsersId(Integer userId);

    @Query("Select c from Chat c Where :user Member of c.users And :requser Member of c.users")
    Chat findUsersChat(@Param("user") User user, @Param("requser") User requser);
}
