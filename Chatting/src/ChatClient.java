import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.border.Border;
public class ChatClient extends JFrame implements ActionListener {
	private BufferedReader in = null;
	private BufferedWriter out = null;
	private Socket socket = null;
	private Receiver receiver = null;
	private JTextField sender = null;
	public ChatClient() {
		setTitle("Ŭ���̾�Ʈ ä��â");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();

		c.setLayout(new BorderLayout());
		receiver = new Receiver();
		receiver.setEditable(false);

		sender = new JTextField();
		sender.addActionListener(this);

		c.add(new JScrollPane(receiver),BorderLayout.CENTER);
		c.add(sender,BorderLayout.SOUTH);

		setSize(400, 200);
		setVisible(true);

		try {
			setupConnection();
		}catch(IOException e) {
			handleError(e.getMessage());
		}
		Thread th = new Thread(receiver);
		th.start();

	}

	public void setupConnection() throws IOException{
		socket = new Socket("localhost",9999);
		receiver.append("������ ���� �Ϸ�");
		int pos = receiver.getText().length();
		receiver.setCaretPosition(pos);

		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	}

	private static void handleError(String string) {
		System.out.println(string);
		System.exit(1);
	}

	private class Receiver extends JTextArea implements Runnable{
		@Override
		public void run() {
			String msg = null;
			while(true) {
				try {
					msg = in.readLine();
				}catch(IOException e) {
					handleError(e.getMessage());
				}
				this.append("\n ���� :"+msg);
				int pos = this.getText().length();
				this.setCaretPosition(pos);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sender) {
			String msg = sender.getText();
			try {
				out.write(msg+"\n");
				out.flush();

				receiver.append("\n Ŭ���̾�Ʈ: "+msg);
				int pos = receiver.getText().length();
				receiver.setCaretPosition(pos);
				sender.setText(null);
			}catch(IOException e1) {
				handleError(e1.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		new ChatClient();
	}

}
