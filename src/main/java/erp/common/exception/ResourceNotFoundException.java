package erp.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String code;
	private String msg;
	
	public ResourceNotFoundException() {
		super();
	}
	
	public ResourceNotFoundException(String msg) {
		this.msg = msg;
	}
	
	public ResourceNotFoundException(String msg, String code) {
		this.code = code;
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		return this.msg;
	}
	
	public String getCode() {
		return this.code;
	}
}
