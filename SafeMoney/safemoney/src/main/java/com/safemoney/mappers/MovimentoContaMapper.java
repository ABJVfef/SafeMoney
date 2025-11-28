package com.safemoney.mappers;

import com.safemoney.domains.ContaBancaria;
import com.safemoney.domains.MovimentoConta;
import com.safemoney.domains.dtos.MovimentoContaDTO;
import com.safemoney.domains.enums.TipoTransacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class MovimentoContaMapper {

    private MovimentoContaMapper() {}

    public static MovimentoContaDTO toDto(MovimentoConta entity) {
        if (entity == null) return null;
        MovimentoContaDTO dto = new MovimentoContaDTO();
        dto.setId(entity.getId());
        dto.setContaId(entity.getConta() != null ? entity.getConta().getId() : null);
        dto.setDataMovimento(entity.getDataMovimento());
        dto.setTipoId(entity.getTipo() != null ? entity.getTipo().getId() : null);
        dto.setValor(entity.getValor());
        dto.setHistorico(entity.getHistorico());
        dto.setReferenciaId(entity.getReferenciaId());
        dto.setReferenciaTipo(entity.getReferenciaTipo());
        return dto;
    }

    public static MovimentoConta toEntity(MovimentoContaDTO dto, ContaBancaria conta) {
        if (dto == null) return null;
        MovimentoConta entity = new MovimentoConta();
        entity.setId(dto.getId());
        entity.setConta(conta);
        entity.setDataMovimento(dto.getDataMovimento());
        entity.setTipo(TipoTransacao.toEnum(dto.getTipoId()));
        entity.setValor(dto.getValor());
        entity.setHistorico(dto.getHistorico());
        entity.setReferenciaId(dto.getReferenciaId());
        entity.setReferenciaTipo(dto.getReferenciaTipo());
        return entity;
    }

    public static List<MovimentoContaDTO> toDtoList(Collection<MovimentoConta> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(MovimentoContaMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Page<MovimentoContaDTO> toDtoPage(Page<MovimentoConta> page) {
        List<MovimentoContaDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}