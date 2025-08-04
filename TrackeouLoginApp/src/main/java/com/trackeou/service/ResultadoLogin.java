package com.trackeou.service;

import com.trackeou.model.Sessao;

public class ResultadoLogin {
    public enum Status { SUCESSO, EMAIL_INVALIDO, SENHA_INVALIDA, CREDENCIAIS_INVALIDAS }

    private final Status status;
    private final Sessao sessao;

    private ResultadoLogin(Status status, Sessao sessao) {
        this.status = status;
        this.sessao = sessao;
    }

    public static ResultadoLogin sucesso(Sessao sessao) {
        return new ResultadoLogin(Status.SUCESSO, sessao);
    }

    public static ResultadoLogin emailInvalido() {
        return new ResultadoLogin(Status.EMAIL_INVALIDO, null);
    }

    public static ResultadoLogin credenciaisInvalidas() {
        return new ResultadoLogin(Status.CREDENCIAIS_INVALIDAS, null);
    }

    public Status getStatus() {
        return status;
    }

    public Sessao getSessao() {
        return sessao;
    }
}
