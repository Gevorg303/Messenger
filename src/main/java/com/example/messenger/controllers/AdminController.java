package com.example.messenger.controllers;

import com.example.messenger.domain.Admin;
import com.example.messenger.domain.Chat;
import com.example.messenger.domain.User;
import com.example.messenger.dto.AdminDto;
import com.example.messenger.dto.PublicChatDto;
import com.example.messenger.service.api.AdminServiceInterface;
import com.example.messenger.service.api.ChatServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(name = "Контроллер администраторов", description = "Контроллер для управления операциями, связанными с администраторами")
public class AdminController {
    @Autowired
    private AdminServiceInterface adminServiceInterface;

    @GetMapping("/list")
    @Operation(tags = "Список админов", description = "Получить список администраторов")
    public @ResponseBody Page<Admin> getAdminList(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size
    ) {
        return adminServiceInterface.getAdminList(PageRequest.of(page, size));
    }

    @DeleteMapping(value = "/delete-admin")
    @Operation(tags = "Удалить админа", description = "Удалить администратора по имени админа")
    public void deleteAdmin(@RequestBody AdminDto adminDto){
        adminServiceInterface.removeAdmin(adminDto);
    }

    @PostMapping("/add-admin")
    @Operation(tags = "Добавить нового админа", description = "Добавить нового администратора с указанным именем админа")
    public @ResponseBody User createAdmin(@RequestBody AdminDto adminDto){
        return adminServiceInterface.crateAdmin(adminDto);
    }
    @PutMapping("/change-chat-max-users")
    @Operation(tags = "Админ изменяет максимальное количество пользователей в чате", description = "Изменить максимальное количество пользователей в чате администратором")
    public void changeChatMaxUsers(@RequestBody AdminDto adminDto, @RequestBody PublicChatDto publicChatDto){
        adminServiceInterface.changeTheNumberOfUsers(adminDto, publicChatDto);
    }
}
