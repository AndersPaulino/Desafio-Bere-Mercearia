package br.com.uniamerica.beresystem.app.dto;

import br.com.uniamerica.beresystem.app.entity.Produto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProdutoDTO {

    private Long id;
    private boolean ativo;
    private LocalDateTime registro;
    private LocalDateTime atualizar;
    private String nomeProduto;
    private String descricao;

    public ProdutoDTO(Produto produto){
        id = produto.getId();
        ativo = produto.isAtivo();
        registro = produto.getRegistro();
        atualizar = produto.getAtualizar();
        nomeProduto = produto.getNomeProduto();
        descricao = produto.getDescricao();
    }

    public ProdutoDTO(Long id, boolean ativo, LocalDateTime registro, LocalDateTime atualizar, String nomeProduto, String descricao){
        this.id = id;
        this.ativo = ativo;
        this.registro = registro;
        this.atualizar = atualizar;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
    }
}
