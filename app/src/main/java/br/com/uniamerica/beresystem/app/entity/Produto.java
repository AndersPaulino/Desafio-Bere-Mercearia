package br.com.uniamerica.beresystem.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_produto", schema = "public")
public class Produto extends AbstractEntity{
    @Getter @Setter
    @Column(name = "nomeProduto", nullable = false)
    private String nomeProduto;

    @Getter @Setter
    @Column(name = "descricao")
    private String descricao;
}
