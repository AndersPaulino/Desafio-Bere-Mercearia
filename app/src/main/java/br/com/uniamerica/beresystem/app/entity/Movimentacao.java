package br.com.uniamerica.beresystem.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_movimentacao", schema = "public")
public class Movimentacao extends AbstractEntity{
    @Getter @Setter
    @OneToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Getter @Setter
    @Column(name = "entrada")
    private int entrada;

    @Getter @Setter
    @Column(name = "saida")
    private int saida;

    @Getter
    @Column(name = "totalProduto")
    private int totalProduto;

    @Getter @Setter
    @Column(name = "valorCompra")
    private double valorCompra;

    @Getter @Setter
    @Column(name = "valorVenda")
    private double valorVenda;

    @Getter
    @Column(name = "lucro")
    private double valorTotal;

    public void setTotalProduto(int totalProduto) {
        this.totalProduto = entrada - saida;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorVenda - valorCompra;
    }
}
