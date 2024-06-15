package com.pontoflowatual.pfw.models;

public enum TipoAprovacao {
    PEDIDO("Pedido"),
    APROVADO("Aprovado"),
    AUTORIZADO("Autorizado");

    private final String descricao;

    TipoAprovacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
