import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;
//360�� ���ڸ� ���� ä�������
//Math.round
public class BookGraph extends JPanel {
	static ArrayList<String> deptName = new ArrayList<>();
	static ArrayList<Integer> deptCount = new ArrayList<>();
	static int sum=0;

	static ArrayList<String> studentId = new ArrayList<>();
	static ArrayList<String> studentName = new ArrayList<>();
	static ArrayList<Integer> studentCount = new ArrayList<>();
	static int sum2=0;

	static ArrayList<String> bookName = new ArrayList<>();
	static ArrayList<Integer> bookCount = new ArrayList<>();
	static int sum3=0;

	static ArrayList<String> date = new ArrayList<>();
	static ArrayList<Integer> dateCount = new ArrayList<>();
	static int sum4=0;
	static Color[] deptColor;
	static Color[] studentColor;
	static Color[] bookColor;
	static int isFirst = 0;
	ResultSet rs = null;
	JRadioButton  deptRb = new JRadioButton("�а���");
	JRadioButton  studentRb = new JRadioButton("�л���");
	JRadioButton  bookRb = new JRadioButton("������");
	JRadioButton  dateRb = new JRadioButton("����");
	CenterPanel cp = new CenterPanel(); // CenterPanelŬ������ ���� ���������� ��� ���� �г� cp����
	NorthPanel np= new NorthPanel();	//NotrhPanelŬ������ ���� �������� �г� np ����
	String query;
	public BookGraph() {
		DBManager db = new DBManager();
		db.Connection();
		if(isFirst==0) {
			try{
				//�а����� �з��ϱ����� ����
				rs = DBManager.stmt.executeQuery("select dept, count(*) as count" 
						+" from (select s.dept, br.rdate"
						+" from student s, books b, bookRent br"
						+" where br.id=s.id"
						+" and br.bookNo=b.no)"
						+" group by dept ");
				int i=0;
				//�а����� BookRent ���̺��� �˻��ؼ� ������ �а�,�а��� ������ ������
				while(rs.next()) {
					deptName.add(i,rs.getString("dept"));
					deptCount.add(i,rs.getInt("count"));
					sum+=deptCount.get(i);
					i++;
				}
				//�л��� �� ���ؼ� �־��ִ��ڵ�,�뿩�ϴ� �л��� ���� ��õ�ϼ��� �����Ƿ� ���� 5�� �����ش�.
				rs = DBManager.stmt.executeQuery("select id,name, count(*) count"
						+" from(select br.id id,name,b.no no,b.title title, br.rdate from student s, books b, bookRent br where br.id=s.id and br.bookNo=b.no)"
						+" group by id,name order by count desc");
				int i2 = 0;
				while(rs.next()) {
					if(i2>4) {
						break;
					}
					studentId.add(i2,rs.getString("id"));
					studentName.add(i2,rs.getString("name"));
					studentCount.add(i2,rs.getInt("count"));
					sum2+=studentCount.get(i2);
					i2++;
				}
				//������ ���� 5������ �����ش�.
				rs = DBManager.stmt.executeQuery("select title, count(*) count"
						+" from(select br.id id,name,b.no no,b.title title, br.rdate from student s, books b, bookRent br where br.id=s.id and br.bookNo=b.no)"
						+" group by title order by count desc");
				int i3 = 0;
				while(rs.next()) {
					if(i3>4) {
						break;
					}
					bookName.add(i3,rs.getString("title"));
					bookCount.add(i3,rs.getInt("count"));
					sum3+=bookCount.get(i3);
					i3++;
				}
				//�����ҋ����� ���� �ٲ��� �ʰ� �̸� ���� ����Ʈ�� �־�д�.
				deptColor = new Color[deptName.size()];
				for(i=0;i<deptName.size();i++) {deptColor[i] =  new Color((int)(Math.random()*255.0),(int)(Math.random()*255.0),(int)(Math.random()*255.0));}
				studentColor =new Color[studentName.size()];
				for(i=0;i<studentName.size();i++) {	studentColor[i] =  new Color((int)(Math.random()*255.0),(int)(Math.random()*255.0),(int)(Math.random()*255.0));}
				bookColor =new Color[studentName.size()];
				for(i=0;i<bookName.size();i++) {bookColor[i] =  new Color((int)(Math.random()*255.0),(int)(Math.random()*255.0),(int)(Math.random()*255.0));			}
			}catch(Exception e){
				e.printStackTrace();
			}
			isFirst++;
		}
		setLayout(new BorderLayout());
		np.setLayout(new FlowLayout());//np�г��� FlowLayout���� ����
		add(cp,BorderLayout.CENTER); //CENTER�� cp�г� �ø�
		add(np,BorderLayout.NORTH);//NORTH�� np�г� �ø�
		setSize(650, 500); // ������ ������ ����
		setVisible(true); // �������� ���̰� ��
	}	
	//BorderLayout.NORTH �г� ���� �� �� ���� �ۼ�
	class NorthPanel extends JPanel{
		public NorthPanel() {
			this.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));
			MyActionListener ma = new MyActionListener();
			ButtonGroup bg = new ButtonGroup();

