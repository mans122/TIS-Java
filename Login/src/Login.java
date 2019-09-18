import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener {
	public Login() {
		this.setTitle("Login");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = this.getContentPane();
		c.setLayout(null); // ���̾ƿ� ��� X
		int gap = 10;
		//ID�Է� ĭ
		JLabel label_id = new JLabel("ID");
		label_id.setBounds(20,20,30,20); //x,y,����ũ��,����ũ�� ����
		c.add(label_id);
		JTextField tf_id = new JTextField();
		tf_id.setBounds(50+gap,20,150,20);
		c.add(tf_id);
		//PW�Է� ĭ
		JLabel label_pwd = new JLabel("PWD");
		label_pwd.setBounds(20,40+gap,30,20); //x,y,����ũ��,����ũ�� ����
		c.add(label_pwd);
		JPasswordField tf_pwd = new JPasswordField();
		tf_pwd.setBounds(50+gap,40+gap,150,20);
		c.add(tf_pwd);
		//�α��� ��ư
		JButton login = new JButton("�α���");
		login.setBounds(40,60+gap*2,80,30); //x,y,����ũ��,����ũ�� ����
		c.add(login);
		//��� ��ư
		JButton cancel = new JButton("ȸ������");
		cancel.setBounds(130,60+gap*2,80,30); //x,y,����ũ��,����ũ�� ����
		c.add(cancel);
		@SuppressWarnings("unused")
		MyActionListener ma = new MyActionListener();
		//--------------------------------------------------------
		//�Ϲ����� Ŭ������ MyActionListener�� �����ؼ� ����ϴ� ���
		//		login.addActionListener(ma);
		//		cancel.addActionListener(ma);
		//--------------------------------------------------------		
		//������ class�� �������� �ʰ� ActionListener�������̽��� ��ӹ޾� ����ϴ� ���
		//ActionPerformed()�� Overriding �ϰ� addActionListener(this)ó�� this ���
		//login.addActionListener(this);
		//cancel.addActionListener(this);
		//--------------------------------------------------------
		//�͸�Ŭ������ �̺�Ʈ������ �ۼ� ����.addActionListener(new ActionListener(){} ���ۼ��ϸ� �����޽������� unimplement ���
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("�α��� ó��");
			}});
		//-----------------------------------------------------------------//
		this.setSize(800, 600);
		this.setVisible(true);
	}
	//--------------------------------------------------------
	//Ŭ������ �����ؼ� ActionListener�� ����ϴ� ���
	class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//		JButton btn=(JButton)e.getSource();
			//		if(btn.getText().equals("�α���")) {
			//			System.out.println("�α��� ó����");
			//		}
			//		else if(btn.getText().equals("ȸ������")) {
			//			System.out.println("ȸ������ ó��");
			//		}

		}

	}

	public static void main(String[] args) {
		new Login();
	}
	//--------------------------------------------------------
	//ActionListener�� ��ӹ޾� Override�Ͽ� ����ϴ� ���
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn=(JButton)e.getSource();
		if(btn.getText().equals("�α���")) {
			System.out.println("�α��� ó����");
		}
		else if(btn.getText().equals("ȸ������")) {
			System.out.println("ȸ������ ó��");
		}
	}

}



