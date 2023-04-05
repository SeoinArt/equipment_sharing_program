package MainFrame;

import javax.swing.*;

import ShareObject.User;

import java.awt.*;
import java.awt.event.*;

public class Stock extends JFrame {

	Container cp;
	JPanel p = new JPanel(), p_1, n;
	JPanel n_1, n_2, n_3, n_4;
	JPanel c = new JPanel();
	JPanel c1, c2;
	JLabel[] m1 = new JLabel[5];
	JPanel[] n1 = new JPanel[5];
	ButtonGroup bg;
	JRadioButton Supple, Rental;
	JLabel jId, jEquip, jName, jCount;
	JTextField tfId, tfName, tfTel;
	JTextField[] tf = new JTextField[5];
	JButton before, commit;

	String str[] = { "Ardiono", "Raspberry", "Module" };
	String nameing[] = { "Type", "detailType", "analogPin", "digitalPin", "usbType" };
	JComboBox<String> jcb;
	int index;
	public Stock(User use) {
		super("::MemoAppView::");
		cp = this.getContentPane();
		cp.add(p, "North");
		n = new JPanel();
		n.setLayout(new BorderLayout());
		cp.add(n, "Center");
		cp.add(c, "South");
		Rental = new JRadioButton("Rental");
		Supple = new JRadioButton("Supple");

		bg = new ButtonGroup();
		bg.add(Rental);
		bg.add(Supple);
		p.add(Rental);
		p.add(Supple);
		p.add(p_1 = new JPanel());
		p_1.add(jEquip = new JLabel("부품 : "));
		jcb = new JComboBox<>(str);
		p_1.add(jcb);

		jcb.addActionListener(e -> {
			JComboBox<String> cb = (JComboBox<String>) e.getSource();
			index = cb.getSelectedIndex();
			changeText(index);

		});

		p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		n.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		c.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		n_1 = new JPanel();
		n_2 = new JPanel();
		n_3 = new JPanel();
		n_4 = new JPanel();

		n.add(n_1, "North");
		n_1.add(new JLabel("아이디 : "));
		n_1.add(tfId = new JTextField(10));
		tfId.setText(use.getId());
		tfId.setEditable(false);

		n.add(n_2, "Center");

		n_2.setLayout(new GridLayout(5, 1));
		for (int i = 0; i < n1.length; i++) {
			n_2.add(n1[i] = new JPanel());
			n1[i].setLayout(new FlowLayout(FlowLayout.RIGHT));
			n1[i].add(m1[i] = new JLabel(nameing[i]));
			n1[i].add(tf[i] = new JTextField(10));
		}

		n_1.add(new JLabel("수량 : "));
		n_1.add(tfId = new JTextField(10));

		c.setLayout(new GridLayout(1, 2));
		c.add(c1 = new JPanel());
		c.add(c2 = new JPanel());
		c1.setLayout(new FlowLayout(FlowLayout.LEFT));
		c2.setLayout(new FlowLayout(FlowLayout.CENTER));
		c1.add(before = new JButton("이전"));
		before.setPreferredSize(new Dimension(80, 30));
		c2.add(commit = new JButton("신청"));
		commit.setPreferredSize(new Dimension(80, 30));
		setSize(400, 500);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void infoLabel(JPanel jp, JPanel s, String str) {
		jp.add(s, "");
		s.add(new JLabel(str));
	}

	public void infoLabel(JPanel s, String str) {
		s.add(new JLabel(str));
	}

	public void changeText(int index) {
		if (index == 1) {
			for (int i = 2; i < 5; i++) {
				m1[2].setText("gpio");
				m1[2].setVisible(true);
				m1[3].setText("usbType");
				m1[3].setVisible(true);
				if (i != 4) {
					tf[i].setVisible(true);
				}
				m1[4].setVisible(false);
				tf[4].setVisible(false);
			}
		} else if (index == 2) {
			for (int i = 2; i < 5; i++) {
				m1[i].setVisible(false);
				tf[i].setVisible(false);
			}
		} else
			for (int i = 0; i < m1.length; i++) {
				m1[i].setVisible(true);
				m1[i].setText(nameing[i]);
				tf[i].setVisible(true);
			}

	}

}
