package com.thiagopolli.mastersworks.services.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.thiagopolli.mastersworks.domain.Cliente;
import com.thiagopolli.mastersworks.dto.ClienteDTO;
import com.thiagopolli.mastersworks.repositories.ClienteRepository;
import com.thiagopolli.mastersworks.resources.exception.FildMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repo ;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
 		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FildMessage> list = new ArrayList<>();
		
		//logica para testar se o email ja existe na atualizaçao cliente
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.addAll(Arrays.asList(new FildMessage("email", "Email Já existe")));	
		}

		
		
		for (FildMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
