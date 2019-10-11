import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BookAdd extends JFrame {
	DefaultTableModel model;
	JTable table;
	static JTextField[] tf_add = new JTextField[3];
	public BookAdd() {
		JFrame f = new JFrame();
		f.setTitle("���� �߰�");
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel title = new JLabel("���� �߰�");
		title.setFont(new Font("Gothic",Font.BOLD,30));
		title.setBounds(80,0, 200, 40);

		JLabel bookNo = new JLabel("å ��ȣ");
		bookNo.setFont(new Font("Gothic",Font.BOLD,15));
		bookNo.setBounds(20, 70, 80, 30);

		JLabel bookName = new JLabel("å �̸�");
		bookName.setFont(new Font("Gothic",Font.BOLD,15));
		bookName.setBounds(20, 120, 80, 30);

		JLabel bookWriter = new JLabel("������");
		bookWriter.setFont(new Font("Gothic",Font.BOLD,15));
		bookWriter.setBounds(20, 170, 80, 30);

		for(int i=0;i<3;i++) {
			tf_add[i] = new JTextField();
			tf_add[i].setBounds(80, 70+(i*50), 170, 30);
			f.add(tf_add[i]);			
		}

		JButton submit = new JButton("���");
		submit.setBounds(110, 220, 80, 30);
		MyActionListener ma = new MyActionListener();
		submit.addActionListener(ma);
		
		String colName[]={"å ��ȣ","������","����"};
		model=new DefaultTableModel(colName,0);
		table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.setPreferredScrollableViewportSize(new Dimension(480,200));
		f.add(table);
		JScrollPane sp=new JScrollPane(table);
		sp.setBounds(270, 40, 300, 300);
		f.add(sp); 
		bookList();

		f.add(submit);
		f.add(bookWriter);
		f.add(bookName);
		f.add(bookNo);
		f.add(title);
		f.setSize(600, 400);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation((screenSize.width-f.getPreferredSize().width)/2,(screenSize.height-f.getPreferredSize().height)/2);
		f.setVisible(true);
	}

	class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String no = tf_add[0].getText();
			String name =tf_add[1].getText();
			String writer =tf_add[2].getText();
			boolean isNumber = false;
			
			if(no.length()==0) {
				System.out.println(no.length());
					JOptionPane.showMessageDialog(null,"å ��ȣ�� �Է��ϼ���","���",JOptionPane.WARNING_MESSAGE);
			}
			else {
				System.out.println("dd");
				if (isStringInt(no))
					isNumber=true;
				else
					isNumber=false;
				
				if(no.length()==6 && isNumber==true) {
					try {
						DBManager.stmt.executeUpdate("insert into books values('"+no+"','"+name+"','"+writer+"')");
						JOptionPane.showMessageDialog(null,"��ϵǾ����ϴ�.","�˸�",JOptionPane.INFORMATION_MESSAGE);
						for(int i=0;i<3;i++) {
							tf_add[i].setText("");
						}
						bookList();
					}
					catch(Exception ee) {
						JOptionPane.showMessageDialog(null,"�̹� �����ϴ� å�Դϴ�.","���",JOptionPane.WARNING_MESSAGE);
						ee.printStackTrace();
					}
				}
				else if(isNumber == false) {
					JOptionPane.showMessageDialog(null,"å��ȣ�� ���ڸ� �Է����ּ���","���",JOptionPane.WARNING_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null,"å ��ȣ�� 6�ڸ��� �Է����ּ���","���",JOptionPane.WARNING_MESSAGE);}
			}
		}
	}
	public static boolean isStringInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public void bookList(){
		try{
			// Select�� ����     
			ResultSet rs= DBManager.stmt.executeQuery("select * from books");
			model.setNumRows(0);
			
			while(rs.next()){
				String[] row=new String[3];//�÷��� ������ 4
				row[0]=rs.getString("no");
				row[1]=rs.getString("title");
				row[2]=rs.getString("author");
				model.addRow(row);
			}
			rs.close();
		}
		catch(Exception e1){
			System.out.println(e1.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new BookAdd();
	}

}
