package pkg;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Mana extends JFrame implements ActionListener{
	JPanel p1,p2;
	
	String s1="";
    String s2="";
	
	JLabel head=new JLabel("管理员");
	
	JButton b1=new JButton("图书信息管理");
	JButton b2=new JButton("读者信息管理");
	JButton b3=new JButton("读者借阅信息管理");
	JButton b4=new JButton("个人信息设置");
	JButton b5=new JButton("返回");
	
	public Mana() {
		super("图书管理系统");
		setLayout(new FlowLayout());
		p1=new JPanel();
		p2=new JPanel();
		
		p1.add(head);
		
		p2.setLayout(new GridLayout(5,1));   //网格布局，5行1列 
		p2.add(b1);
		p2.add(b2);
		p2.add(b3);
		p2.add(b4);
		p2.add(b5);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350,240);
		setLocationRelativeTo(null);
		setVisible(true);
		
		this.add(p1);
		this.add(p2);
	}
	
	public void setString(String s1,String s2) {
		this.s1=s1;
		this.s2=s2;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(b1)) {
			this.dispose();
			new ManaBook().setString(s1,s2);
		}
		if(e.getSource().equals(b2)) {
			this.dispose();
			new ManaRead1().setString(s1, s2);
		}
		if(e.getSource().equals(b3)) {
			this.dispose();
			new ManaRead2().setString(s1, s2);
		}
		if(e.getSource().equals(b4)) {
			System.out.println(s1+s2);
			ManaSelf self=new ManaSelf();
			self.setString(s1, s2);
			self.display();
		}
		if(e.getSource().equals(b5)) {
			this.dispose();
			new Login();
		}
	}
	
	public static void main(String args[]) {
		new Mana();
	}
}
