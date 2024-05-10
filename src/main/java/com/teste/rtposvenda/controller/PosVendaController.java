package com.teste.rtposvenda.controller;

import com.teste.rtposvenda.dto.PosVendaDto;
import com.teste.rtposvenda.dto.RetornoPosVendaDto;
import com.teste.rtposvenda.feign.client.PostClient;
import com.teste.rtposvenda.feign.dto.PostDto;
import com.teste.rtposvenda.service.PosVendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/aditamento")
public class PosVendaController {

    @Autowired
    private PosVendaService posVendaService;

    @Autowired
    private PostClient postClient;

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

    @GetMapping("/lista-posts")
    public List<PostDto> getAllPosts() {
        return postClient.getAllPosts();
    }

}
