package First_project;
import java.awt.Insets;

import javax.swing.*;
public class MyJPanel extends JPanel{
	
	private int a,b,c,d;
	
	public MyJPanel() {
		a=b=c=d=10;
		
	}
	public MyJPanel(int a, int b, int c, int d) {
		this.a=a;
		this.b=b;
		this.c=c;
		this.d=d;
	}
	
	
	//바깥 여백을 반환하는 메서드
	@Override
	public Insets getInsets() {
		Insets in=new Insets(a,b,c,d);//top, left, bottom, right
		return in;
	}

}
