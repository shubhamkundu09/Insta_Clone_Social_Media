package com.insta.service;

import com.insta.modal.Chat;
import com.insta.modal.User;
import com.insta.repo.ChatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService{


    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private UserService userService;


    @Override
    public Chat createChat(User user1, User user2) {
        Chat isExist = chatRepo.findUsersChat(user1,user2);
        if (isExist!=null){
            return isExist;
        }
        Chat newChat = new Chat();
        newChat.getUsers().add(user1);
        newChat.getUsers().add(user2);
        newChat.setTimeStamp(LocalDateTime.now());




        return chatRepo.save(newChat);
    }

    @Override
    public Chat findChatById(Integer chatId) {
        Optional<Chat> chat = chatRepo.findById(chatId);
        if (chat.isEmpty()){
            throw new RuntimeException("Chat Not Found");
        }

        return chat.get();
    }

    @Override
    public List<Chat> findUsersChat(Integer userId) {
        userService.findUserById(userId);

        return chatRepo.findByUsersId(userId);
    }
}
