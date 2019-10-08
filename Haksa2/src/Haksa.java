import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Haksa extends JFrame{
	public static MainProcess main;
	public static Connection conn=null;
	public static Statement stmt=null;
	public static ResultSet rs = null;
	//Oracle 
//	String url = "jdbc:oracle:thin:@localhost:1521:myoracle";
//	String uid = "ora_user";
//	String pass = "hong";
	
	//MySql
	public static String url = "jdbc:mysql://localhost:3306/sampledb?useSSL=false";
	public static String uid = "hkd";
	public static String pass = "1234";
	static JPanel panel = null;
	public Haksa() {
		try {
			//Oracle
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//MySql
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url,uid,pass);
			stmt=conn.createStatement();
		}catch(Exception aa){
			aa.printStackTrace();
		}
		
		Container c = getContentPane();
		this.setTitle("�л����");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
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
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					conn.close();
					stmt.close();
				}
				catch(Exception we) {
					we.printStackTrace();
				}
			}
		});
		c.add(panel);
		setSize(500,500);
		
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
		System.out.println(screenSize);
		setVisible(true);
		
	}
	public void setMain(MainProcess main) {
		this.main = main;
	}
	public static void main(String[] args) {
		new Haksa();
	}
}

// �׷캰  ������Ȳ�� groupby�� ���� ��Ʈ�� ǥ��
