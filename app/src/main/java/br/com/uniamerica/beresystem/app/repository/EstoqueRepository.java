package br.com.uniamerica.beresystem.app.repository;

import br.com.uniamerica.beresystem.app.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}
