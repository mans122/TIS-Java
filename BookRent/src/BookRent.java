import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class BookRent extends JFrame{
	ArrayList<String> deptName = new ArrayList<>();
	Connection conn;//���ᰴü
	Statement stmt;
	DefaultTableModel model;
	JTable table;
	String query;
	int deptNum=0;
	public BookRent(){
		query="select s.id, s.name, b.title, br.rDate"
				+" from student s, books b, bookRent br"
				+" where br.id=s.id"
				+" and br.bookNo=b.no"; 
		// DB����
		ResultSet rs = null;    // select�� ����� �����ϴ� ��ü
		String url = null;      // ���� url
		String uid = "ora_user";       // ID
		String pw = "hong";     // PW         
		url ="jdbc:oracle:thin:@localhost:1521:myoracle";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
			conn = DriverManager.getConnection(url,uid,pw);// ����  
			stmt=conn.createStatement();
			
			rs = stmt.executeQuery("select DISTINCT dept from student");
			int i=0;
			while(rs.next()) {
				
				deptName.add(i,rs.getString("dept"));
				System.out.println(deptName.get(i));
				i++;
			}
			deptNum = deptName.size();
			System.out.println(deptNum);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		setTitle("�л�����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x��ư ������ ���α׷� ����

		setLayout(null);//���̾ƿ�����. ���̾ƿ� ��� ����.

		JLabel l_dept=new JLabel("�а�");
		l_dept.setBounds(10, 10, 30, 20);
		add(l_dept);
		
		//String[] dept = new String[deptNum];
		String[] dept={"��ü","��ǻ�ͽý���","��Ƽ�̵��","��ǻ�Ͱ���"};
		JComboBox cb_dept=new JComboBox(dept);
		cb_dept.setBounds(45, 10, 100, 20);
		add(cb_dept);
		cb_dept.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JComboBox cb=(JComboBox)e.getSource();     
				int deptIndex=cb.getSelectedIndex();
				
				query="select s.id, s.name, b.title, br.rDate"
						+" from student s, books b, bookRent br"
						+" where br.id=s.id"
						+" and br.bookNo=b.no";

				if(deptIndex==0){ // ��ü
					// Select�� ����
					query += " order by br.no";
					bookList();
				}else if(deptIndex==1){ // ��ǻ�ͽý���     
					query += " and s.dept='��ǻ�ͽý���' ";
					query += " order by br.no";
					bookList();
				}else if(deptIndex==2){ // ��Ƽ�̵��
					query += " and s.dept='��Ƽ�̵��' ";
					query += " order by br.no";
					bookList();
				}else if(deptIndex==3){ // ��ǻ�Ͱ���
					query += " and s.dept='��ǻ�Ͱ���' ";
					query += " order by br.no";
					bookList();
				}
			}});

		String colName[]={"�й�","�̸�","������","������"};
		model=new DefaultTableModel(colName,0);
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(470,200));
		add(table);
		JScrollPane sp=new JScrollPane(table);
		sp.setBounds(10, 40, 460, 250);
		setResizable(false);
		add(sp); 



		//�����̺�Ʈ ó��.�����찡 ����� �� DB������ close��.
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				try {
					if(conn!=null){
						conn.close(); // ��������.
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		//setResizable(false);//ȭ��ũ�����
		setSize(490, 400);//ȭ��ũ��
		setVisible(true);
		bookList();
	}

	public void bookList(){
		try{
			// Select�� ����     
			ResultSet rs=stmt.executeQuery(query);
			model.setNumRows(0);

			while(rs.next()){
				String[] row=new String[4];//�÷��� ������ 4
				row[0]=rs.getString("id");
				row[1]=rs.getString("name");
				row[2]=rs.getString("title");
				row[3]=rs.getString("rdate");
				model.addRow(row);
			}
			rs.close();

		}
		catch(Exception e1){
			//e.getStackTrace();
			System.out.println(e1.getMessage());
		}
	}


	public static void main(String[] args) {
		new BookRent();

	}
}