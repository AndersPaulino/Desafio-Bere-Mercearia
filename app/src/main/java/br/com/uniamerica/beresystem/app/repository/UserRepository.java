package br.com.uniamerica.beresystem.app.repository;

import br.com.uniamerica.beresystem.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
