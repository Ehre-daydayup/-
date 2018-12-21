package pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManaRead1Update extends JFrame implements ActionListener {
	JPanel p1, p2, p3;

	JLabel head = new JLabel("修改读者信息");

	JLabel l1 = new JLabel("读者编号：");
	JLabel l2 = new JLabel("姓名：");
	JLabel l3 = new JLabel("性别：");
	JLabel l4 = new JLabel("院系：");
	JLabel l5 = new JLabel("电话：");
	JLabel l6 = new JLabel("登录密码：");

	JTextField t1 = new JTextField(10);
	JTextField t2 = new JTextField(10);
	JTextField t3 = new JTextField(10);
	JTextField t4 = new JTextField(10);
	JTextField t5 = new JTextField(10);
	JTextField t6 = new JTextField(10);

	JButton b1 = new JButton("确定");
	JButton b2 = new JButton("返回");
	
	String s1="";
	String s2="";

	int row = 0;
	Conn a = new Conn();

	public ManaRead1Update() {
		super("图书管理系统");
		setLayout(new FlowLayout());

		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p2.setLayout(new GridLayout(6, 2));
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
		p2.add(l5);
		p2.add(t5);
		p2.add(l6);
		p2.add(t6);

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

	public void setMess(String s1, String s2, String s3, String s4, String s5, String s6) {
		t1.setText(s1);
		t2.setText(s2);
		t3.setText(s3);
		t4.setText(s4);
		t5.setText(s5);
		t6.setText(s6);
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	public void setString(String s1,String s2) {
		this.s1=s1;
		this.s2=s2;
	}

	public void actionPerformed(ActionEvent e) {
		a.connDB();
	    boolean b=false;
		if (e.getSource() == b1) {
			System.out.println("update 读者 set 读者编号 =\'"+ t1.getText().trim() +"\',姓名=\'"+ t2.getText().trim() +"\', 性别=\'"
							+ t3.getText().trim() +"\', 院系 =\'"+ t4.getText().trim() +"\', 电话= \'"+t5.getText().trim() +"\', 登录密码=\'"
							+ t6.getText().trim()+"\' where 读者编号 =\'"+ s1 +"\'");
			if (t1.getText().equals("") || t2.getText().equals("")|| t3.getText().equals("")||
					t4.getText().equals("")|| t5.getText().equals("") || t6.getText().equals("")){
				JOptionPane.showMessageDialog(null, "信息不能为空");
			} else {
				try {
					b= a.stmt.execute("update 读者 set 读者编号 =\'"+ t1.getText().trim() +"\', 姓名=\'"+ t2.getText().trim() +"\', 性别=\'"
							+ t3.getText().trim() +"\', 院系 =\'"+ t4.getText().trim() +"\', 电话= \'"+t5.getText().trim() +"\', 登录密码=\'"
							+ t6.getText().trim()+"\' where 读者编号 =\'"+ s1 +"\'");
					this.dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		if(e.getSource()==b2) {
			this.dispose();
		}

	}

	public static void main(String args[]) {
		new ManaRead1Update();
	}
}
