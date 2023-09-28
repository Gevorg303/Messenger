package com.messenger.Messenger.service.impl;

import com.messenger.Messenger.domain.Chat;
import com.messenger.Messenger.domain.Message;
import com.messenger.Messenger.domain.User;
import com.messenger.Messenger.domain.ChatList;

public interface ChatServiceInterface {
    Chat createChat(ChatList chatList, User creator, String name, boolean isPrivate, String password, int maxUsers);
    void deleteChat(ChatList chatList, User user, Chat chat);
    void addUserToChat(User user, Chat chat, String password);
    void removeUserFromChat(ChatList chatList, User user, Chat chat);
    void writeMessage(User user, Chat chat, Message message);
}
