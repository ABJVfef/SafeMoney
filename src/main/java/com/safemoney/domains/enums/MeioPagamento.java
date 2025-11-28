package com.safemoney.domains.enums;

public enum MeioPagamento {

    CONTA(1, "CONTA"),
    CARTAO(2, "CARTAO"),
    DINHEIRO(3, "DINHEIRO"),
    PIX(4, "PIX");

    private Integer id;
    private String descricao;

    private MeioPagamento(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static MeioPagamento toEnum(Integer id) {
        if (id == null) {
            return null;
        }
        for (MeioPagamento x : MeioPagamento.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}