package com.example.cobrancaservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Fatura implements Serializable {

    private static final long serialVerionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFatura;

    private Long idPortador;
    private Long idAssistencia;
    private String nomePortador;
    private Double valorAssistencia;
    private String status;
    private String codigoFatura;
    private LocalDate dataVenciamento;
    private String assistenciaNome;

    public Fatura(){}

    public Fatura(Long idPortador, Long idAssistencia, String nomePortador, Double valorAssistencia, String status, LocalDate dataVenciamento, String assistenciaNome, String codigoFatura) {
        this.idPortador = idPortador;
        this.idAssistencia = idAssistencia;
        this.nomePortador = nomePortador;
        this.valorAssistencia = valorAssistencia;
        this.status = status;
        this.dataVenciamento = dataVenciamento;
        this.assistenciaNome = assistenciaNome;
        this.codigoFatura = codigoFatura;
    }

    public Long getIdPortador() {
        return idPortador;
    }

    public void setIdPortador(Long idPortador) {
        this.idPortador = idPortador;
    }

    public Long getIdAssistencia() {
        return idAssistencia;
    }

    public void setIdAssistencia(Long idAssistencia) {
        this.idAssistencia = idAssistencia;
    }

    public String getNomePortador() {
        return nomePortador;
    }

    public void setNomePortador(String nomePortador) {
        this.nomePortador = nomePortador;
    }

    public Double getValorAssistencia() {
        return valorAssistencia;
    }

    public void setValorAssistencia(Double valorAssistencia) {
        this.valorAssistencia = valorAssistencia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCodigoFatura() {
        return codigoFatura;
    }

    public void setCodigoFatura(String codigoFatura) {
        this.codigoFatura = codigoFatura;
    }

    public LocalDate getDataVenciamento() {
        return dataVenciamento;
    }

    public void setDataVenciamento(LocalDate dataVenciamento) {
        this.dataVenciamento = dataVenciamento;
    }
}
