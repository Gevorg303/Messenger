package com.example.messenger.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Admin")
public class Admin extends User {
    public Admin(String nameUser) {
        super(nameUser);
    }

    public Admin() {

    }
}
