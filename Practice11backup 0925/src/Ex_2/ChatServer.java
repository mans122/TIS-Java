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

//JScrollPanel.getVerticalScrollBar().setValue(JScrollPanel.getVerticalScrollBar().getMaximum());
public class ChatServer extends JFrame implements ActionListener{
	public static ImageIcon[] image;
	private static int sendCount = 0;
	private static int receiveCount = 0;
	private JLabel imageLabel = new JLabel();
	private BufferedReader in = null;
	private BufferedWriter out = null;
	private ServerSocket listener = null;
	private Socket socket = null;
	private Receiver receiver = null;
	private JTextField sender = null;
	private JTextField nickname=null;
	private JScrollPane	csPanel = new JScrollPane();
	private static JPanel cPanel = new JPanel();
	private JPanel	sPanel = new JPanel();
	private static String mynick = "����";
	private static String clientNick = "Ŭ���̾�Ʈ";
	private static String[] array= new String[3];
	private static JLabel[] sendText = new JLabel[100];
	private static JLabel[] receiveText = new JLabel[100];
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
//		cPanel.setLayout(new GridLayout(5,1,200,0));
//		cPanel.setBackground(Color.GREEN);
		
		for(int i=0; i<100;i++) {
			sendText[i] = new JLabel();
			receiveText[i] = new JLabel();
		}
		cPanel = new JPanel(null) {
			public void paintComponent(Graphics g) {
				if(sendCount+receiveCount<=7)
					cPanel.setLayout(new GridLayout(7,1,200,0));
				else
					cPanel.setLayout(new GridLayout(sendCount+receiveCount,1,200,0));
				c.add(csPanel,BorderLayout.CENTER);
				csPanel.getVerticalScrollBar().setValue(csPanel.getVerticalScrollBar().getMaximum());
				this.setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		
		//�޴� �޽��� �����ִ� â
		receiver = new Receiver();
		//receiver.setEditable(false);
		
		
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
		cPanel.add(receiveText[receiveCount]);
		receiveText[receiveCount].setText("Ŭ��κ��� ����Ϸ�");
		receiveText[receiveCount].requestFocus();
		receiveCount++;
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
	private class Receiver extends JFrame implements Runnable{
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
				cPanel.add(receiveText[receiveCount]);
				receiveText[receiveCount].setText("\n"+clientNick+" :"+msg);
				receiveCount++;
				csPanel.getVerticalScrollBar().setValue(csPanel.getVerticalScrollBar().getMaximum());
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
							sender.setText(null);
						}
					}
				}
				else{
				out.write(msg+"\n");
				out.flush();
				
				cPanel.add(sendText[sendCount]);				
				sendText[sendCount].setText("\n"+mynick+": "+msg);
				sendCount++;
				csPanel.getVerticalScrollBar().setValue(csPanel.getVerticalScrollBar().getMaximum());
				sender.setText(null);
				}
			}catch(IOException e1) {
				handleError(e1.getMessage());
			}
		}else if(e.getSource() == nickname) {
			mynick = nickname.getText();
			try {
				cPanel.add(sendText[sendCount]);
				sendText[sendCount].setText("\n�г����� '"+mynick+"'���� ����Ǿ����ϴ�.");
				sendCount++;
				csPanel.getVerticalScrollBar().setValue(csPanel.getVerticalScrollBar().getMaximum());
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
