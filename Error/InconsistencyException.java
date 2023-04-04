package Error;

import javax.swing.JOptionPane;

public class InconsistencyException extends Exception {
	public InconsistencyException() {
		super("InconsistencyException");
	}
	public InconsistencyException(String msg) {
		super(msg);
	}
}
