package com.thiagopolli.mastersworks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagopolli.mastersworks.domain.Categoria;
import com.thiagopolli.mastersworks.repositories.CategoriaRepository;

@Service
public class CategoriaServices {
	
	@Autowired
	private CategoriaRepository repository;
	
	public List<Categoria> findall(){
		return repository.findAll();
		
	}
	
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		
		return obj.orElse(null);
	}

}
