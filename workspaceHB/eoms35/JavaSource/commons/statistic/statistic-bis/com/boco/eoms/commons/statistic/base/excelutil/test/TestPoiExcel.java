package com.boco.eoms.commons.statistic.base.excelutil.test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.boco.eoms.commons.statistic.base.excelutil.mgr.impl.PoiExcelImpl;
import com.boco.eoms.commons.statistic.base.util.FileUtil;

public class TestPoiExcel {

	/**
	 * @param args
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, FileNotFoundException, IOException, InstantiationException, ClassNotFoundException {
		
		PoiExcelImpl pe = new PoiExcelImpl();
		SimplePoiWork spw = new SimplePoiWork();
		String excelPath = "D:/poi/qt.xls";
		
		//导出Excel模板
//		ByteArrayOutputStream baos = (ByteArrayOutputStream)pe.exportExcel(spw);
//		FileUtil.writeFile(baos, excelPath);
		
		//导入Excel为list
		List list = pe.importExcel(spw,excelPath);
		System.out.println(list);
	}

}
