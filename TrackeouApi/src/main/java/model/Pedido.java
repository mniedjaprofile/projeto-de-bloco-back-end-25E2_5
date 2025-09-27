package model;

import java.time.LocalDateTime;

public class Pedido {
    private Long idPedido;
    private Long idCliente;
    private String codigoRastreio;
    private LocalDateTime dataPedido;
    private LocalDateTime dataPrevisao;
    private Double valorTotal;
    private String statusAtual;
    private Endereco enderecoEntrega;
    private List<ItemPedido> itens;
    private List<HistoricoStatus> historico;

    public Pedido(Long idPedido, Long idCliente, String codigoRastreio,
                  LocalDateTime dataPedido, LocalDateTime dataPrevisao,
                  Double valorTotal, String statusAtual,
                  Endereco enderecoEntrega,
                  List<ItemPedido> itens, List<HistoricoStatus> historico) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.codigoRastreio = codigoRastreio;
        this.dataPedido = dataPedido;
        this.dataPrevisao = dataPrevisao;
        this.valorTotal = valorTotal;
        this.statusAtual = statusAtual;
        this.enderecoEntrega = enderecoEntrega;
        this.itens = itens;
        this.historico = historico;
    }

    public Long getIdPedido() { return idPedido; }
    public Long getIdCliente() { return idCliente; }
    public String getCodigoRastreio() { return codigoRastreio; }
    public LocalDateTime getDataPedido() { return dataPedido; }
    public LocalDateTime getDataPrevisao() { return dataPrevisao; }
    public Double getValorTotal() { return valorTotal; }
    public String getStatusAtual() { return statusAtual; }
    public Endereco getEnderecoEntrega() { return enderecoEntrega; }
    public List<ItemPedido> getItens() { return itens; }
    public List<HistoricoStatus> getHistorico() { return historico; }
}