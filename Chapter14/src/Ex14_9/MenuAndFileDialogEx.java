package Ex14_9;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class MenuAndFileDialogEx extends JFrame {
	private JLabel imgLabel = new JLabel();
	private JLabel label = new JLabel("Hello");
	private JPanel panel = new JPanel();
	public MenuAndFileDialogEx() {
		this.setTitle("Menu");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		panel.setLayout(new FlowLayout());
		c.setLayout(new BorderLayout()); 
		c.add(panel,BorderLayout.CENTER);//c�� CENTER�� �г� panel�� �÷��ش�.
		label.setFont(new Font("Gothic",Font.ITALIC,50));
		panel.add(label);
		panel.add(imgLabel);
		createMenu();
		//getContentPane()�����ϸ� ���������ӿ� �ٷ� �־��ְԵ�.
		//getContentPane().add(label);
		//getContentPane().add(imgLabel,BorderLayout.CENTER);
		setSize(600	, 600);
		setVisible(true);
	}
	//�޴� ����� �����ӿ� ����
	private void createMenu() {
		JMenuBar mb = new JMenuBar();
		JMenuItem[] menuItem = new JMenuItem[4];
		String[] itemTitle = {"����","Color","ReShow","Exit"};
		JMenu screenMenu = new JMenu("File");
		
		//4���� �޴��������� Screen �޴��� �����Ѵ�.
		MenuActionListener listener = new MenuActionListener();
		for(int i=0;i<menuItem.length;i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]);//�޴������� �̸��� itemTitle[i]�� �־��ش�
			menuItem[i].addActionListener(listener);//�޴������ۿ� �����ʸ� ����Ѵ�.
			screenMenu.add(menuItem[i]);//scrrenMenu�� ������� menuItem�� �־��ش�.
		}
		mb.add(screenMenu); //MenuBar mb�� ���� �־��� screenMenu�� �־��ش�.
		setJMenuBar(mb); //MenuBar mb�� �����ӿ� �����Ѵ�.
	}
	class MenuActionListener implements ActionListener{
		private JFileChooser chooser;
		public MenuActionListener() {
			chooser = new JFileChooser();
		}
		public void actionPerformed(ActionEvent e) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg","gif","png");
			chooser.setFileFilter(filter);
			
			String cmd = e.getActionCommand();
			switch(cmd) {
			case "����":
				int ret = chooser.showOpenDialog(null);
				if(ret !=JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null,"������ �������� �ʾҽ��ϴ�.","���",JOptionPane.WARNING_MESSAGE);
					return;
				}
				//���ϼ����ϰ� ���� ��ư ������ ���
				String filePath = chooser.getSelectedFile().getPath();//���ϰ�θ� ����
				imgLabel.setIcon(new ImageIcon(filePath));
				pack();
				break;
			case "Color":
				Color selectedColor = JColorChooser.showDialog(null, "Color", Color.YELLOW);
				if(selectedColor != null)
					label.setForeground(selectedColor);
				break;
			case "ReShow":
				imgLabel.setVisible(true);
				break;
			case "Exit":
				System.exit(0);
				break;
			}
		}
		
	}
	public static void main(String[] args) {
		new MenuAndFileDialogEx();
	}

}

