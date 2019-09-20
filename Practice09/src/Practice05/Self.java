package Practice05;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
class ImgFolder {
	public static ArrayList<String> fileName = new ArrayList<>();
	public static int fileNum;
	
	//img�������� ��θ� fileName��,���� ������ fileNum�� �Է�
	public static void listDirectory(File dir) {
		File[] subFiles = dir.listFiles();
		for(int i=0;i<subFiles.length;i++) {
			File f = subFiles[i];
			fileName.add(i,f.getPath());
			System.out.println(fileName.get(i));//���ϰ�ΰ� �� ������ Ȯ���ϱ����� �ڵ�
		}
		fileNum = fileName.size();
		System.out.println(fileNum); //�������� ���� �Ѱ����� �� ������ Ȯ���ϱ����� �ڵ�
	}
}


public class Self extends JFrame{
	private int index =0;
	private JButton btnLeft;
	private JButton btnRight;
	public ImageIcon[] image;
	public static JLabel imageLabel = new JLabel();
	//public static ImageIcon[] fruitImage = new ImageIcon[4];
	public Self() {
		File f1 = new File("c:/myPhoto");//���ϴ� �������� ������ ������������ ���� ��� ����
		ImgFolder.listDirectory(f1);	//ImgFolder Ŭ������ listDirectory �޼ҵ�� �Ű����� f1�� �־���	
		image = new ImageIcon[ImgFolder.fileNum];//�̹��� �迭 ������ ImgFolderŬ�������� ���� fileNum���� �־��ش�.

		setTitle("�������� 09 5������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER,0,30));
		createMenu();//�޴� ����� �޼ҵ� ȣ��
		//image[]�� ������ �ִ� �ݺ���. ��δ� ImgFolderŬ�������� fileName�� �޾ƿ´�.
		for(int i=0;i<ImgFolder.fileNum;i++) {
			image[i] = new ImageIcon(ImgFolder.fileName.get(i));
		}
		imageLabel.setIcon(image[0]);//�̹��� �ڸ��� ��ó�� index 0�� image �÷��д�.
		//�̹����� ���� �г� �����ϰ� imgPanel�� ���������ӿ� �ø��� imgPanel�� �̹����� �ø���
		JPanel imgPanel = new JPanel();
		imgPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,15));
		imgPanel.setBackground(Color.WHITE);
		imgPanel.setPreferredSize(new Dimension(550,550));
		c.add(imgPanel);
		imgPanel.add(imageLabel);
		
		//�¿��ư�� ���� �г� ����
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
	//�޴��� ����� �޼ҵ�
	private void createMenu() {
		JMenuBar mb = new JMenuBar();
		JMenuItem[] menuItem = new JMenuItem[4];
		String[] itemTitle = {"apple","banana","kiwi","mango"};
		JMenu screenMenu = new JMenu("File");

		MenuActionListener listener = new MenuActionListener();
		for(int i=0;i<menuItem.length;i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]);
			menuItem[i].addActionListener(listener);
			screenMenu.add(menuItem[i]);
		}
		mb.add(screenMenu);
		setJMenuBar(mb);
	}

	//�׼Ǹ����� ��ӹ޴� Ŭ����  ����
	class MyItemListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnLeft) {
				index --;//����ȭ��ǥ ��ư ���� �� index���� ����
				if(index >= 0) {//index�� 0���� ũ�ų� ������ �״�� �־���
					imageLabel.setIcon(image[index]);
				}
				else {
					index = image.length-1;//index�� 0���� ������ image�� ����-1�� ���� ��������, �� �ǵڷ� ���ư�
					imageLabel.setIcon(image[index]);	
				}
			}
			else {
				index ++;
				if(index <= image.length-1) {
					imageLabel.setIcon(image[index]);
				}
				else {
					index = 0;//index�� image�� �������� Ŀ���� ���� 0���κ���,�� �Ǿ����� ���ư�
					imageLabel.setIcon(image[index]);	
				}
			}
		}
	}
	public static void main(String[] args) {
		new Self();
	}

}
