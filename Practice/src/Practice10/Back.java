package Practice10;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
//
public class Back extends JFrame {
	public static JLabel[] bulletLabel = new JLabel[100];
	public static JLabel shipLabel = new JLabel();
	public static JLabel enermyLabel = new JLabel();
	public static JLabel killCount = new JLabel();
	public static JLabel bulletEa = new JLabel();
	public static JLabel noBullet = new JLabel();
	public final static int FLYING_UNIT = 10;
	public static EnermyThread enermyThread = new EnermyThread(enermyLabel);
	public static JPanel backGround;
	private static ImageIcon icon;
	public static int count = 0;
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
				//this.setFocusable(true);
				//this.requestFocus();
				setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
				super.paintComponent(g);
			}
		};
		backGround.setFocusable(true);
		backGround.requestFocus();
		//�Ѿ� �� ----------------------------------------------------------
		for(int i=0;i<100;i++) {
			bulletLabel[i] = new JLabel();
			bulletLabel[i].setIcon(new ImageIcon("img/bullet.png"));
			backGround.add(bulletLabel[i]);
		}
		backGround.addKeyListener(new MyCharacterListenerSpace(backGround));
		//���� ����� �� ------------------------------------------------------
		enermyLabel.setIcon(new ImageIcon("img/enermy2.png"));
		enermyLabel.setLocation(350, 20);
		enermyLabel.setSize(64, 64);
		backGround.add(enermyLabel);
		enermyThread.start();
		//-----------------------------------------------------------------
		
		//�� ����� ��--------------------------------------------------------
		shipLabel.setIcon(new ImageIcon("img/ship.png"));
		shipLabel.setLocation(350, 400);
		shipLabel.setSize(60, 60);
		backGround.add(shipLabel);
		backGround.addKeyListener(new MyCharacterListener(shipLabel));
		//------------------------------------------------------------------
		
		//���� ����� ��----------------------------------------------------
		killCount.setLocation(680, 10);
		killCount.setSize(100, 20);
		backGround.add(killCount);
		//---------------------------------
		
		//�Ѿ��� �����ϴ�---------------------------
		//noBullet.setText("�Ѿ��� �����ϴ�");
		noBullet.setSize(100, 30);
		noBullet.setVisible(false);
		backGround.add(noBullet);
		NoBullet nb = new NoBullet();
		nb.start();
		//------------------------------------------------
		//�����Ѿ˰���----------------------------------------
		bulletEa.setText("���� �Ѿ� :");
		bulletEa.setSize(80, 20);
		bulletEa.setLocation(640, 40);
		bulletEa.setVisible(true);
		backGround.add(bulletEa);
		setContentPane(backGround);
	}

	public static void main(String[] args) {
		Back frame = new Back();
		frame.setTitle("����");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(icon.getIconWidth()+10, icon.getIconHeight()+20);
		System.out.println((icon.getIconWidth()+","+ icon.getIconHeight()));
		frame.setResizable(false);
		frame.setVisible(true);
	}
}