package MainFrame;

import javax.swing.*;

import Error.*;
import ShareObject.User;

import java.awt.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class MyLayout extends JFrame {

	Container cp;

	MyJPanel pm = new MyJPanel();
	HashMap<String, User> use = new HashMap<>();

	JLabel id, pwd;
	JTextField idText;
	JPasswordField pwdText;
	JButton b1, b2, b3;
	int[] a= new int[5];

	public MyLayout() {
		super(":: 비품 공유 시스템::");
		cp = getContentPane();
		cp.add(pm);

		MyJPanel idPanel = new MyJPanel();

		MyJPanel btnPanel = new MyJPanel(5, 70, 10, 70);
		id = new JLabel("ID", JLabel.LEFT);
		pwd = new JLabel("Passwd", JLabel.LEFT);

		idText = new JTextField(15);
		pwdText = new JPasswordField(15);

		b1 = new JButton("회원가입");
		b2 = new JButton("로그인");
		b3 = new JButton("아이디/비밀번호 찾기");
		

		pm.add(idPanel);
		GridLayout gd = new GridLayout(2, 2, 5, 10);
		idPanel.setLayout(gd);
		idPanel.add(id);
		idPanel.add(idText);
		idPanel.add(pwd);
		idPanel.add(pwdText);

		pm.add(btnPanel);
		
		btnPanel.add(b2);
		btnPanel.add(b3);
		btnPanel.add(b1);

		b1.addActionListener(event -> {
			CreateMember CM = new CreateMember(this,use);
			setVisible(false);
			
		});
		b2.addActionListener(event -> {
			try {
				checkId(idText.getText());
				Share sh = new Share(use.get(idText.getText()));
			} catch (NoUserName e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(cp, "아이디 또는 비밀번호가 틀렸습니다.");
			}
		});
		b3.addActionListener(event -> {
			ChangeId ci = new ChangeId(this,use);
			setVisible(false);
		});

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// 생성자-------------------

	
	void checkId(String idText) throws NoUserName{
		if(!use.containsKey(idText) ) {
			throw new NoUserName();
		}
		else {
			try {
				checkPwd(use.get(idText));
			} catch (InconsistencyException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(cp, "아이디 또는 비밀번호가 틀렸습니다.");
			}
		}
	}
	void checkPwd(User user) throws InconsistencyException {
		String pwd = new String(pwdText.getPassword());
		if(!user.getPasswd().equals(pwd)) {
			throw new InconsistencyException();
		}
	}
	
	public static void main(String[] args) {
		MyLayout my = new MyLayout();
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		my.setSize(500, 180);
		my.setLocation(res.width / 2 - 250, res.height / 2 - 100);
		my.setVisible(true);

	}

}
