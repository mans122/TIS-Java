package Ex_2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

//BorderLayout���� Center�� �޴¸޽��� ���â
//SOUTH�� �г��ϳ� �ø��� ������ TextField2���� �г���,�����޽��� �Է�
//CENTER�� 
public class ChatServer extends JFrame implements ActionListener{
	public static ImageIcon[] image;
	private JLabel imageLabel = new JLabel();
	private BufferedReader in = null;
	private BufferedWriter out = null;
	private ServerSocket listener = null;
	private Socket socket = null;
	private Receiver receiver;
	private JTextField sender;
	private JTextField nickname=null;
	private JScrollPane	csPanel = new JScrollPane();
	private JPanel	cPanel = new JPanel();
	private JPanel	sPanel = new JPanel();
	private static String mynick = "����";
	private static String clientNick = "Ŭ���̾�Ʈ";
	private static String[] array= new String[3];
	public ChatServer() {
		setTitle("���� ä�� â");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		sPanel.setLayout(new BorderLayout());
		File f1 = new File("img");//���ϴ� �������� ������ ������������ ���� ��� ����
		ImgFolder.listDirectory(f1);	//ImgFolder Ŭ������ listDirectory �޼ҵ�� �Ű����� f1�� �־���	
		image = new ImageIcon[ImgFolder.fileNum];
		for(int i=0;i<ImgFolder.fileNum;i++) {
			image[i] = new ImageIcon(ImgFolder.fileName.get(i));
		}
		cPanel.setLayout(new GridLayout(4,1,100,0));
		cPanel.setBackground(Color.GREEN);
		JButton bt1 = new JButton("�׽�Ʈ");
		JLabel text1 = new JLabel();
		JLabel text2 = new JLabel();
		JLabel text3 = new JLabel();
		System.out.println(bt1.getPreferredSize().width);
		int x = bt1.getPreferredSize().width;
		System.out.println(bt1.getPreferredSize().height);
		int y = bt1.getPreferredSize().height;
		bt1.setPreferredSize(new Dimension(x, y));
		bt1.setSize(new Dimension(x, y));
		bt1.setSize(x, y);
		bt1.setSize(200, 200);
		
		text1.setText("�����ٶ󸶹ٻ�");
		text2.setText("�׽�Ʈ2");
		text3.setText("�׽�Ʈ3");
		text1.setFont(new Font("Gothic",Font.ITALIC,15));
		text2.setFont(new Font("Gothic",Font.ITALIC,15));
		cPanel.add(bt1);
		cPanel.add(text1);
		cPanel.add(text2);
		cPanel.add(text3);
		
		//c.add(cPanel,BorderLayout.CENTER);
		
		
		//�޴� �޽��� �����ִ� â
		receiver = new Receiver();
		receiver.setEditable(false);
		
		
		//�г��� ��� â
		nickname = new JTextField(5);
		nickname.setText("�г���");
		nickname.addActionListener(this);
		sPanel.add(nickname,BorderLayout.WEST);
		//-------------------------------------------------------------------------------
		
		//������ �޽��� �Է�â
		sender = new JTextField(20);
		sender.addActionListener(this);
		sPanel.add(sender,BorderLayout.CENTER);
		c.add(sPanel,BorderLayout.SOUTH);
		//-------------------------------------------------------------------------------
		
		csPanel = new JScrollPane(cPanel);
		c.add(csPanel,BorderLayout.CENTER);
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
	private void setupConnection() throws IOException{
		listener = new ServerSocket(9999);
		socket = listener.accept();
		receiver.append("Ŭ���̾�Ʈ�κ��� ���� �Ϸ�");
		int pos = receiver.getText().length();
		receiver.setCaretPosition(pos);

		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	}
//------------------------------------------------�ڵ鿡��------------------------------------------------------
	private static void handleError(String string) {
		System.out.println(string);
		System.exit(1);
	}
//-----------------------------------------------------------------------------------------------------------
	
//------------------------------------------------���� �޽��� �����ִ� Receiver------------------------------------------------------
	private class Receiver extends JTextArea implements Runnable{
		@Override
		public void run() {
			String msg = null;
			while(true) {
				try {
					msg = in.readLine();
					if(msg.contains("������ �г�����")) {
						array = msg.split("'");
						clientNick = array[1];
					}
				}catch(IOException e) {
					handleError(e.getMessage());
				}
				this.append("\n"+clientNick+" : "+msg);
				int pos = this.getText().length();
				this.setCaretPosition(pos);
			}
		}
	}
	//-----------------------------------------------------------------------------------------------------------
	
	//------------------------------------------------�޽��� ������------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sender) {
			String msg = sender.getText();
			try {
				if(msg.contains("@@")) {
					int index = msg.indexOf("@@");
					String getIcon = msg.substring(index+2,index+7);
					//System.out.println(getIcon);
					for(int i=0;i<ImgFolder.fileNum;i++) {
						if(ImgFolder.fileName.get(i).contains(getIcon)) {
							//System.out.println(ImgFolder.fileName.get(i));
							imageLabel.setIcon(new ImageIcon(ImgFolder.fileName.get(i)));
							System.out.println(ImgFolder.fileName.get(i));
							int pos = receiver.getText().length();
							receiver.setCaretPosition(pos);
							sender.setText(null);
						}
					}
					
				}
				else{
				out.write(msg+"\n");
				out.flush();

				receiver.append("\n"+mynick+": "+msg);
				int pos = receiver.getText().length();
				receiver.setCaretPosition(pos);
				sender.setText(null);
				}
			}catch(IOException e1) {
				handleError(e1.getMessage());
			}
		}else if(e.getSource() == nickname) {
			mynick = nickname.getText();
			try {
				receiver.append("\n�г����� '"+mynick+"'���� ����Ǿ����ϴ�.");
				int pos = receiver.getText().length();
				receiver.setCaretPosition(pos);
				out.write("������ �г����� \'"+mynick+"\'(��)�� ����Ǿ����ϴ�.\n");
				out.flush();
			}catch(Exception e2) {
				handleError(e2.getMessage());
			}
		}
	}
//------------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		new ChatServer();
	}
}
