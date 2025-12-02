package com.safemoney.resources;

import com.safemoney.domains.dtos.LancamentoDTO;
import com.safemoney.services.LancamentoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/lancamentos")
public class LancamentoResource {

    private final LancamentoService service;

    public LancamentoResource(LancamentoService service) {
        this.service = service;
    }


    @GetMapping("/filter")
    public ResponseEntity<List<LancamentoDTO>> findWithFilters(
            @RequestParam(required = false) Integer tipo,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) LocalDate inicio,
            @RequestParam(required = false) LocalDate fim
    ) {
        return ResponseEntity.ok(service.findWithFilters(tipo, status, inicio, fim));
    }


    @GetMapping
    public ResponseEntity<Page<LancamentoDTO>> list(
            @PageableDefault(size = 20, sort = "dataVencimento") Pageable pageable) {
        List<LancamentoDTO> all = service.findAll();
        Page<LancamentoDTO> page = new PageImpl<>(all, pageable, all.size());
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<LancamentoDTO> create(@RequestBody @Validated(LancamentoDTO.Create.class) LancamentoDTO dto) {
        LancamentoDTO created = service.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LancamentoDTO> update(
            @PathVariable Long id,
            @RequestBody @Validated(LancamentoDTO.Update.class) LancamentoDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        service.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}