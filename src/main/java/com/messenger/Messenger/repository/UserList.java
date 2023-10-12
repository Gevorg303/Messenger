package com.messenger.Messenger.repository;

import com.messenger.Messenger.domain.Admin;
import com.messenger.Messenger.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Repository
public class UserList {
    private List<User> users= Arrays.asList(new User("Vlad"), new User("Roma"), new User("Oleg"), new User("Oly"), new User("Lili"));

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserList{" +
                "users=" + users +
                '}';
    }
}

