package com.safemoney.repositories;

import com.safemoney.domains.FaturaCartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FaturaCartaoRepository extends JpaRepository<FaturaCartao, Long> {

    Optional<FaturaCartao> findByCartaoIdAndCompetencia(Integer cartaoId, String competencia);
}