package pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManaBookUpdate extends JFrame implements ActionListener {
	JPanel p1, p2, p3;

	JLabel head = new JLabel("修改图书信息");

	JLabel l1 = new JLabel("图书编号：");
	JLabel l2 = new JLabel("图书名称：");
	JLabel l3 = new JLabel("分类号：");
	JLabel l4 = new JLabel("作者：");
	JLabel l5 = new JLabel("出版社：");
	JLabel l6 = new JLabel("定价：");
	JLabel l7 = new JLabel("是否在馆：");
	JLabel l8 = new JLabel("入库时间：");

	JTextField t1 = new JTextField(10);
	JTextField t2 = new JTextField(10);
	JTextField t3 = new JTextField(10);
	JTextField t4 = new JTextField(10);
	JTextField t5 = new JTextField(10);
	JTextField t6 = new JTextField(10);
	JTextField t7 = new JTextField(10);
	JTextField t8 = new JTextField(10);

	JButton b1 = new JButton("确定");
	JButton b2 = new JButton("返回");

	int row = 0;
	Conn a = new Conn();

	public ManaBookUpdate() {
		super("图书管理系统");
		setLayout(new FlowLayout());

		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p2.setLayout(new GridLayout(8, 2));
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
		p2.add(l7);
		p2.add(t7);
		p2.add(l8);
		p2.add(t8);

		p3.add(b1);
		p3.add(b2);

		this.add(p1);
		this.add(p2);
		this.add(p3);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(310, 320);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setMess(String s1, String s2, String s3, String s4, String s5, String s6,String s7,String s8) {
		t1.setText(s1);
		t2.setText(s2);
		t3.setText(s3);
		t4.setText(s4);
		t5.setText(s5);
		t6.setText(s6);
		t7.setText(s7);
		t8.setText(s8);
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void actionPerformed(ActionEvent e) {
		a.connDB();
	    boolean b=false;
		if (e.getSource() == b1) {
			System.out.println("update 图书 set 图书编号 =\'"+ t1.getText().trim() +"\',图书名称=\'"+ t2.getText().trim() +"\', 分类号=\'"
							+ t3.getText().trim() +"\', 作者 =\'"+ t4.getText().trim() +"\', 出版社= \'"+t5.getText().trim() +"\', 定价=\'"
							+ t6.getText().trim()+"\', 是否在馆=\'"+ t7.getText().trim()+"\', 入库时间=\'"+ t8.getText().trim()
							+"\' where 图书编号 =\'"+ t1.getText().trim() +"\'");
			if (t1.getText().equals("") || t2.getText().equals("")|| t3.getText().equals("")||t4.getText().equals("")|| 
					t5.getText().equals("") || t6.getText().equals("")||t7.getText().equals("")||t8.getText().equals("")){
				JOptionPane.showMessageDialog(null, "信息不能为空");
			} else {
				try {
					b= a.stmt.execute("update 图书 set 图书编号 =\'"+ t1.getText().trim() +"\',图书名称=\'"+ t2.getText().trim() +"\', 分类号=\'"
							+ t3.getText().trim() +"\', 作者 =\'"+ t4.getText().trim() +"\', 出版社= \'"+t5.getText().trim() +"\', 定价=\'"
							+ t6.getText().trim()+"\', 是否在馆=\'"+ t7.getText().trim()+"\', 入库时间=\'"+ t8.getText().trim()
							+"\' where 图书编号 =\'"+ t1.getText().trim() +"\'");
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
		new ManaBookUpdate();
	}
}
