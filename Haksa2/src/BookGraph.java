import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;

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

	static ArrayList<String> dateYear = new ArrayList<>();
	static ArrayList<String> dateMonth = new ArrayList<>();
	static ArrayList<Integer> dateCount = new ArrayList<>();
	static int sum4=0;
	static Color[] deptColor;
	static Color[] studentColor;
	static Color[] bookColor;
	static Color[] dateColor;
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
		if(isFirst==0) {//���� �ٸ� �޴��� ���� �ٽ� BookGraph�� ȣ��������  ������ �ٽõ����� ���� 
			//�ٽ������ϸ� ���̴°�찡 �߻� BookGraph�޼��� �������� isFirst ���� �������� ������ �� ã�ƿð�� �̺κ��� �Ѿ����
			try{
				//�а����� �з��ϱ����� ����
				rs = DBManager.stmt.executeQuery("select dept, count(*) as count from (select s.dept, br.rdate from student s, books2 b, bookRent2 br"
						+" where br.id=s.id and br.bookNo=b.no) group by dept order by count desc");
				int i=0;
				//====================================================
				
				//�а����� BookRent ���̺��� �˻��ؼ� ������ �а�,�а��� ������ ������
				while(rs.next()) {
					deptName.add(i,rs.getString("dept"));
					deptCount.add(i,rs.getInt("count"));
					sum+=deptCount.get(i);
					i++;
				}
				//====================================================
				//�л��� �� ���ؼ� �־��ִ��ڵ�,�뿩�ϴ� �л��� ���� ��õ�ϼ��� �����Ƿ� ���� 5�� �����ش�.
				rs = DBManager.stmt.executeQuery("select id,name, count(*) count"
						+" from(select br.id id,name,b.no no,b.title title, br.rdate from student s, books2 b, bookRent2 br where br.id=s.id and br.bookNo=b.no)"
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
				//=======================================================================================================================

				//������ ���� 5������ �����ش�.
				rs = DBManager.stmt.executeQuery("select title, count(*) count from(select br.id id,name,b.no no,b.title title, br.rdate from student s,"
						+" books2 b, bookRent2 br where br.id=s.id and br.bookNo=b.no) group by title order by count desc");
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
				//=======================================================================================================================

				//�뿩����� ���� ����� �����ش�.
				rs = DBManager.stmt.executeQuery("select year, month, count(*) count from(select substr(br.rentno,0,4) year,substr(br.rentno,5,2) month from student s, books2 b, bookRent2 br where br.id=s.id(+) and br.bookNo=b.no)" + 
						" group by year,month order by count desc");
				int i4 = 0;
				while(rs.next()) {
					if(i4>4) {
						break;
					}
					dateYear.add(i4,rs.getString("year"));
					dateMonth.add(i4,rs.getString("month"));
					dateCount.add(i4,rs.getInt("count"));
					sum4+=dateCount.get(i4);
					i4++;
				}

				//=========================================================================================================================

				//�����ҋ����� ���� �ٲ��� �ʰ� �̸� ���� ����Ʈ�� �־�д�.
				deptColor = new Color[deptName.size()];
				for(i=0;i<deptName.size();i++) {deptColor[i] =  new Color((int)(Math.random()*255.0),(int)(Math.random()*255.0),(int)(Math.random()*255.0));}
				studentColor =new Color[studentName.size()];
				for(i=0;i<studentName.size();i++) {	studentColor[i] =  new Color((int)(Math.random()*255.0),(int)(Math.random()*255.0),(int)(Math.random()*255.0));}
				bookColor =new Color[studentName.size()];
				for(i=0;i<bookName.size();i++) {bookColor[i] =  new Color((int)(Math.random()*255.0),(int)(Math.random()*255.0),(int)(Math.random()*255.0));			}
				dateColor =new Color[dateYear.size()];
				for(i=0;i<dateYear.size();i++) {dateColor[i] =  new Color((int)(Math.random()*255.0),(int)(Math.random()*255.0),(int)(Math.random()*255.0));			}
				//=======================================================================================================================
			}catch(Exception e){
				e.printStackTrace();
			}
			isFirst++;//if�κ��� �����ϱ��� isFirst�� �������� ������ �� ȣ��� �Ѿ����
		}
		setLayout(new BorderLayout()); // �г� ������ BorderLayout����
		np.setLayout(new FlowLayout());//np�г��� FlowLayout���� ����
		add(cp,BorderLayout.CENTER); //CENTER�� cp�г� �ø�
		add(np,BorderLayout.NORTH);//NORTH�� np�г� �ø�
		setSize(650, 500); // ������ ������ ����
		setVisible(true); // �������� ���̰� ��
	}	
	
	//BorderLayout.NORTH �г� ���� �� �� ���� �ۼ�
	class NorthPanel extends JPanel{
		public NorthPanel() {
			//this.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));
			MyActionListener ma = new MyActionListener();
			ButtonGroup bg = new ButtonGroup();
			//���� ��ư�� �����ϰ� ��ư�׷� bg�� ��� �÷��� �̻ڰ� ����
			bg.add(studentRb);
			bg.add(deptRb);
			bg.add(bookRb);
			bg.add(dateRb);
			add(deptRb);
			add(studentRb);
			add(bookRb);
			add(dateRb);
			
			//��� �����۸����ʿ� �÷���
			deptRb.addItemListener(ma);
			studentRb.addItemListener(ma);
			bookRb.addItemListener(ma);
			dateRb.addItemListener(ma);

		}
	}
