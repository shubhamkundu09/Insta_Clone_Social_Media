package com.insta.service;

import com.insta.modal.Chat;
import com.insta.modal.User;

import java.util.List;

public interface ChatService {

    public Chat createChat(User user1, User user2);

    public Chat findChatById(Integer chatId);

    public List<Chat> findUsersChat(Integer userId);
}
