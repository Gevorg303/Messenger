package com.example.messenger.service;

import com.example.messenger.dto.UserDto;
import com.example.messenger.mapper.api.UserMapperInterface;
import com.example.messenger.repository.api.ChatRepositoryInterface;
import com.example.messenger.repository.api.UserRepositoryInterface;
import com.example.messenger.service.api.UserServiceInterface;
import com.example.messenger.domain.Admin;
import com.example.messenger.domain.Chat;
import com.example.messenger.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserServiceInterface {
    @Autowired
    private UserRepositoryInterface userRepository;
    @Autowired
    private ChatRepositoryInterface chatRepository;
    @Autowired
    private UserMapperInterface userMapper;

    @Override
    @Transactional
    public String createUser(UserDto dto) {
        try {
            User user = userMapper.mapToUser(dto);
            userRepository.save(user);
            return "Пользователь " + user.getNameUser() + " создан.";
        }catch (Exception e){
            return "Ошибка при создании пользователя: " + e.getMessage();
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            userRepository.delete(user);
        } catch (Exception e) {
            log.error("Ошибка при удалении пользователя: " + e.getMessage());
        }
    }

    /*Получить чаты пользователя*/
    @Override
    public List<Chat> getUserChats(User user) {//-
        return user.getChat();
    }

    @Override
    public Chat createChat(ChatServiceImpl chatService, User user, String name, boolean isPrivate, String password, int maxUsers) {
        try {
            Chat chat = chatService.createChat(user, name, isPrivate, password, maxUsers);
            chatRepository.save(chat);
            return chat;
        } catch (Exception e) {
            log.error("Ошибка при создании чата: " + e.getMessage());
            return null;
        }
    }

    @Override//-
    public void deleteChat(ChatServiceImpl chatService, User user, Chat chat) {
        try {
            chatService.deleteChat(user, chat);
        } catch (Exception e) {
            log.error("Ошибка при удалении чата: " + e.getMessage());
        }
    }

    @Override
    public void changeChatMaxUsers(AdminServiceImpl adminService, Admin admin, Chat chat, int maxUsers) {
        try {
            adminService.changeChatMaxUsers(admin, chat, maxUsers);
        } catch (Exception e) {
            log.error("Ошибка при изменении максимального количества пользователей в чате: " + e.getMessage());
        }
    }

    /*Удаляем чаты пользователя*/
    @Override
    public void deleteUserAndChats(ChatServiceImpl chatService, User user) {
        try {
            deleteUser(user);
            List<Chat> userChatsCopy = new ArrayList<>(user.getChat());
            for (Chat chat : userChatsCopy) {
                chatService.deleteChat(user, chat);
            }
        } catch (Exception e) {
            log.error("Ошибка при удалении пользователя и его чатов: " + e.getMessage());
        }
    }

    @Override
    public boolean findUser(UserDto dto) {
        try {
            for (User user : userRepository.findAll()) {
                if (Objects.equals(dto.getNameUser(), user.getNameUser())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            log.error("Ошибка при поиске пользователя: " + e.getMessage());
            return false;
        }
    }
    @Override
    @Transactional
    public Page<User> getUserList(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void removeUser(UserDto userDto) {
        if(findUser(userDto)){
            User user=userMapper.mapToUser(userDto);
            deleteUser(user);
        }else {
            log.info("Пользователь не найден");
        }
    }
}