//NorthPanel �� ==========================================================================================================
	//CenterPanel Ŭ���� �ۼ�
	class CenterPanel extends JPanel{
		ArrayList<Integer> deptGak = new ArrayList<>();
		ArrayList<Integer> studentGak = new ArrayList<>();
		ArrayList<Integer> bookGak = new ArrayList<>();
		ArrayList<Integer> dateGak = new ArrayList<>();
		public void paintComponent(Graphics g) {
			//�а��� ��Ʈ �����ڵ�
			//������ ��ư�� ���� ���๮�� ���⼭ �ۼ�
			if(deptRb.isSelected()) {//������ư deptRb�� ���É��� ���
				super.paintComponent(g);
				deptGak.add(0,0);//ó�� ���۰��� 0�������̱⿡ 0�� �ε����� 0�� ����
				g.setFont(new Font("Gothic",Font.ITALIC,20));
				g.setColor(Color.BLACK);
				g.drawString("�а��� ���� ����", 400, 110);
				int deptGap=360;//���� ī��Ʈ�� ���� ��갪�� ���� 360���� �� �������� �������� ����. �׷����� �̻ڰ� �ȱ׷����Ƿ� �׶� ���ڶ� ��ġ�� �����ֱ����� gap���� �����ֱ����� ����
				for(int k=0;k<deptName.size();k++	) {//deptName�� ũ�� = �а� ������ŭ �ݺ�
					deptGap-=(int)Math.round(((float)deptCount.get(k)/sum)*360);//360���� k��° �а��̸��� count���� ����
					g.setFont(new Font("Gothic",Font.ITALIC,15));
					g.setColor(deptColor[k]);//�̸������ص� ������ ���
					g.drawString(deptName.get(k)+" - "+deptCount.get(k)+"��", 450, 140+(20*k));//����� ��а��� ��Ÿ������ ǥ�����ְ� ����� ���������� ǥ��
					g.fillRect(420, 130+(20*k), 20, 10); //���� �׸��ĭ�� ���������� �ߺ����ֱ����Ѱ�
					if(k==deptName.size()-1)//������ �а��� �׸��� deptGap�� �����ֱ����� �ڵ�
						g.fillArc(50, 50, 300, 300, deptGak.get(k),(int)Math.round((double)deptCount.get(k)/sum*360)+deptGap);
					else//�������а��� �ƴϸ� �׳� �׸�
						g.fillArc(50, 50, 300, 300, deptGak.get(k),(int)Math.round((double)deptCount.get(k)/sum*360));
					deptGak.add(k+1,deptGak.get(k)+(int)Math.round((double)deptCount.get(k)/sum*360));//k+1��° ������ ������ k��° ������ ������ ������
				}
			}
			//==================================================================================================
			
			//�л��� ����5�� ��Ʈ ����
			if(studentRb.isSelected()) {
				int studentGap = 360;
				super.paintComponent(g);
				studentGak.add(0,0);
				g.setFont(new Font("Gothic",Font.ITALIC,20));
				g.setColor(Color.BLACK);
				g.drawString("���� 5�� �������", 400, 110);
				for(int k=0;k<studentName.size();k++	) {
					studentGap-=(int)Math.round(((float)studentCount.get(k)/sum2)*360);
					g.setFont(new Font("Gothic",Font.ITALIC,15));
					g.setColor(studentColor[k]);
					g.drawString(studentName.get(k)+" "+studentId.get(k)+" - "+studentCount.get(k)+"��", 440, 140+(20*k));
					g.fillRect(420, 130+(20*k), 20, 10);
					if(k==studentName.size()-1)
						g.fillArc(50, 50, 300, 300, studentGak.get(k),(int)Math.round((double)studentCount.get(k)/sum2*360)+studentGap);
					else
						g.fillArc(50, 50, 300, 300, studentGak.get(k),(int)Math.round((double)studentCount.get(k)/sum2*360));
					studentGak.add(k+1,studentGak.get(k)+(int)Math.round((double)studentCount.get(k)/sum2*360));
				}
			}
			//==================================================================================================

			//������ ��Ʈ ����
			if(bookRb.isSelected()) {
				int bookGap = 360;
				super.paintComponent(g);
				bookGak.add(0,0);
				g.setFont(new Font("Gothic",Font.ITALIC,20));
				g.setColor(Color.BLACK);
				g.drawString("���� 5�� �������", 400, 110);
				for(int k=0;k<bookName.size();k++	) {
					bookGap-=(int)Math.round(((float)bookCount.get(k)/sum3)*360);
					g.setFont(new Font("Gothic",Font.ITALIC,15));
					g.setColor(bookColor[k]);
					g.drawString(bookName.get(k)+" - "+bookCount.get(k)+"ȸ", 450, 140+(20*k));
					g.fillRect(420, 130+(20*k), 20, 10);
					if(k==bookName.size()-1)
						g.fillArc(50, 50, 300, 300, bookGak.get(k),(int)Math.round((float)bookCount.get(k)/sum3*360)+bookGap);
					else
						g.fillArc(50, 50, 300, 300, bookGak.get(k),(int)Math.round((float)bookCount.get(k)/sum3*360));
					bookGak.add(k+1,bookGak.get(k)+(int)Math.round((float)bookCount.get(k)/sum3*360));
				}
			}
			//==================================================================================================

			//���� ��Ʈ ���� 
			if(dateRb.isSelected()) {
				int dateGap = 360;
				super.paintComponent(g);
				dateGak.add(0,0);
				g.setFont(new Font("Gothic",Font.ITALIC,20));
				g.setColor(Color.BLACK);
				g.drawString("���� 5���� �������", 400, 110);
				for(int k=0;k<dateYear.size();k++	) {
					dateGap-=(int)Math.round(((float)dateCount.get(k)/sum4)*360);
					g.setFont(new Font("Gothic",Font.ITALIC,15));
					g.setColor(dateColor[k]);
					g.drawString(dateYear.get(k)+"�� "+dateMonth.get(k)+"�� - "+dateCount.get(k)+"��", 450, 140+(20*k));
					g.fillRect(420, 130+(20*k), 20, 10);
					if(k==dateYear.size()-1)
						g.fillArc(50, 50, 300, 300, dateGak.get(k),(int)Math.round((float)dateCount.get(k)/sum4*360)+dateGap);
					else
						g.fillArc(50, 50, 300, 300, dateGak.get(k),(int)Math.round((float)dateCount.get(k)/sum4*360));
					dateGak.add(k+1,dateGak.get(k)+(int)Math.round((float)dateCount.get(k)/sum4*360));
				}
			}
			//==================================================================================================
		}
	}
//CenterPanel ��======================================================================================================
	class MyActionListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			cp.repaint();//������ �����ʿ� ����� ��ư���� �����Ƶ� ������ cp.repaint()����
		}
	}

	public static void main(String args[]) {
	}
}