package co.edu.uco.asisteuco.application.usecase.validator;

public interface Validator <I, O> {
	
	O validate(I data);

}
