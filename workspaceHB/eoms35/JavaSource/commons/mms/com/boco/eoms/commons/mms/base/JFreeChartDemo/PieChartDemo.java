package com.boco.eoms.commons.mms.base.JFreeChartDemo;

import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
/**
 * 用于演示饼图的生成
 * @author Winter Lau
 */
public class PieChartDemo {
	public static void main(String[] args) throws IOException{
		DefaultPieDataset data = getDataSet();
		JFreeChart chart = ChartFactory.createPieChart("水果产量图",  // 图表标题
		data, 
		true, // 是否显示图例
		false,
		false
		);
//		Util.setCategoryPlot(chart.getCategoryPlot()); //饼图没有网格线
		PiePlot pieplot = (PiePlot)chart.getPlot();
        pieplot.setLabelFont(new Font("宋体", 0, 12));
        pieplot.setNoDataMessage("无数据");
        pieplot.setCircular(true);
        pieplot.setLabelGap(0.02D);
        pieplot.setBackgroundPaint(new Color(199,237,204));
                pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{0} {2}",
                NumberFormat.getNumberInstance(),
                new DecimalFormat("0.00%")));
                
		//写图表对象到文件，参照柱状图生成源码
		FileOutputStream fos_jpg = null;
		try {
			fos_jpg = new FileOutputStream("D:\\pie.gif");
			ChartUtilities.writeChartAsJPEG(fos_jpg,1.0f,chart,400,300,null);
		} finally {
			try {
				fos_jpg.close();
			} catch (Exception e) {}
		}
	}
	/**
	 * 获取一个演示用的简单数据集对象
	 * @return
	 */
	private static DefaultPieDataset getDataSet() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("苹果",100);
		dataset.setValue("梨子",200);
		dataset.setValue("葡萄",300);
		dataset.setValue("香蕉",400);
		dataset.setValue("荔枝",500);
		return dataset;
	}
}
