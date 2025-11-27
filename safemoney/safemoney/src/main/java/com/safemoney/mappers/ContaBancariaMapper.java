package com.safemoney.mappers;

import com.safemoney.domains.ContaBancaria;
import com.safemoney.domains.dtos.ContaBancariaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class ContaBancariaMapper {

    private ContaBancariaMapper() {}

    public static ContaBancariaDTO toDto(ContaBancaria entity) {
        if (entity == null) return null;
        return new ContaBancariaDTO(
                entity.getId(),
                entity.getInstituicao(),
                entity.getApelido(),
                entity.getAgencia(),
                entity.getNumero(),
                entity.getSaldoInicial(),
                entity.getDataSaldoInicial(),
                entity.isAtiva()
        );
    }

    public static ContaBancaria toEntity(ContaBancariaDTO dto) {
        if (dto == null) return null;
        ContaBancaria entity = new ContaBancaria();
        entity.setId(dto.getId());
        entity.setInstituicao(dto.getInstituicao());
        entity.setApelido(dto.getApelido());
        entity.setAgencia(dto.getAgencia());
        entity.setNumero(dto.getNumero());
        entity.setSaldoInicial(dto.getSaldoInicial());
        entity.setDataSaldoInicial(dto.getDataSaldoInicial());
        entity.setAtiva(dto.isAtiva());
        return entity;
    }

    public static void copyToEntity(ContaBancariaDTO dto, ContaBancaria target) {
        if (dto == null || target == null) return;
        target.setInstituicao(dto.getInstituicao());
        target.setApelido(dto.getApelido());
        target.setAgencia(dto.getAgencia());
        target.setNumero(dto.getNumero());
        target.setAtiva(dto.isAtiva());
    }

    public static List<ContaBancariaDTO> toDtoList(Collection<ContaBancaria> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(ContaBancariaMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<ContaBancaria> toEntityList(Collection<ContaBancariaDTO> dtos) {
        if (dtos == null) return List.of();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(ContaBancariaMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static Page<ContaBancariaDTO> toDtoPage(Page<ContaBancaria> page) {
        List<ContaBancariaDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}