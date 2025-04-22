package com.bookexchange.main.controller;

import com.bookexchange.main.model.ApiResponse;
import com.bookexchange.main.model.User;
import com.bookexchange.main.service.LoginRequest;
import com.bookexchange.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/userlogin")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/userlogin")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Trova l'utente per email
        Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmail());

        // Se l'utente esiste nel database
        if (userOpt.isPresent()) {
            User user = userOpt.get();

            // Usa BCrypt per confrontare la password in chiaro con quella criptata nel database
            boolean isPasswordCorrect = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
            System.out.println("Password corretta: " + isPasswordCorrect); // Log di debug

            if (isPasswordCorrect) {
                // La password è corretta, quindi ritorna un oggetto JSON con il messaggio di successo
                return ResponseEntity.ok(new ApiResponse("Accesso effettuato!", true));
            } else {
                // La password non corrisponde
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse("Password errata", false));
            }
        } else {
            // Se l'utente non esiste nel database
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse("Email non trovata", false));
        }
    }
}
