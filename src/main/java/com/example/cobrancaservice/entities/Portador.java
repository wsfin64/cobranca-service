package com.example.cobrancaservice.entities;


public class Portador {

    private Long idPortador;

    private String nome;
    private String cpf;
    private String telefone;

    private Assistencia assistencia;

    private Boolean adesaoAtiva;

    private Long diaFaturamento;

    public Portador(){}

    public Portador(String nome, String cpf, String telefone, Assistencia assistencia, Long diaFaturamento){
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.assistencia = assistencia;
        this.diaFaturamento = diaFaturamento;
        this.adesaoAtiva = true;
    }

    public Long getIdPortador() {
        return idPortador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getDiaFaturamento() {
        return diaFaturamento;
    }

    public void setDiaFaturamento(Long diaFaturamento) {
        this.diaFaturamento = diaFaturamento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Assistencia getAssistencia() {
        return assistencia;
    }

    public void setAssistencia(Assistencia assistencia) {
        this.assistencia = assistencia;
    }

    public Boolean getAdesaoAtiva() {
        return adesaoAtiva;
    }

    public void setAdesaoAtiva(Boolean adesaoAtiva) {
        this.adesaoAtiva = adesaoAtiva;
    }
}
