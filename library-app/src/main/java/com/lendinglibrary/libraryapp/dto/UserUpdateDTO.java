package com.lendinglibrary.libraryapp.dto;

import com.lendinglibrary.libraryapp.models.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserUpdateDTO(

        @Email(message = "Email should be valid")
        @Size(max = 100, message = "Email must be up to 100 characters")
        String email,

        @Size(max = 50, message = "First name must be up to 50 characters")
        String firstName,

        @Size(max = 50, message = "Last name must be up to 50 characters")
        String lastName,

        Role role
) {
}