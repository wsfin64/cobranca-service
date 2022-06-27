package com.example.cobrancaservice.services;

import com.example.cobrancaservice.FaturaRepository;
import com.example.cobrancaservice.config.SpringConfig;
import com.example.cobrancaservice.entities.Fatura;
import com.example.cobrancaservice.entities.Portador;
import com.example.cobrancaservice.entities.StatusFatura;
import com.example.cobrancaservice.feign.AdesaoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@EnableAsync
public class CobrancaService {

    @Autowired
    AdesaoClient adesaoClient;

    @Autowired
    FaturaRepository faturaRepository;

    public List<Portador> listarPortadorPorDiaFaturamento(Long diaFaturamento){
        List<Portador> portadores = adesaoClient.listarPortadoresDiaFaturamento(diaFaturamento);

        return portadores;
    }

    @Async
    @Scheduled(fixedRate = 5000)
    public void scheduleFixedRateTask() {

        LocalDate hoje = LocalDate.now();

        int dia = hoje.getDayOfMonth();

        List<Portador> portadores = this.listarPortadorPorDiaFaturamento(Long.valueOf(dia));

        LocalDate diaVencimento = hoje.plusDays(10);



        for (Portador portador : portadores){

            Long idPortador = portador.getIdPortador();
            Long idAssistencia = portador.getAssistencia().getIdAssistencia();
            String nomePortador = portador.getNome();
            Double valorAssistencia = portador.getAssistencia().getValor();

            List<Fatura> faturasRegistradas = faturaRepository.findFaturaByIdAssistenciaAndIdPortadorAndDataVenciamento(idAssistencia, idPortador, diaVencimento);

            if (!faturasRegistradas.isEmpty()){
                for(Fatura fat : faturasRegistradas){
                    if (fat.getIdPortador() != idPortador && fat.getIdAssistencia() != idAssistencia && fat.getDataVenciamento() != diaVencimento){

                        Fatura fatura = new Fatura(idPortador, idAssistencia, nomePortador, valorAssistencia, StatusFatura.PENDENTE.toString(), diaVencimento);

                        faturaRepository.save(fatura);

                        System.out.printf("Nova Fatura gerada, vencimento = " + fatura.getDataVenciamento());
                    }
                }
            }else {
                Fatura fatura = new Fatura(idPortador, idAssistencia, nomePortador, valorAssistencia, StatusFatura.PENDENTE.toString(), diaVencimento);

                faturaRepository.save(fatura);
                System.out.printf("Nova Fatura gerada, vencimento = " + fatura.getDataVenciamento());
            }


        }




    }
}
