package com.boco.eoms.commons.statistic.base.statinterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.boco.eoms.commons.statistic.base.anychart.anychartinterface.AnyChartDrawPic;
import com.boco.eoms.commons.statistic.base.anychart.bo.XmlSet;
import com.boco.eoms.commons.statistic.base.config.excel.Sheet;
import com.boco.eoms.commons.statistic.base.config.graphic.GraphicConfig;
import com.boco.eoms.commons.statistic.base.config.graphic.GraphicReport;
import com.boco.eoms.commons.statistic.base.reference.StaticMethod;
import com.boco.eoms.commons.statistic.base.util.Constants;
import com.boco.eoms.commons.statistic.base.util.ExcelConverterUtil;

public class GraphicsFromInterFace {
	
	private static Logger logger = Logger.getLogger(GraphicsFromInterFace.class);
	
	public static Document createGraphicsFromDocument(Sheet reportData,List listResult)
	{
		Document doc = null;
		GraphicConfig graphicConfig = reportData.getGraphicConfigObj();
		
		if(graphicConfig != null)
		{
			String name = reportData.getQueryName();
			
			try {
				GraphicReport graphicReport = graphicConfig.getGraphicReports(name);
				//设置图形报表配置文件的绝对路径
				String ABSConfigPath = StaticMethod.getFilePathForUrl(Constants.CLASSPATH);
				//TODO 需要获取正确的路径
				ABSConfigPath = ABSConfigPath.substring(0, ABSConfigPath.indexOf(Constants.WEB_INF)) + graphicReport.getConfigPath();
				ABSConfigPath = ExcelConverterUtil.replaceAll(ABSConfigPath,"\\","/");
				graphicReport.setConfigPath(ABSConfigPath);
				doc = AnyChartDrawPic.DrawPic(listResult,graphicReport);//调用图形报表接口
			} catch (Exception e) {
				System.err.print("请查看图形报表配置是否正确。");
				e.printStackTrace();
			}
		}
		return doc;
	}
	
	
	
	private static String foundGraphicsDocFile(String graphicsURL, String graphicsPartURl,Document document,String reportName) throws FileNotFoundException
	{
		String ABSURL = StaticMethod.getFilePathForUrl(graphicsURL);
		String graphicsFilePath = ABSURL.substring(0, ABSURL.indexOf(Constants.WEB_INF)) + graphicsPartURl + reportName + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xml";
		File file = null;
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			//format.setEncoding("utf-8");
			file = new File(graphicsFilePath);
			
			FileOutputStream output = new FileOutputStream(file);
			OutputStreamWriter outputwriter = new OutputStreamWriter(output,"utf-8");
			XMLWriter writer = new XMLWriter(outputwriter, format);
			writer.write(document);
			writer.flush();
			writer.close();
			outputwriter.close();
			logger.info("\n保存图形报表数据路径为： " + graphicsFilePath);
		} catch (IOException e) {
			System.err.println("\n保存图形报表需要的xml文件出错");
			e.printStackTrace();
		}
		
		return file.getName();
	}
	
	public static String createGraphicsXMLFile(GraphicReport graphicReport,Document document,String reportName,String graphicsfileURI) throws FileNotFoundException
	{
		String graphicReportFileName = GraphicsFromInterFace.foundGraphicsDocFile(
				Constants.CLASSPATH, graphicsfileURI, document, reportName);
		return graphicReportFileName;
	}
	
	public static void GraphicsFromConverter(Sheet reportData,List listResult,HttpServletRequest actionRequest,String graphicsfileURI) throws Exception
	{
		GraphicConfig graphicConfig = reportData.getGraphicConfigObj();
		if(graphicConfig == null)
		{
			new Exception("请查看图形报表配置文件配置是否正确");
		}
		
		//处理页面传过来的参数，例如：显示图表类型
		String graphicReportType = actionRequest.getParameter("graphicReportType");
		if(graphicReportType == null || "".equalsIgnoreCase(graphicReportType))
		{
			graphicReportType = Constants.COLUMN;//默认值//取值范围：column,line,pie,columnline
		}
		
		logger.info("\n图形报表类型 graphicReportType is " + graphicReportType);
		
		String name = reportData.getQueryName();
		GraphicReport graphicReport = graphicConfig.getGraphicReports(name);
		if(!Constants.COLUMNLINE.equalsIgnoreCase(graphicReportType))
		{
			graphicReport.setFieldDefinesType(graphicReportType);
		}
		graphicConfig.setGraphicReports(graphicReport, name);
		graphicReport.setConfigAndFalshPath(graphicReportType);
		reportData.setGraphicConfigObj(graphicConfig);
		
		Document document = GraphicsFromInterFace.createGraphicsFromDocument(reportData,listResult);
    	
		//生成document的XML
		String relativeConfigPath = graphicReport.getConfigPath();
		String relativeFlashPath = graphicReport.getFlashPath();
		
//		线柱结合的情况，设置图例
		if(Constants.COLUMNLINE.equalsIgnoreCase(graphicReportType))
		{
			List columnSimpleList = XmlSet.getColumnSimple(document);
			List lineSimpleList = XmlSet.getLineSimple(document);
			
			actionRequest.setAttribute("columnsample", columnSimpleList);
			actionRequest.setAttribute("linesample", lineSimpleList);
		}
		//保存图形报表需要的xml文件到磁盘
		String graphicReportFileName = GraphicsFromInterFace.createGraphicsXMLFile(graphicReport,
				 document, graphicReport.getName() + "-" + graphicReportType,graphicsfileURI);
		
		String graphicReportAbsFilePath = actionRequest.getContextPath() + "/" + graphicsfileURI + graphicReportFileName;
		String graphicConfigPath = actionRequest.getContextPath() + "/" + relativeConfigPath;
		String graphicFalshAbsFilePath = actionRequest.getContextPath() + "/" + relativeFlashPath;
		graphicConfigPath = ExcelConverterUtil.replaceAll(graphicConfigPath,"\\","/");
		graphicFalshAbsFilePath = ExcelConverterUtil.replaceAll(graphicFalshAbsFilePath,"\\","/");
		
		String title = graphicReport.getTitle();
		actionRequest.setAttribute("xmlpath", graphicReportAbsFilePath);//图形报表需要的Doc路径
		actionRequest.setAttribute("swfpath", graphicFalshAbsFilePath);//图形报表需要的Flash路径
		actionRequest.setAttribute("title",title);
		
	}
}
