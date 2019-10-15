import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Login extends JFrame{
	public static MainProcess main;
	public static JPanel back;
	public static Login frame;
	public static JTextField loginField;
	public static JPasswordField pwdField;
	JScrollPane scrollPane;
	ImageIcon icon;
	public Login() {
		icon = new ImageIcon("img/back1.jpg");
		back = new JPanel(null) {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
				super.paintComponent(g);
			}
		};
		
		//�α��� ĭ
		JLabel loginLabel = new JLabel("�й�");
		loginLabel.setFont(new Font("Serif",Font.BOLD,22));
		loginLabel.setLocation(140, 35);
		loginLabel.setSize(loginLabel.getPreferredSize().getSize());
		
		loginField = new JTextField();
		loginField.setLocation(140,70);
		loginField.setSize(210,30);
		
		JButton loginButton = new JButton("�α���");
		loginButton.setFont(new Font("Serif",Font.BOLD,15));
		loginButton.setSize(100, 32);
		loginButton.setLocation(140, 200);
		
		//��й�ȣ ĭ
		JLabel pwdLabel = new JLabel("�������");
		pwdLabel.setFont(new Font("Serif",Font.BOLD,22));
		pwdLabel.setLocation(140, 115);
		pwdLabel.setSize(pwdLabel.getPreferredSize().getSize());
		
		pwdField = new JPasswordField();
		pwdField.setEchoChar('*');
		pwdField.setLocation(140,150);
		pwdField.setSize(210,30);
		
		LoginActionListener la = new LoginActionListener();
		loginButton.addActionListener(la);
		
		back.add(loginField);
		back.add(loginLabel);
		back.add(loginButton);
		
		back.add(pwdLabel);
		back.add(pwdField);
		scrollPane = new JScrollPane(back);
		setContentPane(scrollPane);
		setSize(500, 300);
		setVisible(true);
	}
	public void setMain(MainProcess main) {
		this.main = main;
	}
	public static void main(String[] args) {
	}
}
