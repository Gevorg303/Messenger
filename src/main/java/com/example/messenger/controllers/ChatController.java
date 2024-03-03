package com.example.messenger.controllers;

import com.example.messenger.domain.Chat;
import com.example.messenger.domain.Message;
import com.example.messenger.domain.User;
import com.example.messenger.dto.ChatDto;
import com.example.messenger.dto.MessageDto;
import com.example.messenger.dto.PublicChatDto;
import com.example.messenger.dto.UserDto;
import com.example.messenger.service.api.ChatServiceInterface;
import com.example.messenger.service.api.UserServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@Tag(name = "Работа чата", description = "Операции, связанные с чатами")
public class ChatController {
    @Autowired
    private ChatServiceInterface chatServiceInterface;

    @GetMapping(value = "/list")
    @Operation(tags = "Список чатов", description = "Получить список всех чатов")
    public @ResponseBody Page<Chat> getChatList(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size
    ) {
        return chatServiceInterface.getChatList(PageRequest.of(page, size));
    }

    @DeleteMapping(value = "/delete-chat")
    @Operation(tags = "Удалить чат", description = "Удалить чат по имени пользователя и названию чата")
    public void deleteChat(@RequestBody ChatDto chatDto, @RequestBody UserDto userDto){
        chatServiceInterface.removeChat(chatDto, userDto);
    }
    @PostMapping(value = "/create-chat")
    @Operation(tags = "Создать новый чат", description = "Создать новый чат с указанными параметрами")
    public void createChat(@RequestBody ChatDto chatDto, @RequestBody UserDto userDto){
        chatServiceInterface.addChat(userDto, chatDto);
    }
    @PostMapping(value = "/add-user-to-chat")
    @Operation(tags = "Добавление пользователя в чат", description = "Добавить пользователя в чат")
    public void addUserToChat(@RequestBody ChatDto chatDto, @RequestBody UserDto userDto){
        chatServiceInterface.addUserChat(chatDto, userDto);
    }
    @DeleteMapping(value = "/remove-user-from-chat")
    @Operation(tags = "Удаление чатов пользователя", description = "Удалить пользователя из чата")
    public void removeUserFromChat(@RequestBody ChatDto chatDto, @RequestBody UserDto userDto){
        chatServiceInterface.deleteUserToChat(userDto, chatDto);
    }
    @PostMapping("/write-message")
    @Operation(tags = "Отправка сообщения пользователем в чате", description = "Отправить сообщение в чат от имени пользователя")
    public void writeMessage(@RequestBody ChatDto chatDto, @RequestBody UserDto userDto, @RequestBody MessageDto messageDto){
        chatServiceInterface.writeMessageUser(userDto, chatDto, messageDto);
    }
}
