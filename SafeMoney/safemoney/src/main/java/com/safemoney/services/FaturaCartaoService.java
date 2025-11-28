package com.safemoney.services;

import com.safemoney.domains.*;
import com.safemoney.domains.dtos.FaturaCartaoDTO;
import com.safemoney.domains.enums.StatusFatura;
import com.safemoney.domains.enums.TipoTransacao;
import com.safemoney.mappers.FaturaCartaoMapper;
import com.safemoney.repositories.*;
import com.safemoney.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class FaturaCartaoService {

    @Autowired
    private FaturaCartaoRepository repository;
    @Autowired
    private ContaBancariaRepository contaRepository;
    @Autowired
    private MovimentoContaRepository movimentoRepository;
    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Transactional(readOnly = true)
    public List<FaturaCartaoDTO> findByCartao(Integer cartaoId) {
        return List.of();
    }

    @Transactional
    public FaturaCartaoDTO fecharFatura(Integer cartaoId, String competencia) {
        FaturaCartao fatura = repository.findByCartaoIdAndCompetencia(cartaoId, competencia)
                .orElseGet(() -> criarFaturaAutomaticamente(cartaoId, competencia));
        LocalDate inicio = LocalDate.parse(competencia + "-01");
        LocalDate fim = inicio.withDayOfMonth(inicio.lengthOfMonth());


        if (fatura.getValorTotal().compareTo(BigDecimal.ZERO) == 0) {

        }


        fatura.setStatus(StatusFatura.FECHADA);
        fatura.setDataFechamento(LocalDate.now());

        return FaturaCartaoMapper.toDto(repository.save(fatura));
    }


    private FaturaCartao criarFaturaAutomaticamente(Integer cartaoId, String competencia) {
        FaturaCartao novaFatura = new FaturaCartao();
        throw new ObjectNotFoundException("Fatura não encontrada! Crie um lançamento para este cartão e mês primeiro.");
    }

    @Transactional
    public void pagarFatura(Integer cartaoId, Long faturaId, Integer contaBancariaId) {
        FaturaCartao fatura = repository.findById(faturaId)
                .orElseThrow(() -> new ObjectNotFoundException("Fatura não encontrada"));

        if (fatura.getStatus() != StatusFatura.FECHADA) {
            throw new IllegalArgumentException("A fatura precisa estar FECHADA para ser paga.");
        }

        ContaBancaria conta = contaRepository.findById(contaBancariaId)
                .orElseThrow(() -> new ObjectNotFoundException("Conta bancária não encontrada"));

        MovimentoConta debito = new MovimentoConta();
        debito.setConta(conta);
        debito.setDataMovimento(LocalDate.now());
        debito.setTipo(TipoTransacao.DEBITO);
        debito.setValor(fatura.getValorTotal());
        debito.setHistorico("Pagamento Fatura " + fatura.getCartao().getApelido() + " - " + fatura.getCompetencia());
        debito.setReferenciaId(fatura.getId());
        debito.setReferenciaTipo("FATURA");
        movimentoRepository.save(debito);

        fatura.setStatus(StatusFatura.PAGA);
        repository.save(fatura);
    }
}