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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		JLabel[] a = new JLabel[4];
		a[0] = new JLabel("�й�");
		a[1] = new JLabel("�̸�");
		a[2] = new JLabel("�а�");
		a[3] = new JLabel("�ּ�");
		//�ؽ�Ʈ�ʵ�,�˻���ư
		for(int i=0;i<4;i++) {
			a[i].setSize(new Dimension(30,30));
			a[i].setLocation(10, 10+(i*35));
			c.add(a[i]);
			
			tf_num[i] = new JTextField();
			tf_num[i].setLocation(45,12+(i*35));
			tf_num[i].setSize(200, 25);
			c.add(tf_num[i]);
			
			btnSearch[i] = new JButton("�˻�");
			btnSearch[i].setSize(60, 25);
			btnSearch[i].setLocation(250, 12+(i*35));
			c.add(btnSearch[i]);
			}
		//JTable �� ���� ����
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		//text area��� table
		String colName[]={"�й�","�̸�","�а�","�ּ�"}; // ǥ�� ����� Į����
		model=new DefaultTableModel(colName,0); // ǥ�� ������
		table = new JTable(model); // ���̺� ��(������) ���ε�
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		//table.setPreferredScrollableViewportSize(new Dimension(320,280));//���̺� ������
		JScrollPane jp = new JScrollPane(table);
		jp.setSize(new Dimension(510,330));
		jp.setLocation(10, 160);
		c.add(jp);
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
		JButton btnLogout = new JButton("�α׾ƿ�");
		//��ư�� ũ������
		
		btnInsert.setSize(80, 30);
		btnInsert.setLocation(10, 500);
		btnList.setSize(80, 30);
		btnList.setLocation(110, 500);
		btnUpdate.setSize(80, 30);
		btnUpdate.setLocation(210, 500);
		btnDelete.setSize(80, 30);
		btnDelete.setLocation(310, 500);
		btnLogout.setSize(110,30);
		btnLogout.setLocation(410, 500);
		
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
		btnLogout.addActionListener(ma);
		
		//��ư�� �гο� ���
		c.add(btnInsert);
		c.add(btnList);
		c.add(btnUpdate);
		c.add(btnDelete);
		c.add(btnLogout);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					conn.close();
					stmt.close();
					//JOptionPane.showMessageDialog(null,"�α��� ȭ������ ���ư��ϴ�.","�˸�",JOptionPane.INFORMATION_MESSAGE);
					//main.showFrameLogin(); // ����â �޼ҵ带 �̿��� ����
				}
				catch(Exception we) {
					we.printStackTrace();
				}
			}
		});
		createMenu();
		setSize(535,600);
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
		setVisible(true);
		setResizable(false);
	}
	void createMenu() {
		JMenuBar mb = new JMenuBar();
		
		JMenu Menu1 = new JMenu("�л�����");
		JMenu Menu2 = new JMenu("��������");
		JMenuItem itemLoad = new JMenuItem("�л�����");
		JMenuItem itemExit = new JMenuItem("Exit");
		JMenuItem item2 = new JMenuItem("������");
		Menu1.add(itemLoad);
		Menu1.addSeparator();
		Menu1.add(itemExit);
		Menu2.add(item2);
		mb.add(Menu1);
		mb.add(Menu2);
		MenuActionListener listener = new MenuActionListener();
		
		itemLoad.addActionListener(listener);
		itemExit.addActionListener(listener);
		item2.addActionListener(listener);
		
		setJMenuBar(mb);
	}
	public void setMain(MainProcess main) {
		this.main = main;
	}
	public static void main(String[] args) {
		new Haksa();
	}
}
