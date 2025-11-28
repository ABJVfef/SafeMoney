package com.safemoney.mappers;

import com.safemoney.domains.*;
import com.safemoney.domains.dtos.LancamentoDTO;
import com.safemoney.domains.enums.MeioPagamento;
import com.safemoney.domains.enums.StatusLancamento;
import com.safemoney.domains.enums.TipoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class LancamentoMapper {

    private LancamentoMapper() {}

    public static LancamentoDTO toDto(Lancamento entity) {
        if (entity == null) return null;
        LancamentoDTO dto = new LancamentoDTO();
        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        dto.setTipoId(entity.getTipo() != null ? entity.getTipo().getId() : null);
        dto.setStatusId(entity.getStatus() != null ? entity.getStatus().getId() : null);
        dto.setMeioPagamentoId(entity.getMeioPagamento() != null ? entity.getMeioPagamento().getId() : null);
        dto.setValor(entity.getValor());
        dto.setDataCompetencia(entity.getDataCompetencia());
        dto.setDataVencimento(entity.getDataVencimento());
        dto.setEntidadeId(entity.getEntidade() != null ? entity.getEntidade().getId() : null);
        dto.setCentroCustoId(entity.getCentroCusto() != null ? entity.getCentroCusto().getId() : null);
        dto.setContaBancariaId(entity.getContaBancaria() != null ? entity.getContaBancaria().getId() : null);
        dto.setCartaoCreditoId(entity.getCartaoCredito() != null ? entity.getCartaoCredito().getId() : null);
        return dto;
    }

    public static Lancamento toEntity(LancamentoDTO dto, Entidade entidade, CentroCusto centroCusto,
                                      ContaBancaria conta, CartaoCredito cartao) {
        if (dto == null) return null;
        Lancamento entity = new Lancamento();
        entity.setId(dto.getId());
        entity.setDescricao(dto.getDescricao());
        entity.setValor(dto.getValor());
        entity.setDataCompetencia(dto.getDataCompetencia());
        entity.setDataVencimento(dto.getDataVencimento());
        entity.setTipo(TipoLancamento.toEnum(dto.getTipoId()));
        entity.setMeioPagamento(MeioPagamento.toEnum(dto.getMeioPagamentoId()));
        if (dto.getStatusId() != null) {
            entity.setStatus(StatusLancamento.toEnum(dto.getStatusId()));
        }
        entity.setEntidade(entidade);
        entity.setCentroCusto(centroCusto);
        entity.setContaBancaria(conta);
        entity.setCartaoCredito(cartao);
        return entity;
    }

    public static void copyToEntity(LancamentoDTO dto, Lancamento target) {
        if (dto == null || target == null) return;
        target.setDescricao(dto.getDescricao());
        target.setValor(dto.getValor());
        target.setDataCompetencia(dto.getDataCompetencia());
        target.setDataVencimento(dto.getDataVencimento());
        if (dto.getTipoId() != null) {
            target.setTipo(TipoLancamento.toEnum(dto.getTipoId()));
        }
        if (dto.getMeioPagamentoId() != null) {
            target.setMeioPagamento(MeioPagamento.toEnum(dto.getMeioPagamentoId()));
        }
    }

    public static List<LancamentoDTO> toDtoList(Collection<Lancamento> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(LancamentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Page<LancamentoDTO> toDtoPage(Page<Lancamento> page) {
        List<LancamentoDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}