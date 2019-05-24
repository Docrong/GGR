package com.boco.eoms.commons.statistic.base.excelutil.sample;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.commons.statistic.base.excelutil.mgr.impl.PoiExcelImpl;
import com.boco.eoms.commons.statistic.base.excelutil.mgr.impl.StatExcelImpl;
import com.boco.eoms.commons.statistic.base.util.FileUtil;

public class TestPoiExcel {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		TestPoiExcel tpe = new TestPoiExcel();
		//导出Excel
		tpe.testExportExcel();
		
		//导出Excel带有数据
//		tpe.testExportExcelList();
		
		//导入Excel为javaObject
//		tpe.testImportExcel();
		
		//List数据与样式模板结合生成Excel
//		tpe.testResultListExportExcel();
		
		//xml数据与样式模板结合生成Excel
		//tpe.testResultXMLExportExcel();

	}
	
	public void testExportExcel() throws FileNotFoundException, IllegalAccessException, InvocationTargetException, IOException, InstantiationException, ClassNotFoundException
	{
		PoiExcelImpl pe = new PoiExcelImpl();
		SamplePoiWork spw = new SamplePoiWork();
		String excelPath = "D:/poi/qt.xls";
		
		//导出Excel模板
		ByteArrayOutputStream baos = (ByteArrayOutputStream)pe.exportExcel(spw);
		FileUtil.writeFile(baos, excelPath);
	}
	
	public void testExportExcelList() throws FileNotFoundException, IllegalAccessException, InvocationTargetException, IOException, InstantiationException, ClassNotFoundException
	{
		PoiExcelImpl pe = new PoiExcelImpl();
		SamplePoiWork spw = new SamplePoiWork();
		String excelPath = "D:/poi/qt.xls";
		List list = new ArrayList();
		List list1 = new ArrayList();
		list1.add("1");
		list1.add("2");
		list1.add("3");
		list1.add("4");
		list1.add("15");
		list1.add("4");
		list1.add("6");
		List list2 = new ArrayList();
		list2.add("1");
		list2.add("2");
		list2.add("3");
		list2.add("4");
		list2.add("15");
		list2.add("4");
		list2.add("6");
		List list3 = new ArrayList();
		list3.add("1");
		list3.add("2");
		list3.add("3");
		list3.add("4");
		list3.add("15");
		list3.add("4");
		list3.add("6");
		
		list.add(list1);
		list.add(list2);
		list.add(list3);
		
		//导出Excel模板
		ByteArrayOutputStream baos = (ByteArrayOutputStream)pe.exportExcel(spw,list);
		FileUtil.writeFile(baos, excelPath);
	}
	
	
	
	public void testImportExcel() throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException
	{
		PoiExcelImpl pe = new PoiExcelImpl();
		SamplePoiWork spw = new SamplePoiWork();
		String excelPath = "D:/poi/qt.xls";
		
		//导入Excel为list
		List list = pe.importExcel(spw,excelPath);
		System.out.println(list);
	}
	
	public void testResultListExportExcel() throws Exception
	{
		StatExcelImpl sei = new StatExcelImpl();
		SampleListData sld = new SampleListData();
		ByteArrayOutputStream byteOutStream = (ByteArrayOutputStream)sei.resultExportExcel(sld);
		FileUtil.writeFile(byteOutStream, "D:/poi/resExcel.xls");
	}
	
	public void testResultXMLExportExcel() throws Exception
	{
		StatExcelImpl sei = new StatExcelImpl();
		SampleXmlData sxd = new SampleXmlData();
		ByteArrayOutputStream byteOutStream = (ByteArrayOutputStream)sei.resultExportExcel(sxd);
		FileUtil.writeFile(byteOutStream, "D:/poi/resExcel.xls");
		
	}
}
