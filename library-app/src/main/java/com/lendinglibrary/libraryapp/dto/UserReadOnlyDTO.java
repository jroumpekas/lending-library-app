package com.lendinglibrary.libraryapp.dto;

import com.lendinglibrary.libraryapp.models.enums.Role;
import lombok.Builder;

import java.util.UUID;

@Builder
public record UserReadOnlyDTO(
        UUID id,
        String username,
        String email,
        String firstName,
        String lastName,
        Role role
) {
}