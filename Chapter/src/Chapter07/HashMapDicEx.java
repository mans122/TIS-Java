package Chapter07;
import java.util.*;

public class HashMapDicEx {
	public static void main(String[] args) {
		//Hashmap����
		HashMap<String, String> dic = new HashMap<>();
		//var dic = new HashMap<String,String>();�� ������ ��� ����

		dic.put("baby","�Ʊ�");
		dic.put("love","���");
		dic.put("apple","���");

		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.print("ã����� �ܾ� ");
			String eng = sc.next();
			if(eng.equals("exit")) {
				System.out.println("����");
				break;
				}
			String kor = dic.get(eng);
			if(kor == null) {
				System.out.println(eng+"�� ���´ܾ�");
				}
			else {
				System.out.println(kor);
				}
		}
		//Iterator�� �̿��� ��� key�� ���
		Set<String> keys = dic.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()) {
			//System.out.println(it.next());
			String name = it.next();
			dic.get(name);
			System.out.println(dic.get(name));
		}
		System.out.println(dic.get("baby"));
		sc.close();

	}

}
