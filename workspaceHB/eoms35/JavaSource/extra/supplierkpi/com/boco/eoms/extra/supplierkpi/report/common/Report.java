package com.boco.eoms.extra.supplierkpi.report.common;

import java.util.List;

import org.jdom.Element;


public class Report {
	
	private static final String DBINFO_NAME = "dbInfo";
	private static final String ID_NAME = "id";
	private static final String NAME_NAME = "name";
	private static final String CLASSPATH_NAME = "classPath";
	private static final String URL_NAME = "url";
	private static final String USER_NAME = "user";
	private static final String PASSWORD_NAME = "password";
	private static final String REPORTINFO_NAME = "reportInfo";
	private static final String DESTPATH_NAME = "destPath";
	private static final String REPORTPATH_NAME = "reportPath";
	private static final String SQL_NAME = "sql";
	private static final String REALTIME = "realtime";
	private static final String PARAMETERS_NAME = "parameters";
	private static final String PARAMETER_NAME_NAME = "name";
	private static final String PARAMETER_TYPE_NAME = "type";
	
	private String id = "";
	private String name = "";
	private DBInfo dbInfo = new DBInfo();
	private ReportInfo reportInfo = new ReportInfo();
	
	public Report(Element _eReport){
		setReportInfo(_eReport);
	}
	
	private void setReportInfo(Element _eReport){
		Element eDBInfo = _eReport.getChild(DBINFO_NAME);
		this.setId(_eReport.getAttributeValue(ID_NAME));
		this.setName(_eReport.getAttributeValue(NAME_NAME));
		
		this.dbInfo = new DBInfo();
		this.dbInfo.setClassPath(eDBInfo.getChildText(CLASSPATH_NAME));
		this.dbInfo.setUrl(eDBInfo.getChildText(URL_NAME));
		this.dbInfo.setUser(eDBInfo.getChildText(USER_NAME));
		this.dbInfo.setPassword(eDBInfo.getChildText(PASSWORD_NAME));
		
		Element eReportInfo = _eReport.getChild(REPORTINFO_NAME);
		this.reportInfo.setDestPath(eReportInfo.getChildText(DESTPATH_NAME));
		this.reportInfo.setReportPath(eReportInfo.getChildText(REPORTPATH_NAME));
		this.reportInfo.setSql(eReportInfo.getChildText(SQL_NAME));
		boolean realtime = new Boolean(eReportInfo.getChildText(REALTIME).trim()).booleanValue();
		this.reportInfo.setRealtime(realtime);
		
		if(eReportInfo.getChild(PARAMETERS_NAME)!=null){
			Element eParameters = eReportInfo.getChild(PARAMETERS_NAME);
			List lParameters = (List) eParameters.getChildren();
			if(lParameters.size()>0){
				Element eParameter;
				Parameter[] parameters = new Parameter[lParameters.size()];
				for(int i=0;i<lParameters.size();i++){
					eParameter = (Element) lParameters.get(i);
					Parameter param = new Parameter();
					param.setName(eParameter.getAttributeValue(PARAMETER_NAME_NAME));
					param.setType(eParameter.getAttributeValue(PARAMETER_TYPE_NAME));
					param.setValue(eParameter.getValue());
					parameters[i] = param;
				}
				this.reportInfo.setParameters(parameters);
			}
		}
	}
	
	
	public DBInfo getDbInfo() {
		return dbInfo;
	}
	public void setDbInfo(DBInfo dbInfo) {
		this.dbInfo = dbInfo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ReportInfo getReportInfo() {
		return reportInfo;
	}
	public void setReportInfo(ReportInfo reportInfo) {
		this.reportInfo = reportInfo;
	}
	
}
