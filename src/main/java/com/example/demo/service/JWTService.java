package com.example.demo.service;

import com.example.demo.entity.UserAuthDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Service
public class JWTService {
    private static String jwtSecretKey = "ddgdbydjsmsjjsmhdgdndjsksjbdddjdkddk";


    private SecretKey generateKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }
    public String createToken(UserAuthDetails userAuthDetails){
        return Jwts.builder()
                .subject(userAuthDetails.getUsername())
                .claim("email",userAuthDetails.getUsername())
                .claim("role", Set.of(userAuthDetails.getRole()))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60))
                .signWith(generateKey())
                .compact();
    }

    public String generateUserFromToken(String token){
         Claims claims = Jwts.parser().
                 verifyWith(generateKey())
                 .build()
                 .parseSignedClaims(token)
                 .getPayload();
        System.out.println(claims.get("email"));
         return claims.getSubject();
    }
}
