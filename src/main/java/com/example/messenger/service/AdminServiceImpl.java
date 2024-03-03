package com.example.messenger.service;

import com.example.messenger.domain.Chat;
import com.example.messenger.dto.AdminDto;
import com.example.messenger.dto.PublicChatDto;
import com.example.messenger.mapper.api.AdminMapperInterface;
import com.example.messenger.mapper.api.PublicChatMapperInterface;
import com.example.messenger.repository.api.AdminRepositoryInterface;
import com.example.messenger.service.api.AdminServiceInterface;
import com.example.messenger.domain.Admin;
import com.example.messenger.service.api.ChatServiceInterface;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class AdminServiceImpl implements AdminServiceInterface {
    @Autowired
    private AdminRepositoryInterface adminRepository;
    @Autowired
    private ChatServiceInterface chatServiceInterface;
    @Autowired
    private AdminMapperInterface adminMapper;
    @Autowired
    private PublicChatMapperInterface publicChatMapperInterface;

    @Override
    public void deleteAnyChat(ChatServiceImpl chatService, Admin admin, Chat chat) {
        try {
            chatService.deleteChat(admin, chat);
            log.info("Админ " + admin.getNameUser() + " удалил чат " + chat.getChatName());
        } catch (Exception e){
            log.error("Ошибка при удалении чата: " + e.getMessage());
        }
    }
    @Override
    public void changeChatMaxUsers(Admin admin, Chat chat, int maxUsers) {
        try {
            if (admin != null) {
                chat.setMaxUser(maxUsers);
                log.info("Максимальное количество пользователей в чате " + chat.getChatName() + " изменено на " + maxUsers);
            } else {
                log.info("Только администратор может изменить максимальное количество пользователей чата");
            }
        }catch (Exception e){
            log.error("Ошибка: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Admin crateAdmin(AdminDto dto){
        try {
            Admin admin1=adminMapper.mapToAdmin(dto);
            adminRepository.save(admin1);
            log.info("Админ " + admin1.getNameUser() + " создан.");
            return admin1;
        }catch (Exception e){
            log.error("Ошибка при создании админа: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteAdmin(Admin admin) {
        try {
            adminRepository.delete(admin);
        } catch (Exception e) {
            log.error("Ошибка при удалении админа: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<Admin> getAdminList(Pageable pageable) {
        try {
            return adminRepository.getAllAdmins(pageable);
        } catch (Exception e) {
            log.error("Ошибка при получении списка администраторов: " + e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public void removeAdmin(AdminDto adminDto) {
        Admin admin = adminMapper.mapToAdmin(adminDto);
        deleteAdmin(admin);
    }

    @Override
    @Transactional
    public void changeTheNumberOfUsers(AdminDto dto, PublicChatDto publicChatDto) {
        Admin admin = adminMapper.mapToAdmin(dto);
        Chat chat=publicChatMapperInterface.mapToPublicChat(publicChatDto);
        changeChatMaxUsers(admin, chat, chat.getMaxUser());
    }
}
