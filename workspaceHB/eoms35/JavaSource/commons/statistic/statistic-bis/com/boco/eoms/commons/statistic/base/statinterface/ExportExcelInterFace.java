package com.boco.eoms.commons.statistic.base.statinterface;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.statistic.base.config.model.KpiDefine;
import com.boco.eoms.commons.statistic.base.ehcahe.StatEhCacheBean;
import com.boco.eoms.commons.statistic.base.mgr.IStatManager;
import com.boco.eoms.commons.statistic.base.reference.StaticMethod;
import com.boco.eoms.commons.statistic.base.util.ExcelConverterUtil;

public class ExportExcelInterFace {
//	public static OutputStream ExportExcelToStream(StatEhCacheBean statEhCacheBean,IStatManager statManager) throws Exception
//	{
//		String excelConfigURL = statEhCacheBean.getExcelConfigURL();
//		excelConfigURL = String.valueOf(statManager.getStatConfigManager().getExcelConfigMap().get(excelConfigURL));
//		String reportName = statEhCacheBean.getSheet().getSheetName();
//		List listResult = statEhCacheBean.getListResult();
//		Map infoMap = statEhCacheBean.getConditionMap();
//		KpiDefine kpiDefine = statEhCacheBean.getKpiDefine();
//		
//		String ExcelConfigFilePath = StaticMethod.getFilePathForUrl(excelConfigURL);
//		OutputStream fileStream = statManager.exportExcelKpiStream(ExcelConfigFilePath, reportName, listResult, infoMap,kpiDefine);
//		
////		ExcelConverterUtil.writeFile((ByteArrayOutputStream)fileStream,"E:/excel.xls");
//		
//		return fileStream;
//	}
}
