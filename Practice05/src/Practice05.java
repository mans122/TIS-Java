//nextLine() > enter�Է±��� �޾Ƶ���
import java.util.Scanner;
class Member{
	String id;
	public Member() {}
	public Member(String id) {
		id.trim();
		id.replace(" ","");
		this.id = id;
		
	}
	public boolean equals(char obj) {
		for(int i=0; i<id.length();i++) {
			if(id.charAt(i) == obj) {
				System.out.println("���̵� !,@,#,$,%,^�� ���ԵǸ� �ȵ˴ϴ�.");
				return false;
			}
		}
		return true;
	}
}
//0������
interface Mouse{
	abstract public void mouse();
	abstract public void mouseRight();
	abstract public void mouseLeft();
	abstract public void mouseMove();
}
class WheelMouse implements Mouse{
	public WheelMouse() {}
	public void scrollWheel() {}
	@Override
	public void mouseMove() {}
	@Override
	public void mouse() {}
	@Override
	public void mouseRight() {}
	@Override
	public void mouseLeft() {}


}
class LaserMouse extends WheelMouse{
	public LaserMouse() {	}
	@Override
	public void mouseMove() {}
}
public class Practice05 {

	public static void main(String[] args) {
		WheelMouse wm = new WheelMouse();
		LaserMouse lm = new LaserMouse();

		WheelMouse[] wm2 = new WheelMouse[3];
		wm2[0] = new WheelMouse();
		wm2[1] = new WheelMouse();
		wm2[2] = new WheelMouse();
		//1������
		String phoneNum = new String("010-1234-5678");
		System.out.println(phoneNum.substring(0,phoneNum.indexOf("-")));

		//2������
		System.out.println(phoneNum.replace("-",""));
		String[] s = phoneNum.split("-");
		for(int i=0;i<s.length;i++) {
			System.out.print(s[i]);
		}
		System.out.println();

		//3������
		String pNum = new String("E20160001");
		System.out.println(pNum.charAt(0));
		System.out.println(pNum.substring(0,1));

		//4������
		System.out.println(pNum.substring(1,5));

		//5������
		String info = new String("ȫ�浿,010-1111-2222,hkd@hkd.com");
		String[] newInfo = info.split(",");
		for(int i=0;i<newInfo.length;i++) {
			System.out.println(newInfo[i]);
		}

		//6������
		String data = "EL201800001,CH201800021,EN12231";
		String[] dep = data.split(","); 
		for(int i=0;i<dep.length;i++) {
			if(dep[i].contains("EL")==true) {
				System.out.println(dep[i]+": ���ڰ��а�");
			}
			else if(dep[i].contains("CH")==true) {
				System.out.println(dep[i]+": ȭ�а��а�");
			}
			else if(dep[i].contains("EN")==true) {
				System.out.println(dep[i]+": ������а�");
			}
		}

		//7������
		Scanner sc = new Scanner(System.in);
		System.out.println("���̵� �Է��ϼ���");
		Member admin = new Member(sc.nextLine());
		String id = admin.id;
		//=================================
		for(int i=0;i<id.length();i++) {
			char c = id.charAt(i);
			if(c == '!' || c == '@' || c == '#' || c == '$' || c == '%' || c == '^') {
				System.out.println("���̵� !,@,#,$,%,^�� ���ԵǸ� �ȵ˴ϴ�.");
				break;
			}		
		}
		//=====================================	
		
		admin.equals('!');
		admin.equals('@');
		admin.equals('#');
		admin.equals('$');
		admin.equals('%');
		admin.equals('^');
		
		if(id.contains("!")==true || id.contains("@")==true || id.contains("#")==true || id.contains("$")==true || id.contains("%")==true || id.contains("^")==true) {
			System.out.println("���̵� !,@,#,$,%,^�� ���ԵǸ� �ȵ˴ϴ�.");
		}




	}

}
