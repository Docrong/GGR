package com.boco.eoms.commons.statistic.base.util;

import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.block.BorderArrangement;
import org.jfree.chart.block.EmptyBlock;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.CompositeTitle;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

import com.boco.eoms.commons.statistic.base.config.excel.Excel;
import com.boco.eoms.commons.statistic.base.config.excel.Sheet;
import com.boco.eoms.commons.statistic.base.config.graphic.FieldDefine;
import com.boco.eoms.commons.statistic.base.config.graphic.GraphicConfig;
import com.boco.eoms.commons.statistic.base.config.graphic.GraphicReport;
import com.boco.eoms.commons.statistic.base.config.graphic.SummaryDefine;
import com.boco.eoms.commons.statistic.base.mgr.impl.ExcelConverter;
import com.boco.eoms.commons.statistic.base.reference.StaticMethod;

public class JFreeCharCreater {
	
	private Properties properties = null;
	
	public JFreeCharCreater()
	{
		String realPath = null;
		InputStream is = null;
		try 
		{
			realPath = StaticMethod.getFilePathForUrl("classpath:config/statistic/base-config/statpotal.properties");
			is = new FileInputStream(realPath);
			properties = new Properties();
			properties.load(is);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			if(is != null)
			{
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		JFreeCharCreater jcc = new JFreeCharCreater();
		int chart = 2;
		
		//需要根据数据计算 宽度，高度，和显示的字体
		//TODO
		
		Display display = new Display();
		
		if(chart == 1)//柱图
		{
			CategoryDataset[] dataset = {getDataSet2()};//getCategoryDataset();
			ByteArrayOutputStream bos = jcc.CreateChart(chart,dataset,display);
			writeFile(bos,"D://BarChart1.gif");
		}
		else if(chart == 2)//线图
		{
			CategoryDataset[] dataset = {getDataSet2()};
			ByteArrayOutputStream bos = jcc.CreateChart(chart,dataset,display);
			writeFile(bos,"D://LineChart1.gif");
		}
		else  if(chart == 3)//饼图
		{
			PieDataset[] dataset = {getPieDataset()};
			ByteArrayOutputStream bos = jcc.CreateChart(chart,dataset,display);
			writeFile(bos,"D://PieChart1.gif");
		}
		else if(chart == 4)//柱线结合图
		{
			CategoryDataset[] dataset = getBarAndLineCategoryDataset();
			ByteArrayOutputStream bos = jcc.CreateChart(chart,dataset,display);
			writeFile(bos,"D://BarlineChart1.gif");
		}
	}
	
	/**
	 * 建立图形
	 * @param chartType 1:柱图 2:线图 3:饼图
	 */
	public ByteArrayOutputStream CreateChart(int chartType ,Dataset[] dataset,Display display)
	{
		//图片的属性
		float quality = 0.8f; //质量
		int width = 300; //大小 宽
		int height = 180; //大小 高
		int fontSize = 10;//字体
		
		if(properties != null)
		{
			quality = Float.parseFloat(properties.getProperty("quality"));
			width = Integer.parseInt(properties.getProperty("width"));
			height = Integer.parseInt(properties.getProperty("height"));
			fontSize = Integer.parseInt(properties.getProperty("fontSize"));
		}
		
		//需要根据数据计算 宽度，高度，和显示的字体 
		//TODO
		
		ByteArrayOutputStream bos = null;
		if(chartType == 1)//柱图
		{
//			CategoryDataset dataset = getDataSet2();
			CategoryDataset barDataset = (CategoryDataset)dataset[0];
//			bos = (ByteArrayOutputStream)createBarChart("水果产量图","水果","产量",barDataset,PlotOrientation.VERTICAL,quality,width, height,fontSize);
			bos = (ByteArrayOutputStream)createBarChart(display.title,display.targetString,display.valueString,barDataset,PlotOrientation.VERTICAL,quality,width, height,fontSize);
		}
		else if(chartType == 2)//线图
		{
//			CategoryDataset dataset = getDataSet2();
			CategoryDataset lineDataset = (CategoryDataset)dataset[0];
//			bos = (ByteArrayOutputStream)createLineChart("水果产量图","水果","产量",lineDataset,PlotOrientation.VERTICAL,quality,width, height,fontSize);
			bos = (ByteArrayOutputStream)createLineChart(display.title,display.targetString,display.valueString,lineDataset,PlotOrientation.VERTICAL,quality,width, height,fontSize);
		}
		else  if(chartType == 3)//饼图
		{
//			DefaultPieDataset dataset = getDataSetPie();
			DefaultPieDataset pieDataset = (DefaultPieDataset)dataset[0];
//			bos = (ByteArrayOutputStream)createPieChart("水果产量图",pieDataset,quality,width, height,fontSize);
			bos = (ByteArrayOutputStream)createPieChart(display.title,pieDataset,quality,width, height,fontSize);
		}
		else if(chartType == 4)//柱线结合图
		{
			CategoryDataset barDataset = (CategoryDataset)dataset[0];
			CategoryDataset lineDataset = (CategoryDataset)dataset[1];
			bos = (ByteArrayOutputStream)createBarAndLineChart(display.title,display.targetString,display.valueString,barDataset,lineDataset,PlotOrientation.VERTICAL,quality,width, height,fontSize,Constants.PERCENT);
		}
		
		return bos;
		
	}
	
	/**
	 * 打印DataSet数据模型
	 * @param dataset
	 */
	public static void printDataSet(CategoryDataset dataset)
	{
		for(int i=0;i<dataset.getRowCount();i++)
		{
			for(int j=0;j<dataset.getColumnCount();j++)
			{
				System.out.print(dataset.getValue(i, j) + ",");
				System.out.print(dataset.getRowKey(i)+ ",");
				System.out.print(dataset.getColumnKey(j));
				System.out.println("");
			}
			System.out.println("=========================");
		}
	}
	
	//s1为汇总字段，f4,f5,f6为指标字段 转换为Dataset 柱图,多线图情况
	public CategoryDataset CoverterList2CategoryDataset(List list,GraphicReport config)
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		SummaryDefine[] sds = config.getSummaryDefines();
		FieldDefine[] fds = config.getFieldDefines();
		
		for(int i=0;i<list.size();i++)
		{
			Map map = (Map)list.get(i);
			String summaryValue = "";
			for(int j=0;j<sds.length;j++)
			{
				SummaryDefine sd = sds[j];
				if(summaryValue != null && !summaryValue.equalsIgnoreCase(""))
				{
					summaryValue+=",";
				}
				//id2name
				String summaryid = (String)map.get(sd.getId());
				String summaryName = summaryid;
				if(sd.getId2nameService() != null)
				{
					summaryName = StatUtil.id2Name(summaryid, sd.getId2nameService());
				}
				
				summaryValue += summaryName;
			}
			
			for(int k=0; k<fds.length ;k++)
			{
				FieldDefine fd = fds[k];
				String fieldValue = String.valueOf(map.get(fd.getId()));
				int fv = Integer.parseInt(fieldValue);
				dataset.addValue(fv, summaryValue, fd.getName());
			}
			
		}
		
		return dataset;
	}
	
    /**
     * 线柱结合 数据转换
     * @param list
     * @param configig
     * @param type
     * @return
     */
	public static CategoryDataset ConverterList2CategoryDatasetBarAndLine(List list,GraphicReport configig,String type)
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
				String summaryName = summaryid;
				if(sd.getId2nameService() != null)
				{
					summaryName = StatUtil.id2Name(summaryid, sd.getId2nameService());//summaryid;
				}
				
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
    
	//s1为汇总字段，f4,f5,f6为指标字段 转换为Dataset 饼图图情况
	public PieDataset CoverterList2PieDataset (List list,GraphicReport config)
	{
		if(list == null || list.size() == 0)
		{
			return null;
		}
		Map map = null;
		if(list.size() > 1)//饼图只能显示1条记录的数据
		{
			map = (Map)list.get(0);
		}
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		FieldDefine[] fds = config.getFieldDefines();
		
		for(int k=0; k<fds.length ;k++)
		{
			FieldDefine fd = fds[k];
			String fieldValue = String.valueOf(map.get(fd.getId()));
			int fv = Integer.parseInt(fieldValue);
			dataset.setValue(fd.getName(),fv);
		}
		
		return dataset;
	}
	
	
	//*********************************************************************
	
	/**
	 * 柱图,线图的数据模型
	 * @return
	 * @throws Exception
	 */
	private static CategoryDataset getCategoryDataset() throws Exception
	{
		ExcelConverter ec = new ExcelConverter();
		Excel excel = ec.parseExcelToConfig(StaticMethod.getFilePathForUrl("classpath:config/mms/report-config/commonfault-config/oracle/statistic-config-excel-commonfault_T_resolve_KPI4_oracle.xls"));
		Sheet sheet = excel.getSheetByIndex("0");
		
		GraphicConfig gc = (GraphicConfig)GraphicsReportUtil.xml2object(GraphicConfig.class, sheet.getGraphicConfig());
		GraphicReport gr= gc.getGraphicReports()[0];//gc.getGraphicReports("commonfault_T_resolve_byuser");
		
		
		JFreeCharCreater jcc = new JFreeCharCreater();
		CategoryDataset dataset = jcc.CoverterList2CategoryDataset(jcc.initList(),gr);
		
		return dataset;
	}
	
	/**
	 * 柱图,线图的数据模型
	 * @return
	 * @throws Exception
	 */
	private static CategoryDataset[] getBarAndLineCategoryDataset() throws Exception
	{
		ExcelConverter ec = new ExcelConverter();
		Excel excel = ec.parseExcelToConfig(StaticMethod.getFilePathForUrl("classpath:config/mms/report-config/commonfault-config/oracle/statistic-config-excel-commonfault_T_resolve_KPI4_oracle_columnline.xls"));
		Sheet sheet = excel.getSheetByIndex("0");
		
		GraphicConfig gc = (GraphicConfig)GraphicsReportUtil.xml2object(GraphicConfig.class, sheet.getGraphicConfig());
		GraphicReport gr= gc.getGraphicReports()[0];//gc.getGraphicReports("commonfault_T_resolve_byuser");
		
		
		JFreeCharCreater jcc = new JFreeCharCreater();
		CategoryDataset datasetColumn = jcc.ConverterList2CategoryDatasetBarAndLine(jcc.initList(),gr,Constants.COLUMN);
		CategoryDataset datasetLine = jcc.ConverterList2CategoryDatasetBarAndLine(jcc.initList(),gr,Constants.LINE);
		CategoryDataset[] dataset = {datasetColumn,datasetLine};
		
		return dataset;
	}
	
	/**
	 * 饼图的数据模型
	 * @return
	 * @throws Exception
	 */
	private static PieDataset getPieDataset() throws Exception
	{
		ExcelConverter ec = new ExcelConverter();
		Excel excel = ec.parseExcelToConfig(StaticMethod.getFilePathForUrl("classpath:config/mms/report-config/commonfault-config/oracle/statistic-config-excel-commonfault_T_resolve_KPI4_oracle.xls"));
		Sheet sheet = excel.getSheetByIndex("0");
		
		GraphicConfig gc = (GraphicConfig)GraphicsReportUtil.xml2object(GraphicConfig.class, sheet.getGraphicConfig());
		GraphicReport gr= gc.getGraphicReports("commonfault_T_resolve_byuser");
		
		JFreeCharCreater jcc = new JFreeCharCreater();
		PieDataset dataset = jcc.CoverterList2PieDataset(jcc.initList(),gr);
		
		return dataset;
	}
	
	/**
	 * 柱图
	 * @param Title
	 * @param targetString 
	 * @param valueString
	 * @param dataset
	 * @param Orientation
	 * @return
	 */
	private static OutputStream createBarChart(String Title ,String targetString
			,String valueString ,CategoryDataset dataset ,PlotOrientation Orientation,float quality,int width, int height,int fontSize )
	{
		ByteArrayOutputStream bos = null;
		JFreeChart chart = ChartFactory.createBarChart(
				Title, // 图表标题
				targetString, // 目录轴的显示标签
				valueString, // 数值轴的显示标签
				dataset, // 数据集
				Orientation, // 图表方向：水平、垂直
				true, 	// 是否显示图例(对于简单的柱状图必须是false)
				false, 	// 是否生成工具
				false 	// 是否生成URL链接
		);
		CategoryPlot plot = chart.getCategoryPlot();
		Util.setCategoryPlot(plot);
		CategoryItemRenderer renderer = plot.getRenderer();
		renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());

       //设置柱子上数值的字体
       renderer.setItemLabelFont(new Font("宋体",Font.PLAIN,fontSize));  
       renderer.setItemLabelsVisible(true);
       renderer.setItemLabelPaint(Color.RED);
       
       // 设置最高的一个柱与图片顶端的距离,设置最低的一个柱与图片底端的距离
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setUpperMargin(0.3);
		rangeAxis.setLowerMargin(0.3);
		plot.setRangeAxis(rangeAxis); 
       
		try {
			bos = new ByteArrayOutputStream();
			ChartUtilities.writeChartAsJPEG(bos,quality,chart,width,height,null);
		} 
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				if(bos != null)
				{
					bos.close();
				}
			} catch (Exception e) {}
		}
		
		return bos;
	}
	
	
	//=======================
	
	private static OutputStream createBarAndLineChart(String Title ,String targetString
			,String valueString ,CategoryDataset datasetColumn, CategoryDataset datasetLine,PlotOrientation Orientation,float quality,int width, int height,int fontSize,String percent)
    {
        JFreeChart jfreechart = ChartFactory.createBarChart(Title, targetString, valueString, datasetColumn/*createDataset1()*/, Orientation, false, true, false);
        jfreechart.setBackgroundPaint(Color.white);
        CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
        CategoryItemRenderer renderer = categoryplot.getRenderer();
		renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());

        //以下设置，将按照指定格式，制定内容显示每个柱的数值。可以显示柱名称，所占百分比
	    // 设置柱子上数值的字体
		renderer.setItemLabelFont(new Font("宋体", Font.PLAIN, fontSize));
		renderer.setItemLabelsVisible(true);
		renderer.setItemLabelPaint(Color.RED);
		
		// 设置最高的一个柱与图片顶端的距离,设置最低的一个柱与图片底端的距离
		ValueAxis rangeAxis = categoryplot.getRangeAxis();
		rangeAxis.setUpperMargin(0.3);
		rangeAxis.setLowerMargin(0.3);
		categoryplot.setRangeAxis(rangeAxis); 
		
        categoryplot.setBackgroundPaint(new Color(238, 238, 255));
        categoryplot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        CategoryDataset categorydataset = datasetLine;//createDataset2();
        categoryplot.setDataset(1, categorydataset);
        categoryplot.mapDatasetToRangeAxis(1, 1);
