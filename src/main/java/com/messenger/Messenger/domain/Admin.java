package com.messenger.Messenger.domain;

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
