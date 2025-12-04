package com.safemoney.services;

import com.safemoney.domains.Usuario;
import com.safemoney.domains.dtos.UsuarioDTO;
import com.safemoney.mappers.UsuarioMapper;
import com.safemoney.repositories.UsuarioRepository;
import com.safemoney.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> findAll() {
        return UsuarioMapper.toDtoList(repository.findAll());
    }

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Integer id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id é obrigatório");
        }
        Usuario entity = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado! Id=" + id));
        return UsuarioMapper.toDto(entity);
    }

    public Usuario findEntityById(Integer id) {
        if (id == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id do usuário obrigatório");
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado! Id=" + id));
    }

    @Transactional
    public UsuarioDTO create(UsuarioDTO dto) {
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados do usuário são obrigatórios");
        }

        Usuario entity = UsuarioMapper.toEntity(dto);
        entity.setId(null);

        return UsuarioMapper.toDto(repository.save(entity));
    }

    @Transactional
    public UsuarioDTO update(Integer id, UsuarioDTO dto) {
        if (id == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id é obrigatório");
        if (dto == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados obrigatórios");

        Usuario entity = findEntityById(id);

        UsuarioMapper.copyToEntity(dto, entity);

        return UsuarioMapper.toDto(repository.save(entity));
    }
}