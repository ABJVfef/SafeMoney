package com.safemoney.resources;

import com.safemoney.domains.dtos.EnderecoDTO;
import com.safemoney.services.EnderecoService;
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
@RequestMapping("/api/v1/enderecos")
public class EnderecoResource {

    private final EnderecoService service;

    public EnderecoResource(EnderecoService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EnderecoDTO>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping
    public ResponseEntity<Page<EnderecoDTO>> list(
            @PageableDefault(size = 20, sort = "rua") Pageable pageable) {
        List<EnderecoDTO> all = service.findAll();
        Page<EnderecoDTO> page = new PageImpl<>(all, pageable, all.size());
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> create(@RequestBody @Validated(EnderecoDTO.Create.class) EnderecoDTO dto) {
        EnderecoDTO created = service.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{rua}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{rua}")
    public ResponseEntity<EnderecoDTO> update(
            @PathVariable String rua,
            @RequestBody @Validated(EnderecoDTO.Update.class) EnderecoDTO dto) {
        return ResponseEntity.ok(service.update(rua, dto));
    }
}