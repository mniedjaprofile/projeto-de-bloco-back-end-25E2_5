package com.trackeou.dto;

import java.time.LocalDateTime;
import java.util.List;
public record PedidoDTO(
        Long idPedido,
        String codigoRastreio,
        String statusAtual,
        LocalDateTime dataPedido,
        LocalDateTime dataPrevisao,
        Double valorTotal,
        List<ItemPedidoDTO> itens,
        List<HistoricoStatusDTO> historico
) {}
