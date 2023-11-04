package com.messenger.Messenger.repository.impl;

import com.messenger.Messenger.domain.Admin;

import java.util.List;

public interface AdminRepositoryInterface {
    Admin save(Admin admin);

    void delete(Admin admin);

    Admin findAdmin(String adminName);

    List<Admin> getAllAdmins();
}
