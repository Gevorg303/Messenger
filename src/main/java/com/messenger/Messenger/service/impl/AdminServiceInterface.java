package com.messenger.Messenger.service.impl;

import com.messenger.Messenger.domain.Admin;
import com.messenger.Messenger.domain.Chat;
import com.messenger.Messenger.domain.User;
import com.messenger.Messenger.repository.AdminList;
import com.messenger.Messenger.repository.UserList;
import com.messenger.Messenger.repository.ChatList;
import com.messenger.Messenger.service.ChatServiceImpl;

import java.util.List;


public interface AdminServiceInterface {
    void deleteAnyChat(ChatServiceImpl chatService, ChatList chatList, Admin admin, Chat chat);
    void changeChatMaxUsers(Admin admin, Chat chat, int maxUsers);
    Admin crateAdmin(String nameAdmin);
    String deleteAdmin(Admin admin);
    List<Admin> getAdminList();
    Admin findAdmin(String adminName);
}
