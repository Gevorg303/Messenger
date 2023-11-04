package com.messenger.Messenger.service.impl;

import com.messenger.Messenger.domain.Chat;
import com.messenger.Messenger.domain.Message;
import com.messenger.Messenger.domain.User;

import java.util.List;

public interface ChatServiceInterface {
    Chat createChat(User creator, String name, boolean isPrivate, String password, int maxUsers);

    /*Удалить чат*/
    void deleteChat(User user, Chat chat);

    //List<Chat> deleteChat(User user, Chat chat);
   // void addUserToChat(User user, Chat chat, String password);

    /*Удалить пользователя из чата*/
    //void removeUserFromChat(User user, Chat chat);

    //void removeUserFromChat(User user, Chat chat);
    void writeMessage(User user, Chat chat, Message message);
    List<Chat> getChatList();
    Chat findChat(String chatName);
    Message newMessage(String message, boolean isText);

}
