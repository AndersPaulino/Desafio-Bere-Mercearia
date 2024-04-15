package br.com.uniamerica.beresystem.app.repository;

import br.com.uniamerica.beresystem.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String login);
}
