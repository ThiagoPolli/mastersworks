package com.thiagopolli.mastersworks;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thiagopolli.mastersworks.domain.Categoria;
import com.thiagopolli.mastersworks.domain.Cidade;
import com.thiagopolli.mastersworks.domain.Cliente;
import com.thiagopolli.mastersworks.domain.Endereco;
import com.thiagopolli.mastersworks.domain.Estado;
import com.thiagopolli.mastersworks.domain.Produto;
import com.thiagopolli.mastersworks.domain.enums.Tipocliente;
import com.thiagopolli.mastersworks.repositories.CategoriaRepository;
import com.thiagopolli.mastersworks.repositories.CidadeRepository;
import com.thiagopolli.mastersworks.repositories.ClienteRepository;
import com.thiagopolli.mastersworks.repositories.EnderecoRepository;
import com.thiagopolli.mastersworks.repositories.EstadoRepository;
import com.thiagopolli.mastersworks.repositories.ProdutoRepository;

@SpringBootApplication
public class MastersworksApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MastersworksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategoras().addAll(Arrays.asList(cat1));
		p2.getCategoras().addAll(Arrays.asList(cat1,cat2));
		p3.getCategoras().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList( cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "Sorocaba", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		
		Cliente cli1 = new Cliente(null, "Maria", "Maria@gmail", "36378912377", Tipocliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "apt 203", "jardim", "38220834", cli1,c1 );
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "centro", "38777012", cli1,c2 );
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		
		
	}

}
