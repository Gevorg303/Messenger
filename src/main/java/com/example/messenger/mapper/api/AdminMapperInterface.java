package com.example.messenger.mapper.api;

import com.example.messenger.domain.Admin;
import com.example.messenger.dto.AdminDto;

public interface AdminMapperInterface {
    AdminDto mapToAdminDto(Admin admin);
    Admin mapToAdmin(AdminDto adminDto);
}
