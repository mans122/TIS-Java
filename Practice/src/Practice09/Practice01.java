package Practice09;
import javax.swing.*;
import java.awt.*;
public class Practice01 extends JFrame {
	private MyPanel panel = new MyPanel();
	
	public Practice01() {
		setTitle("paintComponent()����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);//Mypanel Ŭ������ ������ panel�� ���������ӿ� ����
		setSize(1000,800);
		setVisible(true);
	}
	//MyPanel Ŭ���� ����
	class MyPanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			//1������ ����׷��� �׸���
			g.setFont(new Font("Jokerman",Font.ITALIC,15));
			g.drawString("�б⺰ ������Ȳ ����׷���", 80, 35);
			g.setColor(Color.BLUE);
			g.drawString("1/4�б�", 10, 65);
			g.fillRect(70, 50, 150, 20);
			g.setColor(Color.RED);
			g.drawString("2/4�б�", 10, 95);
			g.fillRect(70, 80, 200, 20);
			g.setColor(Color.BLACK);
			g.drawString("3/4�б�", 10, 125);
			g.fillRect(70, 110, 250, 20);
			g.setColor(Color.MAGENTA);
			g.drawString("4/4�б�", 10, 155);
			g.fillRect(70, 140, 320, 20);
			
			//2�� �б⺰ ������Ȳ ������ �׷���
			g.drawString("�б⺰ ������Ȳ �������׷���", 80, 250);
			int width=50; // �б⸶�� x������ 50�� �ø������� �̸� 50�ǰ��������� ���� width ����
			g.setColor(Color.BLUE);
			g.drawString("1/4�б�", 50, 610);
			g.drawLine(50, 600, 50+width, 600-150);	//x��50���� width��ŭ ������Ű��, y�� 600���� 1/4�б� ����ŭ ����
			g.drawString("2/4�б�", 100, 460);
			g.drawLine(50+width, 450, 50+width*2, 600-200);
			g.drawString("3/4�б�", 150, 410);
			g.drawLine(50+width*2, 400, 50+width*3, 600-250);
			g.drawString("4/4�б�", 200, 360);
			g.drawLine(50+width*3, 350, 50+width*4, 600-320);
			
			//3�� �б⺰ ������Ȳ ������Ʈ �׸���
			g.drawString("�б⺰ ������Ȳ ������Ʈ", 570, 120);
			g.setColor(Color.BLUE);
			g.fillArc(550, 150, 200, 200, 0, 58);
			g.setColor(Color.RED);
			g.fillArc(550, 150, 200, 200, 58, 78);
			g.setColor(Color.BLACK);
			g.fillArc(550, 150, 200, 200,136, 125);
			g.setColor(Color.MAGENTA);
			g.fillArc(550, 150, 200,200,261,99);
		}
	}
	public static void main(String[] args) {
		new Practice01(); 
	}

}
