package rogalabs.com.rogastore.domain.exception;

public class CategoryNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public CategoryNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public CategoryNotFoundException(Long appId) {
		this(String.format("There is no category record with code %d", appId));
	}

}
