package com.thiagopolli.mastersworks;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thiagopolli.mastersworks.domain.Categoria;
import com.thiagopolli.mastersworks.domain.Produto;
import com.thiagopolli.mastersworks.repositories.CategoriaRepository;
import com.thiagopolli.mastersworks.repositories.ProdutoRepository;

@SpringBootApplication
public class MastersworksApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MastersworksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Joias");
		Categoria cat2 = new Categoria(null, "Alianças");
		
		Produto p1 = new Produto(null, "Anel Formatura", 2000.00);
		Produto p2 = new Produto(null, "Aneil Solitário", 800.00);
		Produto p3 = new Produto(null, "Aliança anatomica", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategoras().addAll(Arrays.asList(cat1));
		p2.getCategoras().addAll(Arrays.asList(cat1,cat2));
		p3.getCategoras().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList( cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		
	}

}
