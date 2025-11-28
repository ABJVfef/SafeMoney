package com.safemoney.domains.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public class CentroCustoDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Integer id;

    @NotBlank(message = "Nome do centro de custo é obrigatório")
    @Size(max = 100)
    private String nome;

    @Size(max = 20)
    private String codigo;

    private boolean ativo = true;

    public CentroCustoDTO() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = (nome == null) ? null : nome.trim(); }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = (codigo == null) ? null : codigo.trim(); }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}