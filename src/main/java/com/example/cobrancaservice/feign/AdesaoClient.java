package com.example.cobrancaservice.feign;

import com.example.cobrancaservice.entities.Portador;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@FeignClient(name = "adesao-service", url = "https://adesao-service-pdist.herokuapp.com/api")
//@FeignClient(name = "adesao-service", url = "http://localhost:8080/api")
public interface AdesaoClient {

    @GetMapping("/portadores/faturamento/{diaFaturamento}")
    List<Portador> listarPortadoresDiaFaturamento(@PathVariable Long diaFaturamento);
}
