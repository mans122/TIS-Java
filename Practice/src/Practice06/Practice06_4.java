package Practice06;
import java.io.*;
public class Practice06_4 {
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("unused")
		PrintStream ps= null;
		FileWriter error = null;
/*
		try {
			ps = new PrintStream("C:\\\\log\\\\20190305153010.log");
			//Exception e = new Exception();
			System.out.println(1/0);
			
		}catch(Exception e) {
			System.out.println("�����߻�!!\n c:\\log\\20190305153010.log�� ����մϴ�. ");
			e.printStackTrace(ps);
		}
*/
		try {
			Exception e = new Exception();
			System.out.println(1/0);
			throw e;
			
		}catch(Exception e) {
			System.out.println("�����߻�!!\n c:\\log\\20190305153010.log�� ����մϴ�. ");
			error = new FileWriter("C:\\log\\20190305153010.log");
			//error.write(e.getMessage()); 	// "/ by zero" ���
			error.write(e.toString());		// "java.lang.ArithmeticException : / by zero" ���
			error.close();
		}
		
	}

}