package com.example.Messenger.service.impl;

import com.example.Messenger.domain.Admin;
import com.example.Messenger.domain.Chat;
import com.example.Messenger.domain.User;
import com.example.Messenger.service.AdminServiceImpl;
import com.example.Messenger.service.ChatServiceImpl;

import java.util.List;

public interface UserServiceInterface {
    String createUser(String username);
    void deleteUser(User user);
    List<Chat> getUserChats(User user);
    Chat createChat(ChatServiceImpl chatService, User user, String name, boolean isPrivate, String password, int maxUsers);
    void deleteChat(ChatServiceImpl chatService, User user, Chat chat);
    void changeChatMaxUsers(AdminServiceImpl adminService, Admin admin, Chat chat, int maxUsers);
    void deleteUserAndChats(ChatServiceImpl chatService, User user);
    User findUser(String userName);
    List<User> getUserList();
}
