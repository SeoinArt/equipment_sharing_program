package first_project;

import javax.swing.*;

import Error.*;

import java.awt.*;
import java.awt.event.*;

import java.util.HashMap;

public class ChangeId extends JFrame {

	Container cp;
	MyJPanel pN = new MyJPanel(10, 70, 30, 70);
	JPanel pc = new JPanel(new GridLayout(3, 1, 0, 0));
	JPanel pc_1, pc_2, pc_3;
	JPanel ps = new JPanel();
	JButton search, before;
	JTextField tfId = new JTextField(10);
	JTextField tfName = new JTextField(10);
	JTextField tfTel = new JTextField(10);
	JTextField[] jt = { tfId, tfName, tfTel };
	JLabel lbTitle, getId, getName, getTel;

	String[] check = new String[jt.length];

	public ChangeId(JFrame jm, HashMap<String, User> use) {
		super("::MemoAppView::");
		cp = this.getContentPane();
		cp.add(pN, "North");
		lbTitle = new JLabel(":: 비밀번호 찾기 ::", JLabel.CENTER);
		lbTitle.setFont(new Font("Serif", Font.BOLD, 25));
		pN.add(lbTitle);

		cp.add(pc, "Center");
		cp.add(ps, "South");
		pc.add(pc_1 = new JPanel());
		pc_1.setLayout(new FlowLayout(FlowLayout.CENTER));
		pc_1.add(getId = new JLabel("아이디 : "));
		pc_1.add(tfId);
		pc.add(pc_2 = new JPanel());
		pc_2.setLayout(new FlowLayout(FlowLayout.CENTER));
		pc_2.add(getId = new JLabel("이름 : "));
		pc_2.add(tfName);
		pc.add(pc_3 = new JPanel());
		pc_3.setLayout(new FlowLayout(FlowLayout.CENTER));
		pc_3.add(getId = new JLabel("전화번호 : "));
		pc_3.add(tfTel);

		ps.setLayout(new FlowLayout(FlowLayout.CENTER));
		ps.add(before = new JButton("이전"));
		ps.add(search = new JButton("검색"));

		search.addActionListener(event -> {
			try {
				checkText(use, jt);
			} catch (OmmisionException | InconsistencyException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(cp, "아이디가 존재하지 않거나 정보가 다릅니다.");
			}

		});

		before.addActionListener(event -> {
			jm.setVisible(true);
			dispose();
		});
		setSize(250, 300);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public void checkText(HashMap<String, User> use, JTextField[] jt) throws OmmisionException, InconsistencyException {

		for (int i = 0; i < jt.length; i++) {
			if (jt[i].getText().equals("")) {
				throw new OmmisionException();
			}
			if (use.containsKey(jt[0].getText())) {
				User user = use.get(jt[0].getText());
				check = user.getAll();

			}
			if (!jt[i].getText().equals(check[i]))
				throw new InconsistencyException();

		}
		String pawd = " ";
		for (;;) {
			pawd = JOptionPane.showInputDialog("비밀번호를 입력하세요");
			if (!(pawd.trim().equals(""))) {
				break;
			}
		}

		JOptionPane.showMessageDialog(cp, "비밀번호가 변경되었습니다.");
	}
}
