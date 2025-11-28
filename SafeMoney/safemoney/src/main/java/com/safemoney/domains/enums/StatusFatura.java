package com.safemoney.domains.enums;

public enum StatusFatura {

    ABERTA(1, "ABERTA"),
    FECHADA(2, "FECHADA"),
    PAGA(3, "PAGA");

    private Integer id;
    private String descricao;

    private StatusFatura(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusFatura toEnum(Integer id) {
        if (id == null) {
            return null;
        }
        for (StatusFatura x : StatusFatura.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}