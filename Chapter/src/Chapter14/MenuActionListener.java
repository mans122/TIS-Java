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

	public class MenuActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			switch(cmd) {
			case "�л����":
				System.out.println("�л����");
				break;
			case "Hide":
				System.out.println("���̵�");
				break;
			case "ReShow":
				break;
			case "Exit":
				System.exit(0);
				break;
			}
		}
		
	public static void main(String[] args) {
	}

}

