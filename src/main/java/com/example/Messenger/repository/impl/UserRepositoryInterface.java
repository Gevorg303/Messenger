package com.example.Messenger.repository.impl;

import com.example.Messenger.domain.User;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface UserRepositoryInterface {
    void save(User user);
    void delete(User user);
    User findUser(String username);
    List<User> getAllUsers();
    EntityManager getEntityManager();
}
