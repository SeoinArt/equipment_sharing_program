package ShareObject;

public class Supple {
	private int count;
	private Equipment eq;
	public Supple(String count, Equipment qp) {
		this.count = Integer.valueOf(count);
		this.eq = qp;
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
}
