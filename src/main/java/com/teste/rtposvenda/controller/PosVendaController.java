package com.teste.rtposvenda.controller;

import com.teste.rtposvenda.dto.PosVendaDto;
import com.teste.rtposvenda.dto.RetornoPosVendaDto;
import com.teste.rtposvenda.service.PosVendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/aditamento")
public class PosVendaController {

    @Autowired
    private PosVendaService posVendaService;

    @PostMapping("/altera-quantidade-parcelas")
    public ResponseEntity<RetornoPosVendaDto> alteraQuantParc(@RequestBody PosVendaDto posVendaDto) {
        RetornoPosVendaDto retornoPosVenda = posVendaService.alteraQuantidadeParcelas(posVendaDto);

        return ResponseEntity.ok().body(retornoPosVenda);
    }

    @PostMapping("/altera-dia-pagamento")
    public ResponseEntity<RetornoPosVendaDto> alteraDiaPag(@RequestBody PosVendaDto posVendaDto) {
        RetornoPosVendaDto retornoPosVenda = posVendaService.alteraDiaPagamento(posVendaDto);

        return ResponseEntity.ok().body(retornoPosVenda);
    }

}
