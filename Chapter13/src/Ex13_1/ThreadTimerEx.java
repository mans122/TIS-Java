package Ex13_1;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;

class TimerThread extends Thread{
	private JLabel timerLabel;
	
	public TimerThread(JLabel timerLabel) {
		this.timerLabel = timerLabel;
	}
	
	@Override
	public void run() {
		int n=0;
		while(true) {
			timerLabel.setText(Integer.toString(n));
			n++;
			try {
				Thread.sleep(10);
			}
			catch(InterruptedException e) {	return;	}
		}
	}
}
public class ThreadTimerEx extends JFrame{
	public ThreadTimerEx() {
		setTitle("Ÿ�̸ӿ���");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		//Ÿ�̸� ���� ����� ���̺� ����
		JLabel timerLabel = new JLabel();
		timerLabel.setFont(new Font("Gothic",Font.ITALIC,80));
		c.add(timerLabel);//���̺��� ����Ʈ�ҿ� ����
		
		//Ÿ�̸� ������ ��ü ����. Ÿ�̸� ���� ����� ���̺��� �����ڿ� ����
		TimerThread th = new TimerThread(timerLabel);
		
		setSize(300,200);
		setVisible(true);
		
		th.start();//Ÿ�̸� ������ ����
	}
	public static void main(String[] args) {
		new ThreadTimerEx();
	}

}
