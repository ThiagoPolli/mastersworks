package com.thiagopolli.mastersworks.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagopolli.mastersworks.domain.Pedido;
import com.thiagopolli.mastersworks.repositories.PedidoRepository;
import com.thiagopolli.mastersworks.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoServices {
	
	@Autowired
	private PedidoRepository repository;
	

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		}

}
