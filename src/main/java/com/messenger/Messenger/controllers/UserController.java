package com.messenger.Messenger.controllers;

import com.messenger.Messenger.domain.Chat;
import com.messenger.Messenger.domain.ChatList;
import com.messenger.Messenger.domain.User;
import com.messenger.Messenger.domain.UserList;
import com.messenger.Messenger.service.ChatServiceImpl;
import com.messenger.Messenger.service.impl.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceInterface userService;

    @Autowired
    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }
    @GetMapping("/{username}/chats")
    public List<Chat> getUserChats(@PathVariable User username) {
        return userService.getUserChats(username);

    }
    @PostMapping("/{username}")
    public User createUser(@RequestBody UserList userList, @PathVariable String username) {
        return userService.createUser(userList, username);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@RequestBody ChatServiceImpl chatService, @RequestBody ChatList chatList, @RequestBody UserList userList, @PathVariable User username) {
        userService.deleteUser(chatService, chatList, userList, username);
        System.out.println("Пользователь был удален");
    }
}
