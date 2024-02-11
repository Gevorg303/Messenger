package com.example.Messenger.repository.impl;

import com.example.Messenger.domain.Admin;
import com.example.Messenger.domain.Chat;
import com.example.Messenger.domain.User;

import java.util.List;

public interface AdminRepositoryInterface{
    Admin save(Admin admin);
    void delete(Admin admin);
    Admin findAdmin(String adminName);
    List<Admin> getAllAdmins();


}
