package br.com.uniamerica.beresystem.app.service;

import br.com.uniamerica.beresystem.app.dto.MovimentacaoDTO;
import br.com.uniamerica.beresystem.app.entity.Movimentacao;
import br.com.uniamerica.beresystem.app.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovimentacaoService {
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository){
        this.movimentacaoRepository = movimentacaoRepository;
    }

    @Transactional(readOnly = true)
    public Optional<MovimentacaoDTO> findById(Long id) {
        return movimentacaoRepository.findById(id).map(movimentacao -> {
            MovimentacaoDTO movimentacaoDTO = new MovimentacaoDTO(movimentacao);
            // Você pode configurar aqui a carga do produto associado, se necessário.
            // movimentacaoDTO.setProduto(new ProdutoDTO(movimentacao.getProduto()));
            return movimentacaoDTO;
        });
    }

    @Transactional(readOnly = true)
    public List<MovimentacaoDTO> findAll() {
        List<Movimentacao> movimentacoes = movimentacaoRepository.findAll();
        return movimentacoes.stream().map(MovimentacaoDTO::new).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(Movimentacao movimentacao) {
        double valorVenda = movimentacao.getValorVenda();
        double valorCompra = movimentacao.getValorCompra();
        double valorTotal = movimentacao.getValorTotal();
        int totalProduto = movimentacao.getTotalProduto();

        movimentacaoRepository.save(movimentacao);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void atualizar(Long id, Movimentacao movimentacao) {
        Optional<Movimentacao> movimentacaoExistenteOptional = movimentacaoRepository.findById(id);

        if (movimentacaoExistenteOptional.isPresent()) {
            Movimentacao movimentacaoExistente = movimentacaoExistenteOptional.get();
            if (movimentacao.getEntrada() != movimentacao.getEntrada()){
                movimentacaoExistente.setEntrada(movimentacaoExistente.getEntrada() + movimentacao.getEntrada());
            }
            if (movimentacao.getEntrada() == movimentacao.getEntrada()){
                movimentacaoExistente.setEntrada(movimentacaoExistente.getEntrada());
            }
            if(movimentacao.getSaida() != movimentacao.getSaida()){
                movimentacaoExistente.setSaida(movimentacao.getSaida());
            }
            if(movimentacao.getSaida() == movimentacao.getSaida()){
                movimentacaoExistente.setSaida(movimentacao.getSaida());
            }
            if(movimentacao.getValorCompra() != movimentacaoExistente.getValorCompra()){
                movimentacaoExistente.setValorCompra(movimentacao.getValorCompra());
            }
            if(movimentacao.getValorVenda() != movimentacaoExistente.getValorVenda()){
                movimentacaoExistente.setValorVenda(movimentacao.getValorVenda());
            }
            double valorVenda = movimentacao.getValorVenda();
            double valorCompra = movimentacao.getValorCompra();
            double valorTotal = movimentacao.getValorTotal();
            int totalProduto = movimentacao.getTotalProduto();

            movimentacaoRepository.save(movimentacaoExistente);
        } else {
            throw new IllegalArgumentException("ID Inválido!");
        }
    }



    public void validarMovimentacao(final Movimentacao movimentacao){
        int entrada = movimentacao.getEntrada();
        int saida = movimentacao.getSaida();

        if (entrada == 0){
            throw new IllegalArgumentException("Nome De Produto Não Preenchido");
        }
        if (entrada < saida){
            throw new IllegalArgumentException("Erro! Saida maior do que os produtos em estoque!");
        }

    }
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void deletar(Long id){
        Optional<Movimentacao> movimentacaoExistenteOptional = movimentacaoRepository.findById(id);

        if (movimentacaoExistenteOptional.isPresent()){
            Movimentacao movimentacaoExistente = movimentacaoExistenteOptional.get();
            movimentacaoExistente.setAtivo(false);
        } else {
            throw new IllegalArgumentException("ID Invalido");
        }
    }
}
