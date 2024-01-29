package com.example.Messenger.service.impl;

import com.example.Messenger.domain.Chat;
import com.example.Messenger.domain.Message;
import com.example.Messenger.domain.User;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ChatServiceInterface {
    Chat createChat(User creator, String name, boolean isPrivate, String password, int maxUsers);

    /*Удалить чат*/
    void deleteChat(User user, Chat chat);

    //List<Chat> deleteChat(User user, Chat chat);
   // void addUserToChat(User user, Chat chat, String password);

    /*Удалить пользователя из чата*/
    //void removeUserFromChat(User user, Chat chat);

    @Transactional
    void addUserToChat(User user, Chat chat);

    //void removeUserFromChat(User user, Chat chat);
    void writeMessage(User user, Chat chat, Message message);
    List<Chat> getChatList();
    Chat findChat(String chatName);
    Message newMessage(String message, boolean isText);

}
