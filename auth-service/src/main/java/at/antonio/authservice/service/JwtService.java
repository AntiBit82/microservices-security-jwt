package at.antonio.authservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

  // random 256 bit HEX String
  private final String SECRET = "4eb17794b67c94e5b26807088e5f152f2c7e23a96e9fbf3014379c842cd6e818";

  public String generateToken(String username) {
    HashMap<String, Object> claims = new HashMap<>();
    return createToken(claims, username);
  }

  public void validateToken(final String token) {
    Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(token);
  }

  private String createToken(Map<String, Object> claims, String username) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(username)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 60))
        .signWith(generateKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  private Key generateKey() {
    byte[] byteKey = Decoders.BASE64.decode(SECRET);
    return Keys.hmacShaKeyFor(byteKey);
  }
}
