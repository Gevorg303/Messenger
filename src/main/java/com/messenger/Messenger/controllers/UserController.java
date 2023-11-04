package com.messenger.Messenger.controllers;

import com.messenger.Messenger.domain.User;
import com.messenger.Messenger.service.impl.UserServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@Tag(name = "Работа пользователя", description = "Операции, связанные с пользователями")
public class UserController {
    @Autowired
    private UserServiceInterface userService;

    @GetMapping(value = "/list")
    @Operation(tags = "Список пользователей", description = "Получить список всех пользователей")
    public @ResponseBody List<User> getUserList(){
        return userService.getUserList();
    }

    @DeleteMapping(value = "/deleteUser/{userName}")
    @Operation(tags = "Удалить пользователя", description = "Удалить пользователя по имени")
    public @ResponseBody String deleteUser(@PathVariable ("userName") String userName){
        User user=userService.findUser(userName);
        return userService.deleteUser(user)+"\nСписок пользователей: "+userService.getUserList();
    }

    @PostMapping(value = "/addUser/{userName}")
    @Operation(tags = "Создать нового пользователя", description = "Создать нового пользователя с указанным именем")
    public @ResponseBody String createUser(@PathVariable String userName){
        return userService.createUser(userName)+"\nСписок пользователей: "+userService.getUserList();
    }
}
