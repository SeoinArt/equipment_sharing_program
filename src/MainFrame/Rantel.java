package MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rantel extends JFrame {

	Container cp;
	JPanel p = new JPanel();

	public Rantel() {
		super("::MemoAppView::");
		cp = this.getContentPane();
		cp.add(p, "Center");
		p.setBackground(Color.white);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		Rantel my = new Rantel();
		my.setSize(700, 500);
		my.setVisible(true);
		my.setLocationRelativeTo(null);
	}

}
