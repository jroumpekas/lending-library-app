package com.lendinglibrary.libraryapp.dto;

public record JwtResponse(

        String token,
        String type,
        String username

) {

    // Constructor για ευκολία, ώστε να μην περνάμε πάντα το type "Bearer" χειροκίνητα
    public JwtResponse(String token, String username) {
        this(token, "Bearer", username);
    }
}
