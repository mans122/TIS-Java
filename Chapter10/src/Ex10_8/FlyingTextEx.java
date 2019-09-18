package Ex10_8;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
public class FlyingTextEx extends JFrame {
	private final int FLYING_UNIT = 10;
	private JLabel la = new JLabel("hello");

	public FlyingTextEx() {
		setTitle("�����¿�Ű");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(null);
		c.addKeyListener(new MyKeyListener());
		la.setLocation(50,50);
		la.setSize(100,20);
		c.add(la);

		setSize(300,300);
		setVisible(true);
		
		c.setFocusable(true);
		c.requestFocus();//����Ʈ���� Ű �Է� ���� �� �ְ� ��Ŀ�� ��������
		
		//����Ʈ�ҿ��� ��Ŀ���� ���� ��� ���콺�� Ŭ���ϸ� �ٽ� ��Ŀ���� ��� ��.
		c.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Component com = (Component)e.getSource();//���콺�� Ŭ���� ������Ʈ
				com.setFocusable(true);
				com.requestFocus();//���콺Ŭ���� ������Ʈ���� ��Ŀ�� 
			}
		});
	}
	//key Listener ����
	class MyKeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			//�Էµ� keyCode�� 
			int keyCode = e.getKeyCode();
			//KeyCode ������ �����¿��̵�
			switch(keyCode) {
			case KeyEvent.VK_UP:
				la.setLocation(la.getX(),la.getY()-FLYING_UNIT); break;
			case KeyEvent.VK_DOWN:
				la.setLocation(la.getX(),la.getY()+FLYING_UNIT); break;
			case KeyEvent.VK_RIGHT:
				la.setLocation(la.getX()+FLYING_UNIT,la.getY()); break;
			case KeyEvent.VK_LEFT:
				la.setLocation(la.getX()-FLYING_UNIT,la.getY()); break;
			}
		}
	}
	
	public static void main(String[] args) {
		new FlyingTextEx();
	}

}
