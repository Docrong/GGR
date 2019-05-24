package com.boco.eoms.extra.supplierkpi.report.common;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class ReportsBO {
//	private static final String FILE_PATH = "/report/configfiles/reports-config.xml";
	private static final String REPORT_NAME = "report";
	private Map reports = new HashMap();
	private Document doc;
	private static ReportsBO reportBO = new ReportsBO();
	private String realPath = "";
	
	
	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public static ReportsBO getInstance(){
		return reportBO;
	}
	
	public Map getReports(){
		return reports;
	}
	
	public void init(String _realPath){
		this.setRealPath(_realPath);
		SAXBuilder builder = new SAXBuilder();
		try {
			Properties properties=new Properties();
			properties.load(getClass().getResourceAsStream("CommonConfig.properties"));
			//System.out.println("reportXml= "+properties.getProperty("reportXml"));
			doc = builder.build(new File(_realPath + properties.getProperty("reportXml")));
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Element eReports = doc.getRootElement();
		if(eReports!=null){
			List lReports = eReports.getChildren(REPORT_NAME);
			if(lReports!=null && lReports.size()>0){
				Element eReport;
				Report report;
				for(int i=0;i<lReports.size();i++){
					eReport = (Element) lReports.get(i);
					report = new Report(eReport);
					this.reports.put(report.getId(), report);
				}
			}
		}
	}
	public void refresh(){
		SAXBuilder builder=new SAXBuilder();
		try{
			Properties properties=new Properties();
			properties.load(getClass().getResourceAsStream("CommonConfig.properties"));
			doc=builder.build(new File(realPath+properties.getProperty("reportXml")));
		}catch(JDOMException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		Element eReports=doc.getRootElement();
		if(eReports!=null){
			List lReports=eReports.getChildren(REPORT_NAME);
			if(lReports!=null && lReports.size()>0){
				Element eReport;
				Report report;
				for(int i=0;i<lReports.size();i++){
					eReport=(Element) lReports.get(i);
					report=new Report(eReport);
					this.reports.put(report.getId(), report);
				}
			}
		}
	}
	
	public Report getReport(String _id){
		if(reports.containsKey(_id)){
			return (Report)reports.get(_id);
		}else{
			return null;
		}
	}

}
