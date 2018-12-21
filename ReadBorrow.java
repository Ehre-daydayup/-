package pkg;

import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadBorrow extends JFrame implements ActionListener {
	JButton b1 = new JButton("还书");
	JButton b2 = new JButton("刷新");
	JButton b3 = new JButton("返回");

	Object[][] arr;
	Conn a = new Conn();
	String s1 = "";
	String s2 = "";

	JTable j1;

	JMenuBar bar = new JMenuBar();

	int row = -1;

	JScrollPane scroll;

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public ReadBorrow(String s1, String s2) {
		super("图书管理系统");

		bar.add(b1);
		bar.add(b2);
		bar.add(b3);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		setJMenuBar(bar);

		setString(s1, s2);
		setSize(400, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		display();
	}

	public void display() {
		System.out.println("select 读者编号,图书编号,借阅日期,归还日期 from 借阅 where 读者编号=\'" + s1 + "\'");
		a.connDB();
		int i = 0, j = 0;
		try {
			a.rs = a.stmt.executeQuery("select 读者编号,图书编号,借阅日期,归还日期 from 借阅 where 读者编号=\'" + s1 + "\'");
			while (a.rs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		arr = new Object[i][3];
		String columnNames[] = { "图书编号", "借阅日期", "归还日期" };
		try {
			System.out.println("select 读者编号,图书编号,借阅日期,归还日期 from 借阅 where 读者编号=\'" + s1 + "\'");
			a.rs = a.stmt.executeQuery("select 图书编号,借阅日期,归还日期 from 借阅 where 读者编号=\'" + s1 + "\'");
			while (a.rs.next()) {
				arr[j][0] = a.rs.getString(1).trim();
				arr[j][1] = a.rs.getString(2).trim();
				arr[j][2] = a.rs.getString(3).trim();
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		j1 = new JTable(arr, columnNames);
		scroll = new JScrollPane(j1);
		this.add(scroll);
	}

	public void setString(String s1, String s2) {
		this.s1 = s1;
		this.s2 = s2;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) {
			row = j1.getSelectedRow();
			System.out.println("update 借阅 set 归还日期=\'" + df.format(new Date()) + "\' where 图书编号=\'" + arr[row][0]
					+ "\' and 读者编号=\'" + s1 + "\'");
			System.out.println("update 图书 set 是否在馆=\'false\' where 图书编号=\'" + arr[row][0] + "\'");

			if (row == -1) {
				JOptionPane.showMessageDialog(null, "请选中要归还的图书");
			} else {
				try {
					int n = 1;
					n = JOptionPane.showConfirmDialog(null, "是否归还?", "图书管理系统", JOptionPane.YES_NO_OPTION);
					if (n == 1) {
						this.dispose();
						new ReadBorrow(s1, s2);
					} else {
						int b = a.stmt.executeUpdate("update 借阅 set 归还日期=\'" + df.format(new Date())
								+ "\' where 图书编号=\'" + arr[row][0] + "\' and 读者编号=\'" + s1 + "\'");
						b = a.stmt.executeUpdate("update 图书 set 是否在馆=\'true\' where 图书编号=\'" + arr[row][0] + "\'");
						JOptionPane.showMessageDialog(null, "归还成功");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource().equals(b2)) {
			this.dispose();
			new ReadBorrow(s1, s2);
		}
		if (e.getSource().equals(b3)) {
			this.dispose();
			new Read().setString(s1, s2);
		}
	}

	public static void main(String args[]) {
		new ReadBorrow("", "");
	}
}
