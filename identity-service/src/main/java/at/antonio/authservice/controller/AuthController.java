package at.antonio.authservice.controller;

import at.antonio.authservice.dto.AuthRequest;
import at.antonio.authservice.entity.UserCredentials;
import at.antonio.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired private AuthService authService;

  @Autowired private AuthenticationManager authenticationManager;

  @PostMapping("/register")
  public String registerUser(@RequestBody UserCredentials userCredentials) {
    return authService.createUser(userCredentials);
  }

  @PostMapping("/token")
  public String getJWT(@RequestBody AuthRequest authRequest) {

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(), authRequest.getPassword()));

    if (authentication.isAuthenticated())
      return authService.generateJwt(authRequest.getUsername());

    throw new RuntimeException("Invalid access!");
  }

  @GetMapping("/validate")
  public String validateJWT(@RequestParam("token") String token) {
    authService.validateJwt(token);
    return "JWT is valid";
  }
}
