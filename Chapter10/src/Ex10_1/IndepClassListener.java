package Ex10_1;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class IndepClassListener extends JFrame{
	public IndepClassListener() {
		setTitle("Action �̺�Ʈ ������ ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		JButton btn = new JButton("Action");
		MyActionListener ma = new MyActionListener();
		btn.addActionListener(ma);
		c.add(btn);
		
		setSize(350,150);
		setVisible(true);
	}
	public static void main(String[] args) {
		new IndepClassListener();
	}

}
