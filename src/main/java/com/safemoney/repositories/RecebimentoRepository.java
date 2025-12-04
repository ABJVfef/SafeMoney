package com.safemoney.repositories;

import com.safemoney.domains.Recebimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecebimentoRepository extends JpaRepository<Recebimento, Long> {
}