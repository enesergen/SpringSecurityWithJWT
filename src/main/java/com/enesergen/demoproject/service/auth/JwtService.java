package com.enesergen.demoproject.service.auth;

import com.enesergen.demoproject.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e";

    public String generateToken(Map<String, String> extraClaims, User user) throws Exception {
        if (user != null) {
            return
                    Jwts.builder()
                            .setClaims(extraClaims)
                            .setSubject(user.getUsername())
                            .setIssuedAt(new Date(System.currentTimeMillis()))
                            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                            .signWith(getSignKey(), SignatureAlgorithm.HS512)
                            .compact();
        } else
            throw new Exception("UserDetails is null");

    }

    public String generateToken(com.enesergen.demoproject.model.User user) throws Exception {
        if (user != null) {
            var map = new HashMap<String, String>();
            map.put("username",user.getUsername());
            map.put("email",user.getEmail());
            map.put("role",user.getRole());
            return generateToken(map, user);
        } else
            throw new Exception("UserDetails is null");
    }

    public Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, User user)  {

            final String username = extractUsername(token);
            return username.equals(user.getUsername()) && !isTokenExpired(token);

    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
