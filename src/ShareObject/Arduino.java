package ShareObject;

public class Arduino extends Equipment {
	private int analogPin;
	private int digitalPin;
	private String usbType;

	public Arduino(String[] str) {
		this.etype = str[0];
		this.edetailType= str[1];
		this.analogPin = Integer.valueOf(str[2]);
		this.digitalPin = Integer.valueOf(str[3]);
		this.usbType = str[4];
	}

	

	public int getAnalogPin() {
		return analogPin;
	}

	public void setAnalogPin(int analogPin) {
		this.analogPin = analogPin;
	}

	public int getDigitalPin() {
		return digitalPin;
	}

	public void setDigitalPin(int digitalPin) {
		this.digitalPin = digitalPin;
	}

	public String getUsbType() {
		return usbType;
	}

	public void setUsbType(String usbType) {
		this.usbType = usbType;
	}

	@Override
	int getTotla() {
		return 5;
	}

}
