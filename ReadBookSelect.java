package pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ReadBookSelect extends JFrame implements ActionListener {
	JTextField text = new JTextField(15);

	JButton b1 = new JButton("查询");
	JButton b2 = new JButton("返回");

	JLabel n1 = new JLabel("查询选择");
	JRadioButton radio1 = new JRadioButton("图书编号");
	JRadioButton radio2 = new JRadioButton("图书名称");
	JRadioButton radio3 = new JRadioButton("作者");
	ButtonGroup group = new ButtonGroup();

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();

	Conn a = new Conn();

	String s1 = "";
	String s2 = "";

	public ReadBookSelect() {
		super("图书管理系统");
		setLayout(new FlowLayout());

		group.add(radio1);
		group.add(radio2);
		group.add(radio3);
		radio1.setSelected(true);

		p2.setLayout(new GridLayout(1, 4));
		p3.setLayout(new GridLayout(1, 2));

		p1.add(text);
		p3.add(b1);
		p3.add(b2);

		p2.add(n1);
		p2.add(radio1);
		p2.add(radio2);
		p2.add(radio3);

		this.add(p1);
		this.add(p2);
		this.add(p3);

		b1.addActionListener(this);
		b2.addActionListener(this);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(450, 180);
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
		if (radio3.isSelected()) {
			x = 3;
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
			if (index == 0 || text.getText().trim().equals(a1)) {
				JOptionPane.showMessageDialog(null, "选择或查询不能为空");
			}
			if (index == 1) {
				a.connDB();
				try {
					a.rs = a.stmt.executeQuery("select * from 图书");
					while (a.rs.next()) {
						if (a.rs.getString(1).trim().contains(text.getText().trim())) {
							a2 = "where 图书编号 like " + "\'%" + text.getText().trim() + "%\'";
							System.out.println(a2);
							
							success = true;
							this.dispose();
						}
					}
					if (success == false) {
						JOptionPane.showMessageDialog(null, "查询失败");
					}else {
						ReadBook l1 = new ReadBook();
						l1.setString(s1, s2);
						l1.setSearch(a2);
						l1.display();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (index == 2) {
				a.connDB();
				try {
					a.rs = a.stmt.executeQuery("select * from 图书");
					while (a.rs.next()) {
						if (a.rs.getString(2).trim().contains(text.getText().trim())) {
							a2 = "where 图书名称 like " + "\'%" + text.getText().trim() + "%\'";
							System.out.println(a2);
							
							success = true;
							this.dispose();
						}
					}
					if (success == false) {
						JOptionPane.showMessageDialog(null, "查询失败");
					}else {
						ReadBook l1 = new ReadBook();
						l1.setString(s1, s2);
						l1.setSearch(a2);
						l1.display();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (index == 3) {
				a.connDB();
				try {
					a.rs = a.stmt.executeQuery("select * from 图书");
					while (a.rs.next()) {
						if (a.rs.getString(4).trim().contains(text.getText().trim())) {
							a2 = "where 作者 like" + "\'%" + text.getText().trim() + "%\'";
							System.out.println(a2);
							
							success = true;
							this.dispose();
						}
					}
					if (success == false) {
						JOptionPane.showMessageDialog(null, "查询失败");
					}else {
						ReadBook l1 = new ReadBook();
						l1.setString(s1, s2);
						l1.setSearch(a2);
						l1.display();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource().equals(b2)) {
			this.dispose();
			new Visitor();
		}
	}

	public static void main(String args[]) {
		new ReadBookSelect();
	}
}
