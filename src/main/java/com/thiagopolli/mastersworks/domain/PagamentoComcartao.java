package com.thiagopolli.mastersworks.domain;

import javax.persistence.Entity;

import com.thiagopolli.mastersworks.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComcartao extends Pagamento{
			
		private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	
	public PagamentoComcartao() {
		
	}


	public PagamentoComcartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}


	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}


	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}


}
