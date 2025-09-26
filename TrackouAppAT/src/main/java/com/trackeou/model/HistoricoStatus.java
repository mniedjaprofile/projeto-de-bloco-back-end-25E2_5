package com.trackeou.model;

import java.time.LocalDateTime;

public class HistoricoStatus {
    private Long id;
    private Long idPedido;
    private String status;
    private LocalDateTime dataHora;

    public HistoricoStatus(Long id, Long idPedido, String status, LocalDateTime dataHora) {
        this.id = id;
        this.idPedido = idPedido;
        this.status = status;
        this.dataHora = dataHora;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}