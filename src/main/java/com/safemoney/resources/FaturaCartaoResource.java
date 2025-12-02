package com.safemoney.resources;

import com.safemoney.domains.dtos.FaturaCartaoDTO;
import com.safemoney.services.FaturaCartaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/cartoes")
public class FaturaCartaoResource {

    private final FaturaCartaoService service;

    public FaturaCartaoResource(FaturaCartaoService service) {
        this.service = service;
    }

    @GetMapping("/{id}/faturas")
    public ResponseEntity<List<FaturaCartaoDTO>> listarFaturas(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findByCartao(id));
    }

    @PostMapping("/{id}/faturas/fechamento")
    public ResponseEntity<FaturaCartaoDTO> fecharFatura(
            @PathVariable Integer id,
            @RequestParam String competencia) {
        FaturaCartaoDTO dto = service.fecharFatura(id, competencia);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{id}/faturas/{faturaId}/pagar")
    public ResponseEntity<Void> pagarFatura(
            @PathVariable Integer id,
            @PathVariable Long faturaId,
            @RequestParam Integer contaId) {
        service.pagarFatura(id, faturaId, contaId);
        return ResponseEntity.ok().build();
    }
}