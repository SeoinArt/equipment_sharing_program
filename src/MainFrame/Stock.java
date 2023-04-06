package MainFrame;

import javax.swing.*;

import Error.DuplicateException;
import Error.OmmisionException;
import ShareObject.*;
import ShareObject.Module;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

public class Stock extends JFrame implements ItemListener {

	Container cp;
	JPanel p = new JPanel(), p_1, n;
	JPanel n_1, n_2, n_3, n_4;
	JPanel c = new JPanel();
	JPanel c1, c2;
	JLabel[] m1 = new JLabel[5];
	JPanel[] n1 = new JPanel[5];
	ButtonGroup bg;
	JRadioButton sup, ren;
	JLabel jId, jEquip, jName, jCount;
	JTextField tfId, tfName, tfTel, tfcon;
	JTextField[] tf = new JTextField[5];
	JButton before, commit;

	String str[] = { "Arduiono", "Raspberry", "Module" };
	String nameing[] = { "Type", "detailType", "analogPin", "digitalPin", "usbType" };
	JComboBox<String> jcb;

	Rental re;
	Supple sp;

	int index = 0;
	int check = 5;
	int radioCheck = 0;

	public Stock(JFrame jf, User use, HashMap<String, Rental> ID_Rental, HashMap<String, Supple> ID_Supple) {
		super("::신청 페이지::");
		cp = this.getContentPane();
		cp.add(p, "North");
		n = new JPanel();
		n.setLayout(new BorderLayout());
		cp.add(n, "Center");
		cp.add(c, "South");
		ren = new JRadioButton("Rental");
		ren.addItemListener(this);
		sup = new JRadioButton("Supple");
		sup.addItemListener(this);

		bg = new ButtonGroup();
		bg.add(ren);
		bg.add(sup);
		p.add(ren);
		p.add(sup);
		p.add(p_1 = new JPanel());
		p_1.add(jEquip = new JLabel("부품 : "));
		jcb = new JComboBox<>(str);
		p_1.add(jcb);
//------------------------------------------------------------------------------------------
		jcb.addActionListener(e -> {
			JComboBox<String> cb = (JComboBox<String>) e.getSource();
			index = cb.getSelectedIndex();
			tf[0].setText((str[index]));
			check = (index == 0) ? 5 : (index == 1) ? 4 : 2;
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
		tfId.setEnabled(false);
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
		tf[0].setText(str[0]);
		tf[0].setEditable(false);
		n_1.add(new JLabel("수량 : "));
		n_1.add(tfcon = new JTextField(10));

		c.setLayout(new GridLayout(1, 2));
		c.add(c1 = new JPanel());
		c.add(c2 = new JPanel());
		c1.setLayout(new FlowLayout(FlowLayout.LEFT));
		c2.setLayout(new FlowLayout(FlowLayout.CENTER));

		c1.add(before = new JButton("이전"));
		before.setPreferredSize(new Dimension(80, 30));
		c2.add(commit = new JButton("신청"));

		before.addActionListener(event -> {
			showFrame(jf, this);
		});

		commit.addActionListener(event -> {
			try {
				checkText(); // 공백 체크
				if (radioCheck == 0) {
					throw new DuplicateException();
				}
				Equipment qp = getText(index);
				String count = tfcon.getText().trim();

				if (radioCheck == 1) {
					ID_Rental.put(use.getId(), new Rental(count, qp));
				} else {
					ID_Supple.put(use.getId(), new Supple(count, qp));
				}
				JOptionPane.showMessageDialog(this, "정상적으로 추가되었습니다.");
				showFrame(jf, this);
			} catch (OmmisionException e) {
				JOptionPane.showMessageDialog(this, "공백이 존재합니다");
			} catch (DuplicateException e1) {
				JOptionPane.showMessageDialog(this, "라디오 버튼을 클릭하세요");
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(this, "수량은 숫자입니다");
			}
		});

		commit.setPreferredSize(new Dimension(80, 30));
		setSize(400, 500);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

//	public void checkKey(User use, HashMap<String, Rental> ID_Rental, HashMap<String, Supple> ID_Supple) {
//		if (ID_Rental.containsKey(use.getId())) {
//			System.out.println("rental 이미 있음");
//		} else
//			System.out.println("rental 아직 없음");
//		if (ID_Supple.containsKey(use.getId())) {
//			System.out.println("supple 이미 있음");
//		} else
//			System.out.println("supple 아직 없음");
//	}	
	
	
	/**
	 * 
	 * @param jp
	 * @param s
	 * @param str 패널에 패널을 붙이고 붙인 패널에 라벨을 넣는 메서드
	 */
	

	public void infoLabel(JPanel jp, JPanel s, String str) {
		jp.add(s);
		s.add(new JLabel(str));
	}

	/**
	 * 텍스트 필드의 공백 체크 메서드
	 * 
	 * @throws OmmisionException
	 */
	public void checkText() throws OmmisionException {
		for (int i = 0; i < check; i++) {
			if (tf[i].getText().trim().equals("") || tfcon.getText().trim().equals("")) {
				throw new OmmisionException();
			}
		}
	}

	/**
	 * 체크박스를 클릭하면 textField가 바뀌는 메서드
	 * 
	 * @param index
	 */
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

	/**
	 * 텍스트필드 전체 클리어 메서드
	 */
	public void textClear() {
		tfcon.setText("");
		for (int i = 0; i < tf.length; i++) {
			tf[i].setText("");
		}
	}

	/**
	 * 프레임 전환 메서드
	 * 
	 * @param jm
	 * @param i
	 */

	public void showFrame(JFrame jm, JFrame i) {
		jm.setVisible(true);
		i.dispose();
	}

	/**
	 * 라디오버튼 이벤트
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (ren.isSelected()) {
			System.out.println("ren 클릭");
			radioCheck = 1;
		} else if (sup.isSelected()) {
			System.out.println("sup 클릭");
			radioCheck = 2;
		}

	}

	/**
	 * 텍스트 필드의 값으로 Equipment 객체를 만드는 메서드
	 * 
	 * @param index
	 * @return
	 */

	public Equipment getText(int index) {
		int check = (index == 0) ? 5 : (index == 1) ? 4 : 2;
		String[] str = new String[check];
		Equipment epa = null;
		for (int i = 0; i < check; i++) {
			str[i] = tf[i].getText();
		}
		if (index == 0) {
			Arduino a = new Arduino(str);
			epa = a;
		} else if (index == 1) {
			Raspberry b = new Raspberry(str);
			epa = b;
		}

		else if (index == 2) {
			Module c = new Module(str);
			epa = c;
		}
		return epa;
	}

}
