package com.safemoney.services;

import com.safemoney.domains.Entidade;
import com.safemoney.domains.Usuario;
import com.safemoney.domains.dtos.EntidadeDTO;
import com.safemoney.mappers.EntidadeMapper;
import com.safemoney.repositories.EntidadeRepository;
import com.safemoney.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EntidadeService {

    private final EntidadeRepository repository;
    private final UsuarioService usuarioService;

    public EntidadeService(EntidadeRepository repository, UsuarioService usuarioService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
    }

    @Transactional(readOnly = true)
    public List<EntidadeDTO> findAll() {
        return EntidadeMapper.toDtoList(repository.findAll());
    }

    public Entidade findEntityById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Entidade não encontrada! Id=" + id));
    }

    @Transactional
    public EntidadeDTO create(EntidadeDTO dto) {
        if (dto == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados obrigatórios");

        Usuario usuario = usuarioService.findEntityById(1);

        Entidade entity = EntidadeMapper.toEntity(dto);
        entity.setId(null);
        entity.setUsuario(usuario);

        return EntidadeMapper.toDto(repository.save(entity));
    }

    @Transactional
    public EntidadeDTO update(Integer id, EntidadeDTO dto) {
        if (id == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id é obrigatório");

        Entidade entity = findEntityById(id);
        EntidadeMapper.copyToEntity(dto, entity);

        return EntidadeMapper.toDto(repository.save(entity));
    }
}