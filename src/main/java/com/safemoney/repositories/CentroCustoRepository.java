package com.safemoney.repositories;

import com.safemoney.domains.CentroCusto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CentroCustoRepository extends JpaRepository<CentroCusto, Integer> {

    List<CentroCusto> findByUsuarioIdAndAtivoTrue(Integer usuarioId);
}