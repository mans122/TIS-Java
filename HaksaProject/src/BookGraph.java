import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

//isFirst������ �߰��ϰ� ���� �����͵� �� �����̾ȉ�
public class BookGraph extends JPanel {
	static ChartPanel chartPanel;
	static JPanel jp = new JPanel();
	static ArrayList<String> deptName = new ArrayList<>();
	static ArrayList<Integer> deptCount = new ArrayList<>();
	static int sum;

	static ArrayList<String> studentId = new ArrayList<>();
	static ArrayList<String> studentName = new ArrayList<>();
	static ArrayList<Integer> studentCount = new ArrayList<>();
	static int sum2;

	static ArrayList<String> bookName = new ArrayList<>();
	static ArrayList<Integer> bookCount = new ArrayList<>();
	static int sum3;
	
	static ArrayList<String> dateYear = new ArrayList<>();
	static ArrayList<String> dateMonth = new ArrayList<>();
	static ArrayList<Integer> dateCount = new ArrayList<>();
	static int sum4;
	static int isFirst = 0;
	ResultSet rs = null;
	static Color[] color = new Color[10];
	JRadioButton  deptRb = new JRadioButton("�а���");
	JRadioButton  studentRb = new JRadioButton("�л���");
	JRadioButton  bookRb = new JRadioButton("������");
	JRadioButton  dateRb = new JRadioButton("����");
	JRadioButton dept = new JRadioButton();
	JRadioButton student = new JRadioButton();
	JRadioButton book = new JRadioButton();
	JRadioButton date = new JRadioButton();
	CenterPanel cp = new CenterPanel(); // CenterPanelŬ������ ���� ���������� ��� ���� �г� cp����
	NorthPanel np= new NorthPanel();	//NotrhPanelŬ������ ���� �������� �г� np ����
	String query;
	public BookGraph() {
		//ó�� ȣ������� �÷����� �־��ش�.
		if(isFirst==0) {
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
			isFirst++;
		}
		
		//ArrayList�� �����ϱ⶧���� BookGraph�� ȣ��ɶ����� �����Ǿ� �׿��� �ڵ尡 ���̱⶧���� ȣ���Ҷ����� ArrayList�� �ʱ�ȭ���ش�
		deptName.clear();
		deptCount.clear();

		studentId.clear();
		studentName.clear();
		studentCount.clear();

		bookName.clear();
		bookCount.clear();

		dateYear.clear();
		dateMonth.clear();
		dateCount.clear();
		//======================================================================================================================================================
		
		try{
			//�а����� �з��ϱ����� ����
			rs = DBManager.stmt.executeQuery("select dept, count(*) as count from (select s.dept, br.rdate from student s, books2 b, bookRent2 br"
					+" where br.id=s.id and br.bookNo=b.no) group by dept order by count desc");
			int i=0;
			sum=0;
			//==========================================================================================================================================================

			//�а����� BookRent ���̺��� �˻��ؼ� ������ �а�,�а��� ������ ������
			while(rs.next()) {
				deptName.add(i,rs.getString("dept"));
				deptCount.add(i,rs.getInt("count"));
				sum+=deptCount.get(i);
				i++;
			}
			//==========================================================================================================================================================
			
			//�л��� �� ���ؼ� �־��ִ��ڵ�,�뿩�ϴ� �л��� ���� ��õ�ϼ��� �����Ƿ� ���� 5�� �����ش�.
			rs = DBManager.stmt.executeQuery("select id,name, count(*) count"
					+" from(select br.id id,name,b.no no,b.title title, br.rdate from student s, books2 b, bookRent2 br where br.id=s.id and br.bookNo=b.no)"
					+" group by id,name order by count desc");
			int i2 = 0;
			sum2=0;
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
			//==========================================================================================================================================================================

			//������ ���� 5������ �����ش�.
			rs = DBManager.stmt.executeQuery("select title, count(*) count from(select br.id id,name,b.no no,b.title title, br.rdate from student s,"
					+" books2 b, bookRent2 br where br.id=s.id and br.bookNo=b.no) group by title order by count desc");
			int i3 = 0;
			sum3=0;
			while(rs.next()) {
				if(i3==5) {
					break;
				}
				bookName.add(i3,rs.getString("title"));
				bookCount.add(i3,rs.getInt("count"));
				sum3+=bookCount.get(i3);
				i3++;
			}
			//==========================================================================================================================================================================

			//�뿩����� ���� ����� �����ش�.
			rs = DBManager.stmt.executeQuery("select year, month, count(*) count from(select substr(br.rentno,0,4) year,substr(br.rentno,5,2) month from student s, books2 b, bookRent2 br where br.id=s.id(+) and br.bookNo=b.no)" + 
					" group by year,month order by count desc");
			int i4 = 0;
			sum4=0;
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
			//============================================================================================================================================================================

		}catch(Exception e){
			e.printStackTrace();
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
	//======================================================================================================================================================
	//BorderLayout.NORTH �г� ���� �� �� ���� �ۼ�
	class NorthPanel extends JPanel{
		public NorthPanel() {
			MyActionListener ma = new MyActionListener();
			ButtonGroup bg = new ButtonGroup();
			JButton bt = new JButton("3D");
			JButton bt22 = new JButton("����");
			JButton bt33 = new JButton("������");
			bt.setPreferredSize(new Dimension(50,25));
			bt22.setPreferredSize(new Dimension(50,25));
			bt33.setPreferredSize(new Dimension(100,25));
			//			���� ��ư�� �����ϰ� ��ư�׷� bg�� ��� �÷��� �̻ڰ� ����
			bg.add(studentRb);
			bg.add(deptRb);
			bg.add(bookRb);
			bg.add(dateRb);
			bg.add(student);
			bg.add(dept);
			bg.add(book);
			bg.add(date);
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
	//============================================================================================================================================================
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
					g.setColor(color[k*2+1]);
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
	//������ �����ʿ� ����� ��ư���� �����Ƶ� ������ cp.repaint()���� (CenterPanel �ٽñ׸���)
	class MyActionListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			cp.repaint();
		}
	}
	//=========================================================================================================================================================
	//3D�� ���� ��ư �������� ���õǾ��ִ� radiobt�� �������� ���� �޾ƿ� ���
	class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			cp.setSize(600,400);
			cp.removeAll();
			cp.revalidate();
			cp.repaint();

			if(deptRb.isSelected()) {
				dept.setSelected(true);
				cp.add(new PieChart3D());
			}
			if(studentRb.isSelected()) {
				student.setSelected(true);
				cp.add(new PieChart3D());
			}
			if(bookRb.isSelected()) {
				book.setSelected(true);
				cp.add(new PieChart3D());
			}
			if(dateRb.isSelected()) {
				date.setSelected(true);
				//				cp.setSize(600,400);
				//				cp.removeAll();
				//				cp.revalidate();
				//				cp.repaint();
				//				cp.setLayout(null);
				cp.add(new PieChart3D());
			}
		}
	}
	//=========================================================================================================================================================
	public static void main(String args[]) {
	}
	//===============================================================================================================================
	//=======3D ������Ʈ ������ִ� �ڵ�=================================================================================================
	class PieChart3D extends JPanel {
		String titleName = null;
		public PieChart3D() {
			final PieDataset dataset = createSampleDataset();
			final JFreeChart chart = createChart(dataset);
			chartPanel = new ChartPanel(chart);
			setSize(600,450);
			add(chartPanel);
			setVisible(true);
		}