			bg.add(studentRb);
			bg.add(deptRb);
			bg.add(bookRb);
			add(deptRb);
			add(studentRb);
			add(bookRb);

			deptRb.addItemListener(ma);
			studentRb.addItemListener(ma);
			bookRb.addItemListener(ma);

		}
	}
	//CenterPanel Ŭ���� �ۼ�
	class CenterPanel extends JPanel{
		ArrayList<Integer> deptGak = new ArrayList<>();
		ArrayList<Integer> studentGak = new ArrayList<>();
		ArrayList<Integer> bookGak = new ArrayList<>();
		public void paintComponent(Graphics g) {
			if(deptRb.isSelected()) {
				super.paintComponent(g);
				deptGak.add(0,0);
				g.setFont(new Font("Gothic",Font.ITALIC,20));
				g.setColor(Color.BLACK);
				g.drawString("�а��� ���� ����", 400, 110);
				for(int k=0;k<deptName.size();k++	) {
					//color = new Color((int)(Math.random()*255.0),(int)(Math.random()*255.0),(int)(Math.random()*255.0));
					g.setFont(new Font("Gothic",Font.ITALIC,15));
					//g.setColor(color);
					g.setColor(deptColor[k]);
					g.drawString(deptName.get(k), 450, 140+(20*k));
					g.fillRect(420, 130+(20*k), 20, 10);
					g.fillArc(50, 50, 300, 300, deptGak.get(k),(360/sum)*deptCount.get(k));
					deptGak.add(k+1,deptGak.get(k)+(360/sum)*deptCount.get(k));
					System.out.println(Math.round(360/sum)*deptCount.get(k));
					System.out.println(deptGak.get(k)+(360/sum)*deptCount.get(k));
				}
			}

			if(studentRb.isSelected()) {
				super.paintComponent(g);
				studentGak.add(0,0);
				g.setFont(new Font("Gothic",Font.ITALIC,20));
				g.setColor(Color.BLACK);
				g.drawString("���� 5�� �������", 400, 110);
				for(int k=0;k<studentName.size();k++	) {
					g.setFont(new Font("Gothic",Font.ITALIC,15));
					g.setColor(studentColor[k]);
					g.drawString(studentName.get(k), 450, 140+(20*k));
					g.drawString(studentId.get(k), 510, 140+(20*k));
					g.fillRect(420, 130+(20*k), 20, 10);
					g.fillArc(50, 50, 300, 300, studentGak.get(k),(360/sum2)*studentCount.get(k));
					studentGak.add(k+1,studentGak.get(k)+(360/sum2)*studentCount.get(k));
					System.out.println((360/sum2)*studentCount.get(k));
					System.out.println(studentGak.get(k)+(360/sum2)*studentCount.get(k));
				}
			}

			if(bookRb.isSelected()) {
				super.paintComponent(g);
				bookGak.add(0,0);
				g.setFont(new Font("Gothic",Font.ITALIC,20));
				g.setColor(Color.BLACK);
				g.drawString("���� 5�� �������", 400, 110);
				for(int k=0;k<bookName.size();k++	) {
					g.setFont(new Font("Gothic",Font.ITALIC,15));
					g.setColor(bookColor[k]);
					g.drawString(bookName.get(k), 450, 140+(20*k));
					g.fillRect(420, 130+(20*k), 20, 10);
					g.fillArc(50, 50, 300, 300, bookGak.get(k),(360/sum3)*bookCount.get(k));
					bookGak.add(k+1,bookGak.get(k)+(360/sum3)*bookCount.get(k));
				}
			}


		}
	}

	class MyActionListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			cp.repaint();
		}
	}

	public static void main(String args[]) {
		new BookGraph();
	}
}