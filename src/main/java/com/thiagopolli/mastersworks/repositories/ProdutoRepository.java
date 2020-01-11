package com.thiagopolli.mastersworks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiagopolli.mastersworks.domain.Produto;

@Repository
public interface ProdutoRepository extends  JpaRepository<Produto, Integer> {

}
