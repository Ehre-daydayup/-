package pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManaSelf extends JFrame implements ActionListener{
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();

	JLabel head = new JLabel("个人信息修改");
	JLabel l1 = new JLabel("管理员号：");
	JLabel l2 = new JLabel("登录密码：");

	JTextField t1 = new JTextField(10);
	JTextField t2 = new JTextField(10);

	JButton b1 = new JButton("确定");
	JButton b2 = new JButton("取消");
	
	String s1,s2;
	
	Conn a=new Conn();

	public ManaSelf() {
		super("图书管理系统");
		setLayout(new FlowLayout());

		p2.setLayout(new GridLayout(2, 2));

		p1.add(head);

		p2.add(l1);
		p2.add(t1);
		p2.add(l2);
		p2.add(t2);

		p3.add(b1);
		p3.add(b2);
		
		add(p1);
		add(p2);
		add(p3);

		b1.addActionListener(this);
		b2.addActionListener(this);
	}
	
	public void display() {
		t1.setText(s1);
		t2.setText(s2);
		setSize(300, 200);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		System.out.println(s1+s2);
		
	}
	
	public void setString(String s1,String s2) {
		this.s1=s1;
		this.s2=s2;
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(b1)) {
			a.connDB();
		    boolean b=false;
			try {
				b=a.stmt.execute("update  管理员 set 管理员号=\'"+t1.getText().trim()+
						"\',登录密码=\'"+ t2.getText().trim()+"\' where 管理员号=\'"+s1+"\'");
				this.dispose();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			
		}
		if(e.getSource().equals(b2)) {
			this.dispose();
		}
	}

	public static void main(String args[]) {
		new ManaSelf();
	}
}
