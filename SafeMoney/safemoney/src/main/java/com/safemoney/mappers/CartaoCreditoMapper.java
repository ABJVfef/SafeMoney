package com.safemoney.mappers;

import com.safemoney.domains.CartaoCredito;
import com.safemoney.domains.dtos.CartaoCreditoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class CartaoCreditoMapper {

    private CartaoCreditoMapper() {}

    public static CartaoCreditoDTO toDto(CartaoCredito entity) {
        if (entity == null) return null;
        CartaoCreditoDTO dto = new CartaoCreditoDTO();
        dto.setId(entity.getId());
        dto.setBandeira(entity.getBandeira());
        dto.setEmissor(entity.getEmissor());
        dto.setApelido(entity.getApelido());
        dto.setFechamentoFaturaDia(entity.getFechamentoFaturaDia());
        dto.setVencimentoFaturaDia(entity.getVencimentoFaturaDia());
        dto.setAtivo(entity.isAtivo());
        return dto;
    }

    public static CartaoCredito toEntity(CartaoCreditoDTO dto) {
        if (dto == null) return null;
        CartaoCredito entity = new CartaoCredito();
        entity.setId(dto.getId());
        entity.setBandeira(dto.getBandeira());
        entity.setEmissor(dto.getEmissor());
        entity.setApelido(dto.getApelido());
        entity.setFechamentoFaturaDia(dto.getFechamentoFaturaDia());
        entity.setVencimentoFaturaDia(dto.getVencimentoFaturaDia());
        entity.setAtivo(dto.isAtivo());
        return entity;
    }

    public static void copyToEntity(CartaoCreditoDTO dto, CartaoCredito target) {
        if (dto == null || target == null) return;
        target.setBandeira(dto.getBandeira());
        target.setEmissor(dto.getEmissor());
        target.setApelido(dto.getApelido());
        target.setFechamentoFaturaDia(dto.getFechamentoFaturaDia());
        target.setVencimentoFaturaDia(dto.getVencimentoFaturaDia());
        target.setAtivo(dto.isAtivo());
    }

    public static List<CartaoCreditoDTO> toDtoList(Collection<CartaoCredito> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(CartaoCreditoMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<CartaoCredito> toEntityList(Collection<CartaoCreditoDTO> dtos) {
        if (dtos == null) return List.of();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(CartaoCreditoMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static Page<CartaoCreditoDTO> toDtoPage(Page<CartaoCredito> page) {
        List<CartaoCreditoDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}