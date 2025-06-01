package co.edu.uco.asisteuco.application.interactor.usecase;

public interface UseCaseConRetorno <D, O> {

	O ejecutar(D domain);
	
} 
