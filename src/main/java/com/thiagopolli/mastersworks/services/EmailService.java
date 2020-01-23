package com.thiagopolli.mastersworks.services;

import org.springframework.mail.SimpleMailMessage;

import com.thiagopolli.mastersworks.domain.Cliente;
import com.thiagopolli.mastersworks.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);

}