//        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
//        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
        NumberAxis numberaxis = new NumberAxis("Secondary");
        categoryplot.setRangeAxis(1, numberaxis);
        LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();
        lineandshaperenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        //不知道为什么设置在线上显示value值 没有效果，可能需要研究
//        lineandshaperenderer.setItemLabelFont(new Font("宋体", Font.PLAIN, 8));
//        lineandshaperenderer.setItemLabelsVisible(true);
//        lineandshaperenderer.setItemLabelPaint(Color.blue);
        
        //设置线图刻度为%比
        if(percent != null && !percent.equalsIgnoreCase(""))
        {
        	NumberAxis numberaxis1 = new NumberAxis(percent);//"Percent"
            numberaxis1.setNumberFormatOverride(NumberFormat.getPercentInstance());
            categoryplot.setRangeAxis(1, numberaxis1);
        }
        
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
        
        ByteArrayOutputStream bos = null;
        try {
			bos = new ByteArrayOutputStream();
			ChartUtilities.writeChartAsJPEG(bos,quality,jfreechart,width,height,null);
		} 
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				if(bos != null)
				{
					bos.close();
				}
			} catch (Exception e) {}
		}
		
		return bos;
    }
	
	
	
	//==========================
	
	
	
	/**
	 * 线图
	 * @param Title
	 * @param targetString 
	 * @param valueString
	 * @param dataset
	 * @param Orientation
	 * @return
	 */
	private static OutputStream createLineChart(String Title ,String targetString
			,String valueString ,CategoryDataset dataset ,PlotOrientation Orientation,float quality,int width, int height,int fontSize )
	{
		ByteArrayOutputStream bos = null;
		JFreeChart chart = ChartFactory.createLineChart(
				Title, // 图表标题
				targetString, // 目录轴的显示标签
				valueString, // 数值轴的显示标签
				dataset, // 数据集
				Orientation, // 图表方向：水平、垂直
				true, 	// 是否显示图例(对于简单的柱状图必须是false)
				false, 	// 是否生成工具
				false 	// 是否生成URL链接
		);
		CategoryPlot plot = chart.getCategoryPlot();
		Util.setCategoryPlot(plot);
		CategoryItemRenderer renderer = plot.getRenderer();
		renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());

       //设置柱子上数值的字体
       renderer.setItemLabelFont(new Font("宋体",Font.PLAIN,fontSize));  
       renderer.setItemLabelsVisible(true);
       renderer.setItemLabelPaint(Color.RED);
       
       	// 设置最高的一个柱与图片顶端的距离,设置最低的一个柱与图片底端的距离
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setUpperMargin(0.3);
		rangeAxis.setLowerMargin(0.3);
		plot.setRangeAxis(rangeAxis); 
       
		try {
			bos = new ByteArrayOutputStream();
			ChartUtilities.writeChartAsJPEG(bos,quality,chart,width,height,null);
		} 
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				if(bos != null)
				{
					bos.close();
				}
			} catch (Exception e) {}
		}
		
		return bos;
	}
	
	/**
	 * 线图
	 * @param Title
	 * @param targetString 
	 * @param valueString
	 * @param dataset
	 * @param Orientation
	 * @return
	 */
	private static OutputStream createPieChart(String Title
			,DefaultPieDataset dataset,float quality,int width, int height,int fontSize )
	{
		ByteArrayOutputStream bos = null;
		JFreeChart chart = ChartFactory.createPieChart(
				Title,  // 图表标题
				dataset, 
				true, // 是否显示图例
				false,
				false
				);
		PiePlot pieplot = (PiePlot)chart.getPlot();
        pieplot.setLabelFont(new Font("宋体", 0, fontSize));
        pieplot.setNoDataMessage("无数据");
        pieplot.setCircular(true);
        pieplot.setLabelGap(0.02D);
        pieplot.setBackgroundPaint(new Color(199,237,204));
                pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{0} {2}",
                NumberFormat.getNumberInstance(),
                new DecimalFormat("0.00%")));
       
		try {
			bos = new ByteArrayOutputStream();
			ChartUtilities.writeChartAsJPEG(bos,quality,chart,width,height,null);
		} 
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				if(bos != null)
				{
					bos.close();
				}
			} catch (Exception e) {}
		}
		
		return bos;
	}
	
	/**
	 * 写文件
	 * @param fileStream
	 * @param filePath
	 */
	private static File writeFile(ByteArrayOutputStream fileStream,String filePath)
	{
		InputStream fis = new ByteArrayInputStream(fileStream.toByteArray());
		File file = new File(filePath);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			byte[] data = new byte[2048];
			int len = 0;
			while ((len = fis.read(data)) > 0) {

				fos.write(data, 0, len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(fis != null)
				{
					fis.close();
				}
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				if(fos != null)
				{
					fos.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				if(fileStream != null)
				{
					fileStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
	/**
	 * 验证图形type
	 * @param type
	 * @return
	 */
    private static boolean validateType(String type)
    {
    	boolean f = false;
//    	type: column 柱图 ，line 线图 ，columnline 柱线结合图
    	String[] str = Constants.G_TYPE;
    	for(int i=0;i<str.length;i++)
    	{
    		if(str[i].equalsIgnoreCase(type))
    		{
    			f = true;
    		}
    	}
    	return f;
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
		dataset.addValue(-240, "北京", "梨子");
		dataset.addValue(480, "北京", "香蕉");
//		dataset.addValue(370, "北京", "葡萄");
//		dataset.addValue(820, "北京", "荔枝");
		
		dataset.addValue(120, "上海", "苹果");
		dataset.addValue(250, "上海", "梨子");
		dataset.addValue(470, "上海", "香蕉");
//		dataset.addValue(380, "上海", "葡萄");
//		dataset.addValue(510, "上海", "荔枝");
		
		dataset.addValue(130, "广州", "苹果");
		dataset.addValue(260, "广州", "梨子");
		dataset.addValue(430, "广州", "香蕉");
//		dataset.addValue(390, "广州", "葡萄");
//		dataset.addValue(600, "广州", "荔枝");
		return dataset;
	}
	
	//s1为汇总字段，f4,f5,f6为指标字段 转换为Dataset 饼图图情况 只取一行记录 因为饼图只能显示1行记录所限制 
	
	//排好序的结果集
    public List initList()
    {
            Map d1 = new HashMap();
            d1.put("s1", "北京");
//            d1.put("s2", "南京");
            d1.put("f1", "410");
            d1.put("f2", "40");
            d1.put("f3", "580");
            d1.put("f4", "110");
            d1.put("f5", "0.6");
            d1.put("f6", "480");
            Map d2 = new HashMap();
            d2.put("s1", "上海");
//            d2.put("s2", "南京");
            d2.put("f1", "210");
            d2.put("f2", "270");
            d2.put("f3", "782");
            d2.put("f4", "250");
            d2.put("f5", "0.8");
            d2.put("f6", "144");
            Map d3 = new HashMap();
            d3.put("s1", "广州");
//            d3.put("s2", "南京");
            d3.put("f1", "16");
            d3.put("f2", "260");
            d3.put("f3", "380");
            d3.put("f4", "130");
            d3.put("f5", "0.2");
            d3.put("f6", "430");
//            Map d4 = new HashMap();
//            d4.put("s1", "01403");
//            d4.put("f4", "30");
//            d4.put("f5", "68");
//            d4.put("f6", "19");
//            Map d5 = new HashMap();
//            d5.put("s1", "01404");
//            d5.put("f4", "330");
//            d5.put("f5", "70");
//            d5.put("f6", "29");
//            Map d6 = new HashMap();
//            d6.put("s1", "01404");
//            d6.put("f4", "30");
//            d6.put("f5", "18");
//            d6.put("f6", "29");
//            
//            Map d7 = new HashMap();
//            d7.put("s1", "01408");
//            d7.put("f4", "30");
//            d7.put("f5", "18");
//            d7.put("f6", "29");
//            
//            Map d8 = new HashMap();
//            d8.put("s1", "01404");
//            d8.put("f4", "30");
//            d8.put("f5", "18");
//            d8.put("f6", "29");
//            
//            Map d9 = new HashMap();
//            d9.put("s1", "01404");
//            d9.put("f4", "30");
//            d9.put("f5", "18");
//            d9.put("f6", "29");
            
            List listResult = new ArrayList();
            listResult.add(d1);
            listResult.add(d2);
            listResult.add(d3);
//            listResult.add(d4);
//            listResult.add(d5);
//            listResult.add(d6);
//            listResult.add(d7);
//            listResult.add(d8);
//            listResult.add(d9);
            
            return listResult;
    }
    
	/**
	 * 获取一个演示用的简单数据集对象
	 * @return
	 */
	private static DefaultPieDataset getDataSetPie() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("苹果",100);
		dataset.setValue("梨子",200);
		dataset.setValue("葡萄",300);
		dataset.setValue("香蕉",400);
		dataset.setValue("荔枝",500);
		return dataset;
	}
}
