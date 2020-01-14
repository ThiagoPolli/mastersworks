package com.thiagopolli.mastersworks.services.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.thiagopolli.mastersworks.domain.enums.Tipocliente;
import com.thiagopolli.mastersworks.dto.ClienteNewDTO;
import com.thiagopolli.mastersworks.resources.exception.FildMessage;
import com.thiagopolli.mastersworks.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FildMessage> list = new ArrayList<>();

		if (objDto.getTipo().equals(Tipocliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getcpfouCnpj())) {
			list.addAll(Arrays.asList(new FildMessage("cpfouCnpj", "CPF inválido")));
		}
		if (objDto.getTipo().equals(Tipocliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getcpfouCnpj())) {
			list.addAll(Arrays.asList(new FildMessage("cpfouCnpj", "CNPJ inválido")));
		}

		
		
		for (FildMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
