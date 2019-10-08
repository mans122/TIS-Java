import java.awt.Container;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.*;

public class Student extends JPanel {
	static DefaultTableModel model = null;
	static JTable table=null;
	
	//�й�~�ּҸ� �Է¹��� TextField 4�� ����
	public static JTextField[] tf_num = new JTextField[4];
	public static JButton[] btnSearch = new JButton[4];
	public Student() {
		setLayout(null);
		JLabel[] a = new JLabel[4];
		a[0] = new JLabel("�й�");
		a[1] = new JLabel("�̸�");
		a[2] = new JLabel("�а�");
		a[3] = new JLabel("�ּ�");
		//�ؽ�Ʈ�ʵ�,�˻���ư
		for(int i=0;i<4;i++) {
			a[i].setSize(new Dimension(30,30));
			a[i].setLocation(10, 10+(i*30));
			add(a[i]);
			
			tf_num[i] = new JTextField();
			tf_num[i].setLocation(45,12+(i*30));
			tf_num[i].setSize(200, 25);
			add(tf_num[i]);
			
			btnSearch[i] = new JButton("�˻�");
			btnSearch[i].setSize(60, 25);
			btnSearch[i].setLocation(250, 12+(i*30));
			add(btnSearch[i]);
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
		jp.setSize(new Dimension(460,250));
		jp.setLocation(10, 140);
		add(jp);
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
		//��ư����
		JButton btnInsert = new JButton("���");
		JButton btnList = new JButton("���");
		JButton btnUpdate = new JButton("����");
		JButton btnDelete = new JButton("����");
		JButton btnLogout = new JButton("�α׾ƿ�");
		//��ư�� ũ������
		
		btnInsert.setSize(80, 30);
		btnInsert.setLocation(10, 400);
		btnList.setSize(80, 30);
		btnList.setLocation(100, 400);
		btnUpdate.setSize(80, 30);
		btnUpdate.setLocation(190, 400);
		btnDelete.setSize(80, 30);
		btnDelete.setLocation(280, 400);
		btnLogout.setSize(100,30);
		btnLogout.setLocation(370, 400);
		
		StudentActionListener ma = new StudentActionListener();//������ ����
		StudentSearchActionListener sa = new StudentSearchActionListener();
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
		add(btnInsert);
		add(btnList);
		add(btnUpdate);
		add(btnDelete);
		add(btnLogout);
		
		setSize(535,600);
//		Dimension frameSize = this.getSize();
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		this.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
		setVisible(true);
	}
	
}
