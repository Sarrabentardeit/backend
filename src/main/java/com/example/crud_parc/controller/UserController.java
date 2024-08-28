package com.example.crud_parc.controller;

import com.example.crud_parc.model.User;
import com.example.crud_parc.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User newUser) {
        try {
            User registeredUser = userService.registerUser(newUser);
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de l'inscription : " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
        try {
            Optional<User> authenticatedUser = userService.authenticate(email, password);
            if (authenticatedUser.isPresent()) {
                return ResponseEntity.ok("Utilisateur authentifié avec succès !");
            } else {
                return ResponseEntity.badRequest().body("Email ou mot de passe incorrect.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de l'authentification : " + e.getMessage());
        }
    }
}
