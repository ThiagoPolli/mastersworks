package com.thiagopolli.mastersworks.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.thiagopolli.mastersworks.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	//Gerar data de vencimento para teste, implementaçao final é com webService 
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto , Date intanteDoPedido) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(intanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}

}
