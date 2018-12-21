package pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManaRead2Select extends JFrame implements ActionListener {
	JTextField text = new JTextField(15);

	JButton b1 = new JButton("查询");
	JButton b2 = new JButton("返回");

	JLabel n1 = new JLabel("查询选择");
	JRadioButton radio1 = new JRadioButton("读者编号");
	JRadioButton radio2 = new JRadioButton("图书编号");
	ButtonGroup group = new ButtonGroup();

	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();

	String s1 = "";
	String s2 = "";

	Conn a = new Conn();

	public ManaRead2Select() {
		super("图书管理系统");
		setLayout(new FlowLayout());

		group.add(radio1);
		group.add(radio2);
		radio1.setSelected(true);

		p3.setLayout(new GridLayout(1, 3));
		p4.setLayout(new GridLayout(1, 2));


		p2.add(text);

		p3.add(n1);
		p3.add(radio1);
		p3.add(radio2);

		p4.add(b1);
		p4.add(b2);

		this.add(p2);
		this.add(p3);
		this.add(p4);

		b1.addActionListener(this);
		b2.addActionListener(this);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(350, 170);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	int index = 0;

	public int select() {
		int x = 1;
		if (radio1.isSelected()) {
			x = 1;
		}
		if (radio2.isSelected()) {
			x = 2;
		}
		return x;
	}

	public void setString(String s1, String s2) {
		this.s1 = s1;
		this.s2 = s2;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) {
			index = select();
			String a1 = "";
			String a2 = "";
			boolean success = false;

			System.out.println("index=" + index);
			if (index == 0 || text.getText().trim().equals(a1)) {
				JOptionPane.showMessageDialog(null, "选择或查询不能为空");
			} else if (index == 1) {
				a.connDB();
				try {
					a.rs = a.stmt.executeQuery("select * from 借阅");
					while (a.rs.next()) {
						if (a.rs.getString(1).trim().contains(text.getText().trim())) {
							a2 = "where 读者编号 like" + "\'%" + text.getText().trim() + "%\'";
							System.out.println(a2);
							success = true;
						}
					}
					if (success == false) {
						JOptionPane.showMessageDialog(null, "查询失败");
					} else {
						this.dispose();
						ManaRead2 l = new ManaRead2();
						l.setString(s1, s2);
						l.setSearch(a2);
						l.display();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else if (index == 2) {
				System.out.println("where 图书编号 like" + "\'%" + text.getText().trim() + "%\'");
				a.connDB();
				try {
					a.rs = a.stmt.executeQuery("select * from 借阅");
					while (a.rs.next()) {
						if (a.rs.getString(2).trim().contains(text.getText().trim())) {
							a2 = "where 图书编号 like" + "\'%" + text.getText().trim() + "%\'";
							System.out.println(a2);
							success = true;
						}
					}
					if (success == false) {
						JOptionPane.showMessageDialog(null, "查询失败");
					} else {
						this.dispose();
						ManaRead2 l = new ManaRead2();
						l.setString(s1, s2);
						l.setSearch(a2);
						l.display();          //必须执行，不然不会显示查询结果
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource().equals(b2)) {
			this.dispose();
			new ManaRead2().setString(s1, s2);
		}
	}

	public static void main(String args[]) {
		new ManaRead2Select();
	}
}
