package Ex12_10;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
public class GraphicsDrawLineMouseEx extends JFrame {
	private MyPanel panel = new MyPanel();
	
	public GraphicsDrawLineMouseEx() {
		setTitle("asd");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		
		setSize(300,300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new GraphicsDrawLineMouseEx();
	}
	//���׸��� �ִ� �г� ����, �� �гο� Mouse ������ ����
	class MyPanel extends JPanel{
		private Vector<Point> vStart = new Vector<Point>();
		private Vector<Point> vEnd = new Vector<Point>();
		
		public MyPanel() {
			//mouse������ ���
			addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent e) {
					Point endP = e.getPoint();
					vEnd.add(endP);
					repaint();//�гο� �ٽñ׸��� ��û
				}
				public void mousePressed(MouseEvent e) {
					Point startP = e.getPoint();
					vStart.add(startP);
				}
				public void mouseExited(MouseEvent e) {	}
				public void mouseEntered(MouseEvent e) {}
				public void mouseClicked(MouseEvent e) {}
				
			});
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLUE);
			
			for(int i=0;i<vStart.size();i++) {
				Point s = vStart.elementAt(i);
				Point e = vEnd.elementAt(i);
				g.drawLine((int)s.getX(), (int)s.getY(), (int)e.getX(), (int)e.getY());
			}
		}
	}

}
