package com.trackeou.app;

import com.trackeou.repository.*;
import com.trackeou.service.*;
import com.trackeou.validator.EmailValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;

@SpringBootApplication(scanBasePackages = "com.trackeou")
public class TrackeouApplication {

    @Bean
    public ClienteRepository clienteRepository() {
        return new ClienteCSVRepository(Path.of("data/clientes.csv"));
    }

    @Bean
    public PedidoRepository pedidoRepository() {
        return new PedidoCSVRepository(Path.of("data/pedidos.csv"));
    }

    @Bean
    public LoginService loginService(ClienteRepository repo) {
        return new LoginService(repo);
    }

    @Bean
    public PedidoService pedidoService(PedidoRepository repo) {
        return new PedidoService(repo);
    }

    @Bean
    public EmailValidator emailValidator() {
        return new EmailValidator();
    }

    public static void main(String[] args) {
        SpringApplication.run(TrackeouApplication.class, args);
    }
}
