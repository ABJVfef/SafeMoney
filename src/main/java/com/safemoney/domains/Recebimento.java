package com.safemoney.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "recebimento")
public class Recebimento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lancamento_id", nullable = false)
    private Lancamento lancamento;

    @Column(name = "data_recebimento", nullable = false)
    private LocalDate dataRecebimento;

    @Column(name = "valor_recebido", nullable = false, precision = 18, scale = 2)
    private BigDecimal valorRecebido;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_destino_id")
    private ContaBancaria contaDestino;

    @Column(length = 200)
    private String observacao;

    public Recebimento() {}


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Lancamento getLancamento() { return lancamento; }
    public void setLancamento(Lancamento lancamento) { this.lancamento = lancamento; }
    public LocalDate getDataRecebimento() { return dataRecebimento; }
    public void setDataRecebimento(LocalDate dataRecebimento) { this.dataRecebimento = dataRecebimento; }
    public BigDecimal getValorRecebido() { return valorRecebido; }
    public void setValorRecebido(BigDecimal valorRecebido) { this.valorRecebido = valorRecebido; }
    public ContaBancaria getContaDestino() { return contaDestino; }
    public void setContaDestino(ContaBancaria contaDestino) { this.contaDestino = contaDestino; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recebimento that = (Recebimento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}