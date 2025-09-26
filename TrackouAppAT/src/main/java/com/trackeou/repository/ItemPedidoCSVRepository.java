package com.trackeou.repository;

import com.trackeou.dto.ItemPedidoDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class ItemPedidoCSVRepository {
    private final Path filePath;

    // Construtor que aceita String
    public ItemPedidoCSVRepository(String csvPath) {
        this.filePath = Path.of(csvPath);
    }

    public List<ItemPedidoDTO> findByPedidoId(Long idPedido) {
        try (Stream<String> lines = Files.lines(filePath)) {
            return lines.skip(1) // pula cabeçalho
                    .map(line -> line.split(","))
                    .filter(cols -> cols.length >= 4 && Long.parseLong(cols[1]) == idPedido)
                    .map(cols -> new ItemPedidoDTO(
                            Long.parseLong(cols[0]),
                            Integer.parseInt(cols[2]),
                            Double.parseDouble(cols[3])
                    ))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Erro lendo itens.csv", e);
        }
    }
}