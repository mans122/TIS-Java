package sync;

public class SynchronizedEx {
	public static void main(String[] args) {
		SharedBoard board = new SharedBoard();//���������� ����

		//������ ���� �� ������������ �ּҸ� �˷��ֳ�. �� ������� ���������Ϳ� ���ÿ� �����Ѵ�.
		Thread th1 = new StudentThread("����",board);
		Thread th2 = new StudentThread("�Ѻ�",board);

		//�� ������ ����
		th1.start();
		th2.start();
	}
}
//���������͸� �ùķ��̼��ϴ� Ŭ����
//�� StudentThread������ �� ���� ���� ���ٵ�
class SharedBoard{
	private int sum = 0;
	//synchronized 
	public void add() {
		int n = sum;
		Thread.yield();//���� �������� ������ �纸
		n +=10;
		sum = n;
		System.out.println(Thread.currentThread().getName() + " : " + sum);	
	}
	public int getSum() { return sum;}
}

//�л��� �ùķ��̼��ϴ� ������ Ŭ����
class StudentThread extends Thread{
	private SharedBoard board; //���������� �ּ�

	public StudentThread(String name,SharedBoard board) {
		super(name);
		this.board = board;
	}
	//������ 10�� ���� ī����
	public void run() {
		try {
			for(int i = 0;i<20;i++) {
				board.add();
				Thread.sleep(200);
			}
		}catch(Exception e) {}
	}
}