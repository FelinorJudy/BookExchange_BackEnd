package com.bookexchange.main.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Setter
@Getter
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Boolean enabled = false;
    private String token;

    public User() {
        this.enabled = false;
        this.token = UUID.randomUUID().toString();
    }

    public User(String id, String name, String surname, String email, String password) {
        this.id = id;
        setName(name);
        setSurname(surname);
        setEmail(email);
        setPassword(password);
        this.enabled = false;
        this.token = UUID.randomUUID().toString();
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Devi inserire un name");
        }
    }

    public void setSurname(String surname) {
        if (surname != null) {
            this.surname = surname;
        } else {
            throw new IllegalArgumentException("Devi inserire un surname");
        }
    }

    public void setEmail(String email) {
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email non valida");
        }
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }

    public void setPassword(String password) {
        if (password != null) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password mancante");
        }
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
