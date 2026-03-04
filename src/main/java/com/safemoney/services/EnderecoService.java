package com.safemoney.services;

import com.safemoney.domains.Endereco;
import com.safemoney.domains.Usuario;
import com.safemoney.domains.dtos.EnderecoDTO;
import com.safemoney.mappers.EnderecoMapper;
import com.safemoney.repositories.EnderecoRepository;
import com.safemoney.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository repository;
    private final UsuarioService usuarioService;

    public EnderecoService(EnderecoRepository repository, UsuarioService usuarioService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
    }

    @Transactional(readOnly = true)
    public List<EnderecoDTO> findAll() {
        return EnderecoMapper.toDtoList(repository.findAll());
    }

    public Endereco findEndereco(String rua) {
        return repository.findByRua(rua)
                .orElseThrow(() -> new ObjectNotFoundException("Endereco não encontrada! Id=" + rua));
    }

    @Transactional
    public EnderecoDTO create(EnderecoDTO dto) {
        if (dto == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados obrigatórios");

        Usuario usuario = usuarioService.findEntityById(1);

        Endereco entity = EnderecoMapper.toEntity(dto);
        entity.setId(null);
        entity.setUsuario(usuario);

        return EnderecoMapper.toDto(repository.save(entity));
    }

    @Transactional
    public EnderecoDTO update(String rua, EnderecoDTO dto) {
        if (rua == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id é obrigatório");

        Endereco entity = findEndereco(rua);
        EnderecoMapper.copyToEntity(dto, entity);

        return EnderecoMapper.toDto(repository.save(entity));
    }
}
