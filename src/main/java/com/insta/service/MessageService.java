package com.insta.service;

import com.insta.modal.Chat;
import com.insta.modal.Message;
import com.insta.modal.User;

import java.util.List;

public interface MessageService {

    public Message creteMessage(User user, Integer chatId, Message msg);

    public List<Message> findChatMessage(Integer chatId);
}
