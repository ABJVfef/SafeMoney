package com.safemoney.services;

import com.safemoney.domains.CartaoCredito;
import com.safemoney.domains.Usuario;
import com.safemoney.domains.dtos.CartaoCreditoDTO;
import com.safemoney.mappers.CartaoCreditoMapper;
import com.safemoney.repositories.CartaoCreditoRepository;
import com.safemoney.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CartaoCreditoService {

    private final CartaoCreditoRepository repository;
    private final UsuarioService usuarioService;

    public CartaoCreditoService(CartaoCreditoRepository repository, UsuarioService usuarioService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
    }

    @Transactional(readOnly = true)
    public List<CartaoCreditoDTO> findAll() {
        return CartaoCreditoMapper.toDtoList(repository.findAll());
    }

    @Transactional(readOnly = true)
    public CartaoCreditoDTO findById(Integer id) {
        if (id == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id é obrigatório");
        return CartaoCreditoMapper.toDto(findEntityById(id));
    }

    public CartaoCredito findEntityById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cartão não encontrado! Id=" + id));
    }

    @Transactional
    public CartaoCreditoDTO create(CartaoCreditoDTO dto) {
        if (dto == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados obrigatórios");

        Usuario usuario = usuarioService.findEntityById(1);

        CartaoCredito entity = CartaoCreditoMapper.toEntity(dto);
        entity.setId(null);
        entity.setUsuario(usuario);
        entity.setAtivo(true);

        return CartaoCreditoMapper.toDto(repository.save(entity));
    }

    @Transactional
    public CartaoCreditoDTO update(Integer id, CartaoCreditoDTO dto) {
        if (id == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id é obrigatório");

        CartaoCredito entity = findEntityById(id);
        CartaoCreditoMapper.copyToEntity(dto, entity);

        return CartaoCreditoMapper.toDto(repository.save(entity));
    }
}