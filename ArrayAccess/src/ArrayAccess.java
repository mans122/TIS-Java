import java.util.Scanner;
public class ArrayAccess {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("�Է��� ������ �����ּ���");
		int a=s.nextInt();
		int intArray[] = new int[a];
		String[][] b;
		int max=0;
		
		System.out.println("���"+a+"���� �Է��ϼ���.");
		for(int i=0;i<intArray.length;i++) {
			intArray[i] = s.nextInt();
			if(intArray[i]>max) {
				max = intArray[i];
			}
		}
		System.out.println("����ū ����"+max+"�Դϴ�.");
		
		for(int k : intArray) {
			System.out.print(k+",");
		}
		
		System.out.println("���� ���� �����ּ���");
		int A=s.nextInt();
		System.out.println("���� ���� �����ּ���");
		int B=s.nextInt();
		b = new String[A][B];
		for(int i=0;i<A;i++) {
			for(int j=0;j<B;j++) {
				System.out.println("b["+i+"]["+j+"]���ڿ��� �Է��ϼ���");
				String c=s.next();
				b[i][j]=c;
			}
		}
		
		s.close();
	}

}
