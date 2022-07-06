package com.example.cobrancaservice.mensageria;

import com.example.cobrancaservice.entities.DTO.PagamentoDTO;
import com.example.cobrancaservice.services.FaturaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    @Autowired
    FaturaService faturaService;

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload String mensagem){
        System.out.println("Mensagem Recebida " + mensagem);

        faturaService.baixaFatura(mensagem);

    }
}
