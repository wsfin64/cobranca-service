package com.example.cobrancaservice.entities;


public class Assistencia {

    private Long idAssistencia;

    private String nome;

    private Double valor;

    private String descricao;

    private Boolean ativo = true;

    public Assistencia(String nome, Double valor, String descricao){
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
    }


    public Assistencia() {}

    public Long getIdAssistencia() {
        return idAssistencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
