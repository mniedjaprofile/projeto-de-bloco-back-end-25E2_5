package com.trackeou.model;

import java.time.LocalDateTime;

public class Sessao {
    private String idSessao;
    private LocalDateTime dataHora;

    public Sessao(String idSessao) {
        this.idSessao = idSessao;
        this.dataHora = LocalDateTime.now();
    }

    public String getIdSessao() {
        return idSessao;
    }
}
