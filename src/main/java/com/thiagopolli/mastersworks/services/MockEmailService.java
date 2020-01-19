package com.thiagopolli.mastersworks.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService{

	private static Logger LOG = LoggerFactory.getLogger(MockEmailService.class); 
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulação envio de Email .....");
		LOG.info(msg.toString());
		LOG.info("Email enviado!");
		
		
	}
	
	

}
