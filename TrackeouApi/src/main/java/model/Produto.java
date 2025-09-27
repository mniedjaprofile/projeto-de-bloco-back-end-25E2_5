package model;

public class Produto {
    private Long idProduto;
    private String nome;
    private String descricao;
    private Double preco;

    public Produto(Long idProduto, String nome, String descricao, Double preco) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Long getIdProduto() { return idProduto; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public Double getPreco() { return preco; }
}