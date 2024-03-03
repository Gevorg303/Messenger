package com.example.messenger.repository.api;

import com.example.messenger.domain.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminRepositoryInterface{
    Admin save(Admin admin);
    void delete(Admin admin);
    Admin findAdmin(String adminName);

    List<Admin> getAllAdmins();

    Page<Admin> getAllAdmins(Pageable pageable);
}
