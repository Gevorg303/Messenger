package com.example.messenger.repository.api;

import com.example.messenger.domain.User;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepositoryInterface extends JpaRepository<User, Long> {
    Page<User> findAll(Pageable pageable);
}
