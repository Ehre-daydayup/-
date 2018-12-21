package pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManaRead2 extends JFrame implements ActionListener {
	JButton b1 = new JButton("添加");
	JButton b2 = new JButton("删除");
	JButton b3 = new JButton("修改");
	JButton b4 = new JButton("查询");
	JButton b5 = new JButton("刷新");
	JButton b6 = new JButton("返回");

	JTable t1 = new JTable();
	JScrollPane scroll;

	int row = -1;

	JMenuBar bar = new JMenuBar();

	Object[][] arr;

	String search = "";
	String s1="";
	String s2="";
			

	Conn a = new Conn();

	public ManaRead2() {
		super("图书管理系统");
		bar.add(b1);
		bar.add(b2);
		bar.add(b3);
		bar.add(b4);
		bar.add(b5);
		bar.add(b6);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);

		setJMenuBar(bar);

		setSize(500, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		display();
	}

	public void display() {
		a.connDB();
		int i = 0, j = 0;
		try {
			a.rs = a.stmt.executeQuery("select * from 借阅 order by 读者编号");
			while (a.rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		arr = new Object[i][4];
		String columnNames[] = { "读者编号", "图书编号", "借阅日期", "归还日期" };
		try {
			a.rs = a.stmt.executeQuery("select * from 借阅 " + search);
			while (a.rs.next()) {
				arr[j][0] = a.rs.getString("读者编号").trim();
				arr[j][1] = a.rs.getString("图书编号").trim();
				arr[j][2] = a.rs.getString("借阅日期").trim();
				arr[j][3] = a.rs.getString("归还日期").trim();

				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		t1 = new JTable(arr, columnNames);
		scroll = new JScrollPane(t1);
		this.add(scroll);
	}

	public void setSearch(String s) {
		search = s;
	}
	public void setString(String s1,String s2) {
		this.s1=s1;
		this.s2=s2;
	}

	
	public void actionPerformed(ActionEvent e) {
		a.connDB();
		int b = 0;
		if (e.getSource().equals(b1)) {
			new ManaRead2Insert();
		}
		if (e.getSource().equals(b2)) {
			row = t1.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "请选中要删除的数据");
			} else {
				try {
					int n = JOptionPane.showConfirmDialog(null, "是否删除?", "图书管理系统", JOptionPane.YES_NO_OPTION);
					if (n == 1) {
						this.dispose();
						new ManaRead2().display();
					} else {
						b = a.stmt.executeUpdate("delete from 借阅 where 读者编号 =\'" + arr[row][0] + "\'  and 图书编号=\'"+arr[row][1]+"\'");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource().equals(b3)) {
			row = t1.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "请选中要修改的数据");
			} else {
				ManaRead2Update r = new ManaRead2Update();
				r.setString(arr[row][0].toString(), arr[row][1].toString());
				r.setRow(row);
				r.setMess(arr[row][0].toString(), arr[row][1].toString(), arr[row][2].toString(),
						arr[row][3].toString());
				System.out.println(row + 1);
			}
		}
		if (e.getSource().equals(b4)) {
			this.dispose();
			new ManaRead2Select().setString(s1, s2);
		}
		if (e.getSource().equals(b5)) {
			this.dispose();
			ManaRead2 read2=new ManaRead2();
			read2.display();
			read2.setString(s1, s2);
		}
		if (e.getSource().equals(b6)) {
			this.dispose();
			new Mana().setString(s1, s2);
		}
	}

	public static void main(String args[]) {
		new ManaRead2().display();
	}
}
