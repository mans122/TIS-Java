package Ex_2;
import javax.swing.*;
import java.awt.*;
public class LabelEx extends JFrame{
	public LabelEx() {
		setTitle("���̺� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		ImageIcon beauty;
		//���ڿ� ���̺� ����
		JLabel textLabel = new JLabel("����մϴ�");
		
		//�̹��� ���̺� ����
		beauty = new ImageIcon("img/beauty.jpg");
		JLabel imageLabel = new JLabel(beauty);
		
		//���ڿ� �̹��� ��� ���� ���̺� ����
		ImageIcon normalIcon = new ImageIcon("img/call.jpg");
		JLabel label = new JLabel("��ȭ�ض�",normalIcon,SwingConstants.CENTER);
		label.setPreferredSize(new Dimension(380,200));
		label.setOpaque(true);
		label.setBackground(Color.MAGENTA);
		
		c.add(textLabel);
		c.add(imageLabel);
		c.add(label);
		
		setSize(1024,1050);
		setVisible(true);
	}
	public static void main(String[] args) {
		new LabelEx();
	}

}
