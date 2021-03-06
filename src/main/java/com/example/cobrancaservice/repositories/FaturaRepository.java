package com.example.cobrancaservice.repositories;

import com.example.cobrancaservice.entities.Fatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long> {

    List<Fatura> findFaturaByIdAssistenciaAndIdPortadorAndDataVenciamento(Long idAssistencia, Long idPortador, LocalDate dataVencimento);

    List<Fatura> findFaturaByIdPortador(Long idPortador);

    Fatura findFaturaByCodigoFatura(String codigoFatura);
}
