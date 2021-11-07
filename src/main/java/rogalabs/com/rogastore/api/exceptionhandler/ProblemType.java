package rogalabs.com.rogastore.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	INVALID_DATA("invalid data"),
	SYSTEM_ERROR("System error"),
	PARAMETER_INVALID("Invalid parameter"),
	INCOMPREHENSIBLE_MESSAGE("Incomprehensible message"),
	RESOURCE_NOT_FOUND("Resource not found"),
	ENTITY_IN_USE("Entity in use"),
	ERROR_BUSINESS("Business rule violation");
	
	private String title;
	
	ProblemType(String title) {
		this.title = title;
	}
	
}
