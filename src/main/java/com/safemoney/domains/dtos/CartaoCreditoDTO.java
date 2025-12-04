package com.safemoney.domains.dtos;

import jakarta.validation.constraints.*;

public class CartaoCreditoDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Integer id;

    @NotBlank(message = "Bandeira é obrigatória")
    @Size(max = 50)
    private String bandeira;

    @NotBlank(message = "Emissor é obrigatório")
    @Size(max = 50)
    private String emissor;

    @NotBlank(message = "Apelido é obrigatório")
    @Size(max = 50)
    private String apelido;

    @NotNull(message = "Dia de fechamento é obrigatório")
    @Min(1) @Max(31)
    private Integer fechamentoFaturaDia;

    @NotNull(message = "Dia de vencimento é obrigatório")
    @Min(1) @Max(31)
    private Integer vencimentoFaturaDia;

    private boolean ativo = true;

    public CartaoCreditoDTO() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getBandeira() { return bandeira; }
    public void setBandeira(String bandeira) { this.bandeira = (bandeira == null) ? null : bandeira.trim(); }
    public String getEmissor() { return emissor; }
    public void setEmissor(String emissor) { this.emissor = (emissor == null) ? null : emissor.trim(); }
    public String getApelido() { return apelido; }
    public void setApelido(String apelido) { this.apelido = (apelido == null) ? null : apelido.trim(); }
    public Integer getFechamentoFaturaDia() { return fechamentoFaturaDia; }
    public void setFechamentoFaturaDia(Integer fechamentoFaturaDia) { this.fechamentoFaturaDia = fechamentoFaturaDia; }
    public Integer getVencimentoFaturaDia() { return vencimentoFaturaDia; }
    public void setVencimentoFaturaDia(Integer vencimentoFaturaDia) { this.vencimentoFaturaDia = vencimentoFaturaDia; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}