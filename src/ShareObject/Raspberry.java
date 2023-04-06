package ShareObject;

public class Raspberry extends Equipment {
	private String gpio;
	private String usbType;

	public Raspberry(String[] str) {
		this.etype = str[0];
		this.edetailType= str[1];
		this.gpio = str[2];
		this.usbType = str[3];

	}

	public void setGpio(String gpio) {
		this.gpio = gpio;
	}

	public void setUsbType(String usbType) {
		this.usbType = usbType;
	}

	public String getGpio() {
		return gpio;
	}

	public String getUsbType() {
		return usbType;
	}

	@Override
	int getTotla() {	
		return 4;
	}

	@Override
	public String[] getArea() {
		String[] str =new String[4];
		str[0] = getEtype();
		str[1] = getEdetailType();
		str[2] = getGpio();
		str[3] = getUsbType();
		return str;

	}

	private char[] getAnalogPin() {
		// TODO Auto-generated method stub
		return null;
	}

}
