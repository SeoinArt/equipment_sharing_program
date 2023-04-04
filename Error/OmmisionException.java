package Error;

public class OmmisionException extends Exception {
	public OmmisionException() {
		super("OmmisionException");
	}
	public OmmisionException(String msg) {
		super(msg);
	}

}
