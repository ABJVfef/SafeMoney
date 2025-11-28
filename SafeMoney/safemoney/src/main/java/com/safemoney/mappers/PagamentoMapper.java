package com.safemoney.mappers;

import com.safemoney.domains.ContaBancaria;
import com.safemoney.domains.Lancamento;
import com.safemoney.domains.Pagamento;
import com.safemoney.domains.dtos.PagamentoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class PagamentoMapper {

    private PagamentoMapper() {}

    public static PagamentoDTO toDto(Pagamento entity) {
        if (entity == null) return null;
        PagamentoDTO dto = new PagamentoDTO();
        dto.setDataPagamento(entity.getDataPagamento());
        dto.setValorPago(entity.getValorPago());
        dto.setObservacao(entity.getObservacao());
        dto.setContaOrigemId(entity.getContaOrigem() != null ? entity.getContaOrigem().getId() : null);
        return dto;
    }

    public static Pagamento toEntity(PagamentoDTO dto, Lancamento lancamento, ContaBancaria contaOrigem) {
        if (dto == null) return null;
        Pagamento entity = new Pagamento();
        entity.setDataPagamento(dto.getDataPagamento());
        entity.setValorPago(dto.getValorPago());
        entity.setObservacao(dto.getObservacao());
        entity.setLancamento(lancamento);
        entity.setContaOrigem(contaOrigem);
        return entity;
    }

    public static void copyToEntity(PagamentoDTO dto, Pagamento target) {
        if (dto == null || target == null) return;
        target.setDataPagamento(dto.getDataPagamento());
        target.setValorPago(dto.getValorPago());
        target.setObservacao(dto.getObservacao());
    }

    public static List<PagamentoDTO> toDtoList(Collection<Pagamento> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(PagamentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Page<PagamentoDTO> toDtoPage(Page<Pagamento> page) {
        List<PagamentoDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}