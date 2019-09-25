import java.io.*;
import java.net.*;
import java.util.*;
public class ServerEx {
	public static void main(String[] args) {
		BufferedReader in = null;
		BufferedWriter out = null;
		ServerSocket listener = null;
		Socket socket = null;
		Scanner s = new Scanner(System.in);
		
		try {
			listener = new ServerSocket(9999);
			System.out.println("���ɴ����");
			socket = listener.accept();
			System.out.println("�����");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while(true) {
				String inputMessage = in.readLine();
				if(inputMessage.equalsIgnoreCase("bye")) {
					System.out.println("Ŭ�󿡼� bye�� ��������");
					break;
				}
				System.out.println("Ŭ���̾�Ʈ : "+inputMessage);
				System.out.print("������>>");
				String outputMessage = s.nextLine();
				out.write(outputMessage + "\n");
				out.flush();
			}
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				s.close();
				socket.close();
				listener.close();
			}
			catch(IOException e) {
				System.out.println("����� �����߻�");
			}
		}
	}

}
