package Chapter15;
import java.io.*;
import java.net.*;
import java.util.*;
public class ClientEx {
	public static void main(String[] args) {
		BufferedReader in = null;
		BufferedWriter out = null;
		Socket socket = null;
		Scanner s = new Scanner(System.in);
		
		try {
			socket = new Socket("192.168.0.56",9999);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while(true) {
				System.out.print("������>>");
				String outputMessage = s.nextLine();
				if(outputMessage.equalsIgnoreCase("bye")) {
					out.write(outputMessage + "\n");
					out.flush();
					break;
				}
				out.write(outputMessage + "\n");
				out.flush();
				String inputMessage = in.readLine();
				System.out.println("���� :"+inputMessage);
			}
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				s.close();
				if(socket != null)
					socket.close();
			}
			catch(IOException e) {
				System.out.println("����� �����߻�");
			}
		}
	}

}
