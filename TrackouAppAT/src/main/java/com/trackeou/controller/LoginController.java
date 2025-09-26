package com.trackeou.controller;

import com.trackeou.service.LoginService;
import com.trackeou.service.ResultadoLogin;
import com.trackeou.validator.EmailValidator;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;
    private final EmailValidator emailValidator;

    public LoginController(LoginService loginService, EmailValidator emailValidator) {
        this.loginService = loginService;
        this.emailValidator = emailValidator;
    }

    @PostMapping
    public Map<String, Object> login(@RequestParam String email, @RequestParam String senha) {
        Map<String, Object> resp = new HashMap<>();
        if (!emailValidator.isValid(email)) {
            resp.put("sucesso", false);
            resp.put("mensagem", "Email inválido");
            return resp;
        }
        ResultadoLogin res = loginService.autenticar(email, senha);
        resp.put("sucesso", res.isSucesso());
        resp.put("mensagem", res.getMensagem());
        if (res.isSucesso()) {
            resp.put("sessao", res.getSessao().getIdSessao());
            resp.put("idCliente", res.getClienteId());
        }
        return resp;
    }
}