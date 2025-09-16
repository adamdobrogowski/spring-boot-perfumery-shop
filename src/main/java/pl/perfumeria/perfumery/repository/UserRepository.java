package pl.perfumeria.perfumery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.perfumeria.perfumery.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}