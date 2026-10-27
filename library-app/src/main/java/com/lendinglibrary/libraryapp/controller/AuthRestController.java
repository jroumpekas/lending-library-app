package com.lendinglibrary.libraryapp.controller;


import com.lendinglibrary.libraryapp.dto.JwtResponse;
import com.lendinglibrary.libraryapp.dto.LoginRequestDTO;
import com.lendinglibrary.libraryapp.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDTO loginRequestDTO) {

        // 1. Έλεγχος credentials μέσω του AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.username(),
                        loginRequestDTO.password()
                )
        );

        // 2. Θέτουμε το authentication στο SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. Παράγουμε το JWT token
        String jwt = jwtTokenProvider.generateToken(authentication);

        // 4. Επιστρέφουμε το token πίσω στον πελάτη
        return ResponseEntity.ok(new JwtResponse(jwt, loginRequestDTO.username()));
    }

}