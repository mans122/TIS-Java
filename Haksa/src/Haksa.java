import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class Haksa extends JFrame {
	//�й�~�ּҸ� �Է¹��� TextField 4�� ����
	public static JTextField[] tf_num = new JTextField[4];
	//����� ����� TextArea�� taList��� �̸����� ���� �� ũ�� ����,
	public static JTextArea taList=new JTextArea(16,29);
	public static JButton[] btnSearch = new JButton[4];
	public Haksa() {
		setTitle("�л����");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT,10,3));
		
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
		SearchActionListener sa = new SearchActionListener();
		//��ư�� �����ʿ� ���
		btnSearch[0].addActionListener(sa);
		btnSearch[1].addActionListener(sa);
		btnSearch[2].addActionListener(sa);
		btnSearch[3].addActionListener(sa);
		btnInsert.addActionListener(ma);
		btnList.addActionListener(ma);
		btnUpdate.addActionListener(ma);
		btnDelete.addActionListener(ma);
		
		
		//��ư�� ũ������
		btnInsert.setPreferredSize(new Dimension(73,30));
		btnList.setPreferredSize(new Dimension(73,30));
		btnUpdate.setPreferredSize(new Dimension(73,30));
		btnDelete.setPreferredSize(new Dimension(73,30));
		
		//��ư�� �гο� ���
		c.add(btnInsert);
		c.add(btnList);
		c.add(btnUpdate);
		c.add(btnDelete);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
				setVisible(false);
				dispose();
				Login.frame.setEnabled(true);
				Login.frame.setFocusable(true);}
				catch(Exception we) {}
			}
		});
		//�ؽ�Ʈ�ʵ� �������ϰ� ����
		taList.setEditable(false);
		setSize(350,480);
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
		setVisible(true);
		setResizable(false);
	}
	public static void main(String[] args) {
		new Haksa();
	}
}
