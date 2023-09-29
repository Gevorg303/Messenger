package com.messenger.Messenger;

import com.messenger.Messenger.domain.*;
import com.messenger.Messenger.domain.ChatList;
import com.messenger.Messenger.service.AdminServiceImpl;
import com.messenger.Messenger.service.ChatServiceImpl;
import com.messenger.Messenger.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Messenger {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ChatServiceImpl chatService;
    @Autowired
    private AdminServiceImpl adminService;
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

    public UserServiceImpl getUserService() {
        return userService;
    }

    public ChatServiceImpl getChatService() {
        return chatService;
    }
    @Autowired
    public void setChatService(ChatServiceImpl chatService) {
        this.chatService = chatService;
    }

    public AdminServiceImpl getAdminService() {
        return adminService;
    }
    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
    @Autowired
    public void setAdminService(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    public User doNewUser(String userName) {
        return getUserService().createUser(getUserList(), userName);
    }

    public Chat doNewChat(User user1, String nameChat, boolean isPrivate, String password, int maxUser) {
        return getUserService().createChat(getChatService(), getChatList(), user1, nameChat, isPrivate, password, maxUser);
    }

    public List<Chat> printListChat(User user) {
        return getUserService().getUserChats(user);
    }

    public void removeChat(User user1, Chat chat1) {
        getUserService().deleteChat(getChatService(), getChatList(), user1, chat1);
    }

    public void changeChatMaxUsers(Admin admin, Chat chat1, int i) {
        getAdminService().changeChatMaxUsers(admin, chat1, i);
    }

    public void addUserToChat(User user2, Chat chat, String password) {
        getChatService().addUserToChat(user2, chat, password);
    }

    public void writeMessage(User user, Chat chat, Message message) {
        getChatService().writeMessage(user,chat, message);
    }

    public void removeChatAdmin(Admin admin, Chat chat1) {
        getAdminService().deleteAnyChat(getChatService(), getChatList(), admin, chat1);
    }

    public Admin doNewAdmin(String nameAdmin) {
        return getAdminService().crateAdmin(getUserList(), nameAdmin);
    }

    public void removeUser(UserList userList, User user2) {
        getUserService().deleteUserAndChats(getChatService(), getChatList(), userList, user2);
    }

    public void removeAdmin(Admin admin) {
        getAdminService().deleteAdmin(getUserList(), admin);
    }

    public List<Chat> printAllChat() {
        return getChatList().getChats();
    }
}
