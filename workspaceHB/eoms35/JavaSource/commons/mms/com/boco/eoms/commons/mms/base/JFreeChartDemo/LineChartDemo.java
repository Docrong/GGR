package com.boco.eoms.commons.mms.base.JFreeChartDemo;

import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * 该类用于演示最简单的柱状图生成
 * @author Winter Lau
 */
public class LineChartDemo {
	public static void main(String[] args) throws IOException{
		CategoryDataset dataset = getDataSet2();
		JFreeChart chart = ChartFactory.createLineChart(
							"水果产量图", // 图表标题
							"水果", // 目录轴的显示标签
							"产量", // 数值轴的显示标签
							dataset, // 数据集
							PlotOrientation.VERTICAL, // 图表方向：水平、垂直
							true, 	// 是否显示图例(对于简单的柱状图必须是false)
							false, 	// 是否生成工具
							false 	// 是否生成URL链接
							);
		CategoryPlot plot = chart.getCategoryPlot();
		Util.setCategoryPlot(plot);
		NumberAxis numberaxis = (NumberAxis)plot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); //显示为整数  关键就是这句
//		numberaxis.setAutoRangeIncludesZero(true);
//		numberaxis.setUpperMargin(0.20);
//		numberaxis.setLabelAngle(Math.PI / 2.0);
		
		CategoryItemRenderer renderer = plot.getRenderer();
		renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());

        //以下设置，将按照指定格式，制定内容显示每个柱的数值。可以显示柱名称，所占百分比

       //renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",new DecimalFormat("0.0%")));    

       //设置柱子上数值的字体
       renderer.setItemLabelFont(new Font("宋体",Font.PLAIN,8));  
       renderer.setItemLabelsVisible(true);
       renderer.setItemLabelPaint(Color.RED);
		
		FileOutputStream fos_jpg = null;
		try {
			fos_jpg = new FileOutputStream("D:\\line.gif");
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
	private static CategoryDataset getDataSet() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(100, null, "苹果");
		dataset.addValue(200, null, "梨子");
		dataset.addValue(300, null, "葡萄");
		dataset.addValue(400, null, "香蕉");
		dataset.addValue(500, null, "荔枝");
		return dataset;
	}
	/**
	 * 获取一个演示用的组合数据集对象
	 * @return
	 */
	private static CategoryDataset getDataSet2() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(110, "北京", "苹果");
		dataset.addValue(120, "上海", "苹果");
		dataset.addValue(230, "广州", "苹果");
		dataset.addValue(240, "北京", "梨子");
		dataset.addValue(250, "上海", "梨子");
		dataset.addValue(260, "广州", "梨子");
		dataset.addValue(370, "北京", "葡萄");
		dataset.addValue(380, "上海", "葡萄");
		dataset.addValue(390, "广州", "葡萄");
		dataset.addValue(480, "北京", "香蕉");
		dataset.addValue(570, "上海", "香蕉");
		dataset.addValue(430, "广州", "香蕉");
		dataset.addValue(520, "北京", "荔枝");
		dataset.addValue(110, "上海", "荔枝");
		dataset.addValue(500, "广州", "荔枝");
		return dataset;
	}
}


