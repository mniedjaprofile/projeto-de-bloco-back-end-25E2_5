package model;

public class Cliente {
    private Long idCliente;
    private String nome;
    private String cpf;
    private String email;
    private String senhaHash; // hash SHA-256
    private boolean ativo;
    private boolean preferenciaNotificacao;

    public Cliente(Long idCliente, String nome, String cpf, String email, String senhaHash, boolean ativo, boolean preferenciaNotificacao) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senhaHash = senhaHash;
        this.ativo = ativo;
        this.preferenciaNotificacao = preferenciaNotificacao;
    }

    public Long getIdCliente() { return idCliente; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }
    public String getSenhaHash() { return senhaHash; }
    public boolean isAtivo() { return ativo; }
    public boolean isPreferenciaNotificacao() { return preferenciaNotificacao; }

}