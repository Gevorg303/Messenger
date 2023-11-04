package com.messenger.Messenger.repository.impl;

import com.messenger.Messenger.domain.User;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface UserRepositoryInterface {
    User save(User user);

    void delete(User user);

    User findUser(String username);

    List<User> getAllUsers();

    EntityManager getEntityManager();
}
