import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
//�а��� �뿩��� �� �� �ֵ���

public class BookManager extends JPanel {
	public static JTextField[] tf_nrb = new JTextField[7];
	DefaultTableModel model = null;
	DefaultTableModel model2 = null;
	static JTable table=null;
	String rentNo = null;
	String lastRentNo = null;
	String today=null;
	static Integer rnCount = null;
	static String query = null;
	//üũ�ڽ��� �ʿ��� ����
	ArrayList<String> deptName = new ArrayList<>();
	int deptNum=0;
	//----------------------------------
	public BookManager() {
		query="select br.rentno rn,b.title title, b.no no, b.author, br.id id, br.rdate rdate, br.returndate redate" + 
				" from books b, (select * from bookRent2) br where b.no = br.bookno order by rentno";
		SimpleDateFormat format1 = new SimpleDateFormat ("YYYYMMdd");
		Date time = new Date();
		today = format1.format(time);
		setLayout(null);
		
		//���� ���̺� ������ ���̾ƿ� ����
		String colName[]={"�뿩��ȣ","������","������ȣ","���ǻ�","�뿩�� ȸ��","�뿩��¥","�ݳ���¥"}; // ǥ�� ����� Į����
		model=new DefaultTableModel(colName,0); // ǥ�� ������
		table = new JTable(model); // ���̺� ��(������) ���ε�
		JScrollPane jp = new JScrollPane(table);
		jp.setSize(new Dimension(575,230));
		jp.setLocation(10, 50);
		add(jp);
		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				table = (JTable)e.getComponent();
				model=(DefaultTableModel)table.getModel();
				//model.getValueAt�� ���� object�� String���� ��ȯ���ش�
				rentNo = (String)model.getValueAt(table.getSelectedRow(),0);
				String[] value=new String[7];
				for(int i=0;i<7;i++) {
					value[i] = (String)model.getValueAt(table.getSelectedRow(), i);
					tf_nrb[i].setText(value[i]);
				}
			}
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
		});

		JLabel rentInfo = new JLabel("�뿩����");
		JLabel bookInfo = new JLabel("��������");
		rentInfo.setFont(new Font("Gothic",Font.BOLD,25));
		rentInfo.setSize(100, 50);
		bookInfo.setFont(new Font("Gothic",Font.BOLD,25));
		bookInfo.setSize(100, 50);
		bookInfo.setLocation(10, 280);
		rentInfo.setLocation(10,0);
		JLabel[] a = new JLabel[7];
		a[0] = new JLabel("�뿩��ȣ");
		a[1] = new JLabel("�� �� ��");
		a[2] = new JLabel("������ȣ");
		a[3] = new JLabel("�� �� ��");
		a[4] = new JLabel("�뿩ȸ��");
		a[5] = new JLabel("�뿩��¥");
		a[6] = new JLabel("�ݳ���¥");
		int k=0;
		for(int i=0;i<7;i++) {
			if(i==3 || i==6)
				k++;
			
			a[i].setSize(new Dimension(60,30));
			a[i].setLocation(10+(k*190), 330+(i*33)-(k*99));
			add(a[i]);
			
			tf_nrb[i] = new JTextField();
			tf_nrb[i].setLocation(75+(k*190),334+(i*33)-(k*99));
			tf_nrb[i].setSize(100, 22);
			add(tf_nrb[i]);
		}
		tf_nrb[5].setText(today);
		tf_nrb[0].setEnabled(false);
		tf_nrb[5].setEnabled(false);
		tf_nrb[6].setEnabled(false);
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
		//==========================================================================
		//�а������� üũ�ڽ� ����� �κ�
		ResultSet rs = null;    // select�� ����� �����ϴ� ��ü
		try{
			rs = DBManager.stmt.executeQuery("select DISTINCT dept from student");
			int i=1;
			deptName.add(0,"��ü");
			while(rs.next()) {
				deptName.add(i,rs.getString("dept"));
				i++;
			}
			deptNum = deptName.size();
		}catch(Exception e){
			e.printStackTrace();
		}
		JLabel l_dept=new JLabel("�а�");
		l_dept.setBounds(150, 15, 30, 20);
		add(l_dept);
		
		String[] dept = new String[deptNum];
		dept[0]=deptName.get(0);
		for(int i=1;i<deptNum;i++) 
			{dept[i]=deptName.get(i);}
		
		JComboBox cb_dept=new JComboBox(dept);
		cb_dept.setBounds(185, 15, 100, 20);
		
		add(cb_dept);
		//�޺��ڽ��� �͸�Ŭ������ ������ ����ϰ� ���� �־��ִ� �ڵ�
		cb_dept.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JComboBox cb=(JComboBox)e.getSource();     
				int deptIndex=cb.getSelectedIndex();
				//���������� ����� ���� �⺻���� Ʋ
				if(deptIndex==0){ // ��ü
					query="select br.rentno rn,b.title title, b.no no, b.author, br.id id, br.rdate rdate, br.returndate redate" + 
							" from books b, (select * from bookRent2) br where b.no = br.bookno order by rn";
					show();
				}else { //Index �� �´� dept�̸����� ���͸�
					query = "select br.rentno rn,b.title title, br.bookno bn, s.id id, s.name name, s.dept dept, br.rdate rd" + 
					" from student s, books2 b, bookrent2 br where br.id = s.id and br.bookno = b.no" +
					" and dept='"+dept[deptIndex]+"' order by rn";
					showDept();
				}
			}});
		//===========================================================================
		//�ݳ����� ����� �������� ���͸��� �߰�
		JCheckBox ch = new JCheckBox("�ݳ��ѻ�� ����");
		ch.setBounds(460, 10, 150, 30);
		ch.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(ch.isSelected()) {
					query="select br.rentno rn,b.title title, b.no no, b.author, br.id id, br.rdate rdate, br.returndate redate" + 
							" from books b, (select * from bookRent2) br where b.no = br.bookno and br.returndate is null order by rn";
					show();
				}
				else {
					query="select br.rentno rn,b.title title, b.no no, b.author, br.id id, br.rdate rdate, br.returndate redate" + 
							" from books b, (select * from bookRent2) br where b.no = br.bookno order by rn";
					show();
				}
			}
		});
		add(ch);
		//===========================================================================
		show();
		add(bookInfo);
		add(rentInfo);
		setSize(700,500);
		setVisible(true);
	}
	
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ResultSet rs=null;
			String rentNo = tf_nrb[0].getText();
			String bookNo = tf_nrb[2].getText();
			String id = tf_nrb[4].getText();
			String rdate = tf_nrb[5].getText();
			String cmd = e.getActionCommand();
			switch(cmd) {
			case "��������":
				new BookAdd();
				break;
			case "�����뿩":
				try {
					rs = DBManager.stmt.executeQuery("select max(rentno) as bn from bookRent2");
					rs.next();
					lastRentNo = rs.getString("bn");
					String lastDay = lastRentNo.substring(0,8);
					if(lastDay.equals(today)) {
						String a = lastRentNo.substring(8,11);
						rnCount = Integer.parseInt(a);
						String c = String.format("%03d", rnCount+1);
						try {
							DBManager.stmt.executeUpdate("insert into bookrent2 values('"+(today+c)+"','"+bookNo+"','"+id+"','"+rdate+"',null)");
							JOptionPane.showMessageDialog(null,"�뿩�� �Ϸ����ϴ�..","�˸�",JOptionPane.INFORMATION_MESSAGE);
						}
						catch(Exception p) {
							p.printStackTrace();
							JOptionPane.showMessageDialog(null,"������ �߻�","�˸�",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					//������������ ���� ������ ���
					else {
						String d = String.format("%03d", 1);
						try {
							DBManager.stmt.executeUpdate("insert into bookrent2 values('"+(today+d)+"','"+bookNo+"','"+id+"','"+rdate+"',null)");
							JOptionPane.showMessageDialog(null,"�뿩�� �Ϸ����ϴ�..","�˸�",JOptionPane.INFORMATION_MESSAGE);
						}
						catch(Exception p) {
							p.printStackTrace();
							JOptionPane.showMessageDialog(null,"������ �߻�","�˸�",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					for(int i=0;i<7;i++) {
						tf_nrb[i].setText("");
					}
					rs.close();
					show();
				}
				catch(Exception e2) {
					e2.printStackTrace();
				}
				break;
			case "�����ݳ�":
				String redate = today;
				try {
					DBManager.stmt.executeUpdate("update bookrent2 set returndate='"+redate+"' where rentno='"+rentNo+"'");
					JOptionPane.showMessageDialog(null,"�ݳ��� �Ϸ����ϴ�..","�˸�",JOptionPane.INFORMATION_MESSAGE);
					show();
				}
				catch(Exception e2) {
					e2.printStackTrace();
				}
				break;
			}
		}
	}
	
	public void show(){
		try{
			ResultSet rs= DBManager.stmt.executeQuery(query);
			String colName[]={"�뿩��ȣ","������","������ȣ","���ǻ�","�뿩�� ȸ��","�뿩��¥","�ݳ���¥"}; // ǥ�� ����� Į����
			model=new DefaultTableModel(colName,0); // ǥ�� ������
			table.setModel(model);
			model.setNumRows(0);
			while(rs.next()){
				String[] row=new String[7];
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
	}
	
	public void showDept(){
		try{
			String colName[]={"�뿩��ȣ","������","������ȣ","�й�","�̸�","�а�","�뿩��¥"}; // ǥ�� ����� Į����
			model2=new DefaultTableModel(colName,0); // ǥ�� ������
			ResultSet rs= DBManager.stmt.executeQuery(query);
			table.setModel(model2);
			model2.setNumRows(0);
			while(rs.next()){
				String[] row=new String[7];
				row[0]=rs.getString("rn");
				row[1]=rs.getString("title");
				row[2]=rs.getString("bn"); 
				row[3]=rs.getString("id");
				row[4]=rs.getString("name");
				row[5]=rs.getString("dept");
				row[6]=rs.getString("rd");
				model2.addRow(row);
			}
			rs.close();
		}
		catch(Exception e1){
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
	}
}
