package com.messenger.Messenger;

import com.messenger.Messenger.domain.*;
import com.messenger.Messenger.service.ChatServiceImpl;
import com.messenger.Messenger.service.impl.AdminServiceInterface;
import com.messenger.Messenger.service.impl.ChatServiceInterface;
import com.messenger.Messenger.service.impl.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Messenger {
    @Autowired
    private UserServiceInterface userServiceInterface;
    @Autowired
    private ChatServiceInterface chatServiceInterface;
    @Autowired
    private AdminServiceInterface adminServiceInterface;
    @Autowired
    private UserList userList;
    @Autowired
    private ChatList chatList;

    public ChatList getChatList() {
        return chatList;
    }
    @Autowired
    public void setChatList(ChatList chatList) {
        this.chatList = chatList;
    }

    public UserList getUserList() {
        return userList;
    }
    @Autowired
    public void setUserList(UserList userList) {
        this.userList = userList;
    }
    @Autowired
    public void setUserServiceInterface(UserServiceInterface userServiceInterface) {
        this.userServiceInterface = userServiceInterface;
    }
    @Autowired
    public void setChatServiceInterface(ChatServiceInterface chatServiceInterface) {
        this.chatServiceInterface = chatServiceInterface;
    }
    @Autowired
    public void setAdminServiceInterface(AdminServiceInterface adminServiceInterface) {
        this.adminServiceInterface = adminServiceInterface;
    }

    public UserServiceInterface getUserServiceInterface() {
        return userServiceInterface;
    }

    public ChatServiceInterface getChatServiceInterface() {
        return chatServiceInterface;
    }

    public AdminServiceInterface getAdminServiceInterface() {
        return adminServiceInterface;
    }

    public User doNewUser(String userName) {
        return getUserServiceInterface().createUser(getUserList(), userName);
    }

    public Chat doNewChat(User user1, String nameChat, boolean isPrivate, String password, int maxUser) {
        return getUserServiceInterface().createChat((ChatServiceImpl) getChatServiceInterface(), getChatList(), user1, nameChat, isPrivate, password, maxUser);
    }

    public List<Chat> printListChat(User user) {
        return getUserServiceInterface().getUserChats(user);
    }

    public void removeChat(User user1, Chat chat1) {
        getUserServiceInterface().deleteChat((ChatServiceImpl) getChatServiceInterface(), getChatList(), user1, chat1);
    }

    public void changeChatMaxUsers(Admin admin, Chat chat1, int i) {
        getAdminServiceInterface().changeChatMaxUsers(admin, chat1, i);
    }

    public void addUserToChat(User user2, Chat chat, String password) {
        getChatServiceInterface().addUserToChat(user2, chat, password);
    }

    public void writeMessage(User user, Chat chat, Message message) {
        getChatServiceInterface().writeMessage(user,chat, message);
    }

    public void removeChatAdmin(Admin admin, Chat chat1) {
        getAdminServiceInterface().deleteAnyChat((ChatServiceImpl) getChatServiceInterface(), getChatList(), admin, chat1);
    }

    public Admin doNewAdmin(String nameAdmin) {
        return getAdminServiceInterface().crateAdmin(getUserList(), nameAdmin);
    }

    public void removeUser(UserList userList, User user2) {
        getUserServiceInterface().deleteUserAndChats((ChatServiceImpl) getChatServiceInterface(), getChatList(), userList, user2);
    }

    public void removeAdmin(Admin admin) {
        getAdminServiceInterface().deleteAdmin(getUserList(), admin);
    }

    public List<Chat> printAllChat() {
        return getChatList().getChats();
    }
}
