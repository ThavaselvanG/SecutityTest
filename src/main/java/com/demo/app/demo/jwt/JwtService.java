package com.demo.app.demo.jwt;

import com.demo.app.demo.apis.accounts.UserRepo;
import com.demo.app.demo.apis.accounts.UserInfo;
import com.demo.app.demo.exceptionhandler.ResourceNotFound;
import com.demo.app.demo.model.JwtResponse;
import com.demo.app.demo.model.RefreshToken;
import com.demo.app.demo.repository.RefreshTokenRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {


    @Autowired
    private RefreshTokenRepo refreshTokenRepo;
    @Autowired
    private UserRepo accountRepo;

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean createRefreshToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 2))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public JwtResponse getTokenResponse(RefreshToken refreshToken) {
        return JwtResponse.builder()
                .sessionId(refreshToken.getSessionsId())
                .accessToken(generateToken(refreshToken.getUserInfo().getUserName()))
                .build();

    }

    public RefreshToken createToken(String userName) {
        UserInfo info = accountRepo.findByUserName(userName).orElseThrow(() -> new ResourceNotFound("User Not Found"));
        Optional<RefreshToken> refreshToken = refreshTokenRepo.findByUserInfoUserId(info.getUserId());
        //check device count
        if (refreshToken.isPresent()) {
            refreshToken.get().setExpiryDate(Instant.now().plusMillis(600000));
            return refreshTokenRepo.save(refreshToken.get());
        } else {
            RefreshToken refreshToken1 = RefreshToken.builder()
                    .userInfo(info)
                    .sessionsId(UUID.randomUUID().toString())
                    .expiryDate(Instant.now().plusMillis(600000))//10
                    .build();
            return refreshTokenRepo.save(refreshToken1);
        }
    }

    public RefreshToken createRefreshToken(String sessionId) {
        return refreshTokenRepo.findByTokenId(sessionId)
                .map(this::verifyExpiration)
                .orElseThrow(() -> new ResourceNotFound("Refresh token is not available in database"));
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepo.delete(token);
            throw new RuntimeException(token.getSessionsId() + " Refresh token was expired. Please make a new signin request");
        }
        return token;
    }
}
