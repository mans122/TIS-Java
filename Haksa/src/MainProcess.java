import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainProcess{
	Login login;
	Haksa haksa;
	
	public static void main(String[] args) {
		
		// 메인클래스 실행
		MainProcess main = new MainProcess();
		main.login = new Login(); //로그인창 보이기
		main.login.setMain(main); // 로그인창에게 메인클래스 보내기
		
		main.login.setTitle("학사관리 로그인");
		main.login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.login.setSize(1000, 600);
		Dimension frameSize = main.login.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		main.login.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
		main.login.setResizable(false);
		main.login.setVisible(true);
	}
	
	// �뀒�뒪�듃�봽�젅�엫李�
	public void showFrameTest(){
		login.dispose(); // 로그인창 닫기
		this.haksa = new Haksa(); // 테스트프레임 오픈
	}

}
