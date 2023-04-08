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
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
	JButton btAdd, btDel, btSea, btBack, btUp;
	JTextField tfName, tfDate, tfNo, tfMsg;

	String[] s = { "신청", "공급" };

	public Share(JFrame jm, User use, HashMap<String, Rental> ID_Rental, HashMap<String, Supple> ID_Supple) {

		super("::MemoAppView::");

		cp = this.getContentPane();
		ta = new JTextArea();
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
		tfDate.setFont(new Font("Serif", Font.BOLD, 14));
		tfDate.setHorizontalAlignment(JTextField.CENTER);
		String date = this.getSysDate();
		tfDate.setText(date);

		pS.setLayout(new GridLayout(1, 3, 20, 20));
		pS.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		pS.add(btBack = new JButton("로그아웃"));
		btBack.addActionListener(event -> showFrame(jm, this));

		pS.add(btAdd = new JButton("신청"));
		pS.add(btUp = new JButton("수정"));
		pS.add(btDel = new JButton("삭제"));
		pS.add(btSea = new JButton("내역"));

		// ------------------------------------------------------------------------------------------------------------
		btAdd.addActionListener(event -> {
			Stock st = new Stock(this, use, ID_Rental, ID_Supple);
			setVisible(false);
		});
		btUp.addActionListener(event -> {
			int rev = JOptionPane.showOptionDialog(this, "수정 내용을 클릭하세요", "조회", 0, 1, null, s, s[0]);
			if (rev == 0) {
				if (hashMap_inCheck_rental(use.getId(), ID_Rental)) {
					UpdateFrame uf = new UpdateFrame(this, use, ID_Rental, ID_Supple, rev);
				} else
					JOptionPane.showMessageDialog(this, "수정할 내용이 없습니다!!");
			} else if (rev == 1) {
				if (hashMap_inCheck_supple(use.getId(), ID_Supple)) {
					UpdateFrame uf = new UpdateFrame(this, use, ID_Rental, ID_Supple, rev);
				} else
					JOptionPane.showMessageDialog(this, "수정할 내용이 없습니다!!");
			} else
				JOptionPane.showMessageDialog(this, "에상치 못한 오류 발생!!");

		});

		btDel.addActionListener(event -> {
			int rev = JOptionPane.showOptionDialog(this, "삭제 내용을 클릭하세요", "조회", 0, 1, null, s, s[0]);
			if (rev == 0) {
				if (hashMap_inCheck_rental(use.getId(), ID_Rental)) {
					delHashMap_Ren(use.getId(), ID_Rental);
					sP(this, "신청을 삭제하였습니다.");
				} else
					sP(this, "삭제할 내용이 없습니다!!");

			} else if (rev == 1) {
				if (hashMap_inCheck_supple(use.getId(), ID_Supple)) {
					delHashMap_Sup(use.getId(), ID_Supple);
					sP(this, "신청을 삭제하였습니다.");

				} else
					sP(this, "삭제할 내용이 없습니다!!");
			} else
				sP(this, "에상치 못한 오류 발생!!");
		});

		btSea.addActionListener(event -> {
			int rev = JOptionPane.showOptionDialog(this, "조회 내용을 클릭하세요", "조회", 0, 1, null, s, s[0]);
			if (rev == 0) {
				if (hashMap_inCheck_rental(use.getId(), ID_Rental)) {
					searchHashMap(use,ID_Rental,ID_Supple,0);
				} else
					sP(this, "신청하신 내용이 없습니다!!");

			} else if (rev == 1) {
				if (hashMap_inCheck_supple(use.getId(), ID_Supple)) {
					searchHashMap(use,ID_Rental,ID_Supple,1);
				} else
					sP(this, "기부하신 내역이 없습니다!!");
			} else
				sP(this, "에상치 못한 오류 발생!!");
		});
		setSize(800, 500);
		setLocationRelativeTo(null);
		setVisible(true);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * HashMap 내역을 검색하는 메서드
	 */
	public void searchHashMap(User use,HashMap<String, Rental> ID_Rental, HashMap<String, Supple> ID_Supple, int rev) {
		StringBuilder str = new StringBuilder("Use ID:   "+use.getId()+"\t");
		if (rev == 0) {
			Iterator<String> it = ID_Rental.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				Rental rel = ID_Rental.get(key);
				str.append((discriminationEq(rel.getEq())).toString());
			}
			ta.setText(str.toString());
		}
		else if (rev == 1) {
			Iterator<String> it = ID_Supple.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				Supple rel = ID_Supple.get(key);
				str.append((discriminationEq(rel.getEq())).toString());
			}
			ta.setText(str.toString());
		}

	}

	/**
	 * 부품 인스턴스를 확인하고 모든 내용을 출력하는 메서드
	 */
	public StringBuilder discriminationEq(Equipment eq) {
		StringBuilder str = new StringBuilder();
		if(eq instanceof Arduino) {
			Arduino ar = (Arduino)eq;
			String [] arr = ar.getArea();
			for(int i =0; i<arr.length; i++) {
				str.append(arr[i]);
				str.append("\t");
			}
			return str;
		}
		else if(eq instanceof Raspberry) {
			Raspberry ar = (Raspberry)eq;
			String [] arr = ar.getArea();
			for(int i =0; i<arr.length; i++) {
				str.append(arr[i]);
				str.append("\t");
			}
			return str;
		}
		else if(eq instanceof Module) {

			Module ar = (Module)eq;
			String [] arr = ar.getArea();
			for(int i =0; i<arr.length; i++) {
				str.append(arr[i]);
				str.append("\t");
			}
			return str;
		}
		else return null;
	}

	/**
	 * JOptionPane이 너무 길어서 사용하는 메소드
	 * 
	 * @param th
	 * @param msg
	 */
	public void sP(Component th, String msg) {
		JOptionPane.showMessageDialog(th, msg);
	}

	/**
	 * HashMap의 값을 삭제하는 메서드
	 */
	public void delHashMap_Ren(String user_id, HashMap<String, Rental> ID_Rental) {
		ID_Rental.remove(user_id);

	}

	public void delHashMap_Sup(String user_id, HashMap<String, Supple> ID_Supple) {
		ID_Supple.remove(user_id);
		System.out.println("정상적으로 삭제되었나?");
		System.out.println(ID_Supple.containsKey(user_id));
	}

	/**
	 * 프레임을 변환하는 메서드
	 * 
	 * @param jm
	 * @param i
	 */
	public void showFrame(JFrame jm, JFrame i) {
		jm.setVisible(true);
		i.dispose();
	}

	/**
	 * 오늘 날짜를 String으로 반환하는 메서드
	 * 
	 * @return
	 */
	public String getSysDate() {
		Date today = new Date(); // java.util.Date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(today);
		return str;

	}

	/**
	 * rental 신청 및 기부 내역을 출력하는 메서드
	 */
	public void searchList(int i, HashMap<String, Rental> rental) {
		StringBuilder str = new StringBuilder();
		if (i == 0) {
			Rental r = rental.get(tfName.getText());
			if (r.getEq() instanceof Arduino) {
				str.append("Arduino");
				Arduino a = (Arduino) r.getEq();
				ta.setText(Arrays.toString(a.getArea()));

			} else if (r.getEq() instanceof Raspberry) {
				str.append("Raspberry");
				Raspberry a = (Raspberry) r.getEq();
				ta.setText(Arrays.toString(a.getArea()));
			} else if (r.getEq() instanceof Module) {
				str.append("Module");
				Module a = (Module) r.getEq();
				ta.setText(Arrays.toString(a.getArea()));
			}
		}
	}

	/**
	 * searchList를 보충하는 메서드
	 * 
	 * @param user_id
	 * @param ID_Rental
	 * @return
	 */
	public boolean hashMap_inCheck_rental(String user_id, HashMap<String, Rental> ID_Rental) {
		boolean b1 = ID_Rental.containsKey(user_id);
		return b1;

	}

	/**
	 * searchList를 보충하는 메서드
	 * 
	 * @param user_id
	 * @param ID_Rental
	 * @return
	 */
	public boolean hashMap_inCheck_supple(String user_id, HashMap<String, Supple> ID_Supple) {
		boolean b1 = ID_Supple.containsKey(user_id);
		return b1;

	}

}
