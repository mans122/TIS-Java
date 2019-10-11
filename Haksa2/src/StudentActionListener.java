import java.awt.event.*;
import java.sql.*;
import javax.swing.JOptionPane;

public class StudentActionListener implements ActionListener {
	DBManager hdb = new DBManager();
	ResultSet rs = null;
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		//��������� Ȯ���ϱ����� value�� ���� ����
		int value = 0;

		//���� �޾ƿ������� �������� �� �� �Է�
		String id = Student.tf_num[0].getText();
		String name = Student.tf_num[1].getText();
		String dept = Student.tf_num[2].getText();
		String address = Student.tf_num[3].getText();
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
				if(Student.tf_num[i].getText().length()==0 || Student.tf_num[i].getText().equals("")) {
					value = 1;//������� value���� 1�� ����
				}
			}

			if (value != 1){ // id,name,dept ���� ������� ���� ���
				if(id.length()==7 && isNumber == true ) { // id�� ���� ���̰� 7�̰�,Int���� ���
					try {
						hdb.stmt.executeUpdate("insert into student values('"+id+"','"+name+"','"+dept+"','"+address+"')");
						JOptionPane.showMessageDialog(null,"��ϵǾ����ϴ�.","�˸�",JOptionPane.INFORMATION_MESSAGE);
						list();
						//����� �Ϸ�� �ؽ�Ʈ�ʵ带 ����ֱ����� �ڵ�
						for(int i=0;i<4;i++) {
							Student.tf_num[i].setText("");
						}
					}
					//SQL���� ������ �߻��ϸ� �˸�â ����
					catch(SQLException sqlE) {
						JOptionPane.showMessageDialog(null,"�̹� �����ϴ� �й��Դϴ�.","���",JOptionPane.WARNING_MESSAGE);
						sqlE.printStackTrace();
					}
					catch(Exception inputE) {
						inputE.printStackTrace();
					}
					//����Ǵ� �ڵ�
					finally {
						try{
							if(rs!=null) {rs.close();}
						}catch(Exception ee) {
							ee.printStackTrace();
						}
					}
				}
				//id�� ���̰� 7��,�������� �ƴҶ� �������� �ƴϸ� ���
				else if(isNumber == false) {
					JOptionPane.showMessageDialog(null,"ID�� ���ڸ� �Է����ּ���","���",JOptionPane.WARNING_MESSAGE);}
				//id�� ������������, 7�ڸ��� �ƴҰ��
				else {
					JOptionPane.showMessageDialog(null,"ID�� 7�ڸ��� �Է����ּ���","���",JOptionPane.WARNING_MESSAGE);}
			}
			else {//������� �޽��� ���
				JOptionPane.showMessageDialog(null,"���� �Էµ��� �ʾҽ��ϴ�.","���",JOptionPane.WARNING_MESSAGE);}
			
			break;
			//--------------------------------------------------------------			
		case "���":
			for(int i=0;i<4;i++) {
				Student.tf_num[i].setText("");
			}
				list();
			break;
			//--------------------------------------------------------------
		case "����":
			try {
				if(id.length()!=0) {
					//�ش��й����� ��ȸ���ؼ� rowcount�� ��ȸ�� �� ���� ��ȸ
					rs = hdb.stmt.executeQuery("select * from student where id = '"+id+"'");
					rs.next();

					//�ش��й��� �����ϸ� �˻������ 1�� ��µǰ� getRow()�� ���� rowcount�� 1 �̵�
					if(rs.getRow() == 1) {
						//�̸�,�а�,�ּҸ� �������� ����
						if(name.length()!=0) {
							hdb.stmt.executeUpdate("update student set name='"+name+"' where id = '"+id+"'");	}
						if(dept.length()!=0) {
							hdb.stmt.executeUpdate("update student set dept='"+dept+"' where id = '"+id+"'");	}
						if(address.length()!=0) {
							hdb.stmt.executeUpdate("update student set address='"+address+"' where id = '"+id+"'");	}
						JOptionPane.showMessageDialog(null,"������ �Ϸ�Ǿ����ϴ�.","�˸�",JOptionPane.INFORMATION_MESSAGE);
						for(int i=0;i<4;i++) {
							Student.tf_num[i].setText("");
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
				break;
			}
			catch(Exception inputE) {
				inputE.printStackTrace();
			}
			for(int i=0;i<4;i++) {
				Student.tf_num[i].setText("");
			}
			break;
			//--------------------------------------------------------------			
		case "����":
			if(JOptionPane.showConfirmDialog(null, "���������Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				if(id.length()!=0) {
					try {
						hdb.stmt.executeUpdate("delete from student where id = '"+id+"'");
						list();
						for(int i=0;i<4;i++) {
							Student.tf_num[i].setText("");
						}
					}
					catch(Exception deleteE) {
						deleteE.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"������ �й��� �Է����ּ���","���",JOptionPane.WARNING_MESSAGE);
				}
			}
			break;
		case "�α׾ƿ�":
			JOptionPane.showMessageDialog(null,"�α׾ƿ��մϴ�","�˸�",JOptionPane.INFORMATION_MESSAGE);
			//MainProcess.haksa.dispose();
			Haksa.f.setVisible(false);
			Haksa.main.showFrameLogin();
			break;
		}
	}
	public static void list(){
		try{
			// Select�� ����
			ResultSet rs=DBManager.stmt.executeQuery("select * from student order by id");

			//JTable �ʱ�ȭ
			Student.model.setNumRows(0);
			while(rs.next()){
				String[] row=new String[4];
				row[0]=rs.getString("id");
				row[1]=rs.getString("name");
				row[2]=rs.getString("dept");
				row[3]=rs.getString("address");
				Student.model.addRow(row);
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
}


