package repository;

import model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository {
    Optional<Pedido> findByCodigoRastreioAndCpf(String codigoRastreio, String cpf);
    List<Pedido> findByCliente(Long idCliente);
    Optional<Pedido> findById(Long idPedido);
}