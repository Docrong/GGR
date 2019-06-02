package com.boco.eoms.commons.statistic.base.excelutil.mgr.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.exolab.castor.xml.Unmarshaller;

import com.boco.eoms.commons.statistic.base.config.report.Data;
import com.boco.eoms.commons.statistic.base.config.report.Datas;
import com.boco.eoms.commons.statistic.base.config.report.DisplayInfo;
import com.boco.eoms.commons.statistic.base.config.report.Field;
import com.boco.eoms.commons.statistic.base.config.report.Info;
import com.boco.eoms.commons.statistic.base.config.report.Report;
import com.boco.eoms.commons.statistic.base.config.report.Reports;
import com.boco.eoms.commons.statistic.base.excelutil.mgr.IListData;
import com.boco.eoms.commons.statistic.base.excelutil.mgr.IXmlData;
import com.boco.eoms.commons.statistic.base.mgr.impl.ExportExcel;


public class StatExcelImpl{
	
	/**
	 * 根据样式模板导出Excel
	 * @param ise List数据源
	 * @return Excel输出流，可以根据自己需要写入文件
	 * @throws Exception
	 */	
	public OutputStream resultExportExcel(IListData ild) throws Exception {

		ExportExcel ee = new ExportExcel();
		String excelPath = ild.getExcelPath();
		String sourceSheetName = ild.getSheetName();
		List rslist= ild.getData();
		Map map = ild.getInfoMap();
		
		return ee.ResultExportExcel(excelPath, sourceSheetName, rslist, map);
	}
	
	/**
	 * 根据样式模板导出Excel
	 * @param ixd XML数据源
	 * @return Excel输出流，可以根据自己需要写入文件
	 * @throws Exception
	 */
	public OutputStream resultExportExcel(IXmlData ixd) throws Exception {

		ExportExcel ee = new ExportExcel();
		String excelPath = ixd.getExcelPath();
		String sourceSheetName = ixd.getSheetName();
		//组成数据
		Reports reports = xml2ReportObject(ixd.getData());
		Report report = reports.getReportbyIndex(null);
		List rslist = report2List(report);
		//动态替换表头表尾数据
		Map map = getMapInfo(report);
		
		return ee.ResultExportExcel(excelPath, sourceSheetName, rslist, map);
	}
	
	// xml 映射为javaObject
	private static Reports xml2ReportObject(String xml) throws Exception 
	{
		try {
			return (Reports)xml2object(Reports.class,xml);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	//Reports转resList（包含多个map的结果集）
	private static List report2List(Report report)
	{
		List reList = new ArrayList();
		Datas datas = report.getDatas();
		Data[] data = datas.getDatas();
		
		for(int j = 0 ;j < data.length; j++)
		{
			Data d = data[j];
			Field fields[] = d.getFields();
			
			Map map = new HashMap();
			for(int k = 0 ; k<fields.length; k++)
			{
				Field f = fields[k];
				String id = f.getId();
				String value = f.getValue();
				String url = f.getUrl();
				
				map.put(id, value);
				map.put(id + "url", url);//标识唯一的url连接
			}
			
			reList.add(map);
		}
		
		return reList;
	}
	
	//读取mapInfo
	private static Map getMapInfo(Report report)
	{
		Map infoMap = new HashMap();
		
		DisplayInfo displayinfo = report.getDisplayinfo();
		Info infos[] = displayinfo.getInfos();
		for(int j=0;j<infos.length;j++)
		{
			Info info = infos[j];
			String name = info.getName();
			String value = info.getValue();
			infoMap.put(name, value);
		}
		
		return infoMap;
	}
	
	/**
	 * 通过实现xml2sheets业务方法,解析xml
	 *
	 * @param cls
	 *           Class名称
	 * @param xmlString
	 *           xml串
	 * @return 返回解析的对象
	 * @throws Exception
	 */
	private static Object xml2object(Class cls, String xmlString)
			throws Exception {
		InputStreamReader isr = null;
		ByteArrayInputStream bis = null;
		// 返回obj,调用者需强制转型
		Object obj = null;
		
		try {
			// 缓存
			// ClassDescriptorResolver cdr = new ClassDescriptorResovlerImpl();
			Unmarshaller un = new Unmarshaller(cls);
			// 设置不验证xml以加快�\uFFFD�度
			un.setValidation(false);
			// Unmarshaller un = new Unmarshaller(cls);
			// un.setResolver(cdr);

			// 读取xml文件
			// in = new FileReader(finalPath);
			bis = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
			isr = new InputStreamReader(bis,"UTF-8");
			obj = un.unmarshal(isr);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			if (bis != null) {
				try {
					// 关闭
					bis.close();
				} catch (IOException e) {
					throw new Exception(e);
				}
				finally{
					bis = null;
				}
			}
			if(isr != null)
			{
				isr.close();
				isr = null;
			}
		}
		return obj;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

}
