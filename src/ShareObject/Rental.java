package ShareObject;

public class Rental {
	private int count;
	private Equipment eq;
	
	public Rental(String count2, Equipment qp) {
		this.count = Integer.valueOf(count2);
		this.eq = qp;
	}
	
	public Rental(int c, Equipment eq) {
		this.count = c;
		this.eq = eq;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Equipment getEq() {
		return eq;
	}
	public void setEq(Equipment eq) {
		this.eq = eq;
	}
	
	public String getAll() {
		String a1 = String.valueOf(getCount());	
		
		return a1; 
	}

}
