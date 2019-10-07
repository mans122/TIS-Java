import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JOptionPane;

public class SearchActionListener implements ActionListener{
	static Connection conn=null;
	static Statement stmt=null;
	static String url = "jdbc:oracle:thin:@localhost:1521:myoracle";
	static String uid = "ora_user";
	static String pass = "hong";
	
	//�˻��� ����� rs�� �־��� ��, �� ������� ws�� �޾Ƽ� ������� talist�� ������ִ� �޼���
	static void tableShow(ResultSet ws) {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,uid,pass);
			stmt=conn.createStatement();

			//JTable �ʱ�ȭ
			Haksa.model.setNumRows(0);
			while(ws.next()){
				String[] row=new String[3];//�÷��� ������ 3
				row[0]=ws.getString("id");
				row[1]=ws.getString("name");
				row[2]=ws.getString("dept");
				Haksa.model.addRow(row);
			}
			ws.close();
		}
		catch(Exception e1){
			System.out.println(e1.getMessage());
		}
	}
	public void actionPerformed(ActionEvent e) {
		String id = Haksa.tf_num[0].getText();
		String name = Haksa.tf_num[1].getText();
		String dept = Haksa.tf_num[2].getText();
		String address = Haksa.tf_num[3].getText();
		ResultSet rs = null;
		boolean isNumber = false;
		if(id.length()!=0)
		{
			if (MyActionListener.isStringInt(id))
				isNumber=true;
			else
				isNumber=false;
		}
		//�й����� �˻�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,uid,pass);
			stmt=conn.createStatement();

			if(e.getSource() == Haksa.btnSearch[0]){
				if(id.length()==0) {
					JOptionPane.showMessageDialog(null,"���� �Էµ��� �ʾҽ��ϴ�.","���",JOptionPane.WARNING_MESSAGE);
				}
				else if(isNumber == false) {
					JOptionPane.showMessageDialog(null,"ID�� ���ڸ� �Է����ּ���","���",JOptionPane.WARNING_MESSAGE);
				}
				else if(id.length()!=7) {
					JOptionPane.showMessageDialog(null,"ID�� 7�ڸ��� �Է����ּ���","���",JOptionPane.WARNING_MESSAGE);
				}
				else {
					rs = stmt.executeQuery("select * from student where id ='"+id+"'");
					tableShow(rs);
					//textarea�� �����ִ��� �ؽ�Ʈ�ʵ� ä��������
					rs = stmt.executeQuery("select * from student where id ='"+id+"'");
					while(rs.next()) {
						Haksa.tf_num[0].setText(rs.getString("id")); 
						Haksa.tf_num[1].setText(rs.getString("name"));
						Haksa.tf_num[2].setText(rs.getString("dept"));
						Haksa.tf_num[3].setText(rs.getString("address"));
					}
				}
			}
			//�̸����� �˻�
			if(e.getSource() == Haksa.btnSearch[1]){
				rs = stmt.executeQuery("select * from student where name ='"+name+"'");
				tableShow(rs);
				Haksa.tf_num[1].setText("");
			}
			//�а��� �˻�
			if(e.getSource() == Haksa.btnSearch[2]){
				rs = stmt.executeQuery("select * from student where dept like '%"+dept+"%'");
				tableShow(rs);
				Haksa.tf_num[2].setText("");
			}
			//�ּҷ� �˻�
			if(e.getSource() == Haksa.btnSearch[3]){
				rs = stmt.executeQuery("select * from student where address like'%"+address+"%'");
				tableShow(rs);
				Haksa.tf_num[1].setText("");
			}
			conn.close();
		}
		catch(Exception e1 ) {
			e1.printStackTrace();
		}
	}
}



