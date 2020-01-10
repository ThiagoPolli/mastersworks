package com.thiagopolli.mastersworks.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thiagopolli.mastersworks.domain.Categoria;
import com.thiagopolli.mastersworks.services.CategoriaServices;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	
	@Autowired
	private CategoriaServices service;
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		
		Categoria obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	@RequestMapping
	public ResponseEntity<List<Categoria>> findAll(){
		List<Categoria> list = service.findall();
		return ResponseEntity.ok().body(list);
	}
	
	

}
