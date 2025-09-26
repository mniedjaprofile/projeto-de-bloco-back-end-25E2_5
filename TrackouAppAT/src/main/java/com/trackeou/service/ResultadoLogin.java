package com.trackeou.service;

import com.trackeou.model.Sessao;

public class ResultadoLogin {
    private final boolean sucesso;
    private final String mensagem;
    private final Sessao sessao;
    private final Long clienteId;
    private final String email;

    public ResultadoLogin(boolean sucesso, String mensagem, Sessao sessao, Long clienteId, String email) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.sessao = sessao;
        this.clienteId = clienteId;
        this.email = email;
    }

    public static ResultadoLogin success(String mensagem, Sessao sessao, Long clienteId, String email) {
        return new ResultadoLogin(true, mensagem, sessao, clienteId, email);
    }

    public static ResultadoLogin failure(String mensagem, String email) {
        return new ResultadoLogin(false, mensagem, null, null, email);
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public String getEmail() {
        return email;
    }
}
