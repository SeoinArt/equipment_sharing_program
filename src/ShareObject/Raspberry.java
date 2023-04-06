package ShareObject;

public class Raspberry extends Equipment {
	private int gpio;
	private String usbType;

	public Raspberry(String[] str) {
		this.etype = str[0];
		this.edetailType= str[1];
		this.gpio = Integer.valueOf(str[2]);
		this.usbType = str[3];

	}

	public void setGpio(int gpio) {
		this.gpio = gpio;
	}

	public void setUsbType(String usbType) {
		this.usbType = usbType;
	}

	public int getGpio() {
		return gpio;
	}

	public String getUsbType() {
		return usbType;
	}

	@Override
	int getTotla() {	
		return 4;
	}

}
