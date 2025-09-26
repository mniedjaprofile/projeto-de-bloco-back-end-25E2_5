package com.trackeou.dto;

import java.time.LocalDateTime;
public record HistoricoStatusDTO(
        LocalDateTime dataHora,
        String status,
        String descricao
) {}
