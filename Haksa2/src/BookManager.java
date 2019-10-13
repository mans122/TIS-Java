import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class BookManager extends JPanel {
	public static JTextField[] tf_nrb = new JTextField[6];
	DefaultTableModel model = null;
	JTable table=null;
	String today=null;
	public BookManager() {
		//SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format1 = new SimpleDateFormat ("YYYYMMdd");
		Date time = new Date();
		today = format1.format(time);
		setLayout(null);
		String colName[]={"�뿩��ȣ","������","������ȣ","���ǻ�","�뿩�� ȸ��","�뿩��¥","�ݳ���¥"}; // ǥ�� ����� Į����
		
		model=new DefaultTableModel(colName,0); // ǥ�� ������
		table = new JTable(model); // ���̺� ��(������) ���ε�
//		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		JScrollPane jp = new JScrollPane(table);
		jp.setSize(new Dimension(575,230));
		jp.setLocation(10, 50);
		add(jp);
		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				table = (JTable)e.getComponent();
				model=(DefaultTableModel)table.getModel();
				//model.getValueAt�� ���� object�� String���� ��ȯ���ش�
				String[] value=new String[6];
				for(int i=0;i<6;i++) {
					value[i] = (String)model.getValueAt(table.getSelectedRow(), i+1);
					tf_nrb[i].setText(value[i]);
				}
			}
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			
		});
		try{
			// Select�� ����
			ResultSet rs = DBManager.stmt.executeQuery("select br.rentno rn,b.title title, b.no no, b.author, br.id id, br.rdate rdate, br.returndate redate"
			+" from books b, (select * from bookRent2) br where b.no = br.bookno");
			//JTable �ʱ�ȭ
			model.setNumRows(0);
			while(rs.next()){
				String[] row=new String[7];
				//row[0]=rs.getString("year"+"��"+"month"+"��"+"day"+"��");
				row[0]=rs.getString("rn");
				row[1]=rs.getString("title");
				row[2]=rs.getString("no"); 
				row[3]=rs.getString("author");
				row[4]=rs.getString("id");
				row[5]=rs.getString("rdate");
				row[6]=rs.getString("redate");
				model.addRow(row);
			}
			rs.close();
		}
		catch(Exception e1){
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}
		
		JLabel bookInfo = new JLabel("��������");
		JLabel rentInfo = new JLabel("��������");
		bookInfo.setFont(new Font("Gothic",Font.BOLD,25));
		bookInfo.setSize(100, 50);
		rentInfo.setFont(new Font("Gothic",Font.BOLD,25));
		rentInfo.setSize(100, 50);
		
		bookInfo.setLocation(10, 280);
		rentInfo.setLocation(10,0);
		JLabel[] a = new JLabel[6];
		a[0] = new JLabel("������ȣ");
		a[1] = new JLabel("������");
		a[2] = new JLabel("���ǻ�");
		a[3] = new JLabel("�뿩ȸ��");
		a[4] = new JLabel("�뿩��¥");
		a[5] = new JLabel("�ݳ���¥");
		int k=0;
		for(int i=0;i<6;i++) {
			if(i==3)
				k=1;
			
			a[i].setSize(new Dimension(60,30));
			a[i].setLocation(10+(k*210), 330+(i*33)-(k*99));
			add(a[i]);
			
			tf_nrb[i] = new JTextField();
			tf_nrb[i].setLocation(75+(k*210),332+(i*33)-(k*99));
			tf_nrb[i].setSize(100, 22);
			add(tf_nrb[i]);
		}
		tf_nrb[4].setText(today);
		tf_nrb[4].setEnabled(false);
		String buttonName[] = {"�������","��������","��������","�����뿩","�����ݳ�"};
		JButton[] book = new JButton[5];
		MyActionListener ma = new MyActionListener();
		
		for(int i=0; i<5;i++)
		{	
			book[i] = new JButton(buttonName[i]);
			book[i].setSize(90,30);
			book[i].setLocation(55+(100*i),450);
			book[i].addActionListener(ma);
			add(book[i]);
		}
		add(bookInfo);
		add(rentInfo);
		
		setSize(700,500);
		setVisible(true);
	}
	
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String rn = tf_nrb[0].getText();
			String title = tf_nrb[1].getText();
			String no = tf_nrb[2].getText();
			String author = tf_nrb[3].getText();
			String id = tf_nrb[4].getText();
			String rdate = tf_nrb[5].getText();
			String redate = tf_nrb[6].getText();
			String cmd = e.getActionCommand();
			switch(cmd) {
			case "�����ݳ�":
				tf_nrb[5].setText(today);
				tf_nrb[5].setEnabled(false);
				try {
					ResultSet rs = DBManager.stmt.executeQuery("update bookrent2 set returndate='"+redate+"' wherer rentno='"+no+"'");
				}
				catch(Exception e2) {
					e2.printStackTrace();
				}
				break;
			}
		}
	}
	
	public static void main(String[] args) {

	}

}
