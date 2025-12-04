package com.safemoney.domains.enums;

public enum TipoTransacao {

    CREDITO(1, "CREDITO"),
    DEBITO(2, "DEBITO"),
    TRANSFERENCIA(3, "TRANSFERENCIA");

    private Integer id;
    private String descricao;

    private TipoTransacao(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoTransacao toEnum(Integer id) {
        if (id == null) {
            return null;
        }
        for (TipoTransacao x : TipoTransacao.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}