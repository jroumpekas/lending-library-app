package com.lendinglibrary.libraryapp.service;

import com.lendinglibrary.libraryapp.dto.UserInsertDTO;
import com.lendinglibrary.libraryapp.dto.UserReadOnlyDTO;
import com.lendinglibrary.libraryapp.dto.UserUpdateDTO;
import com.lendinglibrary.libraryapp.models.enums.Role;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserReadOnlyDTO> getAllUsers();

    UserReadOnlyDTO getUserById(UUID id);

    UserReadOnlyDTO getUserByUsername(String username);

    UserReadOnlyDTO registerUser(UserInsertDTO dto);

    // Νέες προτεινόμενες μέθοδοι:
    UserReadOnlyDTO updateUser(UUID id, UserUpdateDTO dto);

    List<UserReadOnlyDTO> getUsersByRole(Role role);

    void changePassword(UUID id, String oldPassword, String newPassword);

    void deleteUser(UUID id);
}