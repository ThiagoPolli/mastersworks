package com.thiagopolli.mastersworks.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.thiagopolli.mastersworks.domain.Cliente;
import com.thiagopolli.mastersworks.domain.ItemPedidos;
import com.thiagopolli.mastersworks.domain.PagamentoComBoleto;
import com.thiagopolli.mastersworks.domain.Pedido;
import com.thiagopolli.mastersworks.domain.enums.EstadoPagamento;
import com.thiagopolli.mastersworks.repositories.ItemPedidoRepository;
import com.thiagopolli.mastersworks.repositories.PagamentoRepository;
import com.thiagopolli.mastersworks.repositories.PedidoRepository;
import com.thiagopolli.mastersworks.security.UserSS;
import com.thiagopolli.mastersworks.services.exceptions.AuthorizationException;
import com.thiagopolli.mastersworks.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoServices {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService; 
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoServices produtoServices;
	
	@Autowired
	private ClienteServices clienteServices;
	
	@Autowired
	private EmailService emailService;
	

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		
		obj.setCliente(clienteServices.find(obj.getCliente().getId()));
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
			
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for (ItemPedidos ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoServices.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
	
	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Cliente cliente =  clienteServices.find(user.getId());
		return repo.findByCliente(cliente, pageRequest);
	}

}
