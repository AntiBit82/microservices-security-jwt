package at.antonio.swiggygateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.stereotype.Service;


public class JwtUtil {

  // random 256 bit HEX String
  private final static String SECRET = "4eb17794b67c94e5b26807088e5f152f2c7e23a96e9fbf3014379c842cd6e818";

  public static void validateToken(final String token) {
    Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(token);
  }

  private static Key generateKey() {
    byte[] byteKey = Decoders.BASE64.decode(SECRET);
    return Keys.hmacShaKeyFor(byteKey);
  }
}
