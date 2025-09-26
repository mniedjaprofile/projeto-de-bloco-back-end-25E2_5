package com.trackeou;

import com.trackeou.logger.CsvLoggerLogin;
import com.trackeou.logger.LoggerLogin;
import com.trackeou.repository.ClienteCSVRepository;
import com.trackeou.repository.ClienteRepository;
import com.trackeou.service.LoginService;
import com.trackeou.ui.ConsoleUI;
import com.trackeou.validator.EmailValidator;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        // caminhos dos CSVs (ajuste conforme seu projeto)
        Path clientesCsv = Path.of("data/clientes.csv");
        Path loginsCsv = Path.of("data/logins.csv");

        ClienteRepository clienteRepository = new ClienteCSVRepository(clientesCsv);
        LoggerLogin logger = new CsvLoggerLogin(loginsCsv);
        LoginService loginService = new LoginService(clienteRepository);
        EmailValidator emailValidator = new EmailValidator();

        ConsoleUI ui = new ConsoleUI(loginService, emailValidator, logger);
        ui.start();
    }
}