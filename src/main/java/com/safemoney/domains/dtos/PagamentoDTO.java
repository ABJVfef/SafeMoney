package com.safemoney.domains.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public class PagamentoDTO {

    @NotNull(message = "Data do pagamento é obrigatória")
    private LocalDate dataPagamento;

    @NotNull(message = "Valor pago é obrigatório")
    @Positive
    private BigDecimal valorPago;

    @NotNull(message = "Conta de origem é obrigatória")
    private Integer contaOrigemId;

    private String observacao;

    public PagamentoDTO() {}

    public LocalDate getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(LocalDate dataPagamento) { this.dataPagamento = dataPagamento; }
    public BigDecimal getValorPago() { return valorPago; }
    public void setValorPago(BigDecimal valorPago) { this.valorPago = valorPago; }
    public Integer getContaOrigemId() { return contaOrigemId; }
    public void setContaOrigemId(Integer contaOrigemId) { this.contaOrigemId = contaOrigemId; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = (observacao == null) ? null : observacao.trim(); }
}