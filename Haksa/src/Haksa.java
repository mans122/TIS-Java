import javax.swing.*;
import java.awt.*;

public class Haksa extends JFrame {
	//�й�~�ּҸ� �Է¹��� TextField 4�� ����
	public static JTextField[] tf_num = new JTextField[4];
	//����� ����� TextArea ����
	public static JTextArea taList=new JTextArea(15,27);
	
	public Haksa() {
		setTitle("�л����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//24ũ�� �ؽ�Ʈ�ʵ� 4�� ����
		for(int i=0;i<4;i++) {tf_num[i] = new JTextField(24);}
		c.add(new JLabel("�й�"));
		c.add(tf_num[0]);
		c.add(new JLabel("�̸�"));
		c.add(tf_num[1]);
		c.add(new JLabel("�а�"));
		c.add(tf_num[2]);
		c.add(new JLabel("�ּ�"));
		c.add(tf_num[3]);
		//TextArea�� taList��� �̸����� ���� �� ũ�� ����,
		//�гο� ���
		JScrollPane sp = new JScrollPane(taList);
		c.add(sp);
		
		//��ư����
		JButton btnInsert = new JButton("���");
		JButton btnList = new JButton("���");
		JButton btnUpdate = new JButton("����");
		JButton btnDelete = new JButton("����");
		MyActionListener ma = new MyActionListener();//������ ����
		//��ư�� �����ʿ� ���
		btnInsert.addActionListener(ma);
		btnList.addActionListener(ma);
		btnUpdate.addActionListener(ma);
		btnDelete.addActionListener(ma);
		//��ư�� ũ������
		btnInsert.setPreferredSize(new Dimension(74,30));
		btnList.setPreferredSize(new Dimension(74,30));
		btnUpdate.setPreferredSize(new Dimension(74,30));
		btnDelete.setPreferredSize(new Dimension(74,30));
		//��ư�� �гο� ���
		c.add(btnInsert);
		c.add(btnList);
		c.add(btnUpdate);
		c.add(btnDelete);

		taList.setEditable(false);
		taList.append("==========================================\n");
		taList.append("�й�\n");
		taList.append("==========================================\n");

		setSize(335,480);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Haksa();
	}
}
