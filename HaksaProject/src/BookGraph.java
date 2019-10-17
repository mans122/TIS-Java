import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;

public class BookGraph extends JPanel {
	static JPanel jp = new JPanel();
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
	static int isFirst = 0;
	ResultSet rs = null;
	static Color[] color = new Color[10];
	JRadioButton  deptRb = new JRadioButton("�а���");
	JRadioButton  studentRb = new JRadioButton("�л���");
	JRadioButton  bookRb = new JRadioButton("������");
	JRadioButton  dateRb = new JRadioButton("����");
	JRadioButton imsi = new JRadioButton();
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
					if(i3==5) {
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
				color[0]= new Color(239,86,45);
				color[1]= new Color(246,210,88);
				color[2]= new Color(239,206,197);
				color[3]= new Color(151,213,224);
				color[4]= new Color(12,76,138);
				color[5]= new Color(85,135,162);
				color[6]= new Color(209,175,148);
				color[7]= new Color(136,177,75);
				color[8]= new Color(92,113,72);
				color[9]= new Color(209,48,118);
				//=======================================================================================================================
			}catch(Exception e){
				e.printStackTrace();
			}
			isFirst++;//if�κ��� �����ϱ��� isFirst�� �������� ������ �� ȣ��� �Ѿ����
		}
		setLayout(new BorderLayout()); // �г� ������ BorderLayout����
		np.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));//np�г��� FlowLayout���� ����
		add(cp,BorderLayout.CENTER); //CENTER�� cp�г� �ø�
		add(np,BorderLayout.NORTH);//NORTH�� np�г� �ø�
		setBackground(Color.LIGHT_GRAY);
		setOpaque(false);
		setSize(600, 500); // ������ ������ ����
		setVisible(true); // �������� ���̰� ��
	}	
	
	//BorderLayout.NORTH �г� ���� �� �� ���� �ۼ�
	class NorthPanel extends JPanel{
		public NorthPanel() {
			MyActionListener ma = new MyActionListener();
			ButtonGroup bg = new ButtonGroup();
			JButton bt = new JButton("3D�� ����");
//			���� ��ư�� �����ϰ� ��ư�׷� bg�� ��� �÷��� �̻ڰ� ����
			bg.add(studentRb);
			bg.add(deptRb);
			bg.add(bookRb);
			bg.add(dateRb);
			bg.add(imsi);
			add(deptRb);
			add(studentRb);
			add(bookRb);
			add(dateRb);
			//��ư �����ϰ� �ؼ� �޹�� ���̰�
			deptRb.setOpaque(false);
			studentRb.setOpaque(false);
			bookRb.setOpaque(false);
			dateRb.setOpaque(false);
			//--------------------------
			
			//��� �����۸����ʿ� �÷���
			deptRb.addItemListener(ma);
			studentRb.addItemListener(ma);
			bookRb.addItemListener(ma);
			dateRb.addItemListener(ma);
			
			ButtonListener bt2 = new ButtonListener();
			add(bt);
			bt.addActionListener(bt2);
			setOpaque(false); // NorthPanel �����ϰ��ؼ� �޹�溸�̰�
			
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
					g.setFont(new Font("Gothic",Font.BOLD,15));
					g.setColor(color[k]);//�̸������ص� ������ ���
					g.drawString(deptName.get(k)+" - "+deptCount.get(k)+"��", 440, 140+(20*k));//����� ��а��� ��Ÿ������ ǥ�����ְ� ����� ���������� ǥ��
					g.fillRect(410, 130+(20*k), 20, 10); //���� �׸��ĭ�� ���������� �ߺ����ֱ����Ѱ�
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
					g.setFont(new Font("Gothic",Font.BOLD,15));
					g.setColor(color[k+2]);
					g.drawString(studentName.get(k)+" "+studentId.get(k)+" - "+studentCount.get(k)+"��", 430, 140+(20*k));
					g.fillRect(410, 130+(20*k), 20, 10);
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
					g.setFont(new Font("Gothic",Font.BOLD,15));
					g.setColor(color[k+4]);
					g.drawString(bookName.get(k)+" - "+bookCount.get(k)+"ȸ", 440, 140+(20*k));
					g.fillRect(410, 130+(20*k), 20, 10);
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
					g.setFont(new Font("Gothic",Font.BOLD,15));
					g.setColor(color[k*2]);
					g.drawString(dateYear.get(k)+"�� "+dateMonth.get(k)+"�� - "+dateCount.get(k)+"��", 440, 140+(20*k));
					g.fillRect(410, 130+(20*k), 20, 10);
					if(k==dateYear.size()-1)
						g.fillArc(50, 50, 300, 300, dateGak.get(k),(int)Math.round((float)dateCount.get(k)/sum4*360)+dateGap);
					else
						g.fillArc(50, 50, 300, 300, dateGak.get(k),(int)Math.round((float)dateCount.get(k)/sum4*360));
					dateGak.add(k+1,dateGak.get(k)+(int)Math.round((float)dateCount.get(k)/sum4*360));
				}
			}
			//==================================================================================================
			setOpaque(false);
		}
	}
//CenterPanel ��======================================================================================================
	class MyActionListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			cp.repaint();//������ �����ʿ� ����� ��ư���� �����Ƶ� ������ cp.repaint()����
		}
	}
	class ButtonListener implements ActionListener{
		PieChart3D pc3 = new PieChart3D();
		@Override
		public void actionPerformed(ActionEvent e) {
			cp.removeAll();
			cp.revalidate();
			cp.repaint();
			if(dateRb.isSelected()) {
				System.out.println("���L�� üũ~~");
				imsi.setSelected(true);
				
				cp.setSize(600,450);
				cp.setLocation(0,30);
				cp.removeAll();
				cp.revalidate();
				cp.repaint();
				cp.setLayout(null);
				cp.add(pc3);
			}
		}
		
	}
	public static void main(String args[]) {
	}
}