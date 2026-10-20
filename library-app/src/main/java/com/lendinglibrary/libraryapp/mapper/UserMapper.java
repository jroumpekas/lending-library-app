package com.lendinglibrary.libraryapp.mapper;

import com.lendinglibrary.libraryapp.dto.UserInsertDTO;
import com.lendinglibrary.libraryapp.dto.UserReadOnlyDTO;
import com.lendinglibrary.libraryapp.dto.UserUpdateDTO;
import com.lendinglibrary.libraryapp.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // Μετατροπή από User Entity σε UserReadOnlyDTO
    public UserReadOnlyDTO toReadOnlyDTO(User user) {
        if (user == null) {
            return null;
        }
        return UserReadOnlyDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .build();

    }

    // Μετατροπή từ UserInsertDTO σε User Entity (για εγγραφή)
    // Σημείωση: Το password καλό είναι να κρυπτογραφείται (encoded) στο Service πριν αποθηκευτεί!
    public User toEntity(UserInsertDTO insertDTO) {
        if (insertDTO == null) {
            return null;
        }
        return User.builder()
                .username(insertDTO.username())
                .email(insertDTO.email())
                .password(insertDTO.password()) // Θα το κρυπτογραφήσουμε με PasswordEncoder στο Service
                .firstName(insertDTO.firstName())
                .lastName(insertDTO.lastName())
                .role(insertDTO.role())
                .build();
    }

    // Ενημέρωση υφιστάμενου User Entity από UserUpdateDTO (Partial update)
    public void updateEntityFromDTO(UserUpdateDTO updateDTO, User user) {
        if (updateDTO == null || user == null) {
            return;
        }
        if (updateDTO.email() != null) {
            user.setEmail(updateDTO.email());
        }
        if (updateDTO.firstName() != null) {
            user.setFirstName(updateDTO.firstName());
        }
        if (updateDTO.lastName() != null) {
            user.setLastName(updateDTO.lastName());
        }
        if (updateDTO.role() != null) {
            user.setRole(updateDTO.role());
        }
    }
}