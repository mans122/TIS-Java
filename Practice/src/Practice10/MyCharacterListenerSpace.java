package Practice10;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyCharacterListenerSpace extends Thread implements KeyListener{
	public static int bulletCount = 0;
	private BulletThread[] bullet = new BulletThread[100];
	public static int s;
	public MyCharacterListenerSpace(JPanel backGround) {}
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch(keyCode) {
		//case KeyEvent.VK_UP:
		//	Game.shipLabel.setLocation(Game.shipLabel.getX(),Game.shipLabel.getY()-Game.FLYING_UNIT); break;
		//case KeyEvent.VK_DOWN:
		//	Game.shipLabel.setLocation(Game.shipLabel.getX(),Game.shipLabel.getY()+Game.FLYING_UNIT); break;
		case KeyEvent.VK_R:
			s=1;
			break;

		case KeyEvent.VK_SPACE:
			if(bulletCount==99){
				Back.noBullet.setVisible(true);
				Back.noBullet.setText("�Ѿ��� �����ϴ�");
			}else {
			Back.bulletLabel[bulletCount].setSize(16, 16);
			bullet[bulletCount] = new BulletThread(Back.bulletLabel[bulletCount]);
			bullet[bulletCount].start();
			bulletCount++;
			
			//System.out.println(bulletCount);
			}
			break;
		case KeyEvent.VK_F1:
			EnermyThread.setStop(true);
			EnermyThread.nowX= -50;
			Back.count = 0;
			Back.enermyLabel.setIcon(new ImageIcon("img/enermy2.png"));
			System.out.println("f1");
		}
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}