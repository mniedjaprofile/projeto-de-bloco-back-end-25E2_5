package com.trackeou.repository;

import com.trackeou.model.Cliente;

public interface ClienteRepository {
    Cliente buscarPorEmail(String email);
}