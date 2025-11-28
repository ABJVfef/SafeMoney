package com.safemoney.services;

import com.safemoney.domains.*;
import com.safemoney.domains.dtos.RecebimentoDTO;
import com.safemoney.domains.enums.StatusLancamento;
import com.safemoney.domains.enums.TipoTransacao;
import com.safemoney.mappers.RecebimentoMapper;
import com.safemoney.repositories.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class RecebimentoService {

    private final RecebimentoRepository repository;
    private final LancamentoService lancamentoService;
    private final ContaBancariaService contaService;
    private final MovimentoContaRepository movimentoRepository;

    public RecebimentoService(RecebimentoRepository repository, LancamentoService lancamentoService, ContaBancariaService contaService, MovimentoContaRepository movimentoRepository) {
        this.repository = repository;
        this.lancamentoService = lancamentoService;
        this.contaService = contaService;
        this.movimentoRepository = movimentoRepository;
    }

    @Transactional
    public RecebimentoDTO create(Long lancamentoId, RecebimentoDTO dto) {
        if (lancamentoId == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id do lançamento obrigatório");

        Lancamento lancamento = lancamentoService.findEntityById(lancamentoId);
        ContaBancaria conta = contaService.findEntityById(dto.getContaDestinoId());

        Recebimento recebimento = RecebimentoMapper.toEntity(dto, lancamento, conta);
        recebimento = repository.save(recebimento);

        BigDecimal novoValorBaixado = lancamento.getValorBaixado().add(dto.getValorRecebido());
        lancamento.setValorBaixado(novoValorBaixado);

        if (novoValorBaixado.compareTo(lancamento.getValor()) >= 0) {
            lancamento.setStatus(StatusLancamento.BAIXADO);
        } else {
            lancamento.setStatus(StatusLancamento.PARCIAL);
        }

        MovimentoConta movimento = new MovimentoConta();
        movimento.setConta(conta);
        movimento.setDataMovimento(dto.getDataRecebimento());
        movimento.setTipo(TipoTransacao.CREDITO);
        movimento.setValor(dto.getValorRecebido());
        movimento.setHistorico("Recebimento: " + lancamento.getDescricao());
        movimento.setReferenciaId(recebimento.getId());
        movimento.setReferenciaTipo("RECEBIMENTO");

        movimentoRepository.save(movimento);

        return RecebimentoMapper.toDto(recebimento);
    }
}