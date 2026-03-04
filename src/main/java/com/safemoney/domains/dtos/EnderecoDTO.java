package com.safemoney.domains.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public class EnderecoDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Integer id;

    @NotBlank(message = "Nome da endereco é obrigatório")
    @Size(max = 100)
    private String rua;

    @Size(max = 20)
    private String numero;

    @Size(max = 50)
    private String cidade;

    public EnderecoDTO() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
