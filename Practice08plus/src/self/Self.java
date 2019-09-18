package self;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

class ImgFolder {
	public static ArrayList<String> fileName = new ArrayList<>();
	public static int fileNum;
	public static String url;
	public static String[] str;
	public static void listDirectory(File dir) {
		File[] subFiles = dir.listFiles();
		for(int i=0;i<subFiles.length;i++) {
			File f = subFiles[i];
			ImgFolder.fileName.add(i,f.getPath());
			System.out.println(ImgFolder.fileName.get(i));
		}
		ImgFolder.fileNum = fileName.size();
		System.out.println(ImgFolder.fileNum);
	}
	public ImgFolder() {}
}


public class Self extends JFrame{
	private int index =0;
	private JButton btnLeft;
	private JButton btnRight;
	public ImageIcon[] image;
	private JLabel imageLabel = new JLabel();
	
	public Self() {
		File f1 = new File("c:/myPhoto");//���ϴ� �������� ������ ������������ ���� ��� ����
		ImgFolder.listDirectory(f1);	//ImgFolder Ŭ������ listDirectory �޼ҵ�� �Ű����� f1�� �־���	
		image = new ImageIcon[ImgFolder.fileNum];//�̹��� �迭 ������ ImgFolderŬ�������� ���� fileNum���� �־��ش�.
		
		setTitle("�������� 08");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER,0,30));
		//image[]�� ������ �ִ� �ݺ���. ��δ� ImgFolderŬ�������� fileName�� �޾ƿ´�.
		for(int i=0;i<ImgFolder.fileNum;i++) {
			image[i] = new ImageIcon(ImgFolder.fileName.get(i));
		}
		imageLabel.setIcon(image[0]);//�̹��� �ڸ��� ��ó�� index 0�� image �÷��д�.
		JPanel imgPanel = new JPanel();
		imgPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,15));
		imgPanel.setBackground(Color.WHITE);
		imgPanel.setPreferredSize(new Dimension(550,550));
		c.add(imgPanel);
		imgPanel.add(imageLabel);
		
		JPanel iconPanel = new JPanel();
		iconPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,30));
		iconPanel.setBackground(Color.GREEN);
		iconPanel.setPreferredSize(new Dimension(550,200));
		c.add(iconPanel);
		//�¿� ȭ��ǥ �̹��� ����
		ImageIcon previous = new ImageIcon("img/previous.png");
		ImageIcon next = new ImageIcon("img/next.png");
		this.btnLeft = new JButton(previous);
		this.btnRight = new JButton(next);
		//iconPanel�� �¿� ��ư ���
		iconPanel.add(btnLeft);
		iconPanel.add(btnRight);
		//�¿��ư ActionListener�� ���
		btnLeft.addActionListener(new MyItemListener());
		btnRight.addActionListener(new MyItemListener());

		setSize(550,850);
		setVisible(true);
	}

	//�׼Ǹ����� ��ӹ޴� Ŭ����  ����
	class MyItemListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnLeft) {
				index --;
				if(index >= 0) {
					imageLabel.setIcon(image[index]);
				}
				else {
					index = image.length-1;
					imageLabel.setIcon(image[index]);	
				}
			}
			else {
				index ++;
				if(index <= image.length-1) {
					imageLabel.setIcon(image[index]);
				}
				else {
					index = 0;
					imageLabel.setIcon(image[index]);	
				}

			}
		}
	}
	public static void main(String[] args) {
		new Self();
	}

}
