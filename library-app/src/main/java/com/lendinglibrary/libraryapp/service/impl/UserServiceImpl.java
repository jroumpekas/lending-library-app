package com.lendinglibrary.libraryapp.service.impl;

import com.lendinglibrary.libraryapp.dto.UserInsertDTO;
import com.lendinglibrary.libraryapp.dto.UserReadOnlyDTO;
import com.lendinglibrary.libraryapp.dto.UserUpdateDTO;
import com.lendinglibrary.libraryapp.mapper.UserMapper;
import com.lendinglibrary.libraryapp.models.User;
import com.lendinglibrary.libraryapp.models.enums.Role;
import com.lendinglibrary.libraryapp.repository.UserRepository;
import com.lendinglibrary.libraryapp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<UserReadOnlyDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toReadOnlyDTO) // Διορθώθηκε σε toReadOnlyDTO
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserReadOnlyDTO getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " was not found"));

        return userMapper.toReadOnlyDTO(user); // Διορθώθηκε σε toReadOnlyDTO
    }

    @Override
    @Transactional(readOnly = true)
    public UserReadOnlyDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(
                        "User with username " + username + " was not found"
                ));

        return userMapper.toReadOnlyDTO(user); // Διορθώθηκε σε toReadOnlyDTO
    }

    @Override
    public UserReadOnlyDTO registerUser(UserInsertDTO dto) {
        if (userRepository.existsByUsername(dto.username())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (userRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Δημιουργία entity μέσω του mapper και κρυπτογράφηση password
        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));

        User savedUser = userRepository.save(user);

        return userMapper.toReadOnlyDTO(savedUser); // Διορθώθηκε σε toReadOnlyDTO
    }

    @Override
    public UserReadOnlyDTO updateUser(UUID id, UserUpdateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " was not found"));

        // Έλεγχος αν το νέο email υπάρχει σε άλλον χρήστη
        if (dto.email() != null && userRepository.existsByEmailAndIdNot(dto.email(), id)) {
            throw new IllegalArgumentException("Email is already in use by another user");
        }

        userMapper.updateEntityFromDTO(dto, user);
        User updatedUser = userRepository.save(user);

        return userMapper.toReadOnlyDTO(updatedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserReadOnlyDTO> getUsersByRole(Role role) {
        // Το findByRole επιστρέφει List<User>, οπότε κάνουμε stream map
        return userRepository.findByRole(role)
                .stream()
                .map(userMapper::toReadOnlyDTO)
                .toList();
    }

    @Override
    public void changePassword(UUID id, String oldPassword, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " was not found"));

        // Επαλήθευση ότι ο παλιός κωδικός είναι σωστός
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Incorrect old password");
        }

        // Κρυπτογράφηση και αποθήκευση του νέου κωδικού
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User with id " + id + " was not found");
        }

        userRepository.deleteById(id);
    }
}