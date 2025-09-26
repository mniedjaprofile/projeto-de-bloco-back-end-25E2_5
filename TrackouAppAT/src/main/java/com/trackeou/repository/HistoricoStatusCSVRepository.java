package com.trackeou.repository;

import com.trackeou.dto.HistoricoStatusDTO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;


public class HistoricoStatusCSVRepository {
    private final Path filePath = Path.of("data/historico.csv");

    public List<HistoricoStatusDTO> findByPedidoId(Long idPedido) {
        try (Stream<String> lines = Files.lines(filePath)) {
            return lines.skip(1)
                    .map(line -> line.split(","))
                    .filter(cols -> Long.parseLong(cols[0]) == idPedido)
                    .map(cols -> new HistoricoStatusDTO(
                            LocalDateTime.parse(cols[1]),
                            cols[2],
                            cols[3]
                    ))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Erro lendo historico.csv", e);
        }
    }
}
