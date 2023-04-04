package Error;

public class NoUserName extends Exception {
	public NoUserName() {
		super("NoUserName");
	}
	public NoUserName(String msg) {
		super(msg);
	}
}
