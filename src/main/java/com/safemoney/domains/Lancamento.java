package com.safemoney.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.safemoney.domains.enums.MeioPagamento;
import com.safemoney.domains.enums.StatusLancamento;
import com.safemoney.domains.enums.TipoLancamento;
import com.safemoney.infra.MeioPagamentoConverter;
import com.safemoney.infra.StatusLancamentoConverter;
import com.safemoney.infra.TipoLancamentoConverter;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "lancamento")
public class Lancamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 150)
    private String descricao;

    @Column(nullable = false)
    @Convert(converter = TipoLancamentoConverter.class)
    private TipoLancamento tipo;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal valor;

    @Column(name = "valor_baixado", nullable = false, precision = 18, scale = 2)
    private BigDecimal valorBaixado = BigDecimal.ZERO;

    @Column(name = "data_competencia", nullable = false)
    private LocalDate dataCompetencia;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Column(name = "meio_pagamento", nullable = false)
    @Convert(converter = MeioPagamentoConverter.class)
    private MeioPagamento meioPagamento;

    @Column(nullable = false)
    @Convert(converter = StatusLancamentoConverter.class)
    private StatusLancamento status = StatusLancamento.PENDENTE;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entidade_id")
    private Entidade entidade;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centro_custo_id")
    private CentroCusto centroCusto;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_bancaria_id")
    private ContaBancaria contaBancaria;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartao_credito_id")
    private CartaoCredito cartaoCredito;

    @JsonManagedReference
    @OneToMany(mappedBy = "lancamento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Pagamento> pagamentos = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "lancamento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Recebimento> recebimentos = new ArrayList<>();

    public Lancamento() {}


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public TipoLancamento getTipo() { return tipo; }
    public void setTipo(TipoLancamento tipo) { this.tipo = tipo; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public BigDecimal getValorBaixado() { return valorBaixado; }
    public void setValorBaixado(BigDecimal valorBaixado) { this.valorBaixado = valorBaixado; }
    public LocalDate getDataCompetencia() { return dataCompetencia; }
    public void setDataCompetencia(LocalDate dataCompetencia) { this.dataCompetencia = dataCompetencia; }
    public LocalDate getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }
    public MeioPagamento getMeioPagamento() { return meioPagamento; }
    public void setMeioPagamento(MeioPagamento meioPagamento) { this.meioPagamento = meioPagamento; }
    public StatusLancamento getStatus() { return status; }
    public void setStatus(StatusLancamento status) { this.status = status; }
    public Entidade getEntidade() { return entidade; }
    public void setEntidade(Entidade entidade) { this.entidade = entidade; }
    public CentroCusto getCentroCusto() { return centroCusto; }
    public void setCentroCusto(CentroCusto centroCusto) { this.centroCusto = centroCusto; }
    public ContaBancaria getContaBancaria() { return contaBancaria; }
    public void setContaBancaria(ContaBancaria contaBancaria) { this.contaBancaria = contaBancaria; }
    public CartaoCredito getCartaoCredito() { return cartaoCredito; }
    public void setCartaoCredito(CartaoCredito cartaoCredito) { this.cartaoCredito = cartaoCredito; }
    public List<Pagamento> getPagamentos() { return pagamentos; }
    public void setPagamentos(List<Pagamento> pagamentos) { this.pagamentos = pagamentos; }
    public List<Recebimento> getRecebimentos() { return recebimentos; }
    public void setRecebimentos(List<Recebimento> recebimentos) { this.recebimentos = recebimentos; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lancamento that = (Lancamento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}