package com.example.messenger.service.api;

import com.example.messenger.domain.Admin;
import com.example.messenger.domain.Chat;
import com.example.messenger.dto.AdminDto;
import com.example.messenger.dto.PublicChatDto;
import com.example.messenger.service.ChatServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface AdminServiceInterface {
    void deleteAnyChat(ChatServiceImpl chatService, Admin admin, Chat chat);
    void changeChatMaxUsers(Admin admin, Chat chat, int maxUsers);
    Admin crateAdmin(AdminDto dto);
    void deleteAdmin(Admin admin);
    Page<Admin> getAdminList(Pageable pageable);
    void removeAdmin(AdminDto adminDto);
    void changeTheNumberOfUsers(AdminDto dto, PublicChatDto publicChatDto);
}
