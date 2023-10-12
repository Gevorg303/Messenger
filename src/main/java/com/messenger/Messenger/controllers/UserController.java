package com.messenger.Messenger.controllers;

import com.messenger.Messenger.domain.Chat;
import com.messenger.Messenger.domain.User;
import com.messenger.Messenger.repository.UserList;
import com.messenger.Messenger.service.impl.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceInterface userService;

    @GetMapping(value = "/list")
    public @ResponseBody List<User> getUserList(){
        return userService.getUserList();
    }

    @DeleteMapping(value = "/deleteUser/{userName}")
    public @ResponseBody String deleteUser(@PathVariable ("userName") String userName){
        User user=userService.findUser(userName);
        return userService.deleteUser(user)+"\nСписок пользователей: "+userService.getUserList();
    }

    @PostMapping(value = "/addUser/{userName}")
    public @ResponseBody String createUser(@PathVariable String userName){
        return userService.createUser(userName)+"\nСписок пользователей: "+userService.getUserList();
    }
}
