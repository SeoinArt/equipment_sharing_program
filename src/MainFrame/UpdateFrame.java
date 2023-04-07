package MainFrame;

import javax.swing.*;

import Error.InconsistencyException;
import ShareObject.Arduino;
import ShareObject.Equipment;
import ShareObject.Module;
import ShareObject.Raspberry;
import ShareObject.Rental;
import ShareObject.Supple;
import ShareObject.User;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class UpdateFrame extends JFrame {

	Container cp;
	JPanel pc = new JPanel();
	JPanel p = new JPanel();
	static JLabel[] jb = new JLabel[6];
	static JTextField[] jf = new JTextField[6];
	JPanel[] jp = new JPanel[7];
	String[] st = null;
	JButton back, commit;
	String[] getText;
	String[] ard = { "type", "detailType", "analogPin", "digitalPin", "usbType" };
	String[] rab = { "type", "detailType", "gpio", "usbType" };
	String[] mod = { "type", "detailType" };
	int c;
//	int state;
	int sort;

	public UpdateFrame(JFrame jm, User use, HashMap<String, Rental> ID_Rental, HashMap<String, Supple> ID_Supple,
			int rev) {
		super("::MemoAppView::");
		cp = this.getContentPane();
		cp.add(pc, "Center");

		pc.setLayout(new GridLayout(7, 2));

		for (int i = 0; i < jb.length; i++) {
			pc.add(jp[i] = new JPanel());
			jp[i].setLayout(new FlowLayout(FlowLayout.RIGHT));
			jp[i].add(jb[i] = new JLabel(), JLabel.CENTER);
			jp[i].add(jf[i] = new JTextField(10));
		}
		for (int i = 0; i < ard.length; i++) {
			jb[i].setText(ard[i]);
		}

		pc.add(jp[6] = new JPanel());
		jp[6].add(back = new JButton("Back"));
		jp[6].add(commit = new JButton("Commit"));

		if (rev == 0) {
			checkEq((ID_Rental.get(use.getId())).getEq(), ID_Rental.get(use.getId()).getCount());
		} else if (rev == 1) {
			checkEq((ID_Supple.get(use.getId())).getEq(), ID_Supple.get(use.getId()).getCount());
		} else {
			JOptionPane.showMessageDialog(this, "아주 심각한 오류 발생");
		}

		back.addActionListener(event -> {
			jm.setVisible(true);
			dispose();
		});

		commit.addActionListener(event -> {
			output_Eq(rev, use.getId(), ID_Rental, ID_Supple,jm);
		});

		setSize(200, 250);
		setVisible(true);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}// ---------------------------------------------------------- 생성자 메소드

	/**
	 * 부품의 종류를 추출하고 해당 부품의 변수의 개수를 출력하는 메소드.
	 * 
	 * @param eq
	 */
	public void checkEq(Equipment eq, int count) {
		int i = 5;
		if (eq instanceof Arduino) {
			i = 5;
			sort = 0;
			setingView(ard, i, eq, count);
			System.out.println("아두이노");
		} else if (eq instanceof Raspberry) {
			i = 4;
			sort = 1;
			setingView(rab, i, eq, count);
			System.out.println("라즈베리");
		} else if (eq instanceof Module) {
			i = 2;
			sort = 2;
			setingView(mod, i, eq, count);
			System.out.println("모듈");
		}

	}

	public String[] textFieldGetText(int num) {
		String[] s = new String[num + 1];
		for (int i = 0; i < num + 1; i++) {
			s[i] = jf[i].getText();
		}
		return s;
	}

	/**
	 * 부품을 추출하는 메서드
	 */
	public void output_Eq(int rev, String user_id, HashMap<String, Rental> ID_Rental,
			HashMap<String, Supple> ID_Supple,JFrame jm) {
		if (rev == 0) {
			try {
				int cout = (sort == 0) ? 5 : (sort == 1) ? 4 : 2;
				Rental n_rental = new Rental(jf[cout].getText(), createEq(sort));
				n_rental.setCount(cout);
				ID_Rental.put(user_id, n_rental);
				JOptionPane.showMessageDialog(this, "정상적으로 수행되었습니다.");
				showReturn(jm);
			} catch (InconsistencyException e) {
				e.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "수량은 숫자만 적어주세요");
			}
		} else if (rev == 1) {
			try {
				int cout = (sort == 0) ? 5 : (sort == 1) ? 4 : 2;
				Supple n_supple = new Supple(jf[cout].getText(), createEq(sort));
				n_supple.setCount(cout);
				ID_Supple.put(user_id, n_supple);
				JOptionPane.showMessageDialog(this, "정상적으로 수행되었습니다.");
				showReturn(jm);
			} catch (InconsistencyException e) {
				e.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "수량은 숫자만 적어주세요");
				
			}
		}

		else
			JOptionPane.showMessageDialog(this, "예상치 못한 오류 발생!!");
	}
	public void showReturn(JFrame jm) {
		jm.setVisible(true);
		this.dispose();
	}

	/**
	 * 부품을 새롭게 수정하여 생성하는 메서드
	 * 
	 * @throws InconsistencyException
	 * 
	 */

	public Equipment createEq(int state) throws InconsistencyException {
		String str[];
		Equipment neEq;
		if (sort == 0) {
			str = new String[5];
			for (int i = 0; i < 5; i++) {
				str[i] = jf[i].getText();
			}
			neEq = new Arduino(str);
		} else if (sort == 1) {
			str = new String[4];
			for (int i = 0; i < 4; i++) {
				str[i] = jf[i].getText();
			}
			neEq = new Raspberry(str);
		} else if (sort == 2) {
			str = new String[2];
			for (int i = 0; i < 2; i++) {
				str[i] = jf[i].getText();
			}
			neEq = new Module(str);
		} else
			throw new InconsistencyException();

		return neEq;

	}

	/**
	 * 정수를 받아 정수의 textfield 값까지만 저장하는 메소드
	 * 
	 * @param i
	 * @return
	 */

	public String[] getString(int i) {
		String[] text = new String[i];
		for (int j = 0; j < text.length; j++) {
			text[j] = jf[j].getText();
		}
		return text;
	}

	/**
	 * 넘겨받은 부품의 정보를 미리 입력해주는 메소드
	 */

	public void setingView(String[] text, int num, Equipment eq, int count) {

		if (num == 5) {
			System.out.println("1");
			Arduino ar = (Arduino) eq;
			int i = 0;
			for (i = 0; i < num; i++) {
				setingView_1(i, text);
				jf[i].setText(ar.getArea()[i]);
			}
			jb[num].setVisible(true);
			jb[num].setText("Count");
			jf[num].setVisible(true);
			jf[num].setText(String.valueOf(count));
			getText = textFieldGetText(num);
			for (i = (num) + 1; i < jb.length; i++) {
				setingView_2(i);
			}
		} else if (num == 4) {
			System.out.println("2");
			int i = 0;
			Raspberry ra = (Raspberry) eq;
			for (i = 0; i < num; i++) {
				setingView_1(i, text);
				jf[i].setText(ra.getArea()[i]);
			}
			jb[num].setVisible(true);
			jb[num].setText("Count");
			jf[num].setVisible(true);
			jf[num].setText(String.valueOf(count));
			getText = textFieldGetText(num);
			for (i = (num) + 1; i < jb.length; i++) {
				setingView_2(i);
			}
		} else if (num == 2) {
			System.out.println("3");
			int i = 0;
			Module mo = (Module) eq;
			for (i = 0; i < num; i++) {
				setingView_1(i, text);
				jf[i].setText(mo.getArea()[i]);
			}
			jb[num].setVisible(true);
			jb[num].setText("Count");
			jf[num].setVisible(true);
			jf[num].setText(String.valueOf(count));
			getText = textFieldGetText(num);
			for (i = (num) + 1; i < jb.length; i++) {
				setingView_2(i);
			}
		} else
			JOptionPane.showMessageDialog(this, "예상치 못한 오류 발생!!");

	}

	public void setingView_1(int i, String str[]) {
		jb[i].setVisible(true);
		jb[i].setText(str[i]);
		jf[i].setVisible(true);
	}

	public void setingView_2(int i) {
		jb[i].setVisible(false);
		jf[i].setVisible(false);
	}
}
