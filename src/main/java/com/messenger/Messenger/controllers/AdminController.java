package com.messenger.Messenger.controllers;

import com.messenger.Messenger.domain.Admin;
import com.messenger.Messenger.domain.Chat;
import com.messenger.Messenger.domain.User;
import com.messenger.Messenger.service.impl.AdminServiceInterface;
import com.messenger.Messenger.service.impl.ChatServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@Api(description = "Работа админа")
public class AdminController {
    @Autowired
    private AdminServiceInterface adminServiceInterface;
    @Autowired
    private ChatServiceInterface chatServiceInterface;

    @GetMapping("/list")
    @ApiOperation("Список админов:")
    public @ResponseBody List<Admin> getAdminList(){
        return adminServiceInterface.getAdminList();
    }

    @DeleteMapping (value = "/deleteAdmin/{adminName}")
    @ApiOperation("Удалить админа")
    public @ResponseBody String deleteAdmin(@PathVariable ("adminName") String adminName){
        Admin admin = adminServiceInterface.findAdmin(adminName);
        return adminServiceInterface.deleteAdmin(admin)+"\nСписок админов: "+adminServiceInterface.getAdminList();
    }

    @PostMapping("/addAdmin/{adminName}")
    @ApiOperation("Добавить нового админа")
    public @ResponseBody User createAdmin(@PathVariable("adminName") String adminName){
        return adminServiceInterface.crateAdmin(adminName);
    }
    @PutMapping("/changeChatMaxUsers/{adminName}/{chatName}/{maxUsers}")
    @ApiOperation("Админ изменяет максимальное количество пользователей в чате")
    public @ResponseBody void changeChatMaxUsers(@PathVariable("adminName") String adminName,
                                                 @PathVariable("chatName") String chatName,
                                                 @PathVariable("maxUsers") int maxUsers){
        Admin admin = adminServiceInterface.findAdmin(adminName);
        Chat chat=chatServiceInterface.findChat(chatName);
        adminServiceInterface.changeChatMaxUsers(admin, chat, maxUsers);
    }
}
