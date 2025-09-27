package model;

import java.time.LocalDateTime;

public class Endereco {
    private String rua;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private LocalDateTime criadoEm;

    public Endereco(String rua, int numero, String complemento, String bairro,
                    String cidade, String estado, String cep, LocalDateTime criadoEm) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.criadoEm = criadoEm;
    }

    public String getRua() { return rua; }
    public int getNumero() { return numero; }
    public String getComplemento() { return complemento; }
    public String getBairro() { return bairro; }
    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }
    public String getCep() { return cep; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
}