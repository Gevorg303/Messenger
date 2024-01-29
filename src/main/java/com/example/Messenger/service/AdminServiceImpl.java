package com.example.Messenger.service;

import com.example.Messenger.domain.Chat;
import com.example.Messenger.repository.impl.AdminRepositoryInterface;
import com.example.Messenger.service.impl.AdminServiceInterface;
import com.example.Messenger.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminServiceInterface {
    @Autowired
    private AdminRepositoryInterface adminRepository;

    /*Удалить любой чат*/
    @Override
    public void deleteAnyChat(ChatServiceImpl chatService, Admin admin, Chat chat) {
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
        adminRepository.save(admin1);
        System.out.println("Админ " + admin1.getNameUser() + " создан.");
        return admin1;
    }

    /*Удалить админа*/
    @Override
    public void deleteAdmin(Admin admin) {
       adminRepository.delete(admin);
    }

    @Override
    public List<Admin> getAdminList(){
        return adminRepository.getAllAdmins();
    }
    @Override
    public Admin findAdmin(String adminName) {
        for(Admin admin: adminRepository.getAllAdmins()){
            if(Objects.equals(adminName, admin.getNameUser())){
                return admin;
            }
        }
        return null;
    }
}
