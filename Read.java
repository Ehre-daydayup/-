package pkg;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Read extends JFrame implements ActionListener{
    JPanel p1,p2;
	
	JLabel head=new JLabel("读者");
	
	JButton b1=new JButton("图库图书信息查看");
	JButton b2=new JButton("个人借阅信息查看");
	JButton b3=new JButton("个人信息设置");
	JButton b4=new JButton("返回");
	
	String s1="";
	String s2="";
	
	public Read() {
		super("图书管理系统");
		setLayout(new FlowLayout());
		p1=new JPanel();
		p2=new JPanel();
		
		p1.add(head);
		
		p2.setLayout(new GridLayout(4,1));
		p2.add(b1);
		p2.add(b2);
		p2.add(b3);
		p2.add(b4);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
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
			new ReadBook().setString(s1, s2);
		}
		if(e.getSource().equals(b2)) {
			this.dispose();
			System.out.println(s1);
			new ReadBorrow(s1,s2);
		}
		if(e.getSource().equals(b3)) {
			ReadSelf self=new ReadSelf();
			self.setString(s1, s2);
			self.display();
		}
		if(e.getSource().equals(b4)) {
			this.dispose();
			new Login();
		}
	
    }
	
	public static void main(String args[]) {
		new Read();
	}
}
