package com.messenger.Messenger.controllers;

import com.messenger.Messenger.domain.Chat;
import com.messenger.Messenger.domain.Message;
import com.messenger.Messenger.domain.User;
import com.messenger.Messenger.service.impl.ChatServiceInterface;
import com.messenger.Messenger.service.impl.UserServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@Tag(name = "Работа чата", description = "Операции, связанные с чатами")
public class ChatController {
    @Autowired
    private ChatServiceInterface chatServiceInterface;
    @Autowired
    private UserServiceInterface userServiceInterface;

    @GetMapping(value = "/list")
    @Operation(tags = "Список чатов", description = "Получить список всех чатов")
    public @ResponseBody String getChatList(){
        return  chatServiceInterface.getChatList()+"reg";
    }

    @DeleteMapping(value = "/deleteChat/{user}/{chat}")
    @Operation(tags = "Удалить чат", description = "Удалить чат по имени пользователя и названию чата")
    public @ResponseBody String deleteChat(@PathVariable("user") String userName,
                                         @PathVariable("chat") String chatName){
        Chat chat1=chatServiceInterface.findChat(chatName);
        User user1=userServiceInterface.findUser(userName);
        return "";//chatServiceInterface.deleteChat(user1, chat1)+"Список чатов: "+chatServiceInterface.getChatList();
    }
    @PostMapping(value = "/createChat/{userName}/{nameChat}/{isPrivate}/{password}/{maxUser}")
    @Operation(tags = "Создать новый чат", description = "Создать новый чат с указанными параметрами")
    public  @ResponseBody String createChat(@PathVariable("userName")String userName,
                                          @PathVariable("nameChat") String nameChat,
                                          @PathVariable("isPrivate") boolean isPrivate,
                                          @PathVariable("password") String password,
                                          @PathVariable("maxUser") int maxUser){
        User user1 = userServiceInterface.findUser(userName);
        return chatServiceInterface.createChat(user1, nameChat, isPrivate, password, maxUser)+"Чат успешно создан";
    }
    @PostMapping(value = "/addUserToChat/{userName}/{chatName}/{password}")
    @Operation(tags = "Добавление пользователя в чат", description = "Добавить пользователя в чат с указанным паролем")
    public @ResponseBody void addUserToChat(@PathVariable("userName") String userName,
                              @PathVariable("chatName") String chatName,
                              @PathVariable("password") String password){

        Chat chat1=chatServiceInterface.findChat(chatName);
        User user1=userServiceInterface.findUser(userName);
        //chatServiceInterface.addUserToChat(user1,chat1,password);
    }
    @DeleteMapping(value = "/removeUserFromChat/{userName}/{chatName}")
    @Operation(tags = "Удаление чатов пользователя", description = "Удалить пользователя из чата")
    public @ResponseBody void removeUserFromChat(@PathVariable("userName") String userName,
                                                 @PathVariable("chatName") String chatName){
        User user=userServiceInterface.findUser(userName);
        Chat chat=chatServiceInterface.findChat(chatName);
        //chatServiceInterface.removeUserFromChat(user, chat);
    }
    @PostMapping("/writeMessage/{userName}/{chatName}/{isText}/{message}")
    @Operation(tags = "Отправка сообщения пользователем в чате", description = "Отправить сообщение в чат от имени пользователя")
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
