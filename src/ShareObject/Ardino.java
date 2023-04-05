package ShareObject;

public class Ardino extends Equipment {
	private int analogPin;
	private int digitalPin;
	private String usbType;

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
