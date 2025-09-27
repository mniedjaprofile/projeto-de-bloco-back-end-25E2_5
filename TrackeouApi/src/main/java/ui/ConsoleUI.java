package ui;

import logger.LoggerLogin;
import service.LoginService;
import service.ResultadoLogin;
import validator.EmailValidator;

import java.util.Scanner;

public class ConsoleUI {

    private final LoginService loginService;
    private final EmailValidator emailValidator;
    private final LoggerLogin logger;

    public ConsoleUI(LoginService loginService, EmailValidator emailValidator, LoggerLogin logger) {
        this.loginService = loginService;
        this.emailValidator = emailValidator;
        this.logger = logger;
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Trackeou - Login ===");
            System.out.print("Email: ");
            String email = scanner.nextLine().trim();

            if (!emailValidator.isValid(email)) {
                String msg = "Formato de e-mail inválido.";
                System.out.println(msg);
                ResultadoLogin res = ResultadoLogin.failure(msg, email);
                logger.log(res);
                return;
            }

            System.out.print("Senha: ");
            String senha = scanner.nextLine(); // em console real, usar Console.readPassword()

            ResultadoLogin resultado = loginService.autenticar(email, senha);
            // exibir resultado padronizado
            if (resultado.isSucesso()) {
                System.out.println(">>> " + resultado.getMensagem());
                System.out.println("Sessão: " + resultado.getSessao().getIdSessao());
                System.out.println("Cliente ID: " + resultado.getClienteId());
            } else {
                System.out.println("Erro: " + resultado.getMensagem());
            }
            // registrar no log (sucesso ou falha)
            logger.log(resultado);
        } catch (Exception e) {
            System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }
}