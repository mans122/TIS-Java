import java.awt.event.*;
import java.sql.*;
import javax.swing.JOptionPane;

public class MyActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		//�Է¹޴� �׼��� �̸��� String������ ���� cmd�� ����
		String cmd = e.getActionCommand();

		//JDBC ������ ���� ���� ����
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs = null;
		String url = "jdbc:oracle:thin:@localhost:1521:myoracle";
		String uid = "ora_user";
		String pass = "hong";
		//��������� Ȯ���ϱ����� value�� ���� ����
		int value = 0;
		//��Ͻ� ���� �޾ƿ������� ��������
		String id = new String();
		String name = new String();
		String dept = new String();
		String address = new String();

		switch(cmd) {
		case "���":
			for(int i=0;i<4;i++) {//tf_num�� ����ִ°� �ִ��� Ȯ��
			if(Haksa.tf_num[i].getText().length()==0 || Haksa.tf_num[i].getText().equals("")) {
					value = 1;//������� value���� 1�� ����
				}
			}
			if (value != 1){
				try {
					//�й�,�̸�,�а�,�ּҸ� ������� ������ �־���
					id = Haksa.tf_num[0].getText();
					name = Haksa.tf_num[1].getText();
					dept = Haksa.tf_num[2].getText();
					address = Haksa.tf_num[3].getText();
					//JDBC����
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn=DriverManager.getConnection(url,uid,pass);
					System.out.println("����Ϸ�");//���� �� ����� Ȯ���ڵ�
					stmt=conn.createStatement();
					
					//stmt.executeQuery("insert into student values("+id+","+name+","+dept+","+address+")");
					stmt.executeQuery("insert into student values('"+id+"','"+name+"','"+dept+"','"+address+"')");
					
					System.out.println(id);
					System.out.println(name);
					System.out.println(dept);
					System.out.println(address);
				}
				
				
				
				catch(Exception inputE) {
					inputE.printStackTrace();
				}
				for(int i=0;i<4;i++) {
					Haksa.tf_num[i].setText("");
				}
			}
			else {//������� �޽��� ���
				JOptionPane.showMessageDialog(null,"���� �Էµ��� �ʾҽ��ϴ�.","���",JOptionPane.WARNING_MESSAGE);
				break;
			}
			break;
//--------------------------------------------------------------			
		case "���":
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn=DriverManager.getConnection(url,uid,pass);
				System.out.println("����Ϸ�");
				stmt=conn.createStatement();
				rs = stmt.executeQuery("select * from student");
				
				while(rs.next()) {
					
					//Haksa.taList.append(rs.getString("id"));
					Haksa.taList.append(rs.getString("id")); //Į�� �̸���� Į�� �ε����� ��� ����
					Haksa.taList.append("\t     ");
					Haksa.taList.append(rs.getString("name"));
					Haksa.taList.append("\t ");
					Haksa.taList.append(rs.getString("dept"));
					Haksa.taList.append("\t ");
					Haksa.taList.append(rs.getString("address"));
					Haksa.taList.append("\n");
				}
				rs.close();
				conn.close();
			} catch (Exception listE) {
				listE.printStackTrace();
			}
			System.out.println("���");
			break;
//--------------------------------------------------------------
		case "����":
			System.out.println("����");
			break;
//--------------------------------------------------------------			
		case "����":
			if(JOptionPane.showConfirmDialog(null, "���������Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) 
				System.out.println("����ó��");
			break;


		}

	}

}
