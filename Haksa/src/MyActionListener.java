import java.awt.event.*;
import java.sql.*;
import javax.swing.JOptionPane;

public class MyActionListener implements ActionListener {
	//�Է¹޴� �׼��� �̸��� String������ ���� cmd�� ����
	//JDBC ������ ���� ���� ����
	static Connection conn=null;
	static Statement stmt=null;
	static ResultSet rs = null;
	//������ SID orcl
	static String url = "jdbc:oracle:thin:@localhost:1521:myoracle";
	static String uid = "ora_user";
	static String pass = "hong";
	
	public static void list(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,uid,pass);
			stmt=conn.createStatement();
			System.out.println("����Ǿ����ϴ�.....");
			
			// Select�� ����
			ResultSet rs=stmt.executeQuery("select * from student");

			//JTable �ʱ�ȭ
			Haksa.model.setNumRows(0);
			while(rs.next()){
				String[] row=new String[3];//�÷��� ������ 3
				row[0]=rs.getString("id");
				row[1]=rs.getString("name");
				row[2]=rs.getString("dept");
				Haksa.model.addRow(row);
			}
			rs.close();
		}
		catch(Exception e1){
			System.out.println(e1.getMessage());
		}
	}
	public static boolean isStringInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
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
						ResultSet ws=stmt.executeQuery("select * from student");
						list();
						//����� �Ϸ�� �ؽ�Ʈ�ʵ带 ����ֱ����� �ڵ�
						for(int i=0;i<4;i++) {
							Haksa.tf_num[i].setText("");
						}
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
				list();
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
					ResultSet ws = stmt.executeQuery("select * from student where id = '"+id+"'");
					ws.next();
					//int rowcount = rs.getRow();

					//�ش��й��� �����ϸ� �˻������ 1�� ��µǰ� getRow()�� ���� rowcount�� 1 �̵�
					if(ws.getRow() == 1) {
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
						list();
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
						list();
						for(int i=0;i<4;i++) {
							Haksa.tf_num[i].setText("");
						}
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


