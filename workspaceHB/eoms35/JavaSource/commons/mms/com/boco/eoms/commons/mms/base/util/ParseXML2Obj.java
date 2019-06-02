package com.boco.eoms.commons.mms.base.util;

import java.io.FileNotFoundException;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.statistic.base.config.model.KpiConfig;
import com.boco.eoms.commons.statistic.base.reference.ParseXmlManagerImpl;

public class ParseXML2Obj {

	/**
	 * 
	 * @param cls 类名称
	 * @param path 绝对路径
	 * @return
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	public static Object ParseXML2Obj(Class cls,String path) throws FileNotFoundException, Exception
	{
		Object obj = new ParseXmlManagerImpl().xml2object(cls, path);
		return obj;
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String configpath = "classpath:config/mms/report-config/commonfault-config/oracle/statistic-config-query-commonfault_T_resolve_KPI4_oracle.xml";//"classpath:config/mms-config/reporttemplate/reporttemplate-config.xml";
		
		String ABSPath = StaticMethod.getFilePathForUrl(configpath);
		Object obj = ParseXML2Obj(KpiConfig.class/*Reports.class*/,ABSPath);
		System.out.print(obj);
	}

}
