package com.trackeou.app;

import logger.CsvLoggerLogin;
import logger.LoggerLogin;
import repository.ClienteCSVRepository;
import repository.ClienteRepository;
import service.LoginService;
import ui.ConsoleUI;
import validator.EmailValidator;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

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