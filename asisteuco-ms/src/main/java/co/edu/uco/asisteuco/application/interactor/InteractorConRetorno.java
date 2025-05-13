package co.edu.uco.asisteuco.application.interactor;

public interface InteractorConRetorno<I, O> {

	O ejecutar(I dto);
	
}
