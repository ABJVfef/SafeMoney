package com.safemoney.services;

import com.safemoney.domains.*;
import com.safemoney.domains.dtos.LancamentoDTO;
import com.safemoney.domains.enums.MeioPagamento;
import com.safemoney.domains.enums.StatusFatura;
import com.safemoney.domains.enums.StatusLancamento;
import com.safemoney.domains.enums.TipoLancamento;
import com.safemoney.mappers.LancamentoMapper;
import com.safemoney.repositories.*;
import com.safemoney.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository repository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EntidadeRepository entidadeRepository;
    @Autowired
    private CentroCustoRepository centroCustoRepository;
    @Autowired
    private ContaBancariaRepository contaRepository;
    @Autowired
    private CartaoCreditoRepository cartaoRepository;
    @Autowired
    private FaturaCartaoRepository faturaRepository;

    @Transactional(readOnly = true)
    public List<LancamentoDTO> findAll() {
        return LancamentoMapper.toDtoList(repository.findAll());
    }

    @Transactional(readOnly = true)
    public LancamentoDTO findById(Long id) {
        return LancamentoMapper.toDto(findEntityById(id));
    }

    public Lancamento findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Lançamento não encontrado! Id: " + id));
    }

    @Transactional(readOnly = true)
    public List<LancamentoDTO> findWithFilters(Integer tipoId, Integer statusId, LocalDate inicio, LocalDate fim) {
        Integer usuarioId = 1;
        TipoLancamento tipo = (tipoId != null) ? TipoLancamento.toEnum(tipoId) : null;
        StatusLancamento status = (statusId != null) ? StatusLancamento.toEnum(statusId) : null;
        List<Lancamento> list = repository.findByFiltros(usuarioId, tipo, status, inicio, fim);
        return LancamentoMapper.toDtoList(list);
    }

    @Transactional
    public LancamentoDTO create(LancamentoDTO dto) {
        Usuario usuario = usuarioService.findEntityById(1);

        Entidade entidade = dto.getEntidadeId() != null ? entidadeRepository.findById(dto.getEntidadeId()).orElse(null) : null;
        CentroCusto centro = dto.getCentroCustoId() != null ? centroCustoRepository.findById(dto.getCentroCustoId()).orElse(null) : null;
        ContaBancaria conta = dto.getContaBancariaId() != null ? contaRepository.findById(dto.getContaBancariaId()).orElse(null) : null;

        CartaoCredito cartao = null;
        if (dto.getCartaoCreditoId() != null) {
            cartao = cartaoRepository.findById(dto.getCartaoCreditoId()).orElse(null);
        }

        Lancamento entity = LancamentoMapper.toEntity(dto, entidade, centro, conta, cartao);
        entity.setId(null);
        entity.setUsuario(usuario);
        entity.setStatus(StatusLancamento.PENDENTE);

        entity = repository.save(entity);


        if (entity.getMeioPagamento() == MeioPagamento.CARTAO && cartao != null) {
            atualizarFatura(entity, cartao);
        }

        return LancamentoMapper.toDto(entity);
    }

    private void atualizarFatura(Lancamento lancamento, CartaoCredito cartao) {
        String competencia = lancamento.getDataVencimento().toString().substring(0, 7);

        Optional<FaturaCartao> faturaOpt = faturaRepository.findByCartaoIdAndCompetencia(cartao.getId(), competencia);

        FaturaCartao fatura;
        if (faturaOpt.isPresent()) {
            fatura = faturaOpt.get();
        } else {
            fatura = new FaturaCartao();
            fatura.setCartao(cartao);
            fatura.setCompetencia(competencia);
            fatura.setDataVencimento(lancamento.getDataVencimento());
            fatura.setValorTotal(BigDecimal.ZERO);
            fatura.setStatus(StatusFatura.ABERTA);
        }


        fatura.setValorTotal(fatura.getValorTotal().add(lancamento.getValor()));
        faturaRepository.save(fatura);
    }

    @Transactional
    public LancamentoDTO update(Long id, LancamentoDTO dto) {
        Lancamento entity = findEntityById(id);
        if (entity.getStatus() == StatusLancamento.BAIXADO || entity.getStatus() == StatusLancamento.CANCELADO) {
            throw new IllegalArgumentException("Não é possível alterar um lançamento já baixado ou cancelado.");
        }
        LancamentoMapper.copyToEntity(dto, entity);
        if (dto.getEntidadeId() != null) entity.setEntidade(entidadeRepository.findById(dto.getEntidadeId()).orElse(null));
        if (dto.getCentroCustoId() != null) entity.setCentroCusto(centroCustoRepository.findById(dto.getCentroCustoId()).orElse(null));
        if (dto.getContaBancariaId() != null) entity.setContaBancaria(contaRepository.findById(dto.getContaBancariaId()).orElse(null));
        return LancamentoMapper.toDto(repository.save(entity));
    }

    @Transactional
    public void cancelar(Long id) {
        Lancamento entity = findEntityById(id);
        if (entity.getStatus() == StatusLancamento.BAIXADO) {
            throw new IllegalArgumentException("Não é possível cancelar um lançamento já pago.");
        }
        entity.setStatus(StatusLancamento.CANCELADO);
        repository.save(entity);
    }
}