package com.safemoney.resources;

import com.safemoney.domains.dtos.TransferenciaDTO;
import com.safemoney.services.TransferenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/v1/transferencias")
public class TransferenciaResource {

    private final TransferenciaService service;

    public TransferenciaResource(TransferenciaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TransferenciaDTO> create(@RequestBody @Validated TransferenciaDTO dto) {
        TransferenciaDTO created = service.create(dto);
        return ResponseEntity.ok(created);
    }
}