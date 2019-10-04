package Practice11;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.BevelBorder;

//BorderLayout���� Center�� �޴¸޽��� ���â
//SOUTH�� �г��ϳ� �ø��� ������ TextField2���� �г���,�����޽��� �Է�
//CENTER�� 
//JLabel �׵θ�����
//BevelBorder border = new BevelBorder(BevelBorder.RAISED); 
//sText.get(sendCount).setBorder(border); 3����
//eborder=new EtchedBorder(EtchedBorder.RAISED);//��鿡 ���� �ǵ��� �ܰ��� ȿ���� ���� ���̰� �簢�� ȿ���� �ش�.
//label.setBorder(eborder);��鿡 ���� �ǵ��� �ܰ��� ȿ��
//JScrollPanel.getVerticalScrollBar().setValue(JScrollPanel.getVerticalScrollBar().getMaximum());
public class ChatServer extends JFrame implements ActionListener{
	public static ImageIcon[] image;
	private static int sendCount = 0;
	private static int receiveCount = 0;
	private static int imgCount = 0;
	private static int i,k;
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
	private static ArrayList<JLabel> rText = new ArrayList<>();
	private static ArrayList<JLabel> sText = new ArrayList<>();
	public ChatServer() {
		ImageIcon icon = new ImageIcon("img/bgi.jpg");

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
		cPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,5)) {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				System.out.println("d"+(25*(sendCount+receiveCount)+(12*imgCount)));
				System.out.println("a"+cPanel.getPreferredSize().getSize().height);

				rText.add(receiveCount,new JLabel());
				rText.get(receiveCount).setFont(new Font("�ü�", Font.BOLD, 15));
				rText.get(receiveCount).setPreferredSize(new Dimension(cPanel.getSize().width-10,20));

				sText.add(sendCount,new JLabel());
				sText.get(sendCount).setFont(new Font("���� ���", Font.BOLD, 15));
				sText.get(sendCount).setHorizontalAlignment(JLabel.RIGHT);
				sText.get(sendCount).setPreferredSize(new Dimension(cPanel.getSize().width-10,20));
				this.setPreferredSize(new Dimension(csPanel.getSize().width-20,25*(2+sendCount+receiveCount)+(12*imgCount)));

				//csPanel.getVerticalScrollBar().setValue(csPanel.getVerticalScrollBar().getMaximum());
				this.setOpaque(false);
				super.paintComponent(g);
			}
		};


		//�޴� �޽��� �����ִ� â
		receiver = new Receiver();


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

		//��ũ���гο� cPanel�߰��ϰ� csPanel�߰�
		//csPanel = new JScrollPane(cPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		csPanel = new JScrollPane(cPanel);
		c.add(csPanel,BorderLayout.CENTER);
		setSize(400, 500);
		setResizable(false);
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
		cPanel.add(rText.get(receiveCount));
		rText.get(receiveCount).setHorizontalAlignment(JLabel.CENTER);
		rText.get(receiveCount).setForeground(Color.CYAN);
		rText.get(receiveCount).setText("Ŭ���̾�Ʈ�� ����Ǿ����ϴ�.");
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
					if(msg.contains("�г���")) {
						rText.get(receiveCount).setHorizontalAlignment(JLabel.CENTER);
					}
					cPanel.add(rText.get(receiveCount));
					rText.get(receiveCount).setLayout(new FlowLayout(FlowLayout.RIGHT));
					rText.get(receiveCount).setText("\n"+msg);
					csPanel.getVerticalScrollBar().setValue(csPanel.getVerticalScrollBar().getMaximum());
					receiveCount++;
				}catch(IOException e) {
					handleError(e.getMessage());
				}
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
					for(int i=0;i<ImgFolder.fileNum;i++) {
						if(ImgFolder.fileName.get(i).contains(getIcon)) {
							//System.out.println(ImgFolder.fileName.get(i));
							//imageLabel.setIcon(new ImageIcon(ImgFolder.fileName.get(i)));
							cPanel.add(sText.get(sendCount));
							sText.get(sendCount).setIcon(new ImageIcon(ImgFolder.fileName.get(i)));
							sText.get(sendCount).setHorizontalAlignment(JLabel.RIGHT);
							sText.get(sendCount).setPreferredSize(new Dimension(cPanel.getSize().width-10,32));
							imgCount++;
							sendCount++;
							sender.setText(null);
						}
					}
				}
				else{
					out.write(mynick+" >> "+msg+"\n");
					out.flush();
					cPanel.add(sText.get(sendCount));
					sText.get(sendCount).setText("\n"+msg);
					csPanel.getVerticalScrollBar().setValue(csPanel.getVerticalScrollBar().getMaximum());
					sendCount++;
					sender.setText(null);
				}
			}catch(IOException e1) {
				handleError(e1.getMessage());
			}
		}else if(e.getSource() == nickname) {
			mynick = nickname.getText();
			try {
				cPanel.add(sText.get(sendCount));
				sText.get(sendCount).setHorizontalAlignment(JLabel.CENTER);
				sText.get(sendCount).setText("\n�г����� '"+mynick+"'���� ����Ǿ����ϴ�.");
				sendCount++;
				out.write("������ �г����� \'"+mynick+"\'(��)�� ����Ǿ����ϴ�.\n");
				out.flush();
			}catch(Exception e2) {
				handleError(e2.getMessage());
			}
			finally {csPanel.getVerticalScrollBar().setValue(csPanel.getVerticalScrollBar().getMaximum());}
		}
	}
	//------------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		new ChatServer();
	}
}
