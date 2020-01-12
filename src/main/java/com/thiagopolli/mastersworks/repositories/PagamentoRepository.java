package com.thiagopolli.mastersworks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiagopolli.mastersworks.domain.Pagamento;

@Repository
public interface PagamentoRepository extends  JpaRepository<Pagamento, Integer> {

}
