package com.insta.repo;

import com.insta.modal.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Integer> {

    public List<Message> findByChatId(Integer chatId);
}
