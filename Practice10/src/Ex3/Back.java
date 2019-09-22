package Ex3;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Back extends JFrame {
	public static JLabel[] bulletLabel = new JLabel[100];
	public static JLabel shipLabel = new JLabel();
	public static JLabel enermyLabel = new JLabel();
	public final static int FLYING_UNIT = 10;
	public static JScrollPane scrollPane;
	public static EnermyThread enermyThread = new EnermyThread(enermyLabel);
	public static JPanel backGround;
	ImageIcon icon;
	public Back() {
		icon = new ImageIcon("img/bgi.jpg");
		//��� Panel ������ �������������� ����      
		backGround = new JPanel(null) {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				// Approach 3: Fix the image position in the scroll pane
				// Point p = scrollPane.getViewport().getViewPosition();
				// g.drawImage(icon.getImage(), p.x, p.y, null);
				this.setFocusable(true);
				this.requestFocus();
				setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
				super.paintComponent(g);
			}
		};
		//�Ѿ� �� ----------------------------------------------------------
		for(int i=0;i<100;i++) {
			bulletLabel[i] = new JLabel();
			bulletLabel[i].setIcon(new ImageIcon("img/bullet.png"));
			backGround.add(bulletLabel[i]);
		}
		//bulletLabel[0].setLocation(200,200);
		//bulletLabel[0].setSize(16, 16);
		backGround.add(bulletLabel[0]);
		
		//���� ����� �� ------------------------------------------------------
		enermyLabel.setIcon(new ImageIcon("img/enermy2.png"));
		enermyLabel.setLocation(350, 20);
		enermyLabel.setSize(64, 64);
		backGround.add(enermyLabel);
		//EnermyThread enermyThread = new EnermyThread(enermyLabel);
		enermyThread.start();
		//-----------------------------------------------------------------
		
		//�� ����� ��--------------------------------------------------------
		shipLabel.setIcon(new ImageIcon("img/ship.png"));
		shipLabel.setLocation(350, 420);
		shipLabel.setSize(60, 60);
		backGround.add(shipLabel);
		backGround.addKeyListener(new MyCharacterListener(shipLabel));
		//------------------------------------------------------------------
		backGround.setFocusable(true);
		backGround.requestFocus();
		
		scrollPane = new JScrollPane(backGround);
		setContentPane(scrollPane);
	}

	public static void main(String[] args) {
		Back frame = new Back();
		frame.setTitle("����");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(730, 520);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}