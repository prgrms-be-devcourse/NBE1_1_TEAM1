package com.programmers.mycoffee.jwt;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    private final Dotenv dotenv = Dotenv.load();
    private final SecretKey secretKey = new SecretKeySpec(dotenv.get("secret").getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());

    public String getUsername(String token) {
        return Jwts.parser().
                verifyWith(secretKey).
                build().
                parseSignedClaims(token).
                getPayload().
                get("username", String.class);

    }

    public String getRole(String token) {
        return Jwts.parser().
                verifyWith(secretKey).
                build().
                parseSignedClaims(token).
                getPayload().
                get("role", String.class);
    }
    public Boolean isExpired(String token) {

        return Jwts.parser().
                verifyWith(secretKey).
                build().
                parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }
    public String createJwt(String username, String role, Long expiredMs) {

        return Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs*1000))
                .signWith(secretKey)
                .compact();
    }

}
