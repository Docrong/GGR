// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.boco.eoms.commons.mms.base.JFreeChartDemo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.block.BorderArrangement;
import org.jfree.chart.block.EmptyBlock;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.CompositeTitle;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

import com.boco.eoms.commons.mms.base.util.JFreeCharCreater;
import com.boco.eoms.commons.mms.base.util.MMSConstants;
import com.boco.eoms.commons.statistic.base.config.graphic.FieldDefine;
import com.boco.eoms.commons.statistic.base.config.graphic.GraphicConfig;
import com.boco.eoms.commons.statistic.base.config.graphic.GraphicReport;
import com.boco.eoms.commons.statistic.base.config.graphic.SummaryDefine;
import com.boco.eoms.commons.statistic.base.util.GraphicsReportUtil;

public class BarLineChartDemo extends ApplicationFrame
{
    public BarLineChartDemo(String s)
    {
        super(s);
        JFreeChart jfreechart = createChart();
//        FileOutputStream fos_jpg = null;
//        try {
//        	fos_jpg = new FileOutputStream("D:\\bar.gif");
//			ChartUtilities.writeChartAsJPEG(fos_jpg,0.2f,jfreechart,400,300,null);
//		}
//        catch(Exception e)
//        {
//        	e.printStackTrace();
//        }
//        finally {
//			try {
//				fos_jpg.close();
//			} catch (Exception e) {}
//		}
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        chartpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartpanel);
    }
    
    /**
     * 线柱结合 数据转换
     * @param list
     * @param configig
     * @param type
     * @return
     */
    private static CategoryDataset ConverterList2CategoryDatasetBarAndLine(List list,GraphicReport configig,String type)
    {
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	if(!validateType(type))
    	{
    		System.err.println("请查看配置的报表类型值是否正确 取值范围 type: column 柱图 ，line 线图 ，columnline 柱线结合图");
    		return dataset;
    	}
    	
		SummaryDefine[] sds = configig.getSummaryDefines();
		FieldDefine[] fds = configig.getFieldDefines();
		for(int i=0;i<list.size();i++)
		{
			Map map = (Map)list.get(i);
			String summaryValue = "";
			for(int j=0;j<sds.length;j++)
			{
				SummaryDefine sd = sds[j];
				if(summaryValue != null && !summaryValue.equalsIgnoreCase(""))
				{
					summaryValue+="#";
				}
				//id2name
				String summaryid = (String)map.get(sd.getId());
				String summaryName = summaryid;//StatUtil.id2Name(summaryid, sd.getId2nameService());
				summaryValue += summaryName;
			}
			
			if(type.equalsIgnoreCase("line"))
			{
				for(int k=0; k<fds.length ;k++)
				{
					FieldDefine fd = fds[k];
					String fieldValue = String.valueOf(map.get(fd.getId()));
					if(fd.getType().equalsIgnoreCase("line"))
					{
//						int fv = Integer.parseInt(fieldValue);
						double fv = Double.parseDouble(fieldValue);
						dataset.addValue(fv, fd.getName(), summaryValue);
					}
				}
			}
			
			else if(type.equalsIgnoreCase("column"))
			{
				for(int k=0; k<fds.length ;k++)
				{
					FieldDefine fd = fds[k];
					String fieldValue = String.valueOf(map.get(fd.getId()));
					if(fd.getType().equalsIgnoreCase("column"))
					{
//						int fv = Integer.parseInt(fieldValue);
						double fv = Double.parseDouble(fieldValue);
						dataset.addValue(fv, summaryValue, fd.getName());
					}
				}
			}
		}
		return dataset;
    }
    
    private static boolean validateType(String type)
    {
    	boolean f = false;
//    	type: column 柱图 ，line 线图 ，columnline 柱线结合图
    	String[] str = MMSConstants.G_TYPE;
    	for(int i=0;i<str.length;i++)
    	{
    		if(str[i].equalsIgnoreCase(type))
    		{
    			f = true;
    		}
    	}
    	return f;
    }
    
    private static CategoryDataset createDataset1()
    {
        String s = "S1";
        String s1 = "S2";
        String s2 = "S3";
        String s3 = "Category 1";
        String s4 = "Category 2";
        String s5 = "Category 3";
        String s6 = "Category 4";
        String s7 = "Category 5";
        String s8 = "Category 6";
        String s9 = "Category 7";
        String s10 = "Category 8";
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        defaultcategorydataset.addValue(1.0D, s, s3);
        defaultcategorydataset.addValue(4D, s, s4);
        defaultcategorydataset.addValue(3D, s, s5);
        defaultcategorydataset.addValue(5D, s, s6);
        defaultcategorydataset.addValue(5D, s, s7);
        defaultcategorydataset.addValue(7D, s, s8);
        defaultcategorydataset.addValue(7D, s, s9);
        defaultcategorydataset.addValue(8D, s, s10);
        defaultcategorydataset.addValue(5D, s1, s3);
        defaultcategorydataset.addValue(7D, s1, s4);
        defaultcategorydataset.addValue(6D, s1, s5);
        defaultcategorydataset.addValue(8D, s1, s6);
        defaultcategorydataset.addValue(4D, s1, s7);
        defaultcategorydataset.addValue(4D, s1, s8);
        defaultcategorydataset.addValue(2D, s1, s9);
        defaultcategorydataset.addValue(1.0D, s1, s10);
        defaultcategorydataset.addValue(4D, s2, s3);
        defaultcategorydataset.addValue(3D, s2, s4);
        defaultcategorydataset.addValue(2D, s2, s5);
        defaultcategorydataset.addValue(3D, s2, s6);
        defaultcategorydataset.addValue(6D, s2, s7);
        defaultcategorydataset.addValue(3D, s2, s8);
        defaultcategorydataset.addValue(4D, s2, s9);
        defaultcategorydataset.addValue(3D, s2, s10);
        return defaultcategorydataset;
    }

    private static CategoryDataset createDataset2()
    {
        String s = "S4";
        String s1 = "Category 1";
        String s2 = "Category 2";
        String s3 = "Category 3";
        String s4 = "Category 4";
        String s5 = "Category 5";
        String s6 = "Category 6";
        String s7 = "Category 7";
        String s8 = "Category 8";
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        defaultcategorydataset.addValue(15D, s, s1);
        defaultcategorydataset.addValue(24D, s, s2);
        defaultcategorydataset.addValue(31D, s, s3);
        defaultcategorydataset.addValue(25D, s, s4);
        defaultcategorydataset.addValue(56D, s, s5);
        defaultcategorydataset.addValue(37D, s, s6);
        defaultcategorydataset.addValue(77D, s, s7);
        defaultcategorydataset.addValue(18D, s, s8);
        return defaultcategorydataset;
    }

    private static JFreeChart createChart()
    {
    	//===================
    	JFreeCharCreater jcc = new JFreeCharCreater();
        List list = jcc.initList();
        String gath = "E:\\work\\shanxi\\src\\commons\\mms\\com\\boco\\eoms\\commons\\mms\\base\\JFreeChartDemo\\graphicsConfig.xml";
        GraphicConfig gc = null;
        String gconfig = com.boco.eoms.commons.statistic.base.util.FileUtil.readFile(gath);
        try {
			gc = (GraphicConfig)GraphicsReportUtil.xml2object(GraphicConfig.class, gconfig);
		} catch (IOException e) {
			e.printStackTrace();
		}
		CategoryDataset d1 = ConverterList2CategoryDatasetBarAndLine(list,gc.getGraphicReports()[0],"column");
//		jcc.printDataSet(d1);
		CategoryDataset d2 = ConverterList2CategoryDatasetBarAndLine(list,gc.getGraphicReports()[0],"line");
		jcc.printDataSet(d2);
    	//===================
    	
        JFreeChart jfreechart = ChartFactory.createBarChart("Dual Axis Chart", "Category", "Value", d1/*createDataset1()*/, PlotOrientation.VERTICAL, false, true, false);
        jfreechart.setBackgroundPaint(Color.white);
        CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
        CategoryItemRenderer renderer = categoryplot.getRenderer();
		renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		
        //以下设置，将按照指定格式，制定内容显示每个柱的数值。可以显示柱名称，所占百分比
	    // 设置柱子上数值的字体
		renderer.setItemLabelFont(new Font("宋体", Font.PLAIN, 8));
		renderer.setItemLabelsVisible(true);
		renderer.setItemLabelPaint(Color.RED);
        
        categoryplot.setBackgroundPaint(new Color(238, 238, 255));
        categoryplot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        CategoryDataset categorydataset = d2;//createDataset2();
        categoryplot.setDataset(1, categorydataset);
        categoryplot.mapDatasetToRangeAxis(1, 1);
        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
        NumberAxis numberaxis = new NumberAxis("Secondary");
        categoryplot.setRangeAxis(1, numberaxis);
        LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();
        lineandshaperenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
//        lineandshaperenderer.setItemLabelFont(new Font("宋体", Font.PLAIN, 8));
//        lineandshaperenderer.setItemLabelsVisible(true);
//        lineandshaperenderer.setItemLabelPaint(Color.blue);
        NumberAxis numberaxis1 = new NumberAxis("Percent");
        numberaxis1.setNumberFormatOverride(NumberFormat.getPercentInstance());
        categoryplot.setRangeAxis(1, numberaxis1);
        
        categoryplot.setRenderer(1, lineandshaperenderer);
        categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        LegendTitle legendtitle = new LegendTitle(categoryplot.getRenderer(0));
        legendtitle.setMargin(new RectangleInsets(2D, 2D, 2D, 2D));
//        legendtitle.setFrame(new BlockBorder());
        LegendTitle legendtitle1 = new LegendTitle(categoryplot.getRenderer(1));
        legendtitle1.setMargin(new RectangleInsets(2D, 2D, 2D, 2D));
//        legendtitle1.setFrame(new BlockBorder());
        BlockContainer blockcontainer = new BlockContainer(new BorderArrangement());
        blockcontainer.add(legendtitle, RectangleEdge.LEFT);
        blockcontainer.add(legendtitle1, RectangleEdge.RIGHT);
        blockcontainer.add(new EmptyBlock(2000D, 0.0D));
        CompositeTitle compositetitle = new CompositeTitle(blockcontainer);
        compositetitle.setPosition(RectangleEdge.BOTTOM);
        jfreechart.addSubtitle(compositetitle);
        return jfreechart;
    }

    public static JPanel createDemoPanel()
    {
        JFreeChart jfreechart = createChart();
        return new ChartPanel(jfreechart);
    }

    public static void main(String args[])
    {
        BarLineChartDemo dualaxisdemo1 = new BarLineChartDemo("Dual Axis Demo 1");
        dualaxisdemo1.pack();
        RefineryUtilities.centerFrameOnScreen(dualaxisdemo1);
        dualaxisdemo1.setVisible(true);
    }
}
