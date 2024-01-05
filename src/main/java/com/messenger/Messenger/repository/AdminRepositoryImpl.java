package com.messenger.Messenger.repository;

import com.messenger.Messenger.domain.Admin;
import com.messenger.Messenger.repository.impl.AdminRepositoryInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRepositoryImpl implements AdminRepositoryInterface {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Admin save(Admin admin) {
        entityManager.persist(admin);
        return admin;
    }

    @Override
    public void delete(Admin admin) {
        entityManager.remove(admin);
    }

    @Override
    public Admin findAdmin(String adminName) {
        return entityManager.createQuery("SELECT a FROM Admin a WHERE a.nameUser = :adminName", Admin.class)
                .setParameter("adminName", adminName)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return entityManager.createQuery("SELECT a FROM Admin a", Admin.class).getResultList();
    }
}
