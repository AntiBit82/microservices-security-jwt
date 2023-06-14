package at.antonio.authservice.service;

import at.antonio.authservice.repo.UserCredentialsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserCredentialsRepo userCredentialsRepo;
}
