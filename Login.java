package pkg;
import java.awt.*;    //设置布局
import java.awt.event.*;  //处理事件
import javax.swing.*;    //组件

public class Login extends JFrame implements ActionListener{
	  JPanel p1,p2,p3,p4;
      ButtonGroup group;
      
      JLabel head1=new JLabel("用户登录     /");
      JLabel head2=new JLabel("     Login");

      JLabel n1=new JLabel("用户名：");
      JTextField t1=new JTextField(10);
      
      JLabel n2=new JLabel("密码：");
      JPasswordField t2=new JPasswordField(10);
      
      JLabel n3=new JLabel("身份选择：");
      JRadioButton radio1=new JRadioButton("管理员");
      JRadioButton radio2=new JRadioButton("读者");
      
      JButton b1=new JButton("登录");
      JButton b2=new JButton("注册");
      JButton b3=new JButton("重置");
      JButton b4=new JButton("访客登录");
      
      Conn a=new Conn();     //创建数据库对象

      int index=0;
      
      public Login(){
    	  super("图书管理系统");
    	  setLayout(new FlowLayout());
    	  p1=new JPanel();
    	  p2=new JPanel();
    	  p3=new JPanel();
    	  p4=new JPanel();
    	  
    	  p1.add(head1);
    	  p1.add(head2);
    	  
    	  p2.setLayout(new GridLayout(2,2));
    	  p2.add(n1);
    	  p2.add(t1);
    	  p2.add(n2);
    	  p2.add(t2);
    	  
    	  radio1.setSelected(true);
    	  
    	  group=new ButtonGroup();
    	  group.add(radio1);
    	  group.add(radio2);
    	  p3.setLayout(new GridLayout(1,4));
    	  p3.add(n3);
    	  p3.add(radio1);
    	  p3.add(radio2);
    	  
    	  p4.add(b1);
    	  p4.add(b2);
    	  p4.add(b3);
    	  p4.add(b4);
    	  
    	  this.add(p1);
    	  this.add(p2);
    	  this.add(p3);
    	  this.add(p4);
    	  
    	  radio1.addActionListener(this);      //this只能在实现ActionListener接口前提下使用
          radio2.addActionListener(this);      //等同于  b1.addActionListener(new ActionListener(){
          b1.addActionListener(this);          //                     public void actionPerformed(ActionEvent e){
          b2.addActionListener(this);          //                              操作;
          b3.addActionListener(this);          //                     })         
          b4.addActionListener(this);
          
    	  setDefaultCloseOperation(EXIT_ON_CLOSE);
  	      setSize(350,220);
  	      setLocationRelativeTo(null);        //设置居中
  	      setVisible(true);
      }
      
      public int select() {
    	  int x=1;
    	  if(radio2.isSelected()) {       //radio2是否选中
    		  x=2;
    	  }
    	  return x;
      }
      
      public void actionPerformed(ActionEvent e) {
    	  Object source=e.getSource();       
    	  String un="";
    	  String pw=""; 
    	  boolean success=false;
    	  index=select();
    	  if(source==b1) {
    		  if(t1.getText().trim().equals(un)||t2.getText().trim().equals(pw)) {    //trim()将字符串后面多余的空格去除，equal(String s)比较与字符串s是否相等
    			  JOptionPane.showMessageDialog(null, "登录名和密码不能为空");
    		  }
    		  else {
    			  a.connDB();  //连接数据库
    			  try {
    				  if(index==1) {   //是否选择管理员选项
    					  a.rs=a.stmt.executeQuery("select * from 管理员");   //rs是结果集。查询出的记录是一个列表，初始时指针指向的是第一条记录之前的。
    					  while(a.rs.next()) {                //每rs.next()一次指针都会向后移动一位，指向下一条记录
    						  un=a.rs.getString(1).trim();    //获取选中结果集的第一列字符串
    						  pw=a.rs.getString(2).trim();
    						  if(t1.getText().trim().equals(un)&&t2.getText().trim().equals(pw)) {
    							  success=true;
    							  break;
    						  }
    					  }
    						  if(success==false) {
    							  JOptionPane.showMessageDialog(null, "管理员登录失败");
    							  t2.setText("");
    						  }
    						  else {
    							  this.setVisible(false);
    							  Mana mana=new Mana();
    							  mana.setString(t1.getText().trim(),t2.getText().trim());    //将账号密码传到下一个窗口，后面有修改账号密码功能
    							  System.out.println(t1.getText().trim()+t2.getText().trim());  //命令行输出账号密码检查是否有误，后期经常使用这个方法
    						  }
    				  }
    				  if(index==2) {
    					  a.rs=a.stmt.executeQuery("select * from 读者");
    					  while(a.rs.next()) {
    						  un=a.rs.getString(1).trim();
    						  pw=a.rs.getString(6).trim();
    						  if(t1.getText().trim().equals(un)&&t2.getText().trim().equals(pw)) {
    							  success=true;
    							  break;
    						  }
    					  }
    						  if(success==false) {
    							  JOptionPane.showMessageDialog(null, "读者登录失败");
    							  t2.setText("");
    							  
    						  }
    						  else {
    							  this.setVisible(false);
    							  Read r=new Read();
    							  r.setString(t1.getText().trim(),t2.getText().trim());
    							  System.out.println(t1.getText().trim()+t2.getText().trim());
    						  }
    					  
    				  }
    			  }catch(Exception e1) {
    				  e1.printStackTrace();   //在命令行打印异常信息在程序中出错的位置及原因
    			  }
    			  a.closeDB();
    		  }
    	  }
    	  if(source==b2) {
    		  new ManaRead1Insert();
    	  }
    	  if(source==b3) {
    		  t1.setText("");
    		  t2.setText("");
    	  }
    	  if(source==b4) {
    		  this.dispose();
    		  new Visitor();
    	  }
      }
      
      public static void main(String args[]) {
    	  new Login();
      }
         
}
