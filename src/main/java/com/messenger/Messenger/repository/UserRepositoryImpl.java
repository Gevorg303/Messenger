package com.messenger.Messenger.repository;

import com.messenger.Messenger.domain.User;
import com.messenger.Messenger.repository.impl.UserRepositoryInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserRepositoryImpl implements UserRepositoryInterface {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {

        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            user = entityManager.merge(user);
        }
        return user;
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    public User findUser(String username) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.nameUser = :username", User.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
