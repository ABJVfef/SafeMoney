package com.safemoney.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "pagamento")
public class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lancamento_id", nullable = false)
    private Lancamento lancamento;

    @Column(name = "data_pagamento", nullable = false)
    private LocalDate dataPagamento;

    @Column(name = "valor_pago", nullable = false, precision = 18, scale = 2)
    private BigDecimal valorPago;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_origem_id")
    private ContaBancaria contaOrigem;

    @Column(length = 200)
    private String observacao;


    public Pagamento() {}


    public Pagamento(Long id, Lancamento lancamento, LocalDate dataPagamento, BigDecimal valorPago, ContaBancaria contaOrigem, String observacao) {
        this.id = id;
        this.lancamento = lancamento;
        this.dataPagamento = dataPagamento;
        this.valorPago = valorPago;
        this.contaOrigem = contaOrigem;
        this.observacao = observacao;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Lancamento getLancamento() { return lancamento; }
    public void setLancamento(Lancamento lancamento) { this.lancamento = lancamento; }

    public LocalDate getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(LocalDate dataPagamento) { this.dataPagamento = dataPagamento; }

    public BigDecimal getValorPago() { return valorPago; }
    public void setValorPago(BigDecimal valorPago) { this.valorPago = valorPago; }

    public ContaBancaria getContaOrigem() { return contaOrigem; }
    public void setContaOrigem(ContaBancaria contaOrigem) { this.contaOrigem = contaOrigem; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}