package com.example.cobrancaservice.controllers;

import com.example.cobrancaservice.entities.Portador;
import com.example.cobrancaservice.services.CobrancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cobranca/api")
public class CobrancaController {

    @Autowired
    CobrancaService cobrancaService;

    @GetMapping("/portadores/faturamento/{diaFaturamento}")
    public ResponseEntity<List<Portador>> listarPortadoresPorDiaFaturamento(@PathVariable Long diaFaturamento){
        return ResponseEntity.ok(cobrancaService.listarPortadorPorDiaFaturamento(diaFaturamento));
    }
}
