package com.safemoney.services;

import com.safemoney.domains.*;
import com.safemoney.domains.dtos.TransferenciaDTO;
import com.safemoney.domains.enums.TipoTransacao;
import com.safemoney.mappers.TransferenciaMapper;
import com.safemoney.repositories.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TransferenciaService {

    private final TransferenciaRepository repository;
    private final ContaBancariaService contaService;
    private final MovimentoContaRepository movimentoRepository;

    public TransferenciaService(TransferenciaRepository repository, ContaBancariaService contaService, MovimentoContaRepository movimentoRepository) {
        this.repository = repository;
        this.contaService = contaService;
        this.movimentoRepository = movimentoRepository;
    }

    @Transactional
    public TransferenciaDTO create(TransferenciaDTO dto) {
        if (dto == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados obrigatórios");

        if (dto.getContaOrigemId().equals(dto.getContaDestinoId())) {
            throw new IllegalArgumentException("Conta de origem e destino não podem ser iguais.");
        }

        ContaBancaria origem = contaService.findEntityById(dto.getContaOrigemId());
        ContaBancaria destino = contaService.findEntityById(dto.getContaDestinoId());

        Transferencia transf = TransferenciaMapper.toEntity(dto, origem, destino);
        transf.setUsuario(origem.getUsuario());
        transf = repository.save(transf);


        MovimentoConta debito = new MovimentoConta();
        debito.setConta(origem);
        debito.setDataMovimento(dto.getData());
        debito.setTipo(TipoTransacao.DEBITO);
        debito.setValor(dto.getValor());
        debito.setHistorico("Transf. Enviada para: " + destino.getApelido());
        debito.setReferenciaId(transf.getId());
        debito.setReferenciaTipo("TRANSFERENCIA");
        movimentoRepository.save(debito);


        MovimentoConta credito = new MovimentoConta();
        credito.setConta(destino);
        credito.setDataMovimento(dto.getData());
        credito.setTipo(TipoTransacao.CREDITO);
        credito.setValor(dto.getValor());
        credito.setHistorico("Transf. Recebida de: " + origem.getApelido());
        credito.setReferenciaId(transf.getId());
        credito.setReferenciaTipo("TRANSFERENCIA");
        movimentoRepository.save(credito);

        return TransferenciaMapper.toDto(transf);
    }
}