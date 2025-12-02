package com.safemoney.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cartao_credito")
public class CartaoCredito implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 50)
    private String bandeira;

    @Column(nullable = false, length = 50)
    private String emissor;

    @Column(nullable = false, length = 50)
    private String apelido;

    @Column(name = "fechamento_fatura_dia", nullable = false)
    private Integer fechamentoFaturaDia;

    @Column(name = "vencimento_fatura_dia", nullable = false)
    private Integer vencimentoFaturaDia;

    @Column(nullable = false)
    private boolean ativo = true;

    public CartaoCredito() {}

    // Getters e Setters...
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public String getBandeira() { return bandeira; }
    public void setBandeira(String bandeira) { this.bandeira = bandeira; }
    public String getEmissor() { return emissor; }
    public void setEmissor(String emissor) { this.emissor = emissor; }
    public String getApelido() { return apelido; }
    public void setApelido(String apelido) { this.apelido = apelido; }
    public Integer getFechamentoFaturaDia() { return fechamentoFaturaDia; }
    public void setFechamentoFaturaDia(Integer fechamentoFaturaDia) { this.fechamentoFaturaDia = fechamentoFaturaDia; }
    public Integer getVencimentoFaturaDia() { return vencimentoFaturaDia; }
    public void setVencimentoFaturaDia(Integer vencimentoFaturaDia) { this.vencimentoFaturaDia = vencimentoFaturaDia; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartaoCredito that = (CartaoCredito) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}