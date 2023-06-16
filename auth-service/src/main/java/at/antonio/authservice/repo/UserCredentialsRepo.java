package at.antonio.authservice.repo;

import at.antonio.authservice.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialsRepo extends JpaRepository<UserCredentials, Integer> {
    Optional<UserCredentials> findByUsername(String username);
}
