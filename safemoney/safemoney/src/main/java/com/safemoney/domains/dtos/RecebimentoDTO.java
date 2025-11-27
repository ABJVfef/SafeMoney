package com.safemoney.domains.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public class RecebimentoDTO {

    @NotNull(message = "Data do recebimento é obrigatória")
    private LocalDate dataRecebimento;

    @NotNull(message = "Valor recebido é obrigatório")
    @Positive
    private BigDecimal valorRecebido;

    @NotNull(message = "Conta de destino é obrigatória")
    private Integer contaDestinoId;

    private String observacao;

    public RecebimentoDTO() {}

    public LocalDate getDataRecebimento() { return dataRecebimento; }
    public void setDataRecebimento(LocalDate dataRecebimento) { this.dataRecebimento = dataRecebimento; }
    public BigDecimal getValorRecebido() { return valorRecebido; }
    public void setValorRecebido(BigDecimal valorRecebido) { this.valorRecebido = valorRecebido; }
    public Integer getContaDestinoId() { return contaDestinoId; }
    public void setContaDestinoId(Integer contaDestinoId) { this.contaDestinoId = contaDestinoId; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = (observacao == null) ? null : observacao.trim(); }
}