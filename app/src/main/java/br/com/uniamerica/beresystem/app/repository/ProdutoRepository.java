package br.com.uniamerica.beresystem.app.repository;

import br.com.uniamerica.beresystem.app.dto.ProdutoDTO;
import br.com.uniamerica.beresystem.app.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
