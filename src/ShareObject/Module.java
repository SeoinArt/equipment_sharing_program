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

}
