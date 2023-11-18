package com.messenger.Messenger.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Admin")
public class Admin extends User {
    public Admin(String nameUser) {
        super(nameUser);
    }

    public Admin() {

    }
}
