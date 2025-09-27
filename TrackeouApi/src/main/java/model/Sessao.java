package model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Sessao {
    private String idSessao;
    private Long idCliente;
    private LocalDateTime dataCriacao;

    public Sessao(Long idCliente) {
        this.idSessao = UUID.randomUUID().toString();
        this.idCliente = idCliente;
        this.dataCriacao = LocalDateTime.now();
    }

    public String getIdSessao() { return idSessao; }
    public Long getIdCliente() { return idCliente; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
}