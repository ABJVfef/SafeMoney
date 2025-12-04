package com.safemoney.resources;

import com.safemoney.domains.dtos.CentroCustoDTO;
import com.safemoney.services.CentroCustoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/centros-custo")
public class CentroCustoResource {

    private final CentroCustoService service;

    public CentroCustoResource(CentroCustoService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CentroCustoDTO>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping
    public ResponseEntity<Page<CentroCustoDTO>> list(
            @PageableDefault(size = 20, sort = "nome") Pageable pageable) {
        List<CentroCustoDTO> all = service.findAll();
        Page<CentroCustoDTO> page = new PageImpl<>(all, pageable, all.size());
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<CentroCustoDTO> create(@RequestBody @Validated(CentroCustoDTO.Create.class) CentroCustoDTO dto) {
        CentroCustoDTO created = service.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}