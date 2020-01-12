package com.thiagopolli.mastersworks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiagopolli.mastersworks.domain.Cliente;

@Repository
public interface ClienteRepository extends  JpaRepository<Cliente, Integer> {

}
