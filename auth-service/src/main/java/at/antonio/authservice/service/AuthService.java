package at.antonio.authservice.service;

import at.antonio.authservice.entity.UserCredentials;
import at.antonio.authservice.repo.UserCredentialsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserCredentialsRepo userCredentialsRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String createUser(UserCredentials user) {
        String encPwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(encPwd);
        userCredentialsRepo.save(user);
        return "User " + user.getUsername() + " created";
    }

    public String generateJwt(String username) {
        return jwtService.generateToken(username);
    }

    public void validateJwt(String jwt) {
        jwtService.validateToken(jwt);
    }
}
