package model;

import java.time.LocalDateTime;

public class HistoricoStatus {
    private LocalDateTime dataHora;
    private String status;
    private String descricao;

    public HistoricoStatus(LocalDateTime dataHora, String status, String descricao) {
        this.dataHora = dataHora;
        this.status = status;
        this.descricao = descricao;
    }

    public LocalDateTime getDataHora() { return dataHora; }
    public String getStatus() { return status; }
    public String getDescricao() { return descricao; }
}