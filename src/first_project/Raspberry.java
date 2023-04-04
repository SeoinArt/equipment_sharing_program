package first_project;

public class Raspberry extends Equipment {
	private int gpio;
	private int usbType;

	public void setGpio(int gpio) {
		this.gpio = gpio;
	}

	public void setUsbType(int usbType) {
		this.usbType = usbType;
	}

	public int getGpio() {
		return gpio;
	}

	public int getUsbType() {
		return usbType;
	}

}
