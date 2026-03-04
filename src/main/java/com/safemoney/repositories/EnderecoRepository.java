package com.safemoney.repositories;

import com.safemoney.domains.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    Optional<Endereco> findByRua(String rua);
}
