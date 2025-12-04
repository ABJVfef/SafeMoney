package com.safemoney.repositories;

import com.safemoney.domains.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Integer> {

    List<ContaBancaria> findByUsuarioId(Integer usuarioId);
}