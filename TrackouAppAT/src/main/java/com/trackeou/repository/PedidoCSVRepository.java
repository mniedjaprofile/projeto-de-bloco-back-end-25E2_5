package com.trackeou.repository;

import com.trackeou.model.*;
import com.trackeou.model.Pedido;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Leitura de pedidos a partir de CSV simplificado.
 * Estrutura (pedido.csv):
 * idPedido,idCliente,codigoRastreio,dataPedido,dataPrevisao,valorTotal,statusAtual,cpfCliente
 */
public class PedidoCSVRepository implements PedidoRepository {

    private final Path pedidosCsv;

    public PedidoCSVRepository(Path pedidosCsv) {
        this.pedidosCsv = pedidosCsv;
    }

    @Override
    public Optional<Pedido> findByCodigoRastreioAndCpf(String codigoRastreio, String cpf) {
        return loadAll().stream()
                .filter(p -> p.getCodigoRastreio().equalsIgnoreCase(codigoRastreio) &&
                        Objects.equals(findCpfFromCsv(p.getIdPedido()), cpf))
                .findFirst();
    }

    @Override
    public List<Pedido> findByCliente(Long idCliente) {
        List<Pedido> pedidos = new ArrayList<>();
        for (Pedido p : loadAll()) {
            if (Objects.equals(p.getIdCliente(), idCliente)) {
                pedidos.add(p);
            }
        }
        return pedidos;
    }

    @Override
    public Optional<Pedido> findById(Long idPedido) {
        return loadAll().stream()
                .filter(p -> Objects.equals(p.getIdPedido(), idPedido))
                .findFirst();
    }

    // ===== CSV reader simplificado =====
    private List<Pedido> loadAll() {
        List<Pedido> pedidos = new ArrayList<>();
        if (!Files.exists(pedidosCsv)) return pedidos;
        try (BufferedReader br = Files.newBufferedReader(pedidosCsv)) {
            String line = br.readLine(); // header
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",", -1);
                if (parts.length < 8) continue;
                Long idPedido = Long.parseLong(parts[0]);
                Long idCliente = Long.parseLong(parts[1]);
                String codigoRastreio = parts[2];
                LocalDateTime dataPedido = LocalDateTime.parse(parts[3]);
                LocalDateTime dataPrevisao = LocalDateTime.parse(parts[4]);
                Double valorTotal = Double.parseDouble(parts[5]);
                String statusAtual = parts[6];
                // cpf está na coluna 7, mas aqui guardado só para busca
                pedidos.add(new Pedido(idPedido, idCliente, codigoRastreio,
                        dataPedido, dataPrevisao, valorTotal,
                        statusAtual, null, new ArrayList<>(), new ArrayList<>()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro lendo CSV de pedidos", e);
        }
        return pedidos;
    }

    private String findCpfFromCsv(Long idPedido) {
        // No CSV exemplo, cpfCliente está presente em cada linha
        try (BufferedReader br = Files.newBufferedReader(pedidosCsv)) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length >= 8 && Long.parseLong(parts[0]) == idPedido) {
                    return parts[7];
                }
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }
}