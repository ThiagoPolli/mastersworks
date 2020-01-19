package com.thiagopolli.mastersworks.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.thiagopolli.mastersworks.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
		
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		
		SimpleMailMessage sm = new SimpleMailMessage();
		//destinatario do email
		sm.setTo(obj.getCliente().getEmail());
		
		//remetente do email
		sm.setFrom(sender);
		
		//assunto do email
		sm.setSubject("Pedido confirmado! Código: " + obj.getId());
		
		//Data do email
		sm.setSentDate(new Date(System.currentTimeMillis()));
		
		//corpo do email
		sm.setText(obj.toString());
		
		return sm;
	
	}

}
