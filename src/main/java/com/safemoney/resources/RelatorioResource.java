package com.safemoney.resources;

import com.safemoney.services.ExtratoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/relatorios")
public class RelatorioResource {

    private final ExtratoService extratoService;

    public RelatorioResource(ExtratoService extratoService) {
        this.extratoService = extratoService;
    }

    @GetMapping("/extrato")
    public ResponseEntity<Map<String, Object>> getExtrato(
            @RequestParam Integer contaId,
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim) {
        Map<String, Object> extrato = extratoService.gerarExtrato(contaId, inicio, fim);
        return ResponseEntity.ok(extrato);
    }
}