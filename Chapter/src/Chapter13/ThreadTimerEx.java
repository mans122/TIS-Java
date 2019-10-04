package Chapter13;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ThreadTimerEx extends JFrame{
	public ThreadTimerEx() {
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
		
		ThreadA ta = new ThreadA();
		ThreadB tb = new ThreadB();
		ThreadC tc = new ThreadC();
		Thread threadC= new Thread(tc);
		th.start();//Ÿ�̸� ������ ����
		ta.start();
		tb.start();
		threadC.start();
		ta.setPriority(5);
		tb.setPriority(10);
		th.setPriority(3);
		threadC.setPriority(1);

		for (int i = 0; i<50 ;i ++) {System.out.println("MainThread");	}

		setSize(300,100);
		setVisible(true);
	}
	public static void main(String[] args) {
		new ThreadTimerEx();
		new ThreadA();
		new ThreadB();
		long id = Thread.currentThread().getId();
		String name = Thread.currentThread().getName();
		int priority = Thread.currentThread().getPriority();
		Thread.State s = Thread.currentThread().getState();
		System.out.println("���� ������ �̸� = "+name);
		System.out.println("���� ������ ID = "+id);
		System.out.println("���� ������ �켱������ = "+priority);
		System.out.println("���� ������ ���� = "+s);
	}
}
