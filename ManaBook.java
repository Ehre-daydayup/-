package pkg;
import javax.swing.*;
import java.awt.event.*;

public class ManaBook extends JFrame implements ActionListener{
	JButton b1=new JButton("添加");
	JButton b2=new JButton("删除");
	JButton b3=new JButton("修改");
	JButton b4=new JButton("查询");
	JButton b5=new JButton("刷新");
	JButton b6=new JButton("返回");
	
	JTable t1;      //将数据以表格的形式显示给用户看的一种组件
	JScrollPane scroll;   //滚动窗格
	
	
	JMenuBar bar=new JMenuBar();   //创建菜单条
	
	String s1="";
	String s2="";
	String search = "";    //定义查询命令字符串
	Object[][] arr;    //定义了一个二维的对象，这个Object是任何对象的父类，可以转换成任何的类型
	int row = -1;      //所选行
	Conn a=new Conn();
	
	public ManaBook(){
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
		
		setJMenuBar(bar);  //添加菜单条
		
		setSize(600,422);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		display();   //显示数据
	}
	
	public void display() {
		a.connDB();
		int i=0,j=0;
		try {
			a.rs=a.stmt.executeQuery("select 图书编号,图书名称,分类号,作者,出版社 from 图书");
			while(a.rs.next()) {
				i++;  //统计该结果集共有几行
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		arr=new Object[i][8];
		String columnNames[]= {"图书编号","图书名称","分类号","作者","出版社","定价","是否在馆","入库时间"};  //定义表格每一列的属性
		try {
			a.rs=a.stmt.executeQuery("select * from 图书 " +search);
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
		t1=new JTable(arr,columnNames);    //将数据与列名添加进容器
		scroll=new JScrollPane(t1);
		this.add(scroll);
	}
	
	public void setSearch(String s) {    //获取查询命令
		search = s;
	}
	public void setString(String s1,String s2) {  //传递账号密码字符串
		this.s1=s1;
		this.s2=s2;
	}
	
	public void actionPerformed(ActionEvent e) {
		a.connDB();
		int b = 0;
		if(e.getSource().equals(b1)) {
			new ManaBookInsert();
		}
		if(e.getSource().equals(b2)) {
			row = t1.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "请选中要删除的数据");
			} else {
				try {
					int n = JOptionPane.showConfirmDialog(null, "是否删除?", "图书管理系统", JOptionPane.YES_NO_OPTION);  //选择框，true返回0，false返回1
					if (n == 1) {
						this.dispose();
						new ManaBook().setString(s1, s2);
					} else {
						b = a.stmt.executeUpdate("delete from 图书 where 图书编号 =\'" + arr[row][0] + "\'");
						this.dispose();
						new ManaBook().setString(s1, s2);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		if(e.getSource().equals(b3)) {
			row = t1.getSelectedRow();  //获取选中的是第几行
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "请选中要修改的数据");
			} else {
				ManaBookUpdate r = new ManaBookUpdate();
				r.setRow(row);    
				r.setMess(arr[row][0].toString(), arr[row][1].toString(), arr[row][2].toString(),arr[row][3].toString(), 
						arr[row][4].toString(), arr[row][5].toString(),arr[row][6].toString(),arr[row][7].toString());
				System.out.println(row + 1);
			}
		}
		if(e.getSource().equals(b4)) {
			this.dispose();
			new ManaBookSelect().setString(s1, s2);
		}
		if(e.getSource().equals(b5)) {
			this.dispose();
			ManaBook book=new ManaBook();
			book.display();
			book.setString(s1, s2);
			
		}
		if(e.getSource().equals(b6)) {
			this.dispose();
			new Mana().setString(s1, s2);
		}
	}
	public static void main(String args[]) {
		new ManaBook().display();
	}
}
