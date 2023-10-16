package com.messenger.Messenger.repository;

import com.messenger.Messenger.domain.Admin;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
@Repository
public class AdminList {
    private List<Admin> adminList;

    public AdminList(List<Admin> adminList) {
        this.adminList = adminList;
    }

    public List<Admin> getAdminList() {
        return adminList;
    }

    public void setAdminList(List<Admin> adminList) {
        this.adminList = adminList;
    }

    @Override
    public String toString() {
        return "AdminList{" +
                "adminList=" + adminList +
                '}';
    }
}
