import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	static JTextField[] tf_add = new JTextField[4];
	public BookAdd() {
		JFrame f = new JFrame();
		f.setTitle("���� �߰�");
		f.setLayout(null);
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel title = new JLabel("���� ����");
		title.setFont(new Font("Gothic",Font.BOLD,30));
		title.setBounds(50,0, 200, 40);

		JLabel bookNo = new JLabel("������ȣ");
		bookNo.setFont(new Font("Gothic",Font.BOLD,15));
		bookNo.setBounds(20, 60, 80, 30);

		JLabel bookName = new JLabel("������");
		bookName.setFont(new Font("Gothic",Font.BOLD,15));
		bookName.setBounds(20, 100, 80, 30);

		JLabel bookWriter = new JLabel("������");
		bookWriter.setFont(new Font("Gothic",Font.BOLD,15));
		bookWriter.setBounds(20, 140, 80, 30);

		JLabel author = new JLabel("���ǻ�");
		author.setFont(new Font("Gothic",Font.BOLD,15));
		author.setBounds(20, 180, 80, 30);

		for(int i=0;i<4;i++) {
			tf_add[i] = new JTextField();
			tf_add[i].setBounds(90, 60+(i*40), 120, 28);
			f.add(tf_add[i]);			
		}

		JButton submit = new JButton("���");
		submit.setBounds(10, 230, 60, 30);
		JButton update = new JButton("����");
		update.setBounds(80, 230, 60, 30);
		JButton delete = new JButton("����");
		delete.setBounds(150, 230, 60, 30);
		MyActionListener ma = new MyActionListener();
		submit.addActionListener(ma);
		update.addActionListener(ma);
		delete.addActionListener(ma);

		String colName[]={"���� ��ȣ","������","����","���ǻ�"};
		model=new DefaultTableModel(colName,0);
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(480,200));
		f.add(table);
		JScrollPane sp=new JScrollPane(table);
		sp.setBounds(230, 10, 340, 250);
		f.add(sp); 
		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				table = (JTable)e.getComponent();
				model=(DefaultTableModel)table.getModel();
				//model.getValueAt�� ���� object�� String���� ��ȯ���ش�
				String[] value=new String[4];
				for(int i=0;i<4;i++) {
					value[i] = (String)model.getValueAt(table.getSelectedRow(), i);
					tf_add[i].setText(value[i]);
				}
			}
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}

		});
		bookList();

		f.add(author);
		f.add(submit);
		f.add(update);
		f.add(delete);
		f.add(bookWriter);
		f.add(bookName);
		f.add(bookNo);
		f.add(title);
		f.setSize(600, 320);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation((screenSize.width-f.getPreferredSize().width)/2,(screenSize.height-f.getPreferredSize().height)/2);
		f.setVisible(true);
	}

	class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String no = tf_add[0].getText();
			String name =tf_add[1].getText();
			String writer =tf_add[2].getText();
			String author =tf_add[3].getText();
			boolean isNumber = false;
			String cmd = e.getActionCommand();
			switch(cmd) {
			case "���":
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
							DBManager.stmt.executeUpdate("insert into books2 values('"+no+"','"+name+"','"+writer+"','"+author+"')");
							JOptionPane.showMessageDialog(null,"��ϵǾ����ϴ�.","�˸�",JOptionPane.INFORMATION_MESSAGE);
							for(int i=0;i<4;i++) {
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
				break;
			case "����":
				try {
					if(no.length()!=0) {
						//�ش��й����� ��ȸ���ؼ� rowcount�� ��ȸ�� �� ���� ��ȸ
						ResultSet rs = DBManager.stmt.executeQuery("select * from books2 where no = '"+no+"'");
						rs.next();

						if(rs.getRow() == 1) {
							//�̸�,�а�,�ּҸ� �������� ����
							if(name.length()!=0) {
								DBManager.stmt.executeUpdate("update books2 set title='"+name+"' where no = '"+no+"'");	}
							if(writer.length()!=0) {
								DBManager.stmt.executeUpdate("update books2 set writer='"+writer+"' where no = '"+no+"'");	}
							if(author.length()!=0) {
								DBManager.stmt.executeUpdate("update books2 set author='"+author+"' where no = '"+no+"'");	}
							JOptionPane.showMessageDialog(null,"������ �Ϸ�Ǿ����ϴ�.","�˸�",JOptionPane.INFORMATION_MESSAGE);
							for(int i=0;i<4;i++) {
								tf_add[i].setText("");
							}
							bookList();
						}
						else {
							JOptionPane.showMessageDialog(null,"�ش� ������ �������� �ʽ��ϴ�.","�˸�",JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						//������ư�� �������� �й��� �ƹ��͵� �Ƚ������� ������
					}else {
						JOptionPane.showMessageDialog(null,"������ ������ȣ�� �Է����ּ���","���",JOptionPane.WARNING_MESSAGE);
						break;
					}
					break;
				}
				catch(Exception inputE) {
					inputE.printStackTrace();
				}
				for(int i=0;i<4;i++) {
					tf_add[i].setText("");
				}
				bookList();
				break;
			case "����":
				if(JOptionPane.showConfirmDialog(null, "���������Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					if(no.length()!=0) {
						try {
							DBManager.stmt.executeUpdate("delete from books2 where no = '"+no+"'");
							list();
							for(int i=0;i<4;i++) {
								tf_add[i].setText("");
							}
						}
						catch(Exception deleteE) {
							deleteE.printStackTrace();
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"������ �й��� �Է����ּ���","���",JOptionPane.WARNING_MESSAGE);
					}
				}
				bookList();
				break;
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
			ResultSet rs= DBManager.stmt.executeQuery("select * from books2 order by no");
			model.setNumRows(0);

			while(rs.next()){
				String[] row=new String[4];//�÷��� ������ 4
				row[0]=rs.getString("no");
				row[1]=rs.getString("title");
				row[2]=rs.getString("writer");
				row[3]=rs.getString("author");
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
