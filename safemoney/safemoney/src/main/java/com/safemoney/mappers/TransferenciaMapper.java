package com.safemoney.mappers;

import com.safemoney.domains.ContaBancaria;
import com.safemoney.domains.Transferencia;
import com.safemoney.domains.dtos.TransferenciaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class TransferenciaMapper {

    private TransferenciaMapper() {}

    public static TransferenciaDTO toDto(Transferencia entity) {
        if (entity == null) return null;
        TransferenciaDTO dto = new TransferenciaDTO();
        dto.setData(entity.getData());
        dto.setValor(entity.getValor());
        dto.setObservacao(entity.getObservacao());
        dto.setContaOrigemId(entity.getContaOrigem() != null ? entity.getContaOrigem().getId() : null);
        dto.setContaDestinoId(entity.getContaDestino() != null ? entity.getContaDestino().getId() : null);
        return dto;
    }

    public static Transferencia toEntity(TransferenciaDTO dto, ContaBancaria origem, ContaBancaria destino) {
        if (dto == null) return null;
        Transferencia entity = new Transferencia();
        entity.setData(dto.getData());
        entity.setValor(dto.getValor());
        entity.setObservacao(dto.getObservacao());
        entity.setContaOrigem(origem);
        entity.setContaDestino(destino);
        return entity;
    }

    public static List<TransferenciaDTO> toDtoList(Collection<Transferencia> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(TransferenciaMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Page<TransferenciaDTO> toDtoPage(Page<Transferencia> page) {
        List<TransferenciaDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}