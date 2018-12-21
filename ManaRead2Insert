package pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManaRead2Insert extends JFrame implements ActionListener {
	JPanel p1, p2, p3;

	JLabel head = new JLabel("添加读者借阅信息");

	JLabel l1 = new JLabel("读者编号：");
	JLabel l2 = new JLabel("图书编号：");
	JLabel l3 = new JLabel("借阅日期：");
	JLabel l4 = new JLabel("归还日期：");

	JTextField t1 = new JTextField(10);
	JTextField t2 = new JTextField(10);
	JTextField t3 = new JTextField(10);
	JTextField t4 = new JTextField(10);

	JButton b1 = new JButton("确定");
	JButton b2 = new JButton("返回");

	Conn a = new Conn();

	public ManaRead2Insert() {
		super("图书管理系统");
		setLayout(new FlowLayout());

		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p2.setLayout(new GridLayout(4, 2));
		p3.setLayout(new FlowLayout());

		b1.addActionListener(this);
		b2.addActionListener(this);

		p1.add(head);

		p2.add(l1);
		p2.add(t1);
		p2.add(l2);
		p2.add(t2);
		p2.add(l3);
		p2.add(t3);
		p2.add(l4);
		p2.add(t4);

		p3.add(b1);
		p3.add(b2);

		this.add(p1);
		this.add(p2);
		this.add(p3);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(310, 280);
		setLocationRelativeTo(null);
		setVisible(true);
	}



	public void actionPerformed(ActionEvent e) {
		a.connDB();
		boolean b = false;
		if (e.getSource() == b1) {
			System.out.println("insert into  借阅 values (\'" + t1.getText().trim() + "\', \'" + t2.getText().trim()
					+ "\', \'" + t3.getText().trim() + "\', \'" + t4.getText().trim()+"\' )");
			if (t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("") || t4.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "信息不能有空");
			}
				try {
					b = a.stmt.execute("insert into  借阅 values (\'" + t1.getText().trim() + "\', \'" + t2.getText().trim()
							+ "\', \'" + t3.getText().trim() + "\', \'" + t4.getText().trim()+"\' )");
					this.dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			
		}
		if (e.getSource() == b2) {
			this.dispose();
		}

	}

	public static void main(String args[]) {
		new ManaRead2Insert();
	}
}
