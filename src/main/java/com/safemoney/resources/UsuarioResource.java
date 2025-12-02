package com.safemoney.resources;

import com.safemoney.domains.dtos.UsuarioDTO;
import com.safemoney.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioResource {

    private final UsuarioService service;

    public UsuarioResource(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody @Validated(UsuarioDTO.Create.class) UsuarioDTO dto) {
        UsuarioDTO created = service.create(dto); // Nota: Se não tiver create no UsuarioService, adicione-o.
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }
}