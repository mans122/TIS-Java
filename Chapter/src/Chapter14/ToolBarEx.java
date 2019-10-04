package Chapter14;
import javax.swing.*;
import java.awt.*;
public class ToolBarEx extends JFrame{
	private Container c;
	public ToolBarEx() {
		setTitle("����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c= getContentPane();
		createToolBar();
		setSize(400, 200);
		setVisible(true);
		
	}
	
	//���ٻ����ϰ� �������ҿ� ����
	private void createToolBar() {
		//���ٻ���
		JToolBar toolBar = new JToolBar("Kitae Menu");
		toolBar.setBackground(Color.LIGHT_GRAY);
		JButton btnNew=new JButton("New");
		toolBar.add(btnNew);
		//���ٿ� �޴��� ����� ������Ʈ ����
		toolBar.add(new JButton(new ImageIcon("images/open.jpg")));
		toolBar.addSeparator();
		toolBar.add(new JButton(new ImageIcon("images/save.jpg")));
		toolBar.add(new JLabel("search"));
		toolBar.add(new JTextField("text field"));
		JComboBox<String> combo = new JComboBox<String>();
		combo.addItem("java");
		combo.addItem("c#");
		combo.addItem("c");
		combo.addItem("c++");
		toolBar.add(combo);
		
		c.add(toolBar,BorderLayout.NORTH);
	}
	public static void main(String[] args) {
		new ToolBarEx();
	}

}
