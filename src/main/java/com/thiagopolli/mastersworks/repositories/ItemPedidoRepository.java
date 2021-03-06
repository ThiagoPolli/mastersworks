package com.thiagopolli.mastersworks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiagopolli.mastersworks.domain.ItemPedidos;

@Repository
public interface ItemPedidoRepository extends  JpaRepository<ItemPedidos, Integer> {

}
