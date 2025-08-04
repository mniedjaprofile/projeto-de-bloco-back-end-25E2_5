package com.trackeou.ui;

import com.trackeou.service.LoginService;
import com.trackeou.service.ResultadoLogin;
import com.trackeou.service.EmailValidator;

import java.util.Scanner;

public class ConsoleUI {
    private final LoginService loginService;
    private final EmailValidator emailValidator;
    private final Scanner scanner;

    public ConsoleUI(LoginService loginService, EmailValidator emailValidator) {
        this.loginService = loginService;
        this.emailValidator = emailValidator;
        this.scanner = new Scanner(System.in);
    }

    public void executar() {
        String email = solicitarEmailValido();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        ResultadoLogin resultado = loginService.autenticar(email, senha);

        switch (resultado.getStatus()) {
            case SUCESSO:
                System.out.println("Login bem-sucedido! Sessão ID: " + resultado.getSessao().getIdSessao());
                break;
            case CREDENCIAIS_INVALIDAS:
                System.out.println("E-mail ou senha incorretos.");
                break;
            default:
                System.out.println("Erro desconhecido.");
        }
    }

    private String solicitarEmailValido() {
        String email;
        do {
            System.out.print("E-mail: ");
            email = scanner.nextLine();
            if (!emailValidator.isValido(email)) {
                System.out.println("Formato de e-mail inválido. Por favor, tente novamente.");
            }
        } while (!emailValidator.isValido(email));
        return email;
    }
}
