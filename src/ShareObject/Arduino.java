package ShareObject;

public class Arduino extends Equipment {
	
	private String analogPin;
	private String digitalPin;
	private String usbType;

	
	public Arduino() {
		
	}
	
	public Arduino(String[] str) {
		this.etype = str[0];
		this.edetailType= str[1];
		this.analogPin = str[2];
		this.digitalPin = str[3];
		this.usbType = str[4];
	}

	

	public String getAnalogPin() {
		return analogPin;
	}

	public void setAnalogPin(String analogPin) {
		this.analogPin = analogPin;
	}

	public String getDigitalPin() {
		return digitalPin;
	}

	public void setDigitalPin(String digitalPin) {
		this.digitalPin = digitalPin;
	}

	public String getUsbType() {
		return usbType;
	}

	public void setUsbType(String usbType) {
		this.usbType = usbType;
	}

	@Override
	public int getTotla() {
		return 5;
	}



	@Override
	public String[] getArea() {
		String[] str =new String[5];
		str[0] = getEtype();
		str[1] = getEdetailType();
		str[2] = String.valueOf(getAnalogPin());
		str[3] = String.valueOf(getDigitalPin());
		str[4] = getUsbType();
		return str;
	}



}
