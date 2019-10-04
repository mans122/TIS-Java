package Chapter14;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyDialog extends JDialog{
	private JTextField tf = new JTextField(10);
	private JButton okButton = new JButton("OK");
	
	public MyDialog(JFrame frame, String title) {
		//3��° �Ķ���Ͱ��� true�� �ָ� modal�� true�� ����
		super(frame,title,true);
		setLayout(new FlowLayout());
		add(tf);
		add(okButton);
		setSize(200,100);
		//���̾�α� OK��ư�� Action������ �ޱ�
		//�͸�Ŭ������ �ۼ�
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
}

public class DialogEx extends JFrame{
	private MyDialog dialog;
	
	public DialogEx() {
		super("�ٿ��� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton btn = new JButton("Show Dialog");
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		//���̾�α� ����
		dialog = new MyDialog(this, "Test Dialog");
		
		//Show Dialog��ư �׼Ǹ����� ����
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);
			}
		});
		c.add(btn);
		getContentPane().add(btn);
		setSize(300, 300);
		setVisible(true);
	}

	public static void main(String[] args) {
		new DialogEx();
	}

}
