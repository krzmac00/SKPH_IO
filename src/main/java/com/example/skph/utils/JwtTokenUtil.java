package com.example.skph.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
@Component
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int expiration_time;

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration_time))
                .signWith(key())
                .compact();
    }

    public String extractUsername(String token) {
        try {
            // Parsowanie i weryfikacja tokenu
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key())  // Ustawienie klucza weryfikacyjnego
                    .build()
                    .parseClaimsJws(token);  // Parsowanie i weryfikacja tokenu

            // Wyciąganie subject (nazwa użytkownika)
            return claimsJws.getBody().getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            // Obsługa błędów, np. token jest nieprawidłowy
            throw new IllegalArgumentException("Token is invalid or expired", e);
        }
    }
    public String extractRole(String token) {
        try {
            // Parsowanie i weryfikacja tokenu z użyciem parserBuilder
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key())  // Ustawienie klucza weryfikacyjnego
                    .build()
                    .parseClaimsJws(token);  // Parsowanie i weryfikacja tokenu

            // Wyciąganie roli z tokenu
            return claimsJws.getBody().get("role", String.class);
        } catch (JwtException | IllegalArgumentException e) {
            // Obsługa błędów, np. token jest nieprawidłowy
            throw new IllegalArgumentException("Token is invalid or expired", e);
        }
    }
    public boolean validateToken(String token) {
        try {
            // Parsowanie i weryfikacja tokenu z użyciem parserBuilder
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key())  // Ustawienie klucza weryfikacyjnego
                    .build()
                    .parseClaimsJws(token);  // Parsowanie i weryfikacja tokenu

            // Jeśli nie wystąpił wyjątek, oznacza to, że token jest ważny
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Jeśli wystąpił wyjątek, token jest nieważny
            return false;
        }
    }
}
