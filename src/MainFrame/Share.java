package MainFrame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import ShareObject.User;



public class Share extends JFrame{

	Container cp;
	JPanel pN = new JPanel(new GridLayout(2, 1)); // 2행 1열
	JPanel pS = new JPanel(); // FlowLayout 남쪽
	JTextArea ta; // 중앙

	
	JPanel pN_sub = new JPanel();
	JPanel pN_sub_1 = new JPanel();

	
	JLabel lbTitle, lbName, lbDate, lbNo, lbMsg;
	JButton btAdd, btList, btDel, btEdit, btEditEnd, btFind;

	JTextField tfName, tfDate, tfNo, tfMsg;

	public Share(User use) {
		super("::MemoAppView::");
		cp = this.getContentPane();

		ta = new JTextArea("::한줄 메모장::");
		JScrollPane sp = new JScrollPane(ta);
		cp.add(sp, "Center");

		cp.add(pN, "North");
		cp.add(pS, "South");

		ta.setEditable(false); // 편집 불가 (읽기 전용)

		lbTitle = new JLabel(":: 부품 공유  :: ", JLabel.CENTER);
		lbTitle.setFont(new Font("Serif", Font.BOLD, 28));
		pN.add(lbTitle);
		
		pN.add(pN_sub);
		pN_sub.add(pN_sub_1);

		
		
		pN_sub_1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pN_sub_1.add(lbName = new JLabel("신청자: "));
		pN_sub_1.add(tfName = new JTextField(15));
		tfName.setText(use.getId());
		tfName.setHorizontalAlignment(JTextField.CENTER);
		tfName.setEditable(false);
		
		pN_sub_1.add(lbDate = new JLabel("User: "));
		pN_sub_1.add(tfDate = new JTextField(10));
		
		tfDate.setEditable(false);
		tfDate.setForeground(Color.blue);
		tfDate.setFont(new Font("Serif",Font.BOLD,14));
		tfDate.setHorizontalAlignment(JTextField.CENTER);
		String date = this.getSysDate();
		tfDate.setText(date);
		
		
		pS.setLayout(new GridLayout(1,3,20,20));
		pS.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10));
		pS.add(btDel = new JButton("신청"));
		btDel.addActionListener(event ->{
			Stock st = new Stock(use); 
			
			
		});
		pS.add(btEditEnd = new JButton("제공"));
		pS.add(btEdit = new JButton("내역"));
		
		

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public String getSysDate() {
		Date today = new Date(); // java.util.Date 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(today);
		return str;
		
	}

	public static void main(String[] args) {
		User use = new User();
		String str = "a";
		use.setAddr(str);
		use.setId(str);
		use.setName(str);
		use.setTel(str);
		
		Share my = new Share(use);
		
		my.setSize(800, 500);
		my.setLocationRelativeTo(null);
		my.setVisible(true);
	}
}
