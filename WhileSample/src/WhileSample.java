import java.util.Scanner;
public class WhileSample {
	public static void main(String[] args) {
		int count=0;
		int sum=0;
		Scanner s = new Scanner(System.in);
		System.out.println("���� �Է��ϰ� �������� 0�Է�");
		
//		int n = s.nextInt();
//		while(n != 0) {
//			sum+=n;
//			count++;
//			n=s.nextInt();
//		}
		
		
		if(count==0) {
			System.out.println("�Է� ���� 0��");
		}
		else {
			System.out.println("������ ������"+count+"�� �̸�, ����� "+(float)sum/count+"�Դϴ�.");
		}
		s.close();
	}

}

