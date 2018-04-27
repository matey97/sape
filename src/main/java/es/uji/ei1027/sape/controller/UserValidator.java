package es.uji.ei1027.sape.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei1027.sape.model.UserDetails;



public class UserValidator implements Validator {
	
	@Override
    public boolean supports(Class<?> cls) { 
        return UserDetails.class.isAssignableFrom(cls);
    }

	@Override
	public void validate(Object arg0, Errors arg1) {
		UserDetails userDetails = (UserDetails)arg0;
		if(userDetails.getUsername().trim().equals("")){
			arg1.rejectValue("username", "empty", "Introduce un nombre de usuario");
		}
		if(userDetails.getPassword().trim().equals("")){
			arg1.rejectValue("password", "empty", "Introduce una contraseña" );
		}		
	}
}
