package Chapter14;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuActionEventEx extends JFrame {
	private JLabel imgLabel = new JLabel();
	public MenuActionEventEx() {
		this.setTitle("Menu");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createMenu();
		getContentPane().add(imgLabel,BorderLayout.CENTER);
		setSize(600	, 600);
		setVisible(true);
	}
	//�޴� ����� �����ӿ� ����
	private void createMenu() {
		JMenuBar mb = new JMenuBar();
		JMenuItem[] menuItem = new JMenuItem[4];
		String[] itemTitle = {"Load","Hide","ReShow","Exit"};
		JMenu screenMenu = new JMenu("File");
		
		//4���� �޴��������� Screen �޴��� �����Ѵ�.
		MenuActionListener listener = new MenuActionListener();
		for(int i=0;i<menuItem.length;i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]);
			menuItem[i].addActionListener(listener);
			screenMenu.add(menuItem[i]);
		}
		mb.add(screenMenu);
		setJMenuBar(mb);
	}
	class MenuActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			switch(cmd) {
			case "Load":
				if(imgLabel.getIcon()!=null) {return;}
				else {imgLabel.setIcon(new ImageIcon("img/img.png"));}
				break;
			case "Hide":
				imgLabel.setVisible(false);
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
		new MenuActionEventEx();
	}

}

