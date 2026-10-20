package com.lendinglibrary.libraryapp.controller;

import com.lendinglibrary.libraryapp.dto.UserInsertDTO;
import com.lendinglibrary.libraryapp.dto.UserReadOnlyDTO;
import com.lendinglibrary.libraryapp.dto.UserUpdateDTO;
import com.lendinglibrary.libraryapp.models.enums.Role;
import com.lendinglibrary.libraryapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserReadOnlyDTO>> getAllUsers() {
        List<UserReadOnlyDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserReadOnlyDTO> getUserById(@PathVariable UUID id) {
        UserReadOnlyDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserReadOnlyDTO> getUserByUsername(@PathVariable String username) {
        UserReadOnlyDTO user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserReadOnlyDTO>> getUsersByRole(@PathVariable Role role) {
        List<UserReadOnlyDTO> users = userService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    public ResponseEntity<UserReadOnlyDTO> registerUser(@Valid @RequestBody UserInsertDTO dto) {
        UserReadOnlyDTO createdUser = userService.registerUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserReadOnlyDTO> updateUser(@PathVariable UUID id, @Valid @RequestBody UserUpdateDTO dto) {
        UserReadOnlyDTO updatedUser = userService.updateUser(id, dto);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> changePassword(
            @PathVariable UUID id,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {
        userService.changePassword(id, oldPassword, newPassword);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}