package com.messenger.Messenger.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class UserList {
    private List<User> users;/*Хранилище пользователей*/
    private List<Admin> adminList;/*Хранилище админов*/

    public UserList() {
        this.users = new ArrayList<>();
        this.adminList = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Admin> getAdminList() {
        return adminList;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setAdminList(List<Admin> adminList) {
        this.adminList = adminList;
    }
}
