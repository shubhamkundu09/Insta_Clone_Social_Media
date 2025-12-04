package com.insta.service;

import com.insta.modal.Chat;
import com.insta.modal.Message;
import com.insta.modal.User;
import com.insta.repo.ChatRepo;
import com.insta.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepo chatRepo;


    @Override
    public Message creteMessage(User user, Integer chatId, Message msg) {

        Chat chat = chatService.findChatById(chatId);
        // ---- Authorization check
        // ---- Checking that user is inside in the chat or not
        boolean isMember = false;

        for (User u : chat.getUsers()) {
            if (u.getId().equals(user.getId())) {
                isMember = true;
                break;
            }
        }

        if (!isMember) {
            throw new RuntimeException("You are not a member of this chat");
        }
        // -----------------------------------------


        Message newMsg = new Message();
        newMsg.setUser(user);
        newMsg.setContent(msg.getContent());
        newMsg.setImage(msg.getImage());
        newMsg.setTimeStamp(LocalDateTime.now());
        Chat chat1 = chatService.findChatById(chatId);
        newMsg.setChat(chat1);




        return messageRepo.save(newMsg);
    }

    @Override
    public List<Message> findChatMessage(Integer chatId) {
        return messageRepo.findByChatId(chatId);
    }
}
