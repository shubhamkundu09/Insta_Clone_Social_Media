package com.insta.controller;

import com.insta.modal.Message;
import com.insta.modal.User;
import com.insta.service.MessageServiceImpl;
import com.insta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageServiceImpl messageService;

    @Autowired
    private UserService userService;

    @PostMapping("/create/{chatId}")
    public Message create(@RequestHeader("Authorization") String jwt, @PathVariable("chatId") Integer chatId,
                          @RequestBody Message message){

        User user = userService.getUserByJwtToken(jwt);
        return messageService.creteMessage(user,chatId, message);
    }

    @GetMapping("/get-msg-by-chatId/{chatId}")
    public List<Message> getMsgOfChat(@PathVariable("chatId") Integer chatId){
        return messageService.findChatMessage(chatId);
    }

}
