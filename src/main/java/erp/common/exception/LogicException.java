package erp.common.exception;

public class LogicException extends RuntimeException {

	private static final long serialVersionUID = -4124980788043425818L;
	
	private String	code;
	private String	msg;

	public LogicException(String msg) {
		this.code 	= "000";
		this.msg	= msg;
	}

	public LogicException(String msg, String code) {
		this.msg	= msg;
		this.code	= code;
	}

	@Override
	public String getMessage() {
		return this.msg;
	}

	public String getCode() {
		return this.code;
	}
}
