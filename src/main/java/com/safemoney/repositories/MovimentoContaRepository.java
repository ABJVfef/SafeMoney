package com.safemoney.repositories;

import com.safemoney.domains.MovimentoConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimentoContaRepository extends JpaRepository<MovimentoConta, Long> {

    List<MovimentoConta> findByContaIdAndDataMovimentoBetween(Integer contaId, LocalDate inicio, LocalDate fim);

    List<MovimentoConta> findByContaIdAndDataMovimentoBefore(Integer contaId, LocalDate data);
}