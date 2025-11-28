package com.safemoney.domains.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public class EntidadeDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Integer id;

    @NotBlank(message = "Nome da entidade é obrigatório")
    @Size(max = 100)
    private String nome;

    @Size(max = 20)
    private String documento;

    @Size(max = 50)
    private String tipo;

    public EntidadeDTO() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = (nome == null) ? null : nome.trim(); }
    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = (documento == null) ? null : documento.trim(); }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = (tipo == null) ? null : tipo.trim(); }
}