package Ex5_8;
interface PhoneInterface{	//�������̽� ����
	public static final int TIMEOUT = 10000;	//����ʵ� ����
	public abstract void sendCall();			//�߻�޼ҵ�
	public abstract void receiveCall();			//�߻�޼ҵ�
	public default void printLogo() {			//dafault�޼ҵ�
		System.out.println("**Phone**");
	}
}

class SamsungPhone implements PhoneInterface{
	//PhoneInterface�� ��� �߻�޼ҵ� ����

	public void flash() {System.out.println("������");}
	@Override
	public void sendCall() {System.out.println("�츮������");}

	@Override
	public void receiveCall() {System.out.println("��ȭ�Խ��ϴ�.");	}
}

public class InterfaceEx {

	public static void main(String[] args) {
		SamsungPhone phone = new SamsungPhone();
		phone.printLogo();
		phone.sendCall();
		phone.flash();
		phone.receiveCall();
	}

}
