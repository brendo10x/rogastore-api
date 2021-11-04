package rogalabs.com.rogastore.domain.exception;

public class AppNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public AppNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public AppNotFoundException(Long appId) {
		this(String.format("There is no app record with code %d", appId));
	}
}