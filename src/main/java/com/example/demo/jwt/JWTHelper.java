package com.example.demo.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.models.Role;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.sql.Date;
import java.util.stream.Collectors;

@Log4j2
public class JWTHelper {
    static final String issuer = "KKTC";
    private static final Algorithm algorithm = Algorithm.HMAC256("12345".getBytes());

    public static String generateAccessToken(User user) {
        int accessTokenExpirationMinutes = 5;
        int accessTokenExpirationMs = accessTokenExpirationMinutes * 60 * 1000;
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenExpirationMs))
                .withIssuer(issuer)
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

    public static String generateRefreshToken(User user) {
        int refreshTokenExpirationMinutes = 30;
        int refreshTokenExpirationMs = refreshTokenExpirationMinutes * 60 * 1000;
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenExpirationMs))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public static String generateNewAccessToken(com.example.demo.models.User user) {
        int accessTokenExpirationMinutes = 5;
        int accessTokenExpirationMs = accessTokenExpirationMinutes * 60 * 1000;
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenExpirationMs))
                .withIssuer(issuer)
                .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .sign(algorithm);
    }

    public static DecodedJWT validateToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            return verifier.verify(token);
        } catch (JWTVerificationException e){
            log.error(e.getMessage());
            throw new JWTVerificationException(e.getMessage());
        }
    }
}
