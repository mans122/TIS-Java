import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class MyActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		int value = 0;
		switch(cmd) {
		case "���":
			for(int i=0;i<4;i++) {//tf_num�� ����ִ°� �ִ��� Ȯ��
				if(Haksa.tf_num[i].getText().length()==0 || Haksa.tf_num[i].getText().equals("")) {
					value = 1;//������� value���� 1�� ����
				}
			}
			if(value==1) {//������� �޽��� ���
				JOptionPane.showMessageDialog(null,"���� �Էµ��� �ʾҽ��ϴ�.","���",JOptionPane.WARNING_MESSAGE);
			}
			else {
				System.out.println("���");
				for(int i=0;i<4;i++) {
					Haksa.tf_num[i].setText("");
				}
			}
			break;
		case "���":
			System.out.println("���");
			break;
		case "����":
			System.out.println("����");
			break;
		case "����":
			if(JOptionPane.showConfirmDialog(null, "���������Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) 
				System.out.println("����ó��");
			break;
		}
	}
	
}
