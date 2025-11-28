package com.safemoney.domains.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaBancariaDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class, message = "O ID deve ser nulo na criação")
    @NotNull(groups = Update.class, message = "O ID é obrigatório na atualização")
    private Integer id;

    @NotBlank(message = "O nome da instituição é obrigatório")
    @Size(max = 100)
    private String instituicao;

    @NotBlank(message = "O apelido da conta é obrigatório")
    @Size(max = 50)
    private String apelido;

    @Size(max = 20)
    private String agencia;

    @Size(max = 20)
    private String numero;

    @NotNull(groups = Create.class, message = "O saldo inicial é obrigatório")
    @Digits(integer = 15, fraction = 2)
    private BigDecimal saldoInicial;

    @NotNull(groups = Create.class, message = "A data do saldo inicial é obrigatória")
    private LocalDate dataSaldoInicial;

    private boolean ativa = true;

    public ContaBancariaDTO() {}

    public ContaBancariaDTO(Integer id, String instituicao, String apelido, String agencia, String numero, BigDecimal saldoInicial, LocalDate dataSaldoInicial, boolean ativa) {
        this.id = id;
        this.instituicao = instituicao;
        this.apelido = apelido;
        this.agencia = agencia;
        this.numero = numero;
        this.saldoInicial = saldoInicial;
        this.dataSaldoInicial = dataSaldoInicial;
        this.ativa = ativa;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getInstituicao() { return instituicao; }
    public void setInstituicao(String instituicao) { this.instituicao = (instituicao == null) ? null : instituicao.trim(); }
    public String getApelido() { return apelido; }
    public void setApelido(String apelido) { this.apelido = (apelido == null) ? null : apelido.trim(); }
    public String getAgencia() { return agencia; }
    public void setAgencia(String agencia) { this.agencia = (agencia == null) ? null : agencia.trim(); }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = (numero == null) ? null : numero.trim(); }
    public BigDecimal getSaldoInicial() { return saldoInicial; }
    public void setSaldoInicial(BigDecimal saldoInicial) { this.saldoInicial = saldoInicial; }
    public LocalDate getDataSaldoInicial() { return dataSaldoInicial; }
    public void setDataSaldoInicial(LocalDate dataSaldoInicial) { this.dataSaldoInicial = dataSaldoInicial; }
    public boolean isAtiva() { return ativa; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }
}