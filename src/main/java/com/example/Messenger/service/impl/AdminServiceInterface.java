package com.example.Messenger.service.impl;

import com.example.Messenger.domain.Admin;
import com.example.Messenger.domain.Chat;
import com.example.Messenger.service.ChatServiceImpl;

import java.util.List;


public interface AdminServiceInterface {
    void deleteAnyChat(ChatServiceImpl chatService, Admin admin, Chat chat);
    void changeChatMaxUsers(Admin admin, Chat chat, int maxUsers);
    Admin crateAdmin(String nameAdmin);
    void deleteAdmin(Admin admin);
    List<Admin> getAdminList();
    Admin findAdmin(String adminName);
}
