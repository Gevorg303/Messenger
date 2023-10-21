package com.messenger.Messenger.controllers;

import com.messenger.Messenger.domain.Chat;
import com.messenger.Messenger.domain.User;
import com.messenger.Messenger.repository.UserList;
import com.messenger.Messenger.service.impl.UserServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceInterface userService;

    @GetMapping(value = "/list")
    @ApiOperation("Список пользователей")
    public @ResponseBody List<User> getUserList(){
        return userService.getUserList();
    }

    @DeleteMapping(value = "/deleteUser/{userName}")
    @ApiOperation("Удалить пользователя")
    public @ResponseBody String deleteUser(@PathVariable ("userName") String userName){
        User user=userService.findUser(userName);
        return userService.deleteUser(user)+"\nСписок пользователей: "+userService.getUserList();
    }

    @PostMapping(value = "/addUser/{userName}")
    @ApiOperation("Создать нового пользователя")
    public @ResponseBody String createUser(@PathVariable String userName){
        return userService.createUser(userName)+"\nСписок пользователей: "+userService.getUserList();
    }
}
