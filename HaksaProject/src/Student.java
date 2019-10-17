import java.awt.Container;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.*;

public class Student extends JPanel {
	static JComboBox comboboxSearch = null;
	static DefaultTableModel model = null;
	static JTable table=null;
	static JLabel id = new JLabel();
	//�й�~�ּҸ� �Է¹��� TextField 4�� ����
	public static JTextField[] tf_num = new JTextField[5];
	public static JButton btnSearch = new JButton();
	public Student() {
		setLayout(null);
		JLabel[] a = new JLabel[5];
		a[0] = new JLabel("�й�");
		a[1] = new JLabel("�̸�");
		a[2] = new JLabel("�а�");
		a[3] = new JLabel("�ּ�");
		a[4] = new JLabel("����");
		//�ؽ�Ʈ�ʵ�,�˻���ư
		int kk=0;
		for(int i=0;i<5;i++) {
			if(i==1)
				kk=1;
			
			a[i].setSize(new Dimension(30,30));
			a[i].setLocation(10, 8+(i*30));
			add(a[i]);
			
			tf_num[i] = new JTextField();
			tf_num[i].setLocation(45,12+(i*30));
			tf_num[i].setSize(200+(kk*70), 25);
			add(tf_num[i]);
		}
		
		//�˻��ϴ°� �����
		String[] searchList = new String[4];
		for(int i=0;i<4;i++) {
			searchList[i] = a[i].getText(); 
		}
		comboboxSearch=new JComboBox(searchList);
		comboboxSearch.setBounds(250, 12, 65, 25);
		add(comboboxSearch);
		
		JLabel b = new JLabel("(��)��");
		b.setBounds(320, 12, 50, 25);
		add(b);
		
		btnSearch = new JButton("�˻�");
		btnSearch.setBounds(360, 9, 60, 30);
		add(btnSearch);
		//--------------------------------------------------------------------------
		
		//JTable �� ���� ����
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		//text area��� table
		String colName[]={"�й�","�̸�","�а�","�ּ�","����"}; // ǥ�� ����� Į����
		model=new DefaultTableModel(colName,0); // ǥ�� ������
		table = new JTable(model); // ���̺� ��(������) ���ε�
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(230);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
//		table.setPreferredScrollableViewportSize(new Dimension(600,280));//���̺� ������
		JScrollPane jp = new JScrollPane(table);
		jp.setSize(new Dimension(480,220));
		jp.setLocation(10, 170);
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
				String birth = (String)model.getValueAt(table.getSelectedRow(), 4);
				
				tf_num[0].setText(id);
				tf_num[1].setText(name);
				tf_num[2].setText(dept);
				tf_num[3].setText(address);
				tf_num[4].setText(birth);
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
		btnSearch.addActionListener(sa);
		btnInsert.addActionListener(ma);
		btnList.addActionListener(ma);
		btnUpdate.addActionListener(ma);
		btnDelete.addActionListener(ma);
		btnLogout.addActionListener(ma);
		Font font= new Font("Courier",Font.BOLD,20);
		id.setFont(font);
		id.setBounds(350, 50, 150, 40);
		JLabel id2 = new JLabel("ȯ���մϴ�.");
		id2.setFont(font);
		id2.setBounds(350, 90, 150, 40);
		
		//��ư�� �гο� ���
		add(id);
		add(id2);
		add(btnInsert);
		add(btnList);
		add(btnUpdate);
		add(btnDelete);
		add(btnLogout);
		
		setOpaque(false);
		setSize(500,500);
		setVisible(true);
	}
	public static void main(String[] args) {
	}
}
