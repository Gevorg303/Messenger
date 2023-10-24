package com.messenger.Messenger.controllers;

import com.messenger.Messenger.domain.Admin;
import com.messenger.Messenger.domain.Chat;
import com.messenger.Messenger.domain.User;
import com.messenger.Messenger.service.impl.AdminServiceInterface;
import com.messenger.Messenger.service.impl.ChatServiceInterface;
import io.swagger.annotations.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@Tag(name = "Контроллер администраторов", description = "Контроллер для управления операциями, связанными с администраторами")
public class AdminController {
    @Autowired
    private AdminServiceInterface adminServiceInterface;
    @Autowired
    private ChatServiceInterface chatServiceInterface;

    @GetMapping("/list")
    @Operation(tags = "Список админов", description = "Получить список администраторов")
    public @ResponseBody List<Admin> getAdminList(){
        return adminServiceInterface.getAdminList();
    }

    @DeleteMapping (value = "/deleteAdmin/{adminName}")
    @Operation(tags = "Удалить админа", description = "Удалить администратора по имени админа")
    public @ResponseBody String deleteAdmin(@PathVariable ("adminName") String adminName){
        Admin admin = adminServiceInterface.findAdmin(adminName);
        return adminServiceInterface.deleteAdmin(admin)+"\nСписок админов: "+adminServiceInterface.getAdminList();
    }

    @PostMapping("/addAdmin/{adminName}")
    @Operation(tags = "Добавить нового админа", description = "Добавить нового администратора с указанным именем админа")
    public @ResponseBody User createAdmin(@PathVariable("adminName") String adminName){
        return adminServiceInterface.crateAdmin(adminName);
    }
    @PutMapping("/changeChatMaxUsers/{adminName}/{chatName}/{maxUsers}")
    @Operation(tags = "Админ изменяет максимальное количество пользователей в чате", description = "Изменить максимальное количество пользователей в чате администратором")
    public @ResponseBody void changeChatMaxUsers(@PathVariable("adminName") String adminName,
                                                 @PathVariable("chatName") String chatName,
                                                 @PathVariable("maxUsers") int maxUsers){
        Admin admin = adminServiceInterface.findAdmin(adminName);
        Chat chat=chatServiceInterface.findChat(chatName);
        adminServiceInterface.changeChatMaxUsers(admin, chat, maxUsers);
    }
}
