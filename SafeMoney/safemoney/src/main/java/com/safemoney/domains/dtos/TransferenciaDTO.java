package com.safemoney.domains.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TransferenciaDTO {

    @NotNull(message = "Conta de origem é obrigatória")
    private Integer contaOrigemId;

    @NotNull(message = "Conta de destino é obrigatória")
    private Integer contaDestinoId;

    @NotNull(message = "Data da transferência é obrigatória")
    private LocalDate data;

    @NotNull(message = "Valor é obrigatório")
    @Positive
    private BigDecimal valor;

    private String observacao;

    public TransferenciaDTO() {}

    public Integer getContaOrigemId() { return contaOrigemId; }
    public void setContaOrigemId(Integer contaOrigemId) { this.contaOrigemId = contaOrigemId; }
    public Integer getContaDestinoId() { return contaDestinoId; }
    public void setContaDestinoId(Integer contaDestinoId) { this.contaDestinoId = contaDestinoId; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = (observacao == null) ? null : observacao.trim(); }
}