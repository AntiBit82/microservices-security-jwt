package at.antonio.authservice.repo;

import at.antonio.authservice.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialsRepo extends JpaRepository<UserCredentials, Integer> {}
