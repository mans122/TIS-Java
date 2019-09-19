import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
//point�� pointflag�� �ϳ��� Ŭ�����ȿ� �־ ArrayListó��
public class Ex_12 extends JFrame{

	private MyPanel panel=new MyPanel();

	public Ex_12() {
		this.setTitle("Graphic");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setContentPane(panel);

		this.setSize(500, 500);
		this.setVisible(true);
	}

	@SuppressWarnings("serial")
	class MyPanel extends JPanel{

		private ArrayList<Point> aPoint=new ArrayList<Point>();
		private ArrayList<Boolean> aPointFlag=new ArrayList<Boolean>();

		Point startP;
		Point endP;

		public MyPanel() {
			addMouseMotionListener(new MouseMotionListener() {

				@Override
				public void mouseDragged(MouseEvent e) {

					aPoint.add(e.getPoint());
					aPointFlag.add(false);
					repaint();
				}

				@Override
				public void mouseMoved(MouseEvent e) {}
			});

			addMouseListener(new MouseListener() {
				@SuppressWarnings("unchecked")
				@Override
				public void mousePressed(MouseEvent e) {
					aPoint.add(e.getPoint());
					aPointFlag.add(false);
				}
				@SuppressWarnings("unchecked")
				@Override
				public void mouseReleased(MouseEvent e) {
					aPointFlag.remove(aPointFlag.size()-1);
					aPointFlag.add(true);
					repaint();//������ �ٷ� �� ��츦 ����

				}
				@Override
				public void mouseClicked(MouseEvent arg0) {}
				@Override
				public void mouseEntered(MouseEvent e) {}
				@Override
				public void mouseExited(MouseEvent e) {}


			});
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			for(int i=0;i<aPoint.size();i++) {
				//ù��° ���̰ų� ���� ���� ������ ���̸�
				if(i==0 || aPointFlag.get(i-1) == true ) {
					startP= (Point) aPoint.get(i);
				}

				endP=(Point) aPoint.get(i);
				g.drawLine((int)startP.getX(), (int)startP.getY(), (int)endP.getX(), (int)endP.getY());
				startP=endP;
			}
		}
	}
	public static void main(String[] args) {
		new Ex_12();
	}
}