package com.safemoney.repositories;

import com.safemoney.domains.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Integer> {

    List<Entidade> findByUsuarioIdAndNomeContainingIgnoreCase(Integer usuarioId, String nome);
}