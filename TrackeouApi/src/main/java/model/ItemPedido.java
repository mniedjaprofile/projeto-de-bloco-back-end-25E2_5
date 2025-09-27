package model;

public class ItemPedido {
    private Long idProduto;
    private Integer quantidade;
    private Double precoUnitario;

    public ItemPedido(Long idProduto, Integer quantidade, Double precoUnitario) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public Long getIdProduto() { return idProduto; }
    public Integer getQuantidade() { return quantidade; }
    public Double getPrecoUnitario() { return precoUnitario; }
}