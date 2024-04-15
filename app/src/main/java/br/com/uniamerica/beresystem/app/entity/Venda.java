package br.com.uniamerica.beresystem.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_venda", schema = "public")
public class Venda extends AbstractEntity{
    @Getter @Setter
    @ManyToMany
    @JoinTable(name = "vendamovimentacao",
        joinColumns = @JoinColumn(name = "venda_id"),
        inverseJoinColumns = @JoinColumn(name = "movimentacao_id")
    )
    private List<Movimentacao> movimentacaoList = new ArrayList<>();

    @Getter
    private double totalVenda;
    @Getter
    private int totalProdutos;

    public void setTotalVenda(double totalVenda) {
        this.totalVenda = totalVenda;
    }

    public void setTotalProdutos(int totalProdutos) {
        this.totalProdutos = totalProdutos;
    }
}
