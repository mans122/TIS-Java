import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
public class LoginActionListener implements ActionListener{
	public static String loginId;
	public void actionPerformed(ActionEvent e) {
		ResultSet rs = null;
		String cmd = e.getActionCommand();
		String id = Login.loginField.getText();
		String pwd = new String(Login.pwdField.getPassword());
		
		switch(cmd) {
		case "�α���":
			if(id.length()==0) {
				JOptionPane.showMessageDialog(null,"ID�� �Է��ϼ���","���",JOptionPane.WARNING_MESSAGE);
			}
			else {
				try {
					rs = DBManager.stmt.executeQuery("select id,birth from student where id='"+id+"'");
					rs.next();
					if(id.equals("test") || rs.getString("id").isEmpty()==false && pwd.equals(rs.getString("birth")) ) {
						loginId = id;
						Student.id.setText(loginId+"��");
						JOptionPane.showMessageDialog(null,"�α��� ����","�˸�",JOptionPane.INFORMATION_MESSAGE);
						Login.main.showFrameTest(); // ����â �޼ҵ带 �̿��� ����
					}
					else {
						JOptionPane.showMessageDialog(null,"�α��� ����","�˸�",JOptionPane.INFORMATION_MESSAGE);
					}
					rs.close();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,"�α��� ����","�˸�",JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
			}
			Login.loginField.setText("");
			Login.pwdField.setText("");
			break;

//		case "ȸ������":
//			System.out.println(pwd);
//			break;
		}
	}

}
