import javax.swing.*;
import java.awt.*;

public class Haksa extends JFrame {
	public Haksa() {
		setTitle("�л����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));

		//tf_id = �й�
		c.add(new JLabel("�й�"));
		JTextField tf_id=new JTextField(20);
		c.add(tf_id);

		//tf_name = �̸�
		c.add(new JLabel("�̸�"));
		JTextField tf_name=new JTextField(20);
		c.add(tf_name);

		//tf_dep = �й�
		c.add(new JLabel("�й�"));
		JTextField tf_dep=new JTextField(20);
		c.add(tf_dep);

		//tf_address = �й�
		c.add(new JLabel("�ּ�"));
		JTextField tf_address=new JTextField(20);
		c.add(tf_address);
		
		JTextArea taList=new JTextArea(15,25);
		JScrollPane sp = new JScrollPane(taList);
		c.add(sp);
		
		JButton btnInsert = new JButton("���");
		JButton btnList = new JButton("���");
		JButton btnUpdate = new JButton("����");
		JButton btnDelete = new JButton("����");
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
