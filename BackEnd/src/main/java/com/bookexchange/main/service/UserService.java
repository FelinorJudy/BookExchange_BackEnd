package com.bookexchange.main.service;

import com.bookexchange.main.model.User;
import com.bookexchange.main.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean emailExists(String email) {
        return !userRepository.findAllByEmail(email).isEmpty();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void sendToken(User user) {
        String link = "http://localhost:4100/users/confirm" + user.getToken();
        System.out.println("Link per completare la registrazione: " + link);
    }

    public String confirmUser(String token) {
        User user = userRepository.findByToken(token).orElse(null);
        if (user != null) {
            user.setEnabled(true);
            userRepository.save(user);
            return "Accout creato con successo!";
        }
        else {
            return "Token non valido!";
        }
    }
}