		private PieDataset createSampleDataset() {
			final DefaultPieDataset result = new DefaultPieDataset();
			if(dept.isSelected()) {
				for(int i=0;i<deptName.size();i++) {
					result.setValue(deptName.get(i)+" - "+deptCount.get(i)+"��", new Double((100/sum)*deptCount.get(i)));
				}
				titleName = "����5�� �а� 3D��Ʈ";
			}

			if(student.isSelected()) {
				for(int i=0;i<studentName.size();i++) {
					result.setValue(studentName.get(i)+" "+studentId.get(i)+" - "+studentCount.get(i)+"��", new Double((100/sum2)*studentCount.get(i)));
				}
				titleName = "����5�� �л� 3D��Ʈ";
			} 

			if(book.isSelected()) {
				for(int i=0;i<bookName.size();i++) {
					result.setValue(bookName.get(i)+" - "+bookCount.get(i)+"ȸ", new Double((100/sum3)*bookCount.get(i)));
				}
				titleName = "����5�� ���� 3D��Ʈ";
			}

			if(date.isSelected()) {
				for(int i=0;i<dateYear.size();i++) {
					result.setValue(dateYear.get(i)+"�� "+dateMonth.get(i)+"�� - "+dateCount.get(i)+"��", new Double((100/sum4)*dateCount.get(i)));
				}
				titleName = "����5���� 3D��Ʈ";
			}
			return result;
		}

		private JFreeChart createChart(final PieDataset dataset) {
			final JFreeChart chart = ChartFactory.createPieChart3D(
					titleName, dataset, true, true, false
					);

			final PiePlot3D plot = (PiePlot3D) chart.getPlot();
			plot.setStartAngle(290);
			plot.setDirection(Rotation.CLOCKWISE);
			plot.setForegroundAlpha(0.5f);
			plot.setNoDataMessage("No data to display");
			chart.getTitle().setFont(new Font("���", Font.BOLD, 15));
			plot.setLabelFont(new Font("���", Font.PLAIN, 10));
			chart.getLegend().setItemFont(new Font("���", Font.PLAIN, 10));
			return chart;
		}
	}
}