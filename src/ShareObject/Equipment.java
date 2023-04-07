package ShareObject;

abstract public class Equipment {
	protected String etype;
	protected String edetailType;
	
	public void setEtype(String etype) {
		this.etype = etype;
	}
	

	public String getEtype() {
		return etype;
	}
	
	public void setEdetailType(String edetailType) {
		this.edetailType = edetailType;
	}
	
	public String getEdetailType() {
		return edetailType;
	}
	
	abstract int getTotla();
	abstract String[] getArea();

}
