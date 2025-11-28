package com.safemoney.domains.enums;

public enum StatusLancamento {

    PENDENTE(1, "PENDENTE"),
    BAIXADO(2, "BAIXADO"),
    PARCIAL(3, "PARCIAL"),
    CANCELADO(4, "CANCELADO");

    private Integer id;
    private String descricao;

    private StatusLancamento(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusLancamento toEnum(Integer id) {
        if (id == null) {
            return null;
        }
        for (StatusLancamento x : StatusLancamento.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}