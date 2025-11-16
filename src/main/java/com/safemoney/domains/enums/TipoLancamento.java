package com.safemoney.domains.enums;

public enum TipoLancamento {

PAGAR(1, "PAGAR"),
RECEBER(2, "RECEBER");

private Integer id;
private String descricao;

private TipoLancamento(Integer id, String descricao) {
    this.id = id;
    this.descricao = descricao;
}

public Integer getId() {
    return id;
}

public String getDescricao() {
    return descricao;
}

// Método para converter o ID de volta para o Enum (Aula 02) [cite: 10051-10061]
public static TipoLancamento toEnum(Integer id) {
    if (id == null) {
        return null;
    }
    for (TipoLancamento x : TipoLancamento.values()) {
        if (id.equals(x.getId())) {
            return x;
        }
    }
    throw new IllegalArgumentException("Tipo de Lançamento inválido: " + id);
}
}