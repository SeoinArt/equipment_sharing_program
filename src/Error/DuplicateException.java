package Error;

public class DuplicateException extends Exception {
	public DuplicateException() {
		super("DuplicateExceptino");
	}
	public DuplicateException(String msg) {
		super(msg);
	}
}
