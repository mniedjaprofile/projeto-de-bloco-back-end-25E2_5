package com.trackeou.dto;

public record ItemPedidoDTO(
        Long idProduto,
        Integer quantidade,
        Double precoUnitario
) {}
