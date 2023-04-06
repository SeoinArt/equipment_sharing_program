package ShareObject;

public class Module extends Equipment{

	public Module(String[] str) {
		this.etype = str[0];
		this.edetailType= str[1];
	}

	@Override
	int getTotla() {
		return 2;
	}

	@Override
	public String[] getArea() {
		String[] str =new String[2];
		str[0] = getEtype();
		str[1] = getEdetailType();
		return str;
	}

}
