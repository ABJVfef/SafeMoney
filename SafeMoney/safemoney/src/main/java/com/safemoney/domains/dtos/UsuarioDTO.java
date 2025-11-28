package com.safemoney.domains.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class, message = "O ID deve ser nulo na criação")
    @NotNull(groups = Update.class, message = "O ID é obrigatório na atualização")
    private Integer id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    public UsuarioDTO() {}

    public UsuarioDTO(Integer id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = (nome == null) ? null : nome.trim(); }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = (email == null) ? null : email.trim(); }
}