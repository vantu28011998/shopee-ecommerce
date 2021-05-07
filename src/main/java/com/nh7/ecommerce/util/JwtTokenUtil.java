package com.nh7.ecommerce.util;

import com.nh7.ecommerce.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import io.jsonwebtoken.Claims;

public class JwtTokenUtil implements Serializable {

//    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    //retrieve expiration date from jwt token
//    public Date getExpirationDateFromToken(String token) {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
//
//
//    //for retrieveing any information from token we will need the secret key
//    private Claims getAllClaimsFromToken(String token) {
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//    }
//
//    //check if the token has expired
//    private Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//
//    //generate token for user
//    public String generateToken(User user) {
//        Map<String, Object> claims = new HashMap<>();
//        String test=doGenerateToken(claims,user.getUsername()+user.getPassword()+user.getEmailAddress());
//        return doGenerateToken(claims,user.getUsername()+user.getPassword()+user.getEmailAddress());
//    }
//
//    private String doGenerateToken(Map<String, Object> claims, String subject) {
//
//        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//                .signWith(SignatureAlgorithm.HS512, secret).compact();
//    }
//
//    //validate token
//    public Boolean validateToken(String token, User user) {
//        final String username = getUsernameFromToken(token);
//        final String password= getPasswordFromToken(token);
//        final String email = getEmailFromToken(token);
//        return (username.equals(user.getUsername()) && password.equals(user.getPassword()) && email.equals(user.getEmailAddress()) && !isTokenExpired(token));
//    }
//
//    //retrieve username from jwt token
//    public String getUsernameFromToken(String token) {
//        return getClaimFromToken(token, Claims::getSubject);
//    }
//
//    private String getPasswordFromToken(String token) {
//        return getClaimFromToken(token,Claims::getSubject);
//    }
//    private String getEmailFromToken(String token) {
//    }
//    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = getAllClaimsFromToken(token);
//        return claimsResolver.apply(claims);
//    }

}
