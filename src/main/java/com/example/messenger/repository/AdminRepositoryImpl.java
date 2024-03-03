package com.example.messenger.repository;

import com.example.messenger.repository.api.AdminRepositoryInterface;
import com.example.messenger.domain.Admin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRepositoryImpl implements AdminRepositoryInterface {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Admin save(Admin admin) {
        entityManager.merge(admin);
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
    @Override
    public Page<Admin> getAllAdmins(Pageable pageable) {
        TypedQuery<Admin> query = entityManager.createQuery("SELECT a FROM Admin a", Admin.class);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<Admin> admins = query.getResultList();
        long total = getCountOfAdmins();
        return new PageImpl<>(admins, pageable, total);
    }

    private long getCountOfAdmins() {
        return entityManager.createQuery("SELECT COUNT(a) FROM Admin a", Long.class).getSingleResult();
    }
}
