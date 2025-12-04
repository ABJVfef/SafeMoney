package com.safemoney.domains.dtos;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LancamentoDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Long id;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 150)
    private String descricao;

    @NotNull(message = "Tipo de lançamento é obrigatório (1=PAGAR, 2=RECEBER)")
    private Integer tipoId;

    @NotNull(message = "Valor é obrigatório")
    @Positive(message = "Valor deve ser positivo")
    @Digits(integer = 15, fraction = 2)
    private BigDecimal valor;

    @NotNull(message = "Data de competência é obrigatória")
    private LocalDate dataCompetencia;

    @NotNull(message = "Data de vencimento é obrigatória")
    private LocalDate dataVencimento;

    @NotNull(message = "Meio de pagamento é obrigatório")
    private Integer meioPagamentoId;

    private Integer entidadeId;
    private Integer centroCustoId;
    private Integer contaBancariaId;
    private Integer cartaoCreditoId;
    private Integer statusId;

    public LancamentoDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = (descricao == null) ? null : descricao.trim(); }
    public Integer getTipoId() { return tipoId; }
    public void setTipoId(Integer tipoId) { this.tipoId = tipoId; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public LocalDate getDataCompetencia() { return dataCompetencia; }
    public void setDataCompetencia(LocalDate dataCompetencia) { this.dataCompetencia = dataCompetencia; }
    public LocalDate getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }
    public Integer getMeioPagamentoId() { return meioPagamentoId; }
    public void setMeioPagamentoId(Integer meioPagamentoId) { this.meioPagamentoId = meioPagamentoId; }
    public Integer getEntidadeId() { return entidadeId; }
    public void setEntidadeId(Integer entidadeId) { this.entidadeId = entidadeId; }
    public Integer getCentroCustoId() { return centroCustoId; }
    public void setCentroCustoId(Integer centroCustoId) { this.centroCustoId = centroCustoId; }
    public Integer getContaBancariaId() { return contaBancariaId; }
    public void setContaBancariaId(Integer contaBancariaId) { this.contaBancariaId = contaBancariaId; }
    public Integer getCartaoCreditoId() { return cartaoCreditoId; }
    public void setCartaoCreditoId(Integer cartaoCreditoId) { this.cartaoCreditoId = cartaoCreditoId; }
    public Integer getStatusId() { return statusId; }
    public void setStatusId(Integer statusId) { this.statusId = statusId; }
}