package Ex7_3;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListEx {
	public static void main(String[] args) {
		//���ڿ��� ���� ������ ArrayList ����
		ArrayList<String> a = new ArrayList<>();
		//Scanner ��ü ����
		Scanner sc = new Scanner(System.in);
		
		//Ű����κ��� 4���� �̸��� �Է¹޾� ArrayList ����
		for(int i=0;i<4;i++) {
			System.out.print("�̸��� �Է��ϼ���");
			a.add(sc.next());
		}
		
		//ArrayList�� �ִ� ��� �̸� ���
		for(int i=0;i<a.size();i++) {
			System.out.print(a.get(i)+" ");
		}
		
		//���� �� �̸� ���
		int longestIndex = 0;
		for(int i=1;i<a.size();i++) {
			if(a.get(longestIndex).length() < a.get(i).length()) {
				longestIndex = i;
			}
		}
		System.out.println("\n���� �� �̸� : "+a.get(longestIndex)+" "+a.get(longestIndex).length());
		sc.close();
		
	}

}
