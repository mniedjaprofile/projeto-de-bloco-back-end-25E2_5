package com.trackeou.service;

import com.trackeou.model.Pedido;
import com.trackeou.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.trackeou.model.Pedido;
import com.trackeou.repository.PedidoRepository;

import java.util.List;
import java.util.Optional;

public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Optional<Pedido> consultaPublica(String codigoRastreio, String cpf) {
        return pedidoRepository.findByCodigoRastreioAndCpf(codigoRastreio, cpf);
    }

    public List<Pedido> listarPedidosCliente(Long idCliente) {
        return pedidoRepository.findByCliente(idCliente);
    }

    public Optional<Pedido> detalharPedido(Long idPedido) {
        return pedidoRepository.findById(idPedido);
    }
}
