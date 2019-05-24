package com.boco.eoms.commons.statistic.base.anychart.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import com.boco.eoms.commons.statistic.base.anychart.bo.ParesXml;
import com.boco.eoms.commons.statistic.base.anychart.util.ReportTool;

/**
 * 报表对象
 * @author tomilee
 */
public class ReportBean {

	private static String PATH_PROPERTIES="CommonConfig.properties";
	private static String PATH_NAME = "reportXml";
	
	private Document doc;//配置文档
	
	private List blocks = new ArrayList();//数据类集合
	
	private List linesample = new ArrayList();
	private List columnsample = new ArrayList();
	
	private String name;//报表名称
	private String type;//报表类别[用例类别说明]
	private String path;//报表XML模板
	private String swfpath;//报表SWF模板
	private String outpath;//报表结果XML文件输出路径
	private boolean auto = true;//图例配置: true自动配置/false手动配置——按既定规则在HTML中输出

	/**
	 * REPORTBEAN
	 * @param reportid REPORT标识
	 * @param path 部署URL
	 */
	public ReportBean(String reportid,String path,HttpServletRequest request){
		Properties properties = new Properties();
		String configPath = "";
		try {
			properties.load(getClass().getResourceAsStream(ReportBean.PATH_PROPERTIES));
			configPath = path + properties.getProperty(ReportBean.PATH_NAME);		
			doc = ReportTool.readDocument(configPath);
			
			Element root = doc.getRootElement();
			Iterator reports = root.elementIterator("report");
			while(reports.hasNext()){
				Element report = (Element)reports.next();
				if(report.attribute("id").getValue().equals(reportid)){
					//文件路径信息
					setOutpath(report.attribute("outpath").getStringValue());
					setSwfpath(report.attribute("swfpath").getStringValue());
					setPath(report.attribute("path").getStringValue());					
					//报表名称
					setName(report.attribute("name").getStringValue());					
					//报表类型
					setType(report.attribute("type").getStringValue());
					//图例配置——默认true
					if(report.attribute("auto")!=null){
						if("no".equals(report.attribute("auto").getValue())){
							auto = false;
						}
					}
					//装载Block - SQL
					Element query = report.element("query");					
					GetBlocks(query,request);
					
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * REPORTBEAN - 统计报表 - 省略SQL拼接
	 * @param reportid REPORT标识
	 * @param path 部署URL
	 */
	public ReportBean(String reportid,String path){
		Properties properties = new Properties();
		String configPath = "";
		try {
			properties.load(getClass().getResourceAsStream(ReportBean.PATH_PROPERTIES));
			configPath = path + properties.getProperty(ReportBean.PATH_NAME);		
			doc = ReportTool.readDocument(configPath);
			
			Element root = doc.getRootElement();
			Iterator reports = root.elementIterator("report");
			while(reports.hasNext()){
				Element report = (Element)reports.next();
				if(report.attribute("id").getValue().equals(reportid)){
					//文件路径信息
					setOutpath(report.attribute("outpath").getStringValue());
					setSwfpath(report.attribute("swfpath").getStringValue());
					setPath(report.attribute("path").getStringValue());					
					//报表名称
					setName(report.attribute("name").getStringValue());					
					//报表类型
					setType(report.attribute("type").getStringValue());
					//图例配置——默认true
					if(report.attribute("auto")!=null){
						if("no".equals(report.attribute("auto").getValue())){
							auto = false;
						}
					}					
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSwfpath() {
		return swfpath;
	}
	public void setSwfpath(String swfpath) {
		this.swfpath = swfpath;
	}
	public String getOutpath() {
		return outpath;
	}
	public void setOutpath(String outpath) {
		this.outpath = outpath;
	}
	public boolean isAuto() {
		return auto;
	}
	public void setAuto(boolean auto) {
		this.auto = auto;
	}
	public Document getDoc() {
		return doc;
	}
	public List getBlocks() {
		return blocks;
	}
	public List getLinesample() {
		return linesample;
	}
	public void setLinesample(HashMap hmp) {
		this.linesample.add(hmp);
	}
	public List getColumnsample() {
		return columnsample;
	}
	public void setColumnsample(HashMap hmp) {
		this.columnsample.add(hmp);
	}
	
	/**
	 * Create BlockBeans
	 * @param query
	 * @param request
	 */
	private void GetBlocks(Element query, HttpServletRequest request) {
		List blocks = query.elements("block");
		for(int i = 0; i < blocks.size();i++){
			BlockBean bb = new BlockBean();
			Element block = (Element)blocks.get(i);
			
			//单个BLOCK节点基本属性装载
			HashMap hmp = new HashMap();
			//获取BLOCK配置属性
			List attributes = block.attributes();
			for(int k=0;k<attributes.size();k++){
				Attribute attribute = (Attribute)attributes.get(k);
				if(!"".equals(attribute.getStringValue())){
					hmp.put(attribute.getName(), attribute.getStringValue());
				}
			}
			bb.setAttibute(hmp);
			
			Element reference = block.element("reference");
			Element cycle = block.element("cycle");
			Element type = block.element("type");
			
			bb.setReference(reference.getTextTrim());
			bb.setCycle(cycle.getTextTrim());
			bb.setType(type.getTextTrim());
			
			//SQL语句的构造都是相同的，唯一的不同，REFERENCE
			if("yes".equals(bb.getReference())){
				Element sql = block.element("sql");
				//2个ASSORT节点，无周期,多类多指标
				if("multiline".equals(bb.getType()) || "multicolumns".equals(bb.getType())){//1-N指标:line\column
					ParesXml.CreateSqlList(bb,sql,request);
				}else{
					ParesXml.CreateSqls(bb,sql,request);
				}				
			}else{
				//只考虑LINE
				List sqls = block.elements("sql");
				Element mohter = (Element)sqls.get(0);
				ParesXml.CreateSqls(bb,mohter,request);				
				Element child = (Element)sqls.get(1);
				ParesXml.CreateSqlList(bb,child,request);				
			}
			
			this.blocks.add(bb);
		}
	}

	
}
