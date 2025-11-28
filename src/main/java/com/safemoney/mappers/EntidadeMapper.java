package com.safemoney.mappers;

import com.safemoney.domains.Entidade;
import com.safemoney.domains.dtos.EntidadeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class EntidadeMapper {

    private EntidadeMapper() {}

    public static EntidadeDTO toDto(Entidade entity) {
        if (entity == null) return null;
        EntidadeDTO dto = new EntidadeDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDocumento(entity.getDocumento());
        dto.setTipo(entity.getTipo());
        return dto;
    }

    public static Entidade toEntity(EntidadeDTO dto) {
        if (dto == null) return null;
        Entidade entity = new Entidade();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setDocumento(dto.getDocumento());
        entity.setTipo(dto.getTipo());
        return entity;
    }

    public static void copyToEntity(EntidadeDTO dto, Entidade target) {
        if (dto == null || target == null) return;
        target.setNome(dto.getNome());
        target.setDocumento(dto.getDocumento());
        target.setTipo(dto.getTipo());
    }

    public static List<EntidadeDTO> toDtoList(Collection<Entidade> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(EntidadeMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<Entidade> toEntityList(Collection<EntidadeDTO> dtos) {
        if (dtos == null) return List.of();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(EntidadeMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static Page<EntidadeDTO> toDtoPage(Page<Entidade> page) {
        List<EntidadeDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}