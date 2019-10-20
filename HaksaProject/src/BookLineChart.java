
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class BookLineChart extends JPanel {
	String titleName = null;
	String[] type = new String[12];
	String[] series = new String[5];
    public BookLineChart() {
        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 430));
        setSize(new Dimension(610,440));
        add(chartPanel);
        setVisible(true);
    }
    private CategoryDataset createDataset() {
    	
        // row keys...
    	series[0] = "å1";
    	series[1] = "å2";
//        final String series1 = "å1";
//        final String series2 = "book2";
//        final String series3 = "book3";
//        final String series4 = "book4";
//        final String series5 = "book5";
        
        // column keys...
        for(int i=0;i<12;i++) {
        	type[i] = (i+1)+"��";
        	System.out.println(type[i]);
        }
        
        final String type1 = "1��";
        final String type2 = "2��";
        final String type3 = "3��";
        final String type4 = "4��";
        final String type5 = "5��";
        final String type6 = "6��";
        final String type7 = "7��";
        final String type8 = "8��";
        final String type9 = "9��";
        final String type10 = "10��";
        final String type11 = "11��";
        final String type12 = "12��";

        // create the dataset..r.
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(30.0, series[0], type1);
        dataset.addValue(4.0, series[0], type2);
        dataset.addValue(3.0, series[0], type3);
        dataset.addValue(5.0, series[0], type4);
        dataset.addValue(5.0, series[0], type5);
        dataset.addValue(7.0, series[0], type6);
        dataset.addValue(7.0, series[0], type7);
        dataset.addValue(8.0, series[0], type8);
        dataset.addValue(8.0, series[0], type9);
        dataset.addValue(8.0, series[0], type10);
        dataset.addValue(8.0, series[0], type11);
        dataset.addValue(8.0, series[0], type12);
        
        for(int i=0;i<12;i++) {
        	dataset.addValue(5.0, series[1], type[i]);
        }

        return dataset;
    }

    private JFreeChart createChart(final CategoryDataset dataset) {
        // create the chart...
        final JFreeChart chart = ChartFactory.createLineChart(
            "����5�� ���� ���� ��ȭ��",       // chart title
//            "��",                    // domain axis label
            "",
            "��",                   // range axis label
            dataset,                   // data
            PlotOrientation.VERTICAL,  // orientation
            true,                      // include legend
            true,                      // tooltips
            false                      // urls
        );
        chart.setBackgroundPaint(Color.white);
        chart.getTitle().setFont(new Font("����",Font.BOLD,15));
        chart.getLegend().setItemFont(new Font("���", Font.PLAIN, 13));
        
        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);
        //�ǹ� ���� ī�װ� (��) ĭ
        plot.getDomainAxis().setLabelFont(new Font("����",Font.BOLD,15));
        //1~12�� ĭ
        plot.getDomainAxis().setTickLabelFont(new Font("����",Font.BOLD,10));
        
        //���� ������
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setLabelAngle(20);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setLabelFont(new Font("���", Font.BOLD, 18));
        rangeAxis.setAutoRangeIncludesZero(true);

        
        // customise the renderer...
        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setDrawOutlines(true);

        renderer.setSeriesStroke(
            0, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {10.0f, 6.0f}, 0.0f
            )
        );
        renderer.setSeriesStroke(
            1, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {6.0f, 6.0f}, 0.0f
            )
        );
        renderer.setSeriesStroke(
            2, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {2.0f, 6.0f}, 0.0f
            )
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        return chart;
    }

    public static void main(final String[] args) {
//
//        final BookLineChart demo = new BookLineChart("Line Chart Demo");
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
//        demo.setVisible(true);

    }

}