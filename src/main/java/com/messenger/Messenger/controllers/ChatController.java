package com.messenger.Messenger.controllers;

import com.messenger.Messenger.domain.Chat;
import com.messenger.Messenger.domain.Message;
import com.messenger.Messenger.domain.User;
import com.messenger.Messenger.service.impl.ChatServiceInterface;
import com.messenger.Messenger.service.impl.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatServiceInterface chatServiceInterface;
    @Autowired
    private UserServiceInterface userServiceInterface;

    @GetMapping(value = "/list")
    public @ResponseBody List<Chat> getChatList(){
        return  chatServiceInterface.getChatList();
    }

    @DeleteMapping(value = "/deleteChat/{user}/{chat}")
    public @ResponseBody void deleteChat(@PathVariable("user") String userName,
                                         @PathVariable("chat") String chatName){
        Chat chat1=chatServiceInterface.findChat(chatName);
        User user1=userServiceInterface.findUser(userName);
        System.out.println(chatServiceInterface.deleteChat(user1, chat1)+"sadsadasd");
    }
    @PostMapping(value = "/createChat/{userName}/{nameChat}/{isPrivate}/{password}/{maxUser}")
    public  @ResponseBody Chat createChat(@PathVariable("userName")String userName,
                                          @PathVariable("nameChat") String nameChat,
                                          @PathVariable("isPrivate") boolean isPrivate,
                                          @PathVariable("password") String password,
                                          @PathVariable("maxUser") int maxUser){
        User user1 = userServiceInterface.findUser(userName);
        return chatServiceInterface.createChat(user1, nameChat, isPrivate, password, maxUser);
    }
    @PostMapping(value = "/addUserToChat/{userName}/{chatName}/{password}")//работает идеально
    public @ResponseBody void addUserToChat(@PathVariable("userName") String userName,
                              @PathVariable("chatName") String chatName,
                              @PathVariable("password") String password){

        Chat chat1=chatServiceInterface.findChat(chatName);
        User user1=userServiceInterface.findUser(userName);
        chatServiceInterface.addUserToChat(user1,chat1,password);
    }
    @DeleteMapping(value = "/removeUserFromChat/{userName}/{chatName}")//работает идеально
    public @ResponseBody void removeUserFromChat(@PathVariable("userName") String userName,
                                                 @PathVariable("chatName") String chatName){
        User user=userServiceInterface.findUser(userName);
        Chat chat=chatServiceInterface.findChat(chatName);
        chatServiceInterface.removeUserFromChat(user, chat);
    }
    @PostMapping("/writeMessage/{userName}/{chatName}/{isText}/{message}")//работает идеально
    public @ResponseBody void writeMessage(@PathVariable("userName") String userName,
                                           @PathVariable("chatName") String chatName,
                                           @PathVariable("isText") boolean isText,
                                           @PathVariable("message") String message){
        User user=userServiceInterface.findUser(userName);
        Chat chat=chatServiceInterface.findChat(chatName);
        Message message1=chatServiceInterface.newMessage(message, isText);
        chatServiceInterface.writeMessage(user, chat, message1);
    }
}
