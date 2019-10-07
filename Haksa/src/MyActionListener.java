import java.awt.event.*;
import java.sql.*;
import javax.swing.JOptionPane;

public class MyActionListener implements ActionListener {
	 public static boolean isStringInt(String s) {
		    try {
		        Integer.parseInt(s);
		        return true;
		    } catch (NumberFormatException e) {
		        return false;
		    }
		  }

	public void actionPerformed(ActionEvent e) {
		//�Է¹޴� �׼��� �̸��� String������ ���� cmd�� ����
		String cmd = e.getActionCommand();
		//JDBC ������ ���� ���� ����
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs = null;
		//������ SID orcl
		String url = "jdbc:oracle:thin:@localhost:1521:myoracle";
		String uid = "ora_user";
		String pass = "hong";
		
		//��������� Ȯ���ϱ����� value�� ���� ����
		int value = 0;
		
		//���� �޾ƿ������� �������� �� �� �Է�
		String id = Haksa.tf_num[0].getText();
		String name = Haksa.tf_num[1].getText();
		String dept = Haksa.tf_num[2].getText();
		String address = Haksa.tf_num[3].getText();
		boolean isNumber = false;
		if(id.length()!=0)
		{
			if (isStringInt(id))
				isNumber=true;
			else
				isNumber=false;
		}

		switch(cmd) {
		case "���":
			for(int i=0;i<3;i++) {//tf_num�� ����ִ°� �ִ��� Ȯ��
				if(Haksa.tf_num[i].getText().length()==0 || Haksa.tf_num[i].getText().equals("")) {
					value = 1;//������� value���� 1�� ����
				}
			}

			if (value != 1){ // id,name,dept ���� ������� ���� ���
				if(id.length()==7 && isNumber == true ) { // id�� ���� ���̰� 7�̰�,Int���� ���
					try {
						//JDBC����
						Class.forName("oracle.jdbc.driver.OracleDriver");
						conn=DriverManager.getConnection(url,uid,pass);
						stmt=conn.createStatement();
						//�Է¹��� �� �����ϴ� �ڵ� sql������ ���� ������ ������ �����ش�.
						stmt.executeQuery("insert into student values('"+id+"','"+name+"','"+dept+"','"+address+"')");
						JOptionPane.showMessageDialog(null,"��ϵǾ����ϴ�.","�˸�",JOptionPane.INFORMATION_MESSAGE);
						rs = stmt.executeQuery("select * from student order by id");
						SearchActionListener.tableShow(rs);
						//����� �Ϸ�� �ؽ�Ʈ�ʵ带 ����ֱ����� �ڵ�
						for(int i=0;i<4;i++) {
							Haksa.tf_num[i].setText("");
						}
						rs.close();
						conn.close();
					}
					//SQL���� ������ �߻��ϸ� �˸�â ����
					catch(SQLException sqlE) {
						JOptionPane.showMessageDialog(null,"�̹� �����ϴ� �й��Դϴ�.","���",JOptionPane.WARNING_MESSAGE);
					}
					catch(Exception inputE) {
						inputE.printStackTrace();
					}
					//����Ǵ� �ڵ�
					finally {
							try{
								if(stmt!=null) {stmt.close();}
								if(rs!=null) {	rs.close();}
								if(conn!=null) {conn.close();}
							}catch(Exception ee) {
								ee.printStackTrace();
							}
					}
				}
				//id�� ���̰� 7��,�������� �ƴҶ� �������� �ƴϸ� ���
				else if(isNumber == false) {
					JOptionPane.showMessageDialog(null,"ID�� ���ڸ� �Է����ּ���","���",JOptionPane.WARNING_MESSAGE);
				}
				//id�� ������������, 7�ڸ��� �ƴҰ��
				else {
					JOptionPane.showMessageDialog(null,"ID�� 7�ڸ��� �Է����ּ���","���",JOptionPane.WARNING_MESSAGE);
				}
			}
			else {//������� �޽��� ���
				JOptionPane.showMessageDialog(null,"���� �Էµ��� �ʾҽ��ϴ�.","���",JOptionPane.WARNING_MESSAGE);
			}
			break;
//--------------------------------------------------------------			
		case "���":
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn=DriverManager.getConnection(url,uid,pass);
				stmt=conn.createStatement();
				rs = stmt.executeQuery("select * from student order by id");
				
				//����� ���������� ���� �Է��ϱ����� setText�� �ƹ����� �Է������ʰ� �ʱ�ȭ��
				Haksa.taList.setText("");
				Haksa.taList.append("============================================\n");
				Haksa.taList.append("     �й�	  �̸�	    �а�	  �ּ�\n");
				Haksa.taList.append("============================================\n");
				
				while(rs.next()) {
					//DB���� ���� �޾ƿͼ� taList�� �߰�����, ���������� ���鵵 ���� �Է�����
					Haksa.taList.append(rs.getString("id")); //Į�� �̸���� Į�� �ε����� ��� ����
					Haksa.taList.append("	");
					Haksa.taList.append(rs.getString("name"));
					Haksa.taList.append("	");
					Haksa.taList.append(rs.getString("dept"));
					Haksa.taList.append("	");
					Haksa.taList.append(rs.getString("address"));
					Haksa.taList.append("\n");
				}
				rs.close();
				conn.close();
			} catch (Exception listE) {
				listE.printStackTrace();
			}
			break;
//--------------------------------------------------------------
		case "����":
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn=DriverManager.getConnection(url,uid,pass);
				stmt=conn.createStatement();
				//�й��� ���̰� 0�� �ƴҰ��
				if(id.length()!=0) {
					//�ش��й����� ��ȸ���ؼ� rowcount�� ��ȸ�� �� ���� ��ȸ
					rs = stmt.executeQuery("select * from student where id = '"+id+"'");
					rs.next();
					//int rowcount = rs.getRow();
					
					//�ش��й��� �����ϸ� �˻������ 1�� ��µǰ� getRow()�� ���� rowcount�� 1 �̵�
					if(rs.getRow() == 1) {
						//�̸�,�а�,�ּҸ� �������� ����
						if(name.length()!=0) {
							stmt.executeQuery("update student set name='"+name+"' where id = '"+id+"'");	}
						if(dept.length()!=0) {
							stmt.executeQuery("update student set dept='"+dept+"' where id = '"+id+"'");	}
						if(address.length()!=0) {
							stmt.executeQuery("update student set address='"+address+"' where id = '"+id+"'");	}
						JOptionPane.showMessageDialog(null,"������ �Ϸ�Ǿ����ϴ�.","�˸�",JOptionPane.INFORMATION_MESSAGE);
						for(int i=0;i<4;i++) {
							Haksa.tf_num[i].setText("");
						}
						rs = stmt.executeQuery("select * from student where id ='"+id+"'");
						SearchActionListener.tableShow(rs);
					}
					//rowcount �� 0�̸� ��ȸ�Ǵ� �й��� ���ٴ¸���
					else {
						JOptionPane.showMessageDialog(null,"�ش� �й��� �������� �ʽ��ϴ�.","�˸�",JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				//������ư�� �������� �й��� �ƹ��͵� �Ƚ������� ������
				}else {
					JOptionPane.showMessageDialog(null,"������ �й��� �Է����ּ���","���",JOptionPane.WARNING_MESSAGE);
					break;
				}
				rs.close();
				conn.close();
				break;
			}
			catch(Exception inputE) {
				inputE.printStackTrace();
			}
			for(int i=0;i<4;i++) {
				Haksa.tf_num[i].setText("");
			}
			break;
//--------------------------------------------------------------			
		case "����":
			
			if(JOptionPane.showConfirmDialog(null, "���������Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				if(id.length()!=0) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						conn=DriverManager.getConnection(url,uid,pass);
						stmt=conn.createStatement();
						stmt.executeQuery("delete from student where id = '"+id+"'");
						rs = stmt.executeQuery("select * from student order by id");
						SearchActionListener.tableShow(rs);
						for(int i=0;i<4;i++) {
							Haksa.tf_num[i].setText("");
						}
						rs.close();
						conn.close();
					}
					catch(Exception deleteE) {
						deleteE.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"������ �й��� �Է����ּ���","���",JOptionPane.WARNING_MESSAGE);
				}
//				try {
//				rs = stmt.executeQuery("select * from student where id ='"+id+"'");
//				SearchActionListener.tableShow(rs);
//				}
//				catch(Exception aa) {
//					aa.printStackTrace();
//				}
			}
			
			break;
		}
	}
}


