package com.safemoney.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "conta_bancaria")
public class ContaBancaria implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 100)
    private String instituicao;

    @Column(length = 20)
    private String agencia;

    @Column(length = 20)
    private String numero;

    @Column(nullable = false, length = 50)
    private String apelido;

    @Column(name = "saldo_inicial", nullable = false, precision = 18, scale = 2)
    private BigDecimal saldoInicial;

    @Column(name = "data_saldo_inicial", nullable = false)
    private LocalDate dataSaldoInicial;

    @Column(nullable = false)
    private boolean ativa = true;

    @JsonManagedReference
    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovimentoConta> movimentos = new ArrayList<>();

    public ContaBancaria() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public String getInstituicao() { return instituicao; }
    public void setInstituicao(String instituicao) { this.instituicao = instituicao; }
    public String getAgencia() { return agencia; }
    public void setAgencia(String agencia) { this.agencia = agencia; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getApelido() { return apelido; }
    public void setApelido(String apelido) { this.apelido = apelido; }
    public BigDecimal getSaldoInicial() { return saldoInicial; }
    public void setSaldoInicial(BigDecimal saldoInicial) { this.saldoInicial = saldoInicial; }
    public LocalDate getDataSaldoInicial() { return dataSaldoInicial; }
    public void setDataSaldoInicial(LocalDate dataSaldoInicial) { this.dataSaldoInicial = dataSaldoInicial; }
    public boolean isAtiva() { return ativa; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }
    public List<MovimentoConta> getMovimentos() { return movimentos; }
    public void setMovimentos(List<MovimentoConta> movimentos) { this.movimentos = movimentos; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContaBancaria that = (ContaBancaria) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}