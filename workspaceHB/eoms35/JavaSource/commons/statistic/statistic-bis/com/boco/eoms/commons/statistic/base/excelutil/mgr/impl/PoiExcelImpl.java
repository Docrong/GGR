package com.boco.eoms.commons.statistic.base.excelutil.mgr.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.boco.eoms.commons.statistic.base.excelutil.mgr.IPoiWork;

public class PoiExcelImpl {
	
	private static final int START_ROW = 2;

	/**
	 * 导出Excel模板
	 * @param ipw
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 * @throws InstantiationException 
	 */
	public OutputStream exportExcel(IPoiWork ipw) throws IllegalAccessException, InvocationTargetException, FileNotFoundException, IOException, InstantiationException, ClassNotFoundException
	{
		System.out.println(" 开始导出Excel文件 ");
		Object object = Class.forName(ipw.getBeanName()).newInstance();
		Field[] filed = object.getClass().getDeclaredFields();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ExcelWriter ew = new ExcelWriter(baos,ipw.getsheetName());
		//建立表头数据
		ew.createRow(0);
		for(int i=0;i<filed.length;i++)
		{
			//建立这一行数据的每个单元格
			ew.setCell(i, ipw.displayName(filed[i].getName()));
		}
		ew.export();
		System.out.println(" 导出Excel文件[成功] ");
		return baos;
	}
	
	/**
	 * 对Vector数据源将其里面的数据导入到excel表单
	 * 
	 * @param ipw
	 * @param list
	 *            数据源 用List对象,List中放置的也是List对象 保存的顺序需要与IPoiWork接口的Bean相一致。
	 * @return java输出流
	 * 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public OutputStream exportExcel(IPoiWork ipw,List list) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		Object object = Class.forName(ipw.getBeanName()).newInstance();
		Field[] filed = object.getClass().getDeclaredFields();
		String[] fieldName = new String[filed.length];
		for(int i=0;i<filed.length;i++)
		{
			
			fieldName[i] = ipw.displayName(filed[i].getName());
		}
		String sheetName = ipw.getsheetName();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 产生工作薄对象
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 产生工作表对象
		HSSFSheet sheet = workbook.createSheet();
		// 为了工作表能支持中文,设置字符集为UTF_16
		workbook.setSheetName(0, sheetName);
		// 产生一行
		HSSFRow row = sheet.createRow(0);
		// 产生单元格
		HSSFCell cell;
		// 写入各个字段的名称
		for (int i = 0; i < fieldName.length; i++) {
			// 创建第一行各个字段名称的单元格
			cell = row.createCell((short) i);
			// 设置单元格内容为字符串型
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// 为了能在单元格中输入中文,设置字符集为UTF_16
			// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			// 给单元格内容赋值
			cell.setCellValue(new HSSFRichTextString(fieldName[i]));
		}

		// 写入各条记录,每条记录对应excel表中的一行
		// 循环遍历 Vector 对象 Vector中放置的也是Vector对象
		for (int i = 0; i < list.size(); i++) {
			List tmp = (List) list.get(i);
			row = sheet.createRow(i + 1);
			for (int j = 0; j < tmp.size(); j++) {
				cell = row.createCell((short) j);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// System.out.println(ObjectArray[i][j]);
				cell.setCellValue(new HSSFRichTextString(
						(tmp.get(j) == null) ? "" : tmp.get(j).toString()));
			}
		}
		try {
			baos.flush();
			workbook.write(baos);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Output is closed");
		}
		
		return baos;
	}
	
	/**
	 * 导入Excel为List，list中存放的是实现IPoiWork接口的createBean方法的model
	 * @param ipw
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException 
	 * @throws InstantiationException 
	 */
	public List importExcel(IPoiWork ipw,String excelPath) throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException
	{
		Object object = Class.forName(ipw.getBeanName()).newInstance() ;
		Field[] filed = object.getClass().getDeclaredFields();
		String[] beanNames = new String[filed.length];
		for(int i=0;i<filed.length;i++)
		{
			beanNames[i] = filed[i].getName();
		}
		
		ExcelReader er = new ExcelReader();
		//String[] dbname = {"name","attribute","profession","chinese ","math","english","sum"};
		List list = er.readExcel2List(excelPath,beanNames,START_ROW);
		List modelList = new ArrayList();
		for(int i=0;i<list.size();i++)
		{
			Map properties =  (Map)list.get(i);
			Object o = Class.forName(ipw.getBeanName()).newInstance() ;
			BeanUtils.populate(o, properties);
			if(ipw.validate(o))//验证数据，只把符合条件的数据添加到List中
			{
				modelList.add(o);
			}
		}
		
		System.out.println(modelList);
		return modelList;
	}
}
