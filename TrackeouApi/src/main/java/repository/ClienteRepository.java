package repository;

import model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByCpf(String cpf);
    List<Cliente> findAll();
}
