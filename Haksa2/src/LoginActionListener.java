import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
public class LoginActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		ResultSet rs = null;
		String cmd = e.getActionCommand();
		String id = Login.loginField.getText();
		String pwd = Login.pwdField.getToolTipText();
		DBManager db = new DBManager();
		switch(cmd) {
		case "�α���":
			if(id.length()==0) {
				JOptionPane.showMessageDialog(null,"ID�� �Է��ϼ���","���",JOptionPane.WARNING_MESSAGE);
			}
			else {
				try {
					rs = DBManager.stmt.executeQuery("select count(*) as count from student where id='"+id+"'");
					rs.next();
					rs.getInt("count");
					System.out.print(id);
					if(id.equals("test") || rs.getInt("count") == 1) {
						JOptionPane.showMessageDialog(null,"�α��� ����","�˸�",JOptionPane.INFORMATION_MESSAGE);
						Login.main.showFrameTest(); // ����â �޼ҵ带 �̿��� ����
					}
					else {
						JOptionPane.showMessageDialog(null,"�α��� ����","�˸�",JOptionPane.INFORMATION_MESSAGE);
					}
					rs.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			Login.loginField.setText("");
			break;

		case "ȸ������":
			System.out.println(pwd);
			break;
		}
	}

}
