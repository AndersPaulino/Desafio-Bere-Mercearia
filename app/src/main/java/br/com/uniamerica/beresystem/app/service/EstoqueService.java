package br.com.uniamerica.beresystem.app.service;

import br.com.uniamerica.beresystem.app.dto.EstoqueDTO;
import br.com.uniamerica.beresystem.app.entity.Estoque;
import br.com.uniamerica.beresystem.app.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {
    private EstoqueRepository estoqueRepository;

    @Autowired
    public EstoqueService(EstoqueRepository estoqueRepository){
        this.estoqueRepository = estoqueRepository;
    }
    @Transactional(readOnly = true)
    public Optional<EstoqueDTO> findById(Long id) {
        return estoqueRepository.findById(id).map(EstoqueDTO::new);
    }


    @Transactional(readOnly = true)
    public List<EstoqueDTO> findAll() {
        List<Estoque> estoques = estoqueRepository.findAll();
        return estoques.stream().map(EstoqueDTO::new).toList();
    }
    public void validarEstoque(final Estoque estoque) {
        String nomeEstoque = estoque.getNomeEstoque();

        if (nomeEstoque == null || nomeEstoque.isEmpty()) {
            throw new IllegalArgumentException("Nome do Estoque não informado");
        }

        if (!nomeEstoque.matches("[a-zA-Z0-9 ]+")) {
            throw new IllegalArgumentException("Nome do Estoque inválido");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(Estoque estoque) {
        validarEstoque(estoque);
        estoqueRepository.save(estoque);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void atualizar(Long id, Estoque estoque) {
        validarEstoque(estoque);
        Optional<Estoque> estoqueExistenteOptional = estoqueRepository.findById(id);

        if (estoqueExistenteOptional.isPresent()) {
            Estoque estoqueExistente = estoqueExistenteOptional.get();
            estoqueExistente.setNomeEstoque(estoque.getNomeEstoque());

            // Atualizar as movimentações do estoque
            if (estoque.getMovimentacaoList() != null && !estoque.getMovimentacaoList().isEmpty()) {
                // Limpar as movimentações existentes
                estoqueExistente.getMovimentacaoList().clear();

                // Adicionar as novas movimentações
                estoqueExistente.getMovimentacaoList().addAll(estoque.getMovimentacaoList());
            }
            estoqueExistente.setAtualizar(LocalDateTime.now());
            estoqueRepository.save(estoqueExistente); // Salvar o estoque atualizado
        } else {
            throw new IllegalArgumentException("ID de estoque inválido!");
        }
    }




    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void desativar(Long id) {
        Optional<Estoque> estoqueExistenteOptional = estoqueRepository.findById(id);

        if (estoqueExistenteOptional.isPresent()) {
            Estoque estoqueExistente = estoqueExistenteOptional.get();
            estoqueExistente.setAtivo(false);
        } else {
            throw new IllegalArgumentException("ID de estoque inválido!");
        }
    }

}
