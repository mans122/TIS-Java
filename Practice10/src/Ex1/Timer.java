package Ex1;
import java.util.Calendar;
import javax.swing.JLabel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Timer extends JFrame{
	public Timer() {
		setTitle("Ÿ�̸ӿ���");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		//Ÿ�̸� ���� ����� ���̺� ����
		JLabel timerLabel = new JLabel();
		timerLabel.setFont(new Font("Gothic",Font.ITALIC,20));
		c.add(timerLabel);//���̺��� ����Ʈ�ҿ� ����
		//Ÿ�̸� ������ ��ü ����. Ÿ�̸� ���� ����� ���̺��� �����ڿ� ����
		TimerThread th = new TimerThread(timerLabel);

		th.start();//Ÿ�̸� ������ ����
		setSize(300,100);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Timer();
	}
}

class TimerThread extends Thread{
	private JLabel timerLabel;
	public TimerThread(JLabel timerLabel) {
		this.timerLabel = timerLabel;
	}
	public void run() {
		while(true) {
			try {
				Calendar now = Calendar.getInstance();
				SimpleDateFormat format1 = new SimpleDateFormat ("yyyy��MM��dd�� HH:mm:ss");
				Date time = new Date();
				timerLabel.setText(format1.format(time));
				Thread.sleep(100);
			}
			catch(Exception e) {	return;	}
		}
	}
}