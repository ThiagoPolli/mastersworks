package com.thiagopolli.mastersworks.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.thiagopolli.mastersworks.domain.Categoria;
import com.thiagopolli.mastersworks.domain.Cidade;
import com.thiagopolli.mastersworks.domain.Cliente;
import com.thiagopolli.mastersworks.domain.Endereco;
import com.thiagopolli.mastersworks.domain.Estado;
import com.thiagopolli.mastersworks.domain.ItemPedidos;
import com.thiagopolli.mastersworks.domain.Pagamento;
import com.thiagopolli.mastersworks.domain.PagamentoComBoleto;
import com.thiagopolli.mastersworks.domain.PagamentoComcartao;
import com.thiagopolli.mastersworks.domain.Pedido;
import com.thiagopolli.mastersworks.domain.Produto;
import com.thiagopolli.mastersworks.domain.enums.EstadoPagamento;
import com.thiagopolli.mastersworks.domain.enums.Perfil;
import com.thiagopolli.mastersworks.domain.enums.Tipocliente;
import com.thiagopolli.mastersworks.repositories.CategoriaRepository;
import com.thiagopolli.mastersworks.repositories.CidadeRepository;
import com.thiagopolli.mastersworks.repositories.ClienteRepository;
import com.thiagopolli.mastersworks.repositories.EnderecoRepository;
import com.thiagopolli.mastersworks.repositories.EstadoRepository;
import com.thiagopolli.mastersworks.repositories.ItemPedidoRepository;
import com.thiagopolli.mastersworks.repositories.PagamentoRepository;
import com.thiagopolli.mastersworks.repositories.PedidoRepository;
import com.thiagopolli.mastersworks.repositories.ProdutoRepository;


@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	

	
	public void intatiateTestDatabase() throws ParseException {
		
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Cama mesa Banho");
		Categoria cat4 = new Categoria(null, "Eletronicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);
		Produto p4 = new Produto(null, "mesa de escritorio", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "Tv true Colors", 1280.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);
		
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9,p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
	
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));		

		
		categoriaRepository.saveAll(Arrays.asList( cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3,p4,p5,p6,p7,p8,p9,p10,p11));
		
		

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "Sorocaba", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		
		Cliente cli1 = new Cliente(null, "Maria", "thiago.polli.palma@gmail.com", "87799163097", Tipocliente.PESSOAFISICA,passEncoder.encode("12345"));
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Cliente cli2 = new Cliente(null, "Nestor" , "thiago020@gmail.com", "57166601000", Tipocliente.PESSOAJURIDICA, passEncoder.encode("123"));
		cli2.getTelefones().addAll(Arrays.asList("30221212"));
		
		Cliente cli3 = new Cliente(null, "Thiago" , "thiago.polli.palma2020@gmail.com", "32159812030", Tipocliente.PESSOAFISICA, passEncoder.encode("123"));
		cli3.getTelefones().addAll(Arrays.asList("302212548"));
		cli3.addPerfil(Perfil.ADMIN);
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "apt 203", "jardim", "38220834", cli1,c1 );
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "centro", "38777012", cli1,c2 );
		Endereco e3 = new Endereco(null, "Avenida Matos", "105", "sala 800", "centro", "38777012", cli2,c2 );
		Endereco e4 = new Endereco(null, "Avenida JK", "1025", "sala 524", "centro", "254512", cli3,c1 );
		
		
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		cli2.getEnderecos().addAll(Arrays.asList(e1));
		cli3.getEnderecos().addAll(Arrays.asList(e3));
		
		clienteRepository.saveAll(Arrays.asList(cli1,cli2, cli3));
		enderecoRepository.saveAll(Arrays.asList(e1,e2,e3, e4));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:MM");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2019 20:15"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("20/08/2019 10:15"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComcartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2019 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		
		ItemPedidos ip1 = new ItemPedidos(ped1, p1, 0.00, 1, 2000.00);
		ItemPedidos ip2 =new ItemPedidos(ped1, p3, 0.00, 2, 80.00);
		ItemPedidos ip3 = new ItemPedidos(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		
	}

}
