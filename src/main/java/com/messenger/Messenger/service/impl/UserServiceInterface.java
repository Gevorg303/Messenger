package com.messenger.Messenger.service.impl;

import com.messenger.Messenger.domain.Admin;
import com.messenger.Messenger.domain.Chat;
import com.messenger.Messenger.domain.User;
import com.messenger.Messenger.domain.UserList;
import com.messenger.Messenger.domain.ChatList;
import com.messenger.Messenger.service.AdminServiceImpl;
import com.messenger.Messenger.service.ChatServiceImpl;

import java.util.List;

public interface UserServiceInterface {
    /*Создать нового пользователя*/
    User createUser(UserList userList, String username);
    void deleteUser(ChatServiceImpl chatService, ChatList chatList, UserList userList, User user);
    List<Chat> getUserChats(User user);
    Chat createChat(ChatServiceImpl chatService, ChatList chatList, User user, String name, boolean isPrivate, String password, int maxUsers);
    void deleteChat(ChatServiceImpl chatService, ChatList chatList, User user, Chat chat);
    void changeChatMaxUsers(AdminServiceImpl adminService, Admin admin, Chat chat, int maxUsers);
    void deleteUserAndChats(ChatServiceImpl chatService, ChatList chatList, UserList userList, User user);

}
