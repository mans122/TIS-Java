import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JOptionPane;

public class SearchActionListener implements ActionListener{
	void tableShow(ResultSet ws) {
		try {
			Haksa.taList.setText("");
			Haksa.taList.append("============================================\n");
			Haksa.taList.append("  �й�	�̸�	�а�	�ּ�\n");
			Haksa.taList.append("============================================\n");

			while(ws.next()) {
				Haksa.taList.append(ws.getString("id")); 
				Haksa.taList.append("	");
				Haksa.taList.append(ws.getString("name"));
				Haksa.taList.append("	");
				Haksa.taList.append(ws.getString("dept"));
				Haksa.taList.append("	");
				Haksa.taList.append(ws.getString("address"));
				Haksa.taList.append("\n");
			}
			ws.close();
		} catch (Exception listE) {
			listE.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent e) {
		String id = Haksa.tf_num[0].getText();
		String name = Haksa.tf_num[1].getText();
		String dept = Haksa.tf_num[2].getText();
		String address = Haksa.tf_num[3].getText();
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String uid = "ora_user";
		String pass = "hong";
		
		Connection conn=null;
		ResultSet rs = null;
		Statement stmt=null;
		
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
					Haksa.tf_num[0].setText("");
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



