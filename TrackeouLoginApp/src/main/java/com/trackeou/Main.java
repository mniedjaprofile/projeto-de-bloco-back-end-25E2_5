package com.trackeou;

import com.trackeou.logger.CsvLoggerLogin;
import com.trackeou.repository.ClienteCSVRepository;
import com.trackeou.service.EmailValidator;
import com.trackeou.service.LoginService;
import com.trackeou.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        ClienteCSVRepository clienteRepo = new ClienteCSVRepository("clientes.csv");
        CsvLoggerLogin logger = new CsvLoggerLogin("logins.csv");
        EmailValidator emailValidator = new EmailValidator();
        LoginService loginService = new LoginService(clienteRepo, logger, emailValidator);

        ConsoleUI consoleUI = new ConsoleUI(loginService, emailValidator);
        consoleUI.executar();
    }
}