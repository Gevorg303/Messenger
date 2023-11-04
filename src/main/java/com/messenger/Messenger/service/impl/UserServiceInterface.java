package com.messenger.Messenger.service.impl;

import com.messenger.Messenger.domain.Admin;
import com.messenger.Messenger.domain.Chat;
import com.messenger.Messenger.domain.User;
import com.messenger.Messenger.service.AdminServiceImpl;
import com.messenger.Messenger.service.ChatServiceImpl;

import java.util.List;

public interface UserServiceInterface {
    String createUser(String username);
    String deleteUser(User user);
    List<Chat> getUserChats(User user);
    Chat createChat(ChatServiceImpl chatService, User user, String name, boolean isPrivate, String password, int maxUsers);
    void deleteChat(ChatServiceImpl chatService, User user, Chat chat);
    void changeChatMaxUsers(AdminServiceImpl adminService, Admin admin, Chat chat, int maxUsers);
    void deleteUserAndChats(ChatServiceImpl chatService, User user);
    User findUser(String userName);
    List<User> getUserList();
}
