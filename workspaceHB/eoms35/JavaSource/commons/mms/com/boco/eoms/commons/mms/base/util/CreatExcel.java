package com.boco.eoms.commons.mms.base.util;

import java.io.FileNotFoundException;

import com.boco.eoms.commons.statistic.base.mgr.impl.ExportExcel;

public class CreatExcel {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, Exception {
		// TODO Auto-generated method stub
		//111111111111111
//		String configpath = "classpath:config/mms/reporttemplate/reporttemplate-config.xml";
//    	Reports reports = (Reports) ParseXmlService.create().xml2object(Reports.class, StaticMethod.getFilePathForUrl(configpath));
//    	System.out.println(reports);
		
		//2222222222222222222
//		String inFilePath,
//		String sourceSheetName, 
//		List rslist, 
//		Map map, 
//		KpiDefine kpiDefine
		
		String excelPath = "classpath:config/mms/report-config/commonfault-config/oracle/statistic-config-excel-commonfault_T_resolve_KPI4_oracle.xls";
		String quaryPath = "classpath:config/mms/report-config/commonfault-config/oracle/statistic-config-query-commonfault_T_resolve_KPI4_oracle.xml";
		String sourceSheetName = "haha";

//		KpiDefine kpiDefine = ParseXML2Obj(KpiDefine.class,);
		
		ExportExcel ee = new ExportExcel();
//		ee.ResultExportExcel();
	}

}
