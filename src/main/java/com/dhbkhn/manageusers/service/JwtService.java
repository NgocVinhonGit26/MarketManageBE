package com.dhbkhn.manageusers.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dhbkhn.manageusers.model.User;
import com.dhbkhn.manageusers.repository.TokenRepository;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private final String SECRET_KEY = "4bb6d1dfbafb64a681139d1586b6f1160d18159afd57c8c79136d7490630407c";
    private final TokenRepository tokenRepository;

    public JwtService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Boolean isValid(String token, UserDetails user) {
        String username = extractUsername(token);

        Boolean validToken = tokenRepository
                .findByToken(token)
                .map(t -> !t.isLoggedOut())
                .orElse(false);

        return (username.equals(user.getUsername())) && !isTokenExpired(token) && validToken;
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder() // Sử dụng parserBuilder() thay vì parser()
                .setSigningKey(getSigninKey()) // Thêm khóa ký vào parser
                .build()
                .parseClaimsJws(token)
                .getBody(); // Sử dụng parseClaimsJws(token) thay vì parseSignedClaims(token)
    }

    public String generateToken(User user) {
        String token = Jwts
                .builder()
                .setSubject(user.getUsername()) // Sử dụng setSubject(subject) thay vì subject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis())) // Sử dụng setIssuedAt(date) thay vì issuedAt(date)
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(getSigninKey())
                .compact();

        return token;
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
