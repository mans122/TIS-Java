import java.util.Scanner;

public class Practice02 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		/*
		//1������
		System.out.println("�̸�,�й�,�а��� �Է��ϼ���(�������� ����)");
		String name = s.next();
		int num = s.nextInt();
		String dep = s.next();
		System.out.println("�̸�:"+name+",�й�:"+num+",�а�:"+dep);
		
		//2������
		
		System.out.println("����,����,���� ������ �Է��ϼ���(�������� ����)");
		int kor=s.nextInt();
		int eng=s.nextInt();
		int math=s.nextInt();
		System.out.println("�� ������ ��:"+(kor+eng+math)+"\n�� ������ ���:"+((float)(kor+eng+math)/3));
		
		
		//3������
		int count=0;
		int sum=0;
		
		System.out.println("���� �Է��ϰ� �������� -1�� �Է��ϼ���.");
		int n = s.nextInt();
		do {
			sum+=n;
			count++;
			n=s.nextInt();
		}while(n!=-1);
		if(count==0) {
			System.out.println("�Էµ� ���� �����ϴ�.");
		}else {
			System.out.println("������ ������"+count+"�� �̸�,");
			System.out.println("����� "+(double)sum/count+"�Դϴ�.");
		}
		*/
		
		//4������
		
		System.out.println("�л����� �Է��ϼ���.");
		int studentNum = s.nextInt();
		String[] studentName = new String[studentNum];
		int[] studentKor = new int[studentNum];
		int[] studentEng = new int[studentNum];
		int[] studentMath = new int[studentNum];
		int korSum=0,engSum=0,mathSum=0;
		double korAvg=0, engAvg=0,mathAvg=0,allAvg=0;
		
		for(int i=0;i<studentNum;i++) {
				System.out.println((i+1)+"��° �л��� �̸�, ����, ����, ���� ������ �Է��ϼ���(�������� ����)");
				studentName[i] = s.next();
				studentKor[i] = s.nextInt();
				studentEng[i] = s.nextInt();
				studentMath[i] = s.nextInt();
				korSum+=studentKor[i];
				engSum+=studentEng[i];
				mathSum+=studentMath[i];
		}
		korAvg=korSum/studentNum;
		engAvg=engSum/studentNum;
		mathAvg=mathSum/studentNum;
		allAvg=(korAvg+engAvg+mathAvg)/3;
		System.out.println("���� ���\n����:"+korAvg+"\n����:"+engAvg+"\n����:"+mathAvg+"\n��ü ���:"+allAvg);
		/*
		
		double korAvg=0, engAvg=0,mathAvg=0,allAvg=0;
		int korSum=0, engSum=0,mathSum=0;
		System.out.println("�л����� �Է��ϼ���.");
		int studentNum = s.nextInt();
		String[][] studentArray =new String[studentNum][4];
		int[] kor =new int[studentNum];
		int[] eng =new int[studentNum];
		int[] math =new int[studentNum];
		
		
		
		for(int i=0;i<studentNum;i++) {
			System.out.println((i+1)+"��° �л��� �̸�, ����, ����, ���� ������ �Է��ϼ���(�������� ����)");
			studentArray[i][0] = s.next();
			studentArray[i][1] = s.next();
			studentArray[i][2] = s.next();
			studentArray[i][3] = s.next();
			kor[i] = Integer.parseInt(studentArray[i][1]);
			eng[i] = Integer.parseInt(studentArray[i][2]);
			math[i] = Integer.parseInt(studentArray[i][3]);
			korSum+=kor[i];
			engSum+=eng[i];
			mathSum+=math[i];
		}
		korAvg=korSum/studentNum;
		engAvg=engSum/studentNum;
		mathAvg=mathSum/studentNum;
		allAvg=(korAvg+engAvg+mathAvg)/3;
		System.out.println("���� ���\n����:"+korAvg+"\n����:"+engAvg+"\n����:"+mathAvg+"\n��ü ���:"+allAvg);
		*/
		s.close();
		}
	
}
