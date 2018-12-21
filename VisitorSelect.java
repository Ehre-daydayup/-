package pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VisitorSelect extends JFrame implements ActionListener {
	JTextField text = new JTextField(15);

	JButton b1 = new JButton("查询");
	JButton b2 = new JButton("返回");

	JLabel head = new JLabel("查询图书");
	JLabel n1 = new JLabel("查询选择");
	JRadioButton radio1 = new JRadioButton("图书编号");
	JRadioButton radio2 = new JRadioButton("图书名称");
	JRadioButton radio3 = new JRadioButton("作者");
	ButtonGroup group = new ButtonGroup();

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();

	Conn a = new Conn();

	public VisitorSelect() {
		super("图书管理系统");
		setLayout(new FlowLayout());

		group.add(radio1);
		group.add(radio2);
		group.add(radio3);
		radio1.setSelected(true);

		p3.setLayout(new GridLayout(1, 4));
		p4.setLayout(new GridLayout(1, 2));

		p1.add(text);
		p2.add(text);
		p4.add(b1);
		p4.add(b2);

		p3.add(n1);
		p3.add(radio1);
		p3.add(radio2);
		p3.add(radio3);

		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(p4);

		b1.addActionListener(this);
		b2.addActionListener(this);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(420, 170);
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
						Visitor l1 = new Visitor();
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
							a2 = "where 图书名称 like" + "\'%" + text.getText().trim() + "%\'";
							System.out.println("select * from 图书" + a2);
							
							success = true;
							this.dispose();
						}
					}
					if (success == false) {
						JOptionPane.showMessageDialog(null, "查询失败");
					}else {
						Visitor l1 = new Visitor();
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
							a2 = "where 作者=" + "\'" + text.getText().trim() + "\'";
							System.out.println(a2);
							
							success = true;
							this.dispose();
						}
					}
					if (success == false) {
						JOptionPane.showMessageDialog(null, "查询失败");
					}else {
						Visitor l1 = new Visitor();
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
		new VisitorSelect();
	}
}
