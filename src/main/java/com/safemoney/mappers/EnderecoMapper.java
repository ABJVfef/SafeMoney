package com.safemoney.mappers;

import com.safemoney.domains.Endereco;
import com.safemoney.domains.dtos.EnderecoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class EnderecoMapper {

    private EnderecoMapper() {}

    public static EnderecoDTO toDto(Endereco entity) {
        if (entity == null) return null;
        EnderecoDTO dto = new EnderecoDTO();
        dto.setId(entity.getId());
        dto.setRua(entity.getRua());
        dto.setNumero(entity.getNumero());
        dto.setCidade(entity.getCidade());
        return dto;
    }

    public static Endereco toEntity(EnderecoDTO dto) {
        if (dto == null) return null;
        Endereco entity = new Endereco();
        entity.setId(dto.getId());
        entity.setRua(dto.getRua());
        entity.setNumero(dto.getNumero());
        entity.setCidade(dto.getCidade());
        return entity;
    }

    public static void copyToEntity(EnderecoDTO dto, Endereco target) {
        if (dto == null || target == null) return;
        target.setRua(dto.getRua());
        target.setNumero(dto.getNumero());
        target.setCidade(dto.getCidade());
    }

    public static List<EnderecoDTO> toDtoList(Collection<Endereco> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(EnderecoMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<Endereco> toEntityList(Collection<EnderecoDTO> dtos) {
        if (dtos == null) return List.of();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(EnderecoMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static Page<EnderecoDTO> toDtoPage(Page<Endereco> page) {
        List<EnderecoDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}
