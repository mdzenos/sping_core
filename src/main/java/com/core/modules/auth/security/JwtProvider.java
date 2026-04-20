package com.core.modules.auth.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private final String SECRET = "secret-key";

    private final long AT_EXP = 1000 * 60 * 15;
    private final long RT_EXP = 1000L * 60 * 60 * 24 * 7;

    public String generateAT(String userId) {
        return build(userId, AT_EXP);
    }

    public String generateRT(String userId) {
        return build(userId, RT_EXP);
    }

    private String build(String userId, long exp) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String getUserId(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
