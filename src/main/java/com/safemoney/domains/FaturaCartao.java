package com.safemoney.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.safemoney.domains.enums.StatusFatura;
import com.safemoney.infra.StatusFaturaConverter;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "fatura_cartao")
public class FaturaCartao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartao_id", nullable = false)
    private CartaoCredito cartao;

    @Column(nullable = false, length = 7)
    private String competencia;

    @Column(name = "data_fechamento")
    private LocalDate dataFechamento;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "valor_total", precision = 18, scale = 2)
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @Column(nullable = false)
    @Convert(converter = StatusFaturaConverter.class)
    private StatusFatura status = StatusFatura.ABERTA;

    public FaturaCartao() {}


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public CartaoCredito getCartao() { return cartao; }
    public void setCartao(CartaoCredito cartao) { this.cartao = cartao; }
    public String getCompetencia() { return competencia; }
    public void setCompetencia(String competencia) { this.competencia = competencia; }
    public LocalDate getDataFechamento() { return dataFechamento; }
    public void setDataFechamento(LocalDate dataFechamento) { this.dataFechamento = dataFechamento; }
    public LocalDate getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
    public StatusFatura getStatus() { return status; }
    public void setStatus(StatusFatura status) { this.status = status; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FaturaCartao that = (FaturaCartao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}