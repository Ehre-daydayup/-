package pkg;
import javax.swing.*;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*
;public class Visitor extends JFrame implements ActionListener{
	JButton b1=new JButton("查询");
	JButton b2=new JButton("刷新");
	JButton b3=new JButton("返回");
	
	JTable t1=new JTable();
	JScrollPane scroll;
	
	
	JMenuBar bar=new JMenuBar();
	
	Object[][] arr;
	

	String search="";
	
	int row=-1;

	Conn a=new Conn();
	

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
	
	public Visitor(){
		super("图书管理系统");
		bar.add(b1);
		bar.add(b2);
		bar.add(b3);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		setJMenuBar(bar);
		
		setSize(800,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		display();
	}
	
	public void display() {
		a.connDB();
		int i=0,j=0;
		try {
			a.rs=a.stmt.executeQuery("select 图书编号,图书名称,分类号,作者,出版社 from 图书");
			while(a.rs.next()) {
				i++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		arr=new Object[i][8];
		String columnNames[]= {"图书编号","图书名称","分类号","作者","出版社","定价","是否在馆","入库时间"};
		try {
			a.rs=a.stmt.executeQuery("select 图书编号,图书名称,分类号,作者,出版社,定价,是否在馆,入库时间 from 图书 "+search);
			while(a.rs.next()) {
				arr[j][0]=a.rs.getString("图书编号").trim();
				arr[j][1]=a.rs.getString("图书名称").trim();
				arr[j][2]=a.rs.getString("分类号").trim();
				arr[j][3]=a.rs.getString("作者").trim();
				arr[j][4]=a.rs.getString("出版社").trim();
				arr[j][5]=a.rs.getDouble("定价");
				arr[j][6]=a.rs.getBoolean("是否在馆");
				arr[j][7]=a.rs.getDate("入库时间");
				j++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		t1=new JTable(arr,columnNames);
		scroll=new JScrollPane(t1);
		this.add(scroll);
	}
	
	public void setSearch(String s) {
		search=s;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(b1)) {
			this.dispose();
			new VisitorSelect();
		}
		if(e.getSource().equals(b2)) {
			this.dispose();
			new Visitor();
		}
		if(e.getSource().equals(b3)) {
			this.dispose();
			new Login();
		}
	}
	public static void main(String args[]) {
		new Visitor().display();
	}
}
