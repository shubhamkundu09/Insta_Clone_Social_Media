package com.insta.controller;

import com.insta.modal.Chat;
import com.insta.modal.User;
import com.insta.request.ChatCreateRequest;
import com.insta.service.ChatService;
import com.insta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping("/add/{userId2}")
    public Chat createChat(@PathVariable("userId2") Integer userId2, @RequestHeader("Authorization") String jwt){
        User user = userService.getUserByJwtToken(jwt);
        User user2 = userService.findUserById(userId2);
        return chatService.createChat(user, user2);


    }

    @GetMapping("/chat-by-id/{chatId}")
    public Chat findChatById(@PathVariable("chatId") Integer chatId){
        return chatService.findChatById(chatId);
    }


    @GetMapping("/find-user-chat")
    public List<Chat> findUserChat(@RequestHeader("Authorization") String jwt){
        User user = userService.getUserByJwtToken(jwt);
        return chatService.findUsersChat(user.getId());
    }
}
