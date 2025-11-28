package com.safemoney.services;

import com.safemoney.domains.CentroCusto;
import com.safemoney.domains.Usuario;
import com.safemoney.domains.dtos.CentroCustoDTO;
import com.safemoney.mappers.CentroCustoMapper;
import com.safemoney.repositories.CentroCustoRepository;
import com.safemoney.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CentroCustoService {

    private final CentroCustoRepository repository;
    private final UsuarioService usuarioService;

    public CentroCustoService(CentroCustoRepository repository, UsuarioService usuarioService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
    }

    @Transactional(readOnly = true)
    public List<CentroCustoDTO> findAll() {
        return CentroCustoMapper.toDtoList(repository.findAll());
    }

    public CentroCusto findEntityById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Centro de Custo não encontrado! Id=" + id));
    }

    @Transactional
    public CentroCustoDTO create(CentroCustoDTO dto) {
        if (dto == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados obrigatórios");

        Usuario usuario = usuarioService.findEntityById(1);

        CentroCusto entity = CentroCustoMapper.toEntity(dto);
        entity.setId(null);
        entity.setUsuario(usuario);
        entity.setAtivo(true);

        return CentroCustoMapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Integer id) {
        if (id == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id é obrigatório");

        CentroCusto entity = findEntityById(id);
        entity.setAtivo(false);
        repository.save(entity);
    }
}