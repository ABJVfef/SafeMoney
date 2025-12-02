package com.safemoney.resources;

import com.safemoney.domains.dtos.RecebimentoDTO;
import com.safemoney.services.RecebimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/v1/recebimentos")
public class RecebimentoResource {

    private final RecebimentoService service;

    public RecebimentoResource(RecebimentoService service) {
        this.service = service;
    }

    @PostMapping("/{lancamentoId}")
    public ResponseEntity<RecebimentoDTO> create(
            @PathVariable Long lancamentoId,
            @RequestBody @Validated RecebimentoDTO dto) {
        RecebimentoDTO created = service.create(lancamentoId, dto);
        return ResponseEntity.ok(created);
    }
}