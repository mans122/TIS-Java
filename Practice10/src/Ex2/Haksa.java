package Ex2;
import javax.swing.*;
import java.awt.*;

public class Haksa extends JFrame {
	public static JTextField[] tf_num = new JTextField[4];
	public Haksa() {
		setTitle("�л����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//20ũ�� �ؽ�Ʈ�ʵ� 4�� ����
		for(int i=0;i<4;i++) {tf_num[i] = new JTextField(20);}
		c.add(new JLabel("�й�"));
		c.add(tf_num[0]);
		c.add(new JLabel("�̸�"));
		c.add(tf_num[1]);
		c.add(new JLabel("�й�"));
		c.add(tf_num[2]);
		c.add(new JLabel("�ּ�"));
		c.add(tf_num[3]);
		
		JTextArea taList=new JTextArea(15,25);
		JScrollPane sp = new JScrollPane(taList);
		c.add(sp);
		
		//��ư���� �� �����ʵ�� ,�г� ���
		JButton btnInsert = new JButton("���");
		JButton btnList = new JButton("���");
		JButton btnUpdate = new JButton("����");
		JButton btnDelete = new JButton("����");
		MyActionListener ma = new MyActionListener();//������ ����
		btnInsert.addActionListener(ma);
		btnList.addActionListener(ma);
		btnUpdate.addActionListener(ma);
		btnDelete.addActionListener(ma);
		c.add(btnInsert);
		c.add(btnList);
		c.add(btnUpdate);
		c.add(btnDelete);
		
		taList.append("===========================================\n");
		taList.append("�й�	�̸�	�а�	�ּ�\n");
		taList.append("===========================================\n");

		setSize(300,500);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Haksa();
	}
}
