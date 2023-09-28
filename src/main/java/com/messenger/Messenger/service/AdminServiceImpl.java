package com.messenger.Messenger.service;

import com.messenger.Messenger.service.impl.AdminServiceInterface;
import com.messenger.Messenger.domain.Admin;
import com.messenger.Messenger.domain.Chat;
import com.messenger.Messenger.domain.UserList;
import com.messenger.Messenger.domain.ChatList;
import org.springframework.stereotype.Service;
@Service
public class AdminServiceImpl implements AdminServiceInterface {

    /*Удалить любой чат*/
    @Override
    public void deleteAnyChat(ChatServiceImpl chatService, ChatList chatList, Admin admin, Chat chat) {
        chatService.deleteChat(chatList, admin, chat);
        System.out.println("Админ " + admin.getNameUser() + " удалил чат " + chat.getChatName());
    }

    /*Изменить максимальное количество пользователей в чате*/
    @Override
    public void changeChatMaxUsers(Admin admin, Chat chat, int maxUsers) {
        if (admin != null) {
            chat.setMaxUser(maxUsers);
            System.out.println("Максимальное количество пользователей в чате " + chat.getChatName() + " изменено на " + maxUsers);
        } else {
            System.out.println("Только администратор может изменить максимальное количество пользователей чата");
        }
    }

    /*Добавить нового админа*/
    @Override
    public Admin crateAdmin(UserList userList, String nameAdmin){
        Admin admin1=new Admin(nameAdmin);
        userList.getUsers().add(admin1);
        System.out.println("Админ " + admin1.getNameUser() + " создан.");
        return admin1;
    }

    /*Удалить админа*/
    @Override
    public void deleteAdmin(UserList userList, Admin admin) {
        userList.getAdminList().remove(admin);
        System.out.println("Админ " + admin.getNameUser() + " удален.");
    }
}
