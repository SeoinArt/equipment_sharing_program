package ShareObject;

abstract public class Equipment {
	private String etype;
	private String edetailType;
	
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
	
	
}
