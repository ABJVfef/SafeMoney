package com.safemoney.resources;

import com.safemoney.domains.dtos.ContaBancariaDTO;
import com.safemoney.services.ContaBancariaService;
import com.safemoney.services.ExtratoService;
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
import java.util.Map;

@Validated
@RestController
@RequestMapping("/api/v1/contas")
public class ContaBancariaResource {

    private final ContaBancariaService service;
    private final ExtratoService extratoService;

    public ContaBancariaResource(ContaBancariaService service, ExtratoService extratoService) {
        this.service = service;
        this.extratoService = extratoService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ContaBancariaDTO>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping
    public ResponseEntity<Page<ContaBancariaDTO>> list(
            @PageableDefault(size = 20, sort = "apelido") Pageable pageable) {
        List<ContaBancariaDTO> all = service.findAll();
        Page<ContaBancariaDTO> page = new PageImpl<>(all, pageable, all.size());
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaBancariaDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ContaBancariaDTO> create(@RequestBody @Validated(ContaBancariaDTO.Create.class) ContaBancariaDTO dto) {
        ContaBancariaDTO created = service.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaBancariaDTO> update(
            @PathVariable Integer id,
            @RequestBody @Validated(ContaBancariaDTO.Update.class) ContaBancariaDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/extrato")
    public ResponseEntity<Map<String, Object>> getExtrato(
            @PathVariable Integer id,
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim) {
        Map<String, Object> extrato = extratoService.gerarExtrato(id, inicio, fim);
        return ResponseEntity.ok(extrato);
    }
}