import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Haksa extends JFrame {
	public static Connection conn=null;
	public static Statement stmt=null;
	public static ResultSet rs = null;
	String url = "jdbc:oracle:thin:@localhost:1521:myoracle";
	String uid = "ora_user";
	String pass = "hong";
	
	static DefaultTableModel model = null;
	static JTable table=null;
	static MainProcess main;
	
	//�й�~�ּҸ� �Է¹��� TextField 4�� ����
	public static JTextField[] tf_num = new JTextField[4];
	public static JButton[] btnSearch = new JButton[4];
	public Haksa() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,uid,pass);
			stmt=conn.createStatement();
		}catch(Exception aa){
			aa.printStackTrace();
		}
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
		
		//text area��� table
		String colName[]={"�й�","�̸�","�а�","�ּ�"}; // ǥ�� ����� Į����
		model=new DefaultTableModel(colName,0); // ǥ�� ������
		table = new JTable(model); // ���̺� ��(������) ���ε�
		table.setPreferredScrollableViewportSize(new Dimension(320,280));//���̺� ������
		c.add(new JScrollPane(table));

		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				table = (JTable)e.getComponent();
				model=(DefaultTableModel)table.getModel();
				//model.getValueAt�� ���� object�� String���� ��ȯ���ش�
				String id = (String)model.getValueAt(table.getSelectedRow(), 0); //������ ���� id�� ���Ѱ�
				String name = (String)model.getValueAt(table.getSelectedRow(), 1);
				String dept = (String)model.getValueAt(table.getSelectedRow(), 2);
				String address = (String)model.getValueAt(table.getSelectedRow(), 3);
				
				tf_num[0].setText(id);
				tf_num[1].setText(name);
				tf_num[2].setText(dept);
				tf_num[3].setText(address);
			}
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
		});
		//�ý�Ʈ�ʵ带 ��ũ���гο� �־� sp�� ���� �� �����̳ʿ� ���
		//JScrollPane sp = new JScrollPane(taList);
		//c.add(sp);
		
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
					conn.close();
					JOptionPane.showMessageDialog(null,"�α��� ȭ������ ���ư��ϴ�.","�˸�",JOptionPane.INFORMATION_MESSAGE);
					main.showFrameLogin(); // ����â �޼ҵ带 �̿��� ����
				}
				catch(Exception we) {
					we.printStackTrace();
				}
			}
		});
		setSize(350,480);
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
		setVisible(true);
		setResizable(false);
	}
	public void setMain(MainProcess main) {
		this.main = main;
	}
	public static void main(String[] args) {
	}
}
