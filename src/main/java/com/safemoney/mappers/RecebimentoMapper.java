package com.safemoney.mappers;

import com.safemoney.domains.ContaBancaria;
import com.safemoney.domains.Lancamento;
import com.safemoney.domains.Recebimento;
import com.safemoney.domains.dtos.RecebimentoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class RecebimentoMapper {

    private RecebimentoMapper() {}

    public static RecebimentoDTO toDto(Recebimento entity) {
        if (entity == null) return null;
        RecebimentoDTO dto = new RecebimentoDTO();
        dto.setDataRecebimento(entity.getDataRecebimento());
        dto.setValorRecebido(entity.getValorRecebido());
        dto.setObservacao(entity.getObservacao());
        dto.setContaDestinoId(entity.getContaDestino() != null ? entity.getContaDestino().getId() : null);
        return dto;
    }

    public static Recebimento toEntity(RecebimentoDTO dto, Lancamento lancamento, ContaBancaria contaDestino) {
        if (dto == null) return null;
        Recebimento entity = new Recebimento();
        entity.setDataRecebimento(dto.getDataRecebimento());
        entity.setValorRecebido(dto.getValorRecebido());
        entity.setObservacao(dto.getObservacao());
        entity.setLancamento(lancamento);
        entity.setContaDestino(contaDestino);
        return entity;
    }

    public static List<RecebimentoDTO> toDtoList(Collection<Recebimento> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(RecebimentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Page<RecebimentoDTO> toDtoPage(Page<Recebimento> page) {
        List<RecebimentoDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}