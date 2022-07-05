package com.yazykov.shop.jwt;

import com.yazykov.shop.jwt.utils.BearerToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;

@Component
public class JwtSupport {

    @Value("${jwt.secret.key}")
    private String key;
    @Value("${jwt.secret.expired}")
    private Long expiredInMillis;

    private SecretKey secret = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    private JwtParser parser = Jwts.parserBuilder().setSigningKey(secret).build();

    public BearerToken generateToken(String username){
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusMillis(expiredInMillis)))
                .signWith(secret);

        return new BearerToken(jwtBuilder.compact());
    }

    public String getUsernameFromToken(BearerToken token){
        return parser.parseClaimsJws(token.getValue()).getBody().getSubject();
    }

    public boolean isValidToken(BearerToken token, UserDetails user){
        Claims claims = parser.parseClaimsJws(token.getValue()).getBody();
        boolean expired = claims.getExpiration().after(Date.from(Instant.now()));

        return expired && (claims.getSubject().equals(user.getUsername()));
    }
}
