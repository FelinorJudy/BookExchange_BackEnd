package com.bookexchange.main.controller;

import com.bookexchange.main.model.User;
import com.bookexchange.main.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userService.emailExists(user.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("error", "Email già in uso!"));
        }
        else {
            userService.saveUser(user);
            userService.sendToken(user);
            return ResponseEntity.status(201).body(Collections.singletonMap("message", "Utente creato, clicca sul link per confermare la tua mail: " + "http://localhost:4100/users/confirm?token=" + user.getToken()));
        }
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmUser(@RequestParam("token") String token) {
        String result = userService.confirmUser(token);
        return ResponseEntity.ok(result);
    }
}
