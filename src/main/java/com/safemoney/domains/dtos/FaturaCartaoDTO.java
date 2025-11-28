package com.safemoney.domains.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FaturaCartaoDTO {

    private Long id;
    private Integer cartaoId;
    private String competencia;
    private LocalDate dataVencimento;
    private LocalDate dataFechamento;
    private BigDecimal valorTotal;
    private Integer statusId;

    public FaturaCartaoDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getCartaoId() { return cartaoId; }
    public void setCartaoId(Integer cartaoId) { this.cartaoId = cartaoId; }
    public String getCompetencia() { return competencia; }
    public void setCompetencia(String competencia) { this.competencia = competencia; }
    public LocalDate getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }
    public LocalDate getDataFechamento() { return dataFechamento; }
    public void setDataFechamento(LocalDate dataFechamento) { this.dataFechamento = dataFechamento; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
    public Integer getStatusId() { return statusId; }
    public void setStatusId(Integer statusId) { this.statusId = statusId; }
}