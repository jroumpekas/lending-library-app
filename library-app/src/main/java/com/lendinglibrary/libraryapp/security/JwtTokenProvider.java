package com.lendinglibrary.libraryapp.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // Ένα κρυφό κλειδί για την υπογραφή του token (πρέπει να είναι αρκετά μεγάλο σε μέγεθος)
    private final String jwtSecret = "myVeryLongAndSuperSecureSecretKeyForLendingLibraryAppThatHasMoreThan64Characters1234567890!@#$";

    // Η διάρκεια ισχύος του token (π.χ. 24 ώρες σε χιλιοστά του δευτερολέπτου)
    private final long jwtExpirationMs = 86400000;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    // 1. Παραγωγή Token
    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    // 2. Εξαγωγή Username από το Token
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // 3. Επικύρωση Token
    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            // Εδώ μπορούμε να πιάσουμε διάφορα σφάλματα (λήξη token, λάθος υπογραφή κλπ.)
            System.err.println("Invalid JWT token: " + ex.getMessage());
        }
        return false;
    }
}