package Haksa2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JOptionPane;

public class SearchActionListener2 implements ActionListener{
	void tableShow(ResultSet ws) {
		try {
			Haksa2.taList.setText("");
			Haksa2.taList.append("============================================\n");
			Haksa2.taList.append("  �й�	�̸�	�а�	�ּ�\n");
			Haksa2.taList.append("============================================\n");

			while(ws.next()) {
				Haksa2.taList.append(ws.getString("id")); 
				Haksa2.taList.append("	");
				Haksa2.taList.append(ws.getString("name"));
				Haksa2.taList.append("	");
				Haksa2.taList.append(ws.getString("dept"));
				Haksa2.taList.append("	");
				Haksa2.taList.append(ws.getString("address"));
				Haksa2.taList.append("\n");
			}
			ws.close();
		} catch (Exception listE) {
			listE.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent e) {
		String id = Haksa2.tf_num[0].getText();
		String name = Haksa2.tf_num[1].getText();
		String dept = Haksa2.tf_num[2].getText();
		String address = Haksa2.tf_num[3].getText();
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String uid = "ora_user";
		String pass = "hong";
		
		Connection conn=null;
		ResultSet rs = null;
		Statement stmt=null;
		
		boolean isNumber = false;
		if(id.length()!=0)
		{
			if (MyActionListener2.isStringInt(id))
				isNumber=true;
			else
				isNumber=false;
		}
		//�й����� �˻�
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,uid,pass);
			stmt=conn.createStatement();

			if(e.getSource() == Haksa2.btnSearch[0]){
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
					Haksa2.tf_num[0].setText("");
				}
			}
			//�̸����� �˻�
			if(e.getSource() == Haksa2.btnSearch[1]){
				rs = stmt.executeQuery("select * from student where name ='"+name+"'");
				tableShow(rs);
				Haksa2.tf_num[1].setText("");
			}
			//�а��� �˻�
			if(e.getSource() == Haksa2.btnSearch[2]){
				rs = stmt.executeQuery("select * from student where dept like '%"+dept+"%'");
				tableShow(rs);
				Haksa2.tf_num[2].setText("");
			}
			//�ּҷ� �˻�
			if(e.getSource() == Haksa2.btnSearch[3]){
				rs = stmt.executeQuery("select * from student where address like'%"+address+"%'");
				tableShow(rs);
				Haksa2.tf_num[1].setText("");
			}
			conn.close();
		}
		catch(Exception e1 ) {
			e1.printStackTrace();
		}
	}
}



