package com.trackeou.service;

import com.trackeou.logger.LoggerLogin;
import com.trackeou.model.Cliente;
import com.trackeou.model.Sessao;
import com.trackeou.repository.ClienteRepository;

import java.util.UUID;

public class LoginService {
    private ClienteRepository clienteRepository;
    private LoggerLogin loggerLogin;
    private EmailValidator emailValidator;

    public LoginService(ClienteRepository clienteRepository, LoggerLogin loggerLogin, EmailValidator emailValidator) {
        this.clienteRepository = clienteRepository;
        this.loggerLogin = loggerLogin;
        this.emailValidator = emailValidator;
    }

    public ResultadoLogin autenticar(String email, String senha) {
        if (!emailValidator.isValido(email) || email.trim().isEmpty()) {
            loggerLogin.registrar(email, "EMAIL_INVALIDO");
            return ResultadoLogin.emailInvalido();
        }

        Cliente cliente = clienteRepository.buscarPorEmail(email);
        if (cliente != null && cliente.validarSenha(senha)) {
            loggerLogin.registrar(email, "SUCESSO");
            return ResultadoLogin.sucesso(new Sessao(UUID.randomUUID().toString()));
        } else {
            loggerLogin.registrar(email, "SENHA_INVALIDA");
            return ResultadoLogin.credenciaisInvalidas();
        }
    }
}