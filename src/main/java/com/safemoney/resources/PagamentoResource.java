package com.safemoney.resources;

import com.safemoney.domains.dtos.PagamentoDTO;
import com.safemoney.services.PagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/v1/pagamentos")
public class PagamentoResource {

    private final PagamentoService service;

    public PagamentoResource(PagamentoService service) {
        this.service = service;
    }

    @PostMapping("/{lancamentoId}")
    public ResponseEntity<PagamentoDTO> create(
            @PathVariable Long lancamentoId,
            @RequestBody @Validated PagamentoDTO dto) {
        PagamentoDTO created = service.create(lancamentoId, dto);
        return ResponseEntity.ok(created);
    }
}