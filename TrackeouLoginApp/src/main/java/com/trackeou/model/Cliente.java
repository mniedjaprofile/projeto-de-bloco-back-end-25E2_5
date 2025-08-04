package com.trackeou.model;

public class Cliente {
    private int idCliente;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private boolean preferenciaNotificacao;

    public Cliente(int idCliente, String nome, String cpf, String email, String senha, boolean preferenciaNotificacao) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.preferenciaNotificacao = preferenciaNotificacao;
    }

    public boolean validarSenha(String senhaDigitada) {
        return senha.equals(senhaDigitada);
    }

    public String getEmail() {
        return email;
    }
}
