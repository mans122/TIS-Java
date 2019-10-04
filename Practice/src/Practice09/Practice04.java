package Practice09;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Practice04 {
	JPanel p1 = new JPanel();
	//public CenterPanel cp = new CenterPanel();
	public static CenterPanel cp = new CenterPanel(); // CenterPanelŬ������ ���� ���������� ��� ���� �г� cp����
	public NorthPanel np= new NorthPanel();	//NotrhPanelŬ������ ���� �������� �г� np ����
	public static JLabel[] bk = new JLabel[4];//�б⸦ ǥ������ JLabel bk 4�� �迭�� ����
	public static JTextField[] tf_num = new JTextField[4];//�б⺰ ���� �Է¹��� JTextField tf_num�� �迭�� 4�� ����
	public static Integer[] bk_num = new Integer[4]; //tf_num�� ���� ���� ���������� ��ȯ�ؼ� ���� ���������� bk_nume �� �迭�� 4������
	public static int sum =0;//���� �бⰪ���� ���� ������ sum ����
	public static int[] bkp = {0,0,0,0};//������Ʈ���� �б⺰ ������ ���ϱ����� bkp�� �б⺰ �ۼ�Ʈ������ ������ ������
	public static int gap = 0;//360������ ���ڶ� ��ŭ 4/4�б⿡ �����ֱ� ���� ���� ���� ���� 

	public Practice04() {
		JFrame f = new JFrame("��������09-4");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �������� x��ư�� Ȱ��ȭ�Ͽ� �ݱ��ư�� ���డ������
		f.add(p1); // ���������� f�� �ǳ�p1�� �߰��Ѵ�.
		p1.setLayout(new BorderLayout()); //���ο� �ö� p1�ǳ��� ���̾ƿ��� BorderLayout���� ����
		np.setLayout(new FlowLayout());//np�г��� FlowLayout���� ����
		p1.add(cp,BorderLayout.CENTER); //CENTER�� cp�г� �ø�
		p1.add(np,BorderLayout.NORTH);//NORTH�� np�г� �ø�
		f.setSize(560, 500); // ������ ������ ����
		f.setVisible(true); // �������� ���̰� ��
	}	
	//BorderLayout.NORTH �г� ���� �� �� ���� �ۼ�
	class NorthPanel extends JPanel{
		JButton input = new JButton("�Է�");
		public NorthPanel() {
			//��,�ؽ�Ʈ�ʵ� ����
			for(int i=0;i<4;i++) {
				bk[i] = new JLabel((i+1)+"/4�б�");
				tf_num[i] = new JTextField(5);
			}
			//ActionListener ���� �� input��ư ���
			MyActionListener ma = new MyActionListener();
			input.addActionListener(ma);
			
			//�гο� bk,tf_num ���
			this.setBackground(Color.LIGHT_GRAY);
			for(int i=0;i<4;i++) {
				this.add(bk[i]);
				this.add(tf_num[i]);
			}
			this.add(input); //input��ư ���
		}
	}
	//CenterPanel Ŭ���� �ۼ�
	static class CenterPanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			System.out.println(sum); // �Է��� ���� ���� �� ����Ǿ����� Ȯ���ϱ����� �ۼ�
			g.setFont(new Font("Gothic",Font.ITALIC,15));
			g.drawString("�б⺰ ������Ȳ ������Ʈ", 570, 120);
			g.setColor(Color.BLUE);
			g.drawString("1/4�б�", 480, 150);
			g.fillRect(450, 140, 20, 10);
			g.fillArc(100, 50, 300, 300, 0, bkp[0]);//���� ���� 0���� 1/4�б���� ������.
			
			g.setColor(Color.RED);
			g.drawString("2/4�б�", 480, 170);
			g.fillRect(450, 160, 20, 10);
			g.fillArc(100, 50, 300, 300, bkp[0],bkp[1]); //1/4�б� �������� 2/4�бⰡ �����Ǿ���
			
			g.setColor(Color.BLACK);
			g.drawString("3/4�б�", 480, 190);
			g.fillRect(450, 180, 20, 10);
			g.fillArc(100, 50, 300, 300, bkp[0]+ bkp[1],bkp[2]);//3/4�б��� ������ 1,2�бⰡ ������ �� �������� �����Ǿ���
			
			g.setColor(Color.MAGENTA);
			g.drawString("4/4�б�", 480, 210);
			g.fillRect(450, 200, 20, 10);
			g.fillArc(100, 50, 300, 300, bkp[0]+ bkp[1]+ bkp[2],bkp[3]+gap);
			//4�б�� 1,2,3�б� �� ���������� ���۵Ǿ� �ϰ�, �Ҽ��������� ���������� �޴ٺ��� 1~4�б� ���� 360�� �ȵǴ°�찡 ����,360���� ���ڶ� ���� gap���� ���ؼ� ������
		}
	}
	//MyActionListener Ŭ������ �ܺ�Ŭ������ ������
	/*
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i=0;i<4;i++) {
				bk_num[i] = Integer.parseInt(tf_num[i].getText());
				sum+=bk_num[i];
			}
			for(int i=0;i<4;i++) {
				bkp[i] = (360*bk_num[i])/sum;
				bkp[i] = Math.round(bkp[i]);
				gap += bkp[i];
				System.out.println(bkp[i]);
			}
			gap = 360-gap;
			System.out.println("gap :" + gap);
			cp.repaint();
		}
	}
*/
	public static void main(String args[]) {
		new Practice04();
	}
}