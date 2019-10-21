

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.RefineryUtilities;

	public class HaksaMenuActionListener implements ActionListener{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			switch(cmd) {
			case "�л�����":
				Haksa.panel.removeAll();
				Haksa.panel.revalidate();
				Haksa.panel.repaint();
				Haksa.panel.add(new Student());
				Haksa.panel.setLayout(null);
				Haksa.f.setPreferredSize(new Dimension(500,500));
				Haksa.f.setResizable(false);
				Haksa.f.pack();
				break;
			case "���� �� �ݳ�":
				Haksa.panel.removeAll();
				Haksa.panel.revalidate();
				Haksa.panel.repaint();
				Haksa.panel.add(new BookManager());
				Haksa.f.setPreferredSize(new Dimension(610,550));
				Haksa.panel.setLayout(null);
				Haksa.f.pack();
				break;

			case "Exit":
				System.exit(0);
				break;
			case "������Ȳ":
				Haksa.panel.removeAll();
				Haksa.panel.revalidate();
				Haksa.panel.repaint();
				Haksa.panel.add(new BookGraph());
				Haksa.f.setPreferredSize(new Dimension(610,550));
				Haksa.panel.setLayout(null);
				Haksa.f.pack();
				break;
			case "������Ȳ":
				Haksa.panel.removeAll();
				Haksa.panel.revalidate();
				Haksa.panel.repaint();
				Haksa.panel.add(new BookLineChart());
				Haksa.f.setPreferredSize(new Dimension(610,500));
				Haksa.panel.setLayout(null);
				Haksa.f.pack();
				break;
				
			case "������Ȳ":
				BarChartBean bcb = new BarChartBean();
		        JFreeChart chart = bcb.getBarChart();
		        ChartFrame frame1 = new ChartFrame("��Ʈ",chart);
		        frame1.setSize(700,250);  
		        frame1.setVisible(true);
		        frame1.setLocation(100, 200);
				break;
			}
		}
	public static void main(String[] args) {
	}

}

