package com.safemoney.mappers;

import com.safemoney.domains.FaturaCartao;
import com.safemoney.domains.dtos.FaturaCartaoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class FaturaCartaoMapper {

    private FaturaCartaoMapper() {}

    public static FaturaCartaoDTO toDto(FaturaCartao entity) {
        if (entity == null) return null;
        FaturaCartaoDTO dto = new FaturaCartaoDTO();
        dto.setId(entity.getId());
        dto.setCartaoId(entity.getCartao() != null ? entity.getCartao().getId() : null);
        dto.setCompetencia(entity.getCompetencia());
        dto.setDataFechamento(entity.getDataFechamento());
        dto.setDataVencimento(entity.getDataVencimento());
        dto.setValorTotal(entity.getValorTotal());
        dto.setStatusId(entity.getStatus() != null ? entity.getStatus().getId() : null);
        return dto;
    }

    public static List<FaturaCartaoDTO> toDtoList(Collection<FaturaCartao> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(FaturaCartaoMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Page<FaturaCartaoDTO> toDtoPage(Page<FaturaCartao> page) {
        List<FaturaCartaoDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}