package Ex6_9;

import java.util.StringTokenizer;

public class StringTokenizerEx {
	public static void main(String[] args) {
		StringTokenizer st = new StringTokenizer("ȫ�浿/��ȭ/ȫ��/����/����","/");
		
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
		
		
		String as = new String("ȫ�浿/��ȭ/ȫ��/����/����");
		String[] a = as.split("/");
		for(int i=0;i<a.length;i++) {
			System.out.println(a[i]);
		}
	}

}
