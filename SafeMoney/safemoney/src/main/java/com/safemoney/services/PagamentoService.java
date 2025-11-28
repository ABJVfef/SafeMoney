package com.safemoney.services;

import com.safemoney.domains.*;
import com.safemoney.domains.dtos.PagamentoDTO;
import com.safemoney.domains.enums.StatusLancamento;
import com.safemoney.domains.enums.TipoTransacao;
import com.safemoney.mappers.PagamentoMapper;
import com.safemoney.repositories.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class PagamentoService {

    private final PagamentoRepository repository;
    private final LancamentoService lancamentoService;
    private final ContaBancariaService contaService;
    private final MovimentoContaRepository movimentoRepository;

    public PagamentoService(PagamentoRepository repository, LancamentoService lancamentoService, ContaBancariaService contaService, MovimentoContaRepository movimentoRepository) {
        this.repository = repository;
        this.lancamentoService = lancamentoService;
        this.contaService = contaService;
        this.movimentoRepository = movimentoRepository;
    }

    @Transactional
    public PagamentoDTO create(Long lancamentoId, PagamentoDTO dto) {
        if (lancamentoId == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id do lançamento obrigatório");

        Lancamento lancamento = lancamentoService.findEntityById(lancamentoId);
        ContaBancaria conta = contaService.findEntityById(dto.getContaOrigemId());

        Pagamento pagamento = PagamentoMapper.toEntity(dto, lancamento, conta);
        pagamento = repository.save(pagamento);


        BigDecimal novoValorBaixado = lancamento.getValorBaixado().add(dto.getValorPago());
        lancamento.setValorBaixado(novoValorBaixado);

        if (novoValorBaixado.compareTo(lancamento.getValor()) >= 0) {
            lancamento.setStatus(StatusLancamento.BAIXADO);
        } else {
            lancamento.setStatus(StatusLancamento.PARCIAL);
        }

        MovimentoConta movimento = new MovimentoConta();
        movimento.setConta(conta);
        movimento.setDataMovimento(dto.getDataPagamento());
        movimento.setTipo(TipoTransacao.DEBITO);
        movimento.setValor(dto.getValorPago());
        movimento.setHistorico("Pagamento: " + lancamento.getDescricao());
        movimento.setReferenciaId(pagamento.getId());
        movimento.setReferenciaTipo("PAGAMENTO");

        movimentoRepository.save(movimento);

        return PagamentoMapper.toDto(pagamento);
    }
}