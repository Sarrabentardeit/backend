package com.example.crud_parc.controller;

import com.example.crud_parc.model.ParkUser;
import com.example.crud_parc.service.ParkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parkusers")
public class ParkUserController {
    @Autowired
    private ParkUserService parkUserService;

    // Récupère tous les utilisateurs
    @GetMapping("/")
    public List<ParkUser> getAllUsers() {
        return parkUserService.findAllUsers();
    }

    // Récupère un utilisateur spécifique par ID
    @GetMapping("/{id}")
    public ParkUser getUserById(@PathVariable Long id) {
        return parkUserService.findUserById(id);
    }

    // Crée ou met à jour un utilisateur
    @PostMapping("/")
    public ParkUser createUser(@RequestBody ParkUser user) {
        return parkUserService.saveUser(user);
    }

    // Supprime un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        parkUserService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
