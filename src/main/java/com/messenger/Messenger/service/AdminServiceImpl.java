package com.messenger.Messenger.service;

import com.messenger.Messenger.domain.User;
import com.messenger.Messenger.repository.AdminList;
import com.messenger.Messenger.service.impl.AdminServiceInterface;
import com.messenger.Messenger.domain.Admin;
import com.messenger.Messenger.domain.Chat;
import com.messenger.Messenger.repository.UserList;
import com.messenger.Messenger.repository.ChatList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminServiceInterface {

    @Autowired
    private AdminList adminList;

    /*Удалить любой чат*/
    @Override
    public void deleteAnyChat(ChatServiceImpl chatService, ChatList chatList, Admin admin, Chat chat) {
        chatService.deleteChat(admin, chat);
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
    public Admin crateAdmin(String nameAdmin){
        Admin admin1=new Admin(nameAdmin);
        adminList.getAdminList().add(admin1);
        System.out.println("Админ " + admin1.getNameUser() + " создан.");
        return admin1;
    }

    /*Удалить админа*/
    @Override
    public String deleteAdmin(Admin admin) {
        List<Admin> adminsToRemove = new ArrayList<>();

        for (Admin admin1 : adminList.getAdminList()) {
            if (Objects.equals(admin1.getNameUser(), admin.getNameUser())) {
                adminsToRemove.add(admin1);
            }
        }

        if (!adminsToRemove.isEmpty()) {
            adminList.getAdminList().removeAll(adminsToRemove);
            return "Админ " + admin.getNameUser() + " удален.";
        } else {
            return "Админ " + admin.getNameUser() + " не найден.";
        }
    }

    @Override
    public List<Admin> getAdminList(){
        return adminList.getAdminList();
    }
    @Override
    public Admin findAdmin(String adminName) {
        for(Admin admin: adminList.getAdminList()){
            if(Objects.equals(adminName, admin.getNameUser())){
                return admin;
            }
        }
        return null;
    }
}
