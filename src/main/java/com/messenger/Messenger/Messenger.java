package com.messenger.Messenger;

import com.messenger.Messenger.domain.Admin;
import com.messenger.Messenger.domain.Chat;
import com.messenger.Messenger.domain.Message;
import com.messenger.Messenger.domain.User;
import com.messenger.Messenger.service.impl.AdminServiceInterface;
import com.messenger.Messenger.service.impl.ChatServiceInterface;
import com.messenger.Messenger.service.impl.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Messenger {
    @Autowired
    private UserServiceInterface userServiceInterface;
    @Autowired
    private ChatServiceInterface chatServiceInterface;
    @Autowired
    private AdminServiceInterface adminServiceInterface;

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
    public void GGG(User user1, String nameChat, boolean isPrivate, String password, int maxUser){
        chatServiceInterface.createChat(user1, nameChat, isPrivate, password, maxUser);
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

//    public String doNewUser(String userName) {
//        return getUserServiceInterface().createUser(userName);
//    }

//    public Chat doNewChat(User user1, String nameChat, boolean isPrivate, String password, int maxUser) {
//        return getUserServiceInterface().createChat((ChatServiceImpl) getChatServiceInterface(), getChatList(), user1, nameChat, isPrivate, password, maxUser);
//    }

//    public List<Chat> printListChat(User user) {
//        return getUserServiceInterface().getUserChats(user);
//    }

//    public void removeChat(User user1, Chat chat1) {
//        getUserServiceInterface().deleteChat((ChatServiceImpl) getChatServiceInterface(), getChatList(), user1, chat1);
//    }

    public void changeChatMaxUsers(Admin admin, Chat chat1, int i) {
        getAdminServiceInterface().changeChatMaxUsers(admin, chat1, i);
    }

//    public void addUserToChat(User user2, Chat chat, String password) {
//        getChatServiceInterface().addUserToChat(user2, chat, password);
//    }

    public void writeMessage(User user, Chat chat, Message message) {
        getChatServiceInterface().writeMessage(user,chat, message);
    }

//    public void removeChatAdmin(Admin admin, Chat chat1) {
//        getAdminServiceInterface().deleteAnyChat((ChatServiceImpl) getChatServiceInterface(), getChatList(), admin, chat1);
//    }

    public Admin doNewAdmin(String nameAdmin) {
        return getAdminServiceInterface().crateAdmin(nameAdmin);
    }

//    public void removeUser(User user2) {
//        getUserServiceInterface().deleteUserAndChats((ChatServiceImpl) getChatServiceInterface(), getChatList(), user2);
//    }

//    public void removeAdmin(Admin admin) {
//        getAdminServiceInterface().deleteAdmin(admin);
//    }

//    public List<Chat> printAllChat() {
//        return getChatList().getChats();
//    }

//    public void findUser(String userName) {
//        getUserServiceInterface().findUser(userName);
//    }

}
