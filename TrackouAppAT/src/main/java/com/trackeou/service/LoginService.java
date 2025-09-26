package com.trackeou.service;

import com.trackeou.model.Cliente;
import com.trackeou.model.Sessao;
import com.trackeou.repository.ClienteRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * Responsabilidade: lógica de autenticação.
 */
public class LoginService {

    private final ClienteRepository clienteRepository;

    public LoginService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Tenta autenticar e retorna ResultadoLogin (sucesso/falha + mensagem)
     */
    public ResultadoLogin autenticar(String email, String senha) {
        if (senha == null || senha.trim().isEmpty()) {
            return ResultadoLogin.failure("Senha não pode ser nula ou vazia", email);
        }
        Optional<Cliente> opt = clienteRepository.findByEmail(email);
        if (opt.isEmpty()) {
            return ResultadoLogin.failure("Credenciais inválidas", email);
        }
        Cliente cliente = opt.get();
        String hashSenhaInformada = hashSHA256(senha);
        if (!hashSenhaInformada.equals(cliente.getSenhaHash())) {
            return ResultadoLogin.failure("Credenciais inválidas", email);
        }
        if (!cliente.isAtivo()) {
            return ResultadoLogin.failure("Conta inativa. Entre em contato com o suporte.", email);
        }
        Sessao sessao = new Sessao(cliente.getIdCliente());
        return ResultadoLogin.success("Login realizado com sucesso", sessao, cliente.getIdCliente(), email);
    }

    // Utilitário simples de hash SHA-256
    private String hashSHA256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritmo SHA-256 não disponível", e);
        }
    }
}