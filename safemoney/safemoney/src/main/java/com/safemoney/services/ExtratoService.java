package com.safemoney.services;

import com.safemoney.domains.ContaBancaria;
import com.safemoney.domains.MovimentoConta;
import com.safemoney.domains.dtos.MovimentoContaDTO;
import com.safemoney.domains.enums.TipoTransacao;
import com.safemoney.mappers.MovimentoContaMapper;
import com.safemoney.repositories.MovimentoContaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExtratoService {

    private final MovimentoContaRepository movimentoRepository;
    private final ContaBancariaService contaService;

    public ExtratoService(MovimentoContaRepository movimentoRepository, ContaBancariaService contaService) {
        this.movimentoRepository = movimentoRepository;
        this.contaService = contaService;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> gerarExtrato(Integer contaId, LocalDate inicio, LocalDate fim) {

        ContaBancaria conta = contaService.findEntityById(contaId);

        List<MovimentoConta> movimentosAnteriores = movimentoRepository.findByContaIdAndDataMovimentoBefore(contaId, inicio);

        BigDecimal saldoAnterior = conta.getSaldoInicial();

        for (MovimentoConta m : movimentosAnteriores) {
            if (m.getTipo() == TipoTransacao.CREDITO) {
                saldoAnterior = saldoAnterior.add(m.getValor());
            } else {
                saldoAnterior = saldoAnterior.subtract(m.getValor());
            }
        }

        List<MovimentoConta> movimentosPeriodo = movimentoRepository.findByContaIdAndDataMovimentoBetween(contaId, inicio, fim);

        BigDecimal saldoFinal = saldoAnterior;
        for (MovimentoConta m : movimentosPeriodo) {
            if (m.getTipo() == TipoTransacao.CREDITO) {
                saldoFinal = saldoFinal.add(m.getValor());
            } else {
                saldoFinal = saldoFinal.subtract(m.getValor());
            }
        }

        List<MovimentoContaDTO> dtos = MovimentoContaMapper.toDtoList(movimentosPeriodo);

        Map<String, Object> extrato = new HashMap<>();
        extrato.put("conta", conta.getInstituicao() + " - " + conta.getApelido());
        extrato.put("periodoInicio", inicio);
        extrato.put("periodoFim", fim);
        extrato.put("saldoAnterior", saldoAnterior);
        extrato.put("movimentos", dtos);
        extrato.put("saldoFinal", saldoFinal);

        return extrato;
    }
}