package com.core.modules.auth.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtProvider {

    private final String SECRET = "change-this-secret-key-change-this-secret-key";

    private final SecretKey key =
            Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateAT(String userId) {
        return build(userId, 1000 * 60 * 15);
    }

    public String generateRT(String userId) {
        return build(userId, 1000L * 60 * 60 * 24 * 7);
    }

    private String build(String userId, long exp) {
        return Jwts.builder()
                .subject(userId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + exp))
                .signWith(key)
                .compact();
    }

    public String getUserId(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
