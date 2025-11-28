package com.safemoney.services;

import com.safemoney.domains.ContaBancaria;
import com.safemoney.domains.Usuario;
import com.safemoney.domains.dtos.ContaBancariaDTO;
import com.safemoney.mappers.ContaBancariaMapper;
import com.safemoney.repositories.ContaBancariaRepository;
import com.safemoney.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ContaBancariaService {

    private final ContaBancariaRepository repository;
    private final UsuarioService usuarioService;

    public ContaBancariaService(ContaBancariaRepository repository, UsuarioService usuarioService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
    }

    @Transactional(readOnly = true)
    public List<ContaBancariaDTO> findAll() {
        return ContaBancariaMapper.toDtoList(repository.findAll());
    }

    @Transactional(readOnly = true)
    public ContaBancariaDTO findById(Integer id) {
        if (id == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id é obrigatório");
        return ContaBancariaMapper.toDto(findEntityById(id));
    }

    public ContaBancaria findEntityById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Conta Bancária não encontrada! Id=" + id));
    }

    @Transactional
    public ContaBancariaDTO create(ContaBancariaDTO dto) {
        if (dto == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados obrigatórios");


        Usuario usuario = usuarioService.findEntityById(1);

        ContaBancaria entity = ContaBancariaMapper.toEntity(dto);
        entity.setId(null);
        entity.setUsuario(usuario);
        entity.setAtiva(true);

        return ContaBancariaMapper.toDto(repository.save(entity));
    }

    @Transactional
    public ContaBancariaDTO update(Integer id, ContaBancariaDTO dto) {
        if (id == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id é obrigatório");

        ContaBancaria entity = findEntityById(id);
        ContaBancariaMapper.copyToEntity(dto, entity);

        return ContaBancariaMapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Integer id) {
        if (id == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id é obrigatório");

        ContaBancaria entity = findEntityById(id);

        entity.setAtiva(false);
        repository.save(entity);
    }
}