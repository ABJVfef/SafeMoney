package com.safemoney.mappers;

import com.safemoney.domains.Usuario;
import com.safemoney.domains.dtos.UsuarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class UsuarioMapper {

    private UsuarioMapper() {}

    public static UsuarioDTO toDto(Usuario entity) {
        if (entity == null) return null;
        return new UsuarioDTO(entity.getId(), entity.getNome(), entity.getEmail());
    }

    public static Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) return null;
        Usuario entity = new Usuario();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        return entity;
    }

    public static void copyToEntity(UsuarioDTO dto, Usuario target) {
        if (dto == null || target == null) return;
        target.setNome(dto.getNome());
        target.setEmail(dto.getEmail());
    }



    public static List<UsuarioDTO> toDtoList(Collection<Usuario> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(UsuarioMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<Usuario> toEntityList(Collection<UsuarioDTO> dtos) {
        if (dtos == null) return List.of();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(UsuarioMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static Page<UsuarioDTO> toDtoPage(Page<Usuario> page) {
        List<UsuarioDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}