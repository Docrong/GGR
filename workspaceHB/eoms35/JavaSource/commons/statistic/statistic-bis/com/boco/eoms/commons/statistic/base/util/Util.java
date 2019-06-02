package com.boco.eoms.commons.statistic.base.util;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
//资料来源:学网(www.xue5.com),原文地址:http://www.xue5.com/itedu/200707/133963_25.html
public class Util {
	
	/**
	 * 网格线
	 * @param plot
	 */
	public static void setCategoryPlot(CategoryPlot plot){
	     plot.getDomainAxis().setVisible(true);
	     plot.getDomainAxis().setLabelFont(new Font("宋体", Font.PLAIN, 12));
	     plot.getDomainAxis().setLabelPaint(Color.BLACK);
	     plot.getDomainAxis().setTickLabelFont(new Font("宋体", Font.PLAIN, 12));
	     plot.getDomainAxis().setTickLabelPaint(Color.BLACK);
	     plot.getDomainAxis().setTickLabelsVisible(true);
//	     plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
	     
	     plot.getRangeAxis().setVisible(true);
	     plot.getRangeAxis().setLabelFont(new Font("宋体", Font.PLAIN, 12));
	     plot.getRangeAxis().setLabelPaint(Color.BLACK);
	     plot.getRangeAxis().setTickLabelFont(new Font("宋体", Font.PLAIN, 12));
	     plot.getRangeAxis().setTickLabelPaint(Color.BLACK);
	     plot.getRangeAxis().setVerticalTickLabels(false);
	     plot.getRangeAxis().setLabelAngle(0.0D);
	     
	     plot.setDomainGridlinesVisible(true);
	     plot.setRangeGridlinesVisible(true);

	}	
}
