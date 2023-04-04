package first_project;

import javax.swing.*;

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
			setTitle("회원가입");
			CreateMember CM = new CreateMember(this,use);
			setVisible(false);
			
		});
		b2.addActionListener(event -> {
			setTitle("로그인");
			for(int i=0;i<a.length;i++) {
				System.out.println(a[i]);
			}
		});
		b3.addActionListener(event -> {
			setTitle("아이디");
		});

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// 생성자-------------------

	public static void main(String[] args) {
		MyLayout my = new MyLayout();
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		my.setSize(500, 180);
		my.setLocation(res.width / 2 - 250, res.height / 2 - 100);
		my.setVisible(true);

	}

}
