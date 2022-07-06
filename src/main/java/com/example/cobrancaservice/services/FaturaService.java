package com.example.cobrancaservice.services;

import com.example.cobrancaservice.repositories.FaturaRepository;
import com.example.cobrancaservice.entities.DTO.StatusDTO;
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
import java.util.Arrays;
import java.util.List;

@Service
@EnableAsync
public class FaturaService {

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
    public void verificarFaturamentos() {

        LocalDate hoje = LocalDate.now();

        int dia = hoje.getDayOfMonth();

        List<Portador> portadores = this.listarPortadorPorDiaFaturamento(Long.valueOf(dia));

        LocalDate diaVencimento = hoje.plusDays(10);


        for (Portador portador : portadores){

            if (portador.getAssistencia() != null){
                Long idPortador = portador.getIdPortador();
                Long idAssistencia = portador.getAssistencia().getIdAssistencia();
                String nomePortador = portador.getNome();
                Double valorAssistencia = portador.getAssistencia().getValor();
                String assistenciaNome = portador.getAssistencia().getNome();

                List<Fatura> faturasRegistradas = faturaRepository.findFaturaByIdAssistenciaAndIdPortadorAndDataVenciamento(idAssistencia, idPortador, diaVencimento);

                if (!faturasRegistradas.isEmpty()){
                    for(Fatura fat : faturasRegistradas){
                        if (fat.getIdPortador() != idPortador && fat.getIdAssistencia() != idAssistencia && fat.getDataVenciamento() != diaVencimento){

                            if(portador.getAdesaoAtiva() == true){
                                Fatura fatura = new Fatura(idPortador, idAssistencia, nomePortador, valorAssistencia, StatusFatura.PENDENTE.toString(), diaVencimento, assistenciaNome, this.gerarCodigoDeBarras(idAssistencia, idPortador, diaVencimento));

                                faturaRepository.save(fatura);

                                System.out.printf("Nova Fatura gerada, vencimento = " + fatura.getDataVenciamento());
                            }


                        }
                    }
                }else {
                    if(portador.getAdesaoAtiva() == true){
                        Fatura fatura = new Fatura(idPortador, idAssistencia, nomePortador, valorAssistencia, StatusFatura.PENDENTE.toString(), diaVencimento, assistenciaNome, this.gerarCodigoDeBarras(idAssistencia, idPortador, diaVencimento));

                        faturaRepository.save(fatura);

                        System.out.printf("Nova Fatura gerada, vencimento = " + fatura.getDataVenciamento());
                    }
                }
            }


        }

    }

    public String gerarCodigoDeBarras(Long idAssistencia, Long idPortador, LocalDate dataVencimento){

        String cod = "" + idAssistencia;

        cod = cod + dataVencimento.getDayOfMonth() + dataVencimento.getMonthValue() + dataVencimento.getYear();

        cod = cod + idPortador;

        return cod;
    }

    public List<Fatura> getFaturasPorPortador(Long idPortador){
        return faturaRepository.findFaturaByIdPortador(idPortador);
    }

    public List<Fatura> listarFaturas(){
        return faturaRepository.findAll();
    }

    public Fatura faturaPorId(Long id){
        return faturaRepository.findById(id).get();
    }

    public List<StatusFatura> listarStatusFatura(){
        return Arrays.asList(StatusFatura.values());
    }

    public Fatura alterarStatusFatura(Long idfatura, StatusDTO statusDTO){
        Fatura fatura = this.faturaPorId(idfatura);

        List<StatusFatura> status = Arrays.asList(StatusFatura.values());

        for(StatusFatura st : status){
            if (String.valueOf(st).equals(statusDTO.getStatus())){
                fatura.setStatus(statusDTO.getStatus());

                return faturaRepository.save(fatura);
            }
        }

        return fatura;
    }

    public Fatura cancelarFatura(Long idFatura){
        Fatura fatura = this.faturaPorId(idFatura);

        fatura.setStatus("CANCELADA");

        return faturaRepository.save(fatura);
    }

    public Fatura buscarFaturaPorCodigoFatura(String codigoFatura){
        return faturaRepository.findFaturaByCodigoFatura(codigoFatura);
    }

    public void baixaFatura(String codigoFatura){

        Fatura fatura = buscarFaturaPorCodigoFatura(codigoFatura);

        if (fatura != null && !fatura.getStatus().equals("PAGA")){
            fatura.setStatus(StatusFatura.PAGA.toString());
            faturaRepository.save(fatura);

            System.out.println("Fatura de ID " + fatura.getCodigoFatura() + " paga");
        }


    }

}
