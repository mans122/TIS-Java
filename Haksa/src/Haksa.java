import javax.swing.*;
import java.awt.*;

public class Haksa extends JFrame {
	//�й�~�ּҸ� �Է¹��� TextField 4�� ����
	public static JTextField[] tf_num = new JTextField[4];
	//����� ����� TextArea�� taList��� �̸����� ���� �� ũ�� ����,
	public static JTextArea taList=new JTextArea(16,28);
	public static JButton[] btnSearch = new JButton[4];
	public Haksa() {
		setTitle("�л����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		btnSearch[0] = new JButton("�˻�");
		btnSearch[1] = new JButton("�˻�");
		btnSearch[2] = new JButton("�˻�");
		btnSearch[3] = new JButton("�˻�");
		
		btnSearch[0].setPreferredSize(new Dimension(60,22));
		btnSearch[1].setPreferredSize(new Dimension(60,22));
		btnSearch[2].setPreferredSize(new Dimension(60,22));
		btnSearch[3].setPreferredSize(new Dimension(60,22));
		//�ؽ�Ʈ�ʵ� ���� �� ��,�ؽ�Ʈ�ʵ� �гο� ���
		for(int i=0;i<4;i++) {
			tf_num[i] = new JTextField(19);
			}
		c.add(new JLabel("�й�"));
		c.add(tf_num[0]);
		c.add(btnSearch[0]);
		c.add(new JLabel("�̸�"));
		c.add(tf_num[1]);
		c.add(btnSearch[1]);
		c.add(new JLabel("�а�"));
		c.add(tf_num[2]);
		c.add(btnSearch[2]);
		c.add(new JLabel("�ּ�"));
		c.add(tf_num[3]);
		c.add(btnSearch[3]);
		
		
		//�ý�Ʈ�ʵ带 ��ũ���гο� �־� sp�� ���� �� �����̳ʿ� ���
		JScrollPane sp = new JScrollPane(taList);
		c.add(sp);
		
		//��ư����
		JButton btnInsert = new JButton("���");
		JButton btnList = new JButton("���");
		JButton btnUpdate = new JButton("����");
		JButton btnDelete = new JButton("����");
		MyActionListener ma = new MyActionListener();//������ ����
		
		//��ư�� �����ʿ� ���
		btnSearch[0].addActionListener(ma);
		btnSearch[1].addActionListener(ma);
		btnSearch[2].addActionListener(ma);
		btnSearch[3].addActionListener(ma);
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

		//�ؽ�Ʈ�ʵ� �������ϰ� ����
		taList.setEditable(false);

		setSize(335,480);
		setVisible(true);
		setResizable(false);
	}
	public static void main(String[] args) {
		new Haksa();
	}
}
