package erp.common.exception;

public class RuntimeLogicException extends RuntimeException {

	private static final long serialVersionUID = -8535601856507813560L;
	
	private String	code;
	private String	msg;

	public RuntimeLogicException(String msg) {
		this.code 	= "000";
		this.msg	= msg;
	}

	public RuntimeLogicException(String msg, String code) {
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
