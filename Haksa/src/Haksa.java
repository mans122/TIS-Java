import javax.swing.*;
import java.awt.*;
//��,��ư,�г� � �е���
//label.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0 , 0));

public class Haksa extends JFrame {
	public Haksa() {
		setTitle("�л����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));

		//tf_id = �й�
		c.add(new JLabel("  �й�  "));
		JTextField tf_id=new JTextField(24);
		c.add(tf_id);

		//tf_name = �̸�
		c.add(new JLabel("  �̸�  "));
		JTextField tf_name=new JTextField(24);
		c.add(tf_name);

		//tf_dep = �й�
		c.add(new JLabel("  �а�  "));
		JTextField tf_dep=new JTextField(24);
		c.add(tf_dep);

		//tf_address = �й�
		c.add(new JLabel("  �ּ�  "));
		JTextField tf_address=new JTextField(24);
		c.add(tf_address);
		
		JTextArea taList=new JTextArea(15,28);
		JScrollPane sp = new JScrollPane(taList);
		c.add(sp);
		
		JButton btnInsert = new JButton("���");
		JButton btnList = new JButton("���");
		JButton btnUpdate = new JButton("����");
		JButton btnDelete = new JButton("����");
		btnInsert.setPreferredSize(new Dimension(74,30));
		btnList.setPreferredSize(new Dimension(74,30));
		btnUpdate.setPreferredSize(new Dimension(74,30));
		btnDelete.setPreferredSize(new Dimension(74,30));
		c.add(btnInsert);
		c.add(btnList);
		c.add(btnUpdate);
		c.add(btnDelete);
		
		taList.setEditable(false);
		taList.append("============================================\n");
		taList.append("  �й�	�̸�	�а�	�ּ�\n");
		taList.append("============================================\n");

		
		createMenu();
		setSize(335,480);
		setResizable(false);
		setVisible(true);
	}
	private void createMenu() {
		JMenuBar mb = new JMenuBar();
		JMenuItem[] menuItem = new JMenuItem[4];
		String[] itemTitle= {"Open","Save","aaa","dd"};
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
	
	public static void main(String[] args) {
		new Haksa();
	}

}
