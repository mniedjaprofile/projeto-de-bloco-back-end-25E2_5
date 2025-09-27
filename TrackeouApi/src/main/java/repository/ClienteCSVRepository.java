package repository;

import model.Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementação simples de leitura de CSV.
 * Formato esperado (header):
 * idCliente,nome,cpf,email,senhaHash,ativo,preferenciaNotificacao
 */
public class ClienteCSVRepository implements ClienteRepository {

    private final Path csvPath;

    public ClienteCSVRepository(Path csvPath) {
        this.csvPath = csvPath;
    }

    @Override
    public Optional<Cliente> findByEmail(String email) {
        return findAll().stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public Optional<Cliente> findByCpf(String cpf) {
        return findAll().stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst();
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        if (!Files.exists(csvPath)) {
            return clientes;
        }
        try (BufferedReader br = Files.newBufferedReader(csvPath)) {
            String line = br.readLine(); // header
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                // split simples por vírgula
                String[] parts = line.split(",", -1);
                // proteger contra linhas inválidas
                if (parts.length < 7) continue;
                Long id = tryParseLong(parts[0]);
                String nome = parts[1].trim();
                String cpf = parts[2].trim();
                String email = parts[3].trim();
                String senhaHash = parts[4].trim();
                boolean ativo = Boolean.parseBoolean(parts[5].trim());
                boolean pref = Boolean.parseBoolean(parts[6].trim());
                clientes.add(new Cliente(id, nome, cpf, email, senhaHash, ativo, pref));
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro lendo CSV de clientes: " + e.getMessage(), e);
        }
        return clientes;
    }

    private Long tryParseLong(String s) {
        try { return Long.parseLong(s.trim()); }
        catch (Exception e) { return null;}
    }
}