package First_project;

import java.awt.*;

import java.util.HashMap;
import java.util.Scanner;

import javax.swing.*;
import Error.*;

public class CreateMember extends JFrame {
	private Container cp;
	private GridBagConstraints gc = new GridBagConstraints();
	private JLabel jl[] = new JLabel[6];
	private TextField ta[] = new TextField[6];
	private JPanel mainJP = new JPanel();
	private JLabel resetLabel;
	private JPanel jp[] = new JPanel[16];
	private ButtonGroup bg = new ButtonGroup();
	JRadioButton man, fman;
	JButton last, reset, commit;
	String[] info = new String[6];
	String[] text = new String[6];

	private String[] str = { "ID", "Passwd", "RePWD", "NAME", "Addr", "Tel" };

	CreateMember(JFrame jm, HashMap<String, User> use) {

		super(":: 회원 가입 ::");
		setSize(700, 500);
		cp = getContentPane();

		add(mainJP);
		mainJP.setLayout(new GridBagLayout());
		for (int i = 0; i < jp.length; i++) {
			jp[i] = new JPanel();

			if (i < jl.length) {
				jl[i] = new JLabel("", JLabel.CENTER);
				jl[i].setFont(new Font("Serif", Font.BOLD, 18));
				addLb(jp[i], jl[i], str[i]);
			}
			if (i < ta.length)
				ta[i] = new TextField(25);
			if (i > str.length - 1 && i - str.length < ta.length) {
				addTa(jp[i], ta[i - str.length]);
			}

		}

		addGB(0, 0, 1, 1, 1, 1, jp[0]);
		addGB(0, 1, 1, 1, 1, 1, jp[1]);
		addGB(0, 2, 1, 1, 1, 1, jp[2]);
		addGB(0, 3, 1, 1, 1, 1, jp[3]);
		addGB(0, 4, 1, 1, 1, 1, jp[4]);
		addGB(0, 5, 1, 1, 1, 1, jp[5]);

		addGB(3, 0, 1, 1, 1, 0, jp[6]);
		addGB(3, 1, 1, 1, 1, 0, jp[7]);
		addGB(3, 2, 1, 1, 1, 0, jp[8]);
		addGB(3, 3, 1, 1, 1, 0, jp[9]);
		addGB(3, 4, 1, 1, 1, 0, jp[10]);
		addGB(3, 5, 1, 1, 1, 0, jp[11]);
		addGB(3, 6, 1, 1, 1, 0, jp[12]);

		addGB(5, 0, 0, 4, 1, 1, jp[13]);
		addGB(5, 7, 0, 2, 1, 1, jp[14]);

		jp[13].setLayout(new BorderLayout());
		jp[13].add(resetLabel = new JLabel("", JLabel.CENTER), BorderLayout.CENTER);
		resetLabel.setFont(new Font("Serif", Font.BOLD, 20));
		jp[13].setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

		jp[14].add(last = new JButton("이전"));
		jp[14].add(commit = new JButton("생성"));
		jp[14].add(reset = new JButton("Reset"));

		last.addActionListener(evemt -> {
			setTitle("이전");
			jm.setVisible(true);
			dispose();

		});

		reset.addActionListener(event -> {
			textFieldReset(ta);
			resetLabel.setText("초기화 되었습니다.");
		});

		commit.addActionListener(event -> {
			try {
				textFieldGetText(use);
				jm.setVisible(true);
				dispose();
			} catch (OmmisionException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(cp, "정보를 채워주세요");
			}
		});

		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);

	}// 생성자-------------------

	void checkId(String id, HashMap<String, User> use) throws DuplicateException {
		if (use.containsKey(id)) {
			throw new DuplicateException();
		}
	}
	// 아이디 중복 체크

	void checkPasswd(String fPwd, String SPwd) throws InconsistencyException {
		if (!fPwd.equals(SPwd)) {
			throw new InconsistencyException();
		}
	}
	// 패스워드 비교 체크

	void textFieldReset(TextField[] ta) {
		for (int i = 0; i < ta.length; i++) {
			ta[i].setText("");
		}
	}
	// textField clear

	// textField 내용을 string으로 가져오는 method
	void textFieldGetText(HashMap<String, User> use) throws OmmisionException {
		for (int i = 0; i < ta.length; i++) {
			if (ta[i].getText().equals("")){ 
				throw new OmmisionException();
				}
			else {
				if (i == 0) {
					try {
						checkId(ta[0].getText(), use);
						
					} catch (DuplicateException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(cp, "아이디가 이미 존재합니다.");
						return;
					}
				}
				if (i == 2) {
					try {
						checkPasswd(ta[1].getText(), ta[2].getText());
					} catch (InconsistencyException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(cp, "비밀번호가 다릅니다");
						return;
					}
				}
				text[i] = ta[i].getText();
			}
		}
		addUser(text, use);
		JOptionPane.showMessageDialog(cp, "회원가입 완료");
		textFieldReset(ta);
		
		
	}

	void addUser(String[] text, HashMap<String, User> use) {
		User us = new User();
		us.setId(text[0].trim());
		us.setPasswd(text[1].trim());
		us.setName(text[3].trim());
		us.setAddr(text[4].trim());
		us.setTel(text[5].trim());
		use.put(us.getId(), us);
	}

	// user 정보를 HashMap에 저장

	void addGB(int x, int y, int w, int h, double wx, double wy, JPanel j) {
		gc.gridx = x;
		gc.gridy = y;
		gc.gridwidth = w;
		gc.gridheight = h;
		gc.weightx = wx;
		gc.weighty = wy;
		gc.fill = GridBagConstraints.BOTH;
		mainJP.add(j, gc);
	}
	// 그리드 레이아웃 위치 설정

	void addLb(JPanel j, JLabel l, String str) {
		j.setLayout(new BorderLayout());
		l.setText(str);
		j.add(l);
	}
	// 라벨 추가

	void addTa(JPanel j, TextField tf) {
		j.setLayout(new FlowLayout(FlowLayout.LEFT));
		j.setBorder(BorderFactory.createEmptyBorder(14, 1, 1, 1));
		j.add(tf, BorderLayout.EAST);

	}
	// 텍스트 필드 추가
}
