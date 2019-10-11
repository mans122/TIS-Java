import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
select substr(aa.no,1,4) year,substr(aa.no,5,2) month,substr(aa.no,7,2) day,aa.id id,aa.bookno bn,bs.title
from  books bs,   
(select DISTINCT b.no no,b.id id,b.bookno bookno,a.bn bn from bookRent b,
(select br.id id, br.bookno bn  from bookRent br,bookReturn rb where br.id=rb.id and br.bookno = rb.bookno) a
where b.id = a.id(+) and b.bookno = a.bn(+)) aa
where bn is null and bs.no = aa.bookno order by aa.no;

 */
public class NoReturnBook extends JPanel {
	public static JTextField[] tf_nrb = new JTextField[4];
	DefaultTableModel model = null;
	JTable table=null;
	public NoReturnBook() {
		setLayout(null);
		String colName[]={"�뿩��¥","�й�","å��ȣ","å�̸�"}; // ǥ�� ����� Į����
		model=new DefaultTableModel(colName,0); // ǥ�� ������
		table = new JTable(model); // ���̺� ��(������) ���ε�
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		JScrollPane jp = new JScrollPane(table);
		jp.setSize(new Dimension(475,230));
		jp.setLocation(10, 200);
		add(jp);
		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				table = (JTable)e.getComponent();
				model=(DefaultTableModel)table.getModel();
				//model.getValueAt�� ���� object�� String���� ��ȯ���ش�
//				String id = (String)model.getValueAt(table.getSelectedRow(), 0); //������ ���� id�� ���Ѱ�
//				String name = (String)model.getValueAt(table.getSelectedRow(), 1);
//				String dept = (String)model.getValueAt(table.getSelectedRow(), 2);
//				String address = (String)model.getValueAt(table.getSelectedRow(), 3);
//				
//				tf_num[0].setText(id);
//				tf_num[1].setText(name);
//				tf_num[2].setText(dept);
//				tf_num[3].setText(address);
			}
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			
		});
		try{
			// Select�� ����
			ResultSet rs=DBManager.stmt.executeQuery("select substr(aa.no,1,4) year,substr(aa.no,5,2) month,substr(aa.no,7,2) day,aa.id ,aa.bookno bkn,bs.title" + 
					" from  books bs, " + 
					" (select DISTINCT b.no no,b.id id, b.bookno bookno, a.bn bn from bookRent b," + 
					" (select br.id id, br.bookno bn  from bookRent br,bookReturn rb where br.id=rb.id and br.bookno = rb.bookno) a" + 
					" where b.id = a.id(+) and b.bookno = a.bn(+)) aa" + 
					" where bn is null and bs.no = aa.bookno order by aa.no");

			//JTable �ʱ�ȭ
			model.setNumRows(0);
			while(rs.next()){
				String[] row=new String[4];
				//row[0]=rs.getString("year"+"��"+"month"+"��"+"day"+"��");
				row[0]=rs.getString("year")+rs.getString("month")+rs.getString("day");
				row[1]=rs.getString("id");
				row[2]=rs.getString("bkn");
				row[3]=rs.getString("title");
				model.addRow(row);
			}
			rs.close();
		}
		catch(Exception e1){
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}
		JLabel inPut = new JLabel("����");
		JLabel outPut = new JLabel("�ݳ�");
		inPut.setSize(100, 50);
		outPut.setSize(100, 50);
		
		inPut.setLocation(110, 0);
		outPut.setLocation(360, 0);
		JLabel[] a = new JLabel[4];
		a[0] = new JLabel("�뿩��¥");
		a[1] = new JLabel("�й�");
		a[2] = new JLabel("å��ȣ");
		a[3] = new JLabel("sss");
		
		for(int i=0;i<4;i++) {
			a[i].setSize(new Dimension(60,30));
			a[i].setLocation(10, 50+(i*27));
			add(a[i]);
			
			tf_nrb[i] = new JTextField();
			tf_nrb[i].setLocation(75,52+(i*27));
			tf_nrb[i].setSize(150, 22);
			add(tf_nrb[i]);
		}
		add(inPut);
		add(outPut);
		
		
		setSize(500,500);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
