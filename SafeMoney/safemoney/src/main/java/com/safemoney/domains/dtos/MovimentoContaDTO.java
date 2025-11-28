package com.safemoney.domains.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MovimentoContaDTO {

    private Long id;
    private Integer contaId;
    private LocalDate dataMovimento;
    private Integer tipoId;
    private BigDecimal valor;
    private String historico;
    private Long referenciaId;
    private String referenciaTipo;

    public MovimentoContaDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getContaId() { return contaId; }
    public void setContaId(Integer contaId) { this.contaId = contaId; }
    public LocalDate getDataMovimento() { return dataMovimento; }
    public void setDataMovimento(LocalDate dataMovimento) { this.dataMovimento = dataMovimento; }
    public Integer getTipoId() { return tipoId; }
    public void setTipoId(Integer tipoId) { this.tipoId = tipoId; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public String getHistorico() { return historico; }
    public void setHistorico(String historico) { this.historico = historico; }
    public Long getReferenciaId() { return referenciaId; }
    public void setReferenciaId(Long referenciaId) { this.referenciaId = referenciaId; }
    public String getReferenciaTipo() { return referenciaTipo; }
    public void setReferenciaTipo(String referenciaTipo) { this.referenciaTipo = referenciaTipo; }
}