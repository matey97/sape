package es.uji.ei1027.sape.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei1027.sape.model.PeticionRevision;

public class PeticionRevisionValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PeticionRevision.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PeticionRevision pr = (PeticionRevision)target;
		if (pr.getTextoPeticion().length() == 0) {
			errors.rejectValue("textoPeticion", "noText", "Introduce un mensaje");
		}

	}

}
