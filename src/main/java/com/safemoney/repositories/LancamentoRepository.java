package com.safemoney.repositories;

import com.safemoney.domains.Lancamento;
import com.safemoney.domains.enums.StatusLancamento;
import com.safemoney.domains.enums.TipoLancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    List<Lancamento> findByUsuarioId(Integer usuarioId);

    @Query("SELECT l FROM Lancamento l WHERE l.usuario.id = :usuarioId " +
            "AND (:tipo IS NULL OR l.tipo = :tipo) " +
            "AND (:status IS NULL OR l.status = :status) " +
            "AND (cast(:inicio as date) IS NULL OR l.dataVencimento >= :inicio) " +
            "AND (cast(:fim as date) IS NULL OR l.dataVencimento <= :fim)")
    List<Lancamento> findByFiltros(
            @Param("usuarioId") Integer usuarioId,
            @Param("tipo") TipoLancamento tipo,
            @Param("status") StatusLancamento status,
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim
    );
}