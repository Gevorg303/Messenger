package com.messenger.Messenger.service.impl;

import com.messenger.Messenger.domain.Admin;
import com.messenger.Messenger.domain.Chat;
import com.messenger.Messenger.domain.UserList;
import com.messenger.Messenger.domain.ChatList;
import com.messenger.Messenger.service.ChatServiceImpl;


public interface AdminServiceInterface {
    void deleteAnyChat(ChatServiceImpl chatService, ChatList chatList, Admin admin, Chat chat);
    void changeChatMaxUsers(Admin admin, Chat chat, int maxUsers);
    Admin crateAdmin(UserList userList, String nameAdmin);
    void deleteAdmin(UserList userList, Admin admin);
}
