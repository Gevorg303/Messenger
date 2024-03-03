package com.example.messenger.mapper;

import com.example.messenger.domain.Admin;
import com.example.messenger.dto.AdminDto;
import com.example.messenger.mapper.api.AdminMapperInterface;
import org.springframework.stereotype.Component;

@Component
public class AdminMapperImpl implements AdminMapperInterface {

    @Override
    public AdminDto mapToAdminDto(Admin admin) {
        AdminDto dto = new AdminDto();
        dto.setId(admin.getId());
        dto.setNameUser(admin.getNameUser());
        return dto;
    }

    @Override
    public Admin mapToAdmin(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setId(adminDto.getId());
        admin.setNameUser(adminDto.getNameUser());
        return admin;
    }
}
