package com.trackeou.repository;

import com.trackeou.model.Cliente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ClienteCSVRepository implements ClienteRepository {
    private String resource;

    public ClienteCSVRepository(String resource) {
        String appDir = System.getProperty("user.dir");
        this.resource = appDir + File.separator + "TrackeouLoginApp" + File.separator + "db" + File.separator + resource;
        System.out.println("📂 Caminho do arquivo de clientes: " + this.resource);
    }

    @Override
    public Cliente buscarPorEmail(String email) {
        try (BufferedReader br = new BufferedReader(new FileReader(resource))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados[3].equals(email)) {
                    return new Cliente(
                            Integer.parseInt(dados[0]),
                            dados[1],
                            dados[2],
                            dados[3],
                            dados[4],
                            Boolean.parseBoolean(dados[5])
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
