package Chapter15;
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
			System.out.println("연걸대기중");
			socket = listener.accept();
			System.out.println("연결됨");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while(true) {
				String inputMessage = in.readLine();
				if(inputMessage.equalsIgnoreCase("bye")) {
					System.out.println("클라에서 bye로 연결종료");
					break;
				}
				System.out.println("클라이언트 : "+inputMessage);
				System.out.print("보내기>>");
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
				System.out.println("통신중 오류발생");
			}
		}
	}

}
