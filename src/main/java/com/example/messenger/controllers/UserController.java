package com.example.messenger.controllers;

import com.example.messenger.domain.User;
import com.example.messenger.dto.UserDto;
import com.example.messenger.service.api.UserServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Работа пользователя", description = "Операции, связанные с пользователями")
public class UserController {
    @Autowired
    private UserServiceInterface userService;

    @GetMapping(value = "/list")
    @Operation(tags = "Список пользователей", description = "Получить список всех пользователей")
    public @ResponseBody Page<User> getUserList(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size
    ) {
        return userService.getUserList(PageRequest.of(page, size));
    }

    @DeleteMapping(value = "/delete-user")
    @Operation(tags = "Удалить пользователя", description = "Удалить пользователя по имени")
    public void deleteUser(@RequestBody UserDto userDto){
        userService.removeUser(userDto);
    }

    @PostMapping(value = "/add-user")
    @Operation(tags = "Создать нового пользователя", description = "Создать нового пользователя с указанным именем")
    public String createUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }
}
