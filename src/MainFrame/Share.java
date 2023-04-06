package MainFrame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import ShareObject.Arduino;
import ShareObject.Equipment;
import ShareObject.Module;
import ShareObject.Raspberry;
import ShareObject.Rental;
import ShareObject.Supple;
import ShareObject.User;



public class Share extends JFrame {

	Container cp;
	JPanel pN = new JPanel(new GridLayout(2, 1)); // 2행 1열
	JPanel pS = new JPanel(); // FlowLayout 남쪽
	JTextArea ta; // 중앙
	JPanel pN_sub = new JPanel();
	JPanel pN_sub_1 = new JPanel();
	JLabel lbTitle, lbName, lbDate, lbNo, lbMsg;
	JButton btAdd, btList, btDel, btEdit, btEditEnd, btFind;
	JTextField tfName, tfDate, tfNo, tfMsg;
	HashMap<String,Rental> rental = new HashMap<>();
	HashMap<String,Supple> supple = new HashMap<>();
	int rev;
	
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
		pN_sub_1.add(lbName = new JLabel("User: "));
		pN_sub_1.add(tfName = new JTextField(15));
		tfName.setText(use.getId());
		tfName.setHorizontalAlignment(JTextField.CENTER);
		tfName.setEditable(false);
		
		pN_sub_1.add(lbDate = new JLabel("Today: "));
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
			Stock st = new Stock(this,use,rental,supple); 
		});
		pS.add(btEditEnd = new JButton("삭제"));
		pS.add(btEdit = new JButton("내역"));
		btEdit.addActionListener(event ->{
			if(!rental.containsKey(use.getId())&&!supple.containsKey(use.getId())) {
				JOptionPane.showMessageDialog(cp, "이용한 적이 없습니다.");
			}
			else
			{
				String[] s = {"신청","공급"};
				rev = JOptionPane.showOptionDialog(this, "조회 내용을 클릭하세요","조회", 0,1,null, s,s[0]);
				searchList(rev);
			}
		});
		
		setSize(800, 500);
		setLocationRelativeTo(null);
		setVisible(true);
		

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public String getSysDate() {
		Date today = new Date(); // java.util.Date 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(today);
		return str;
		
	}
	public void searchList(int i) {
		String s = "";
		if(i==0) {
			Rental r = rental.get(tfName.getText());
			if(r.getEq() instanceof Arduino) {
				s ="Arduino";
			} 
			else if (r.getEq() instanceof Raspberry) {
				s ="Raspberry";
			}
			else if (r.getEq() instanceof Module) {
				s = "Module";
			}
		}
		JOptionPane.showMessageDialog(cp,s);
	}

	
}
