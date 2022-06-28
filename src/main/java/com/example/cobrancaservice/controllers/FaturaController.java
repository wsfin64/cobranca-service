package com.example.cobrancaservice.controllers;

import com.example.cobrancaservice.entities.DTO.StatusDTO;
import com.example.cobrancaservice.entities.Fatura;
import com.example.cobrancaservice.entities.Portador;
import com.example.cobrancaservice.entities.StatusFatura;
import com.example.cobrancaservice.services.FaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cobranca/api")
public class FaturaController {

    @Autowired
    FaturaService faturaService;

    @GetMapping("/portadores/faturamento/{diaFaturamento}")
    public ResponseEntity<List<Portador>> listarPortadoresPorDiaFaturamento(@PathVariable Long diaFaturamento){
        return ResponseEntity.ok(faturaService.listarPortadorPorDiaFaturamento(diaFaturamento));
    }

    @GetMapping("/faturas/portador/{idPortador}")
    public ResponseEntity<List<Fatura>> faturasPorPortador(@PathVariable Long idPortador){
        return ResponseEntity.ok(faturaService.getFaturasPorPortador(idPortador));
    }

    @GetMapping("/faturas")
    public ResponseEntity<List<Fatura>> listarFaturas(){
        return ResponseEntity.ok(faturaService.listarFaturas());
    }

    @GetMapping("/faturas/{id}")
    public ResponseEntity<Fatura> faturaPorId(@PathVariable Long id){
        return ResponseEntity.ok(faturaService.faturaPorId(id));
    }

    @GetMapping("/faturas/status")
    public List<StatusFatura> listaStatusFatura(){
        return faturaService.listarStatusFatura();
    }

    @PatchMapping("/faturas/{id}")
    public ResponseEntity<Fatura> atualizarStatusFatura(@PathVariable Long id, @RequestBody StatusDTO statusDTO){
        return ResponseEntity.ok(faturaService.alterarStatusFatura(id, statusDTO));
    }

    @DeleteMapping("faturas/{idFatura}")
    public ResponseEntity<Fatura> cancelarFatura(@PathVariable Long idFatura){
        return ResponseEntity.ok(faturaService.cancelarFatura(idFatura));
    }


}
