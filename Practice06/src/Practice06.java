import java.util.*; 

public class Practice06 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ArrayList<String> book = new ArrayList<>();
		
		//1������		
		System.out.println("å����, ���� , ���ǻ�, ������ �Է��ϼ���");
		for(int i=0; i<4;i++) {
			book.add(s.nextLine());
		}
					
		//2������
		HashMap<String,String> bookInfo = new HashMap<>();
		bookInfo.put("å����",book.get(0));
		bookInfo.put("����",book.get(1));
		bookInfo.put("���ǻ�",book.get(2));
		bookInfo.put("����",book.get(3));
		
		
		Set<String> keys = bookInfo.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()) {
			String name = it.next();
			bookInfo.get(name);
			System.out.println(bookInfo.get(name));
		}
		s.close();
	}
}