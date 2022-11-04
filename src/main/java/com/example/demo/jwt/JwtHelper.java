package com.example.demo.jwt;

import org.springframework.beans.factory.annotation.Value;

public class JwtHelper {
    static final String issuer = "KKTC";

    @Value("")
    private int accessTokenExpirationMs;
    @Value("")
    private int refreshTokenExpirationMs;
}
