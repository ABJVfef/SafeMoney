package com.safemoney.mappers;

import com.safemoney.domains.CentroCusto;
import com.safemoney.domains.dtos.CentroCustoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class CentroCustoMapper {

    private CentroCustoMapper() {}

    public static CentroCustoDTO toDto(CentroCusto entity) {
        if (entity == null) return null;
        CentroCustoDTO dto = new CentroCustoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCodigo(entity.getCodigo());
        dto.setAtivo(entity.isAtivo());
        return dto;
    }

    public static CentroCusto toEntity(CentroCustoDTO dto) {
        if (dto == null) return null;
        CentroCusto entity = new CentroCusto();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setCodigo(dto.getCodigo());
        entity.setAtivo(dto.isAtivo());
        return entity;
    }

    public static void copyToEntity(CentroCustoDTO dto, CentroCusto target) {
        if (dto == null || target == null) return;
        target.setNome(dto.getNome());
        target.setCodigo(dto.getCodigo());
        target.setAtivo(dto.isAtivo());
    }

    public static List<CentroCustoDTO> toDtoList(Collection<CentroCusto> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(CentroCustoMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<CentroCusto> toEntityList(Collection<CentroCustoDTO> dtos) {
        if (dtos == null) return List.of();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(CentroCustoMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static Page<CentroCustoDTO> toDtoPage(Page<CentroCusto> page) {
        List<CentroCustoDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}