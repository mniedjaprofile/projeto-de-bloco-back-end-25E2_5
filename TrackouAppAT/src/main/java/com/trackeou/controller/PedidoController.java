package com.trackeou.controller;

import com.trackeou.model.Pedido;
import com.trackeou.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // Consulta pública
    @GetMapping("/public/{codigoRastreio}")
    public Optional<Pedido> consultaPublica(@PathVariable String codigoRastreio, @RequestParam String cpf) {
        return pedidoService.consultaPublica(codigoRastreio, cpf);
    }

    // Consulta autenticada (mock: idCliente passado como query param)
    @GetMapping
    public List<Pedido> listarPedidos(@RequestParam Long idCliente) {
        return pedidoService.listarPedidosCliente(idCliente);
    }

    @GetMapping("/{idPedido}")
    public Optional<Pedido> detalharPedido(@PathVariable Long idPedido) {
        return pedidoService.detalharPedido(idPedido);
    }
}