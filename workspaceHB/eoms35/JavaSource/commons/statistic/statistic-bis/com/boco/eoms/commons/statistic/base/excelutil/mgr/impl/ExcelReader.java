package com.boco.eoms.commons.statistic.base.excelutil.mgr.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.boco.eoms.commons.statistic.base.excelutil.model.ModelTemplete;

/** 
 * 读取Excel常用方法
 * @author caihua 
 * lizhenyou修改
 */
public class ExcelReader {
	private HSSFWorkbook wb = null;// book [includes sheet] 

	private HSSFSheet sheet = null;

	private HSSFRow row = null;

	private int sheetNum = 0; // 第sheetnum个工作表 

	private int rowNum = 0;

	private FileInputStream fis = null;

	private File file = null;

	public ExcelReader() {
	}

	public ExcelReader(File file) {
		this.file = file;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public void setSheetNum(int sheetNum) {
		this.sheetNum = sheetNum;
	}

	public void setFile(File file) {
		this.file = file;
	}

	/** 
	 * 读取excel文件获得HSSFWorkbook对象 
	 */
	public void open() throws IOException {
		fis = new FileInputStream(file);
		wb = new HSSFWorkbook(new POIFSFileSystem(fis));
		fis.close();
	}

	/** 
	 * 返回sheet表数目 
	 * 
	 * @return int 
	 */
	public int getSheetCount() {
		int sheetCount = -1;
		sheetCount = wb.getNumberOfSheets();
		return sheetCount;
	}

	/** 
	 * sheetNum下的记录行数 
	 * 
	 * @return int 
	 */
	public int getRowCount() {
		if (wb == null)
			System.out.println("=============>WorkBook为空");
		HSSFSheet sheet = wb.getSheetAt(this.sheetNum);
		int rowCount = -1;
		rowCount = sheet.getLastRowNum();
		return rowCount;
	}

	/** 
	 * 读取指定sheetNum的rowCount 
	 * 
	 * @param sheetNum 
	 * @return int 
	 */
	public int getRowCount(int sheetNum) {
		HSSFSheet sheet = wb.getSheetAt(sheetNum);
		int rowCount = -1;
		rowCount = sheet.getLastRowNum();
		return rowCount;
	}

	/** 
	 * 得到指定行的内容 
	 * 
	 * @param lineNum 
	 * @return String[] 
	 */
	public String[] readExcelLine(int lineNum) {
		return readExcelLine(this.sheetNum, lineNum);
	}

	/** 
	 * 指定工作表和行数的内容 
	 * 
	 * @param sheetNum 
	 * @param lineNum 
	 * @return String[] 
	 */
	public String[] readExcelLine(int sheetNum, int lineNum) {
		if (sheetNum < 0 || lineNum < 0)
			return null;
		String[] strExcelLine = null;
		try {
			sheet = wb.getSheetAt(sheetNum);
			row = sheet.getRow(lineNum);

			int cellCount = row.getLastCellNum();
			strExcelLine = new String[cellCount + 1];
			for (int i = 0; i <= cellCount; i++) {
				strExcelLine[i] = readStringExcelCell(lineNum, i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strExcelLine;
	}

	/** 
	 * 读取指定列的内容 
	 * 
	 * @param cellNum 
	 * @return String 
	 */
	public String readStringExcelCell(int cellNum) {
		return readStringExcelCell(this.rowNum, cellNum);
	}

	/** 
	 * 指定行和列编号的内容 
	 * 
	 * @param rowNum 
	 * @param cellNum 
	 * @return String 
	 */
	public String readStringExcelCell(int rowNum, int cellNum) {
		return readStringExcelCell(this.sheetNum, rowNum, cellNum);
	}

	/** 
	 * 指定工作表、行、列下的内容 
	 * 
	 * @param sheetNum 
	 * @param rowNum 
	 * @param cellNum 
	 * @return String 
	 */
	public String readStringExcelCell(int sheetNum, int rowNum, int cellNum) {
		if (sheetNum < 0 || rowNum < 0)
			return "";
		String strExcelCell = "";
		try {
			sheet = wb.getSheetAt(sheetNum);
			row = sheet.getRow(rowNum);

			if (row.getCell((short) cellNum) != null) { // add this condition 
				// judge 
				switch (row.getCell((short) cellNum).getCellType()) {
				case HSSFCell.CELL_TYPE_FORMULA: //计算公式
				{
					//strExcelCell = "FORMULA ";
					HSSFFormulaEvaluator evaluator = new HSSFFormulaEvaluator(sheet, wb); 
					evaluator.setCurrentRow(row);
					HSSFFormulaEvaluator.CellValue cellValue = evaluator.evaluate(row.getCell((short) cellNum)); 
					switch (cellValue.getCellType()) 
					{ 
						case HSSFCell.CELL_TYPE_BOOLEAN: 
							//System.out.println(cellValue.getBooleanValue()); 
							strExcelCell = String.valueOf(cellValue.getBooleanValue());
							break; 
						case HSSFCell.CELL_TYPE_NUMERIC: 
							//System.out.println(cellValue.getNumberValue());
							strExcelCell =  String.valueOf(cellValue.getNumberValue());
							break; 
						case HSSFCell.CELL_TYPE_STRING: 
							//System.out.println(strValue);
							strExcelCell = String.valueOf(cellValue.getRichTextStringValue()) ;
							break; 
						case HSSFCell.CELL_TYPE_BLANK: 
							break; 
						case HSSFCell.CELL_TYPE_ERROR: 
							break; 
						// CELL_TYPE_FORMULA will never happen 
						case HSSFCell.CELL_TYPE_FORMULA: 
							break; 
					}
				}
					break;
				
				case HSSFCell.CELL_TYPE_NUMERIC: {
					strExcelCell = String.valueOf(row.getCell((short) cellNum).getNumericCellValue());
				}
					break;
				case HSSFCell.CELL_TYPE_STRING:
					strExcelCell = String.valueOf(row.getCell((short) cellNum).getRichStringCellValue());
					break;
				case HSSFCell.CELL_TYPE_BLANK:
					strExcelCell = "";
					break;
				default:
					strExcelCell = "";
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strExcelCell;
	}
	
	public static void main(String args[]) throws IllegalAccessException, InvocationTargetException {
//		File file = new File("D:\\poitest.xls");
//		ExcelReader readExcel = new ExcelReader(file);
//		try {
//			readExcel.open();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		readExcel.setSheetNum(0); // 设置读取索引为0的工作表 
//		// 总行数 
//		int count = readExcel.getRowCount();
//		for (int i = 0; i <= count; i++) {
//			String[] rows = readExcel.readExcelLine(i);
//			for (int j = 0; j < rows.length; j++) {
//				System.out.print(rows[j] + " ");
//			}
//			System.out.print("\n");
//		}
		ExcelReader er = new ExcelReader();
		String[] dbname = {"name","attribute","profession","chinese ","math","english","sum"};
		List list = er.readExcel2List("D:\\poi\\model.xls",dbname,2);
		
		List modelList = new ArrayList();
		for(int i=0;i<list.size();i++)
		{
			Map properties =  (Map)list.get(i);
			ModelTemplete mt = new ModelTemplete();
			BeanUtils.populate(mt, properties);
			modelList.add(mt);
		}
		
		Field[] filed = ModelTemplete.class.getDeclaredFields();
		
		System.out.println(modelList);
	}
	
	/**
	 * 读取excel为list
	 * @param xlsPath excel；路径
	 * @param name 表头的名称 model的属性名称
	 * @param sRow excel表头所在的行数
	 * @return
	 */
	public List readExcel2List(String xlsPath,String[] name,int sRow)
	{
		sRow = sRow - 1;
		File file = new File(xlsPath);
		ExcelReader readExcel = new ExcelReader(file);
		try {
			readExcel.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
		readExcel.setSheetNum(0); // 设置读取索引为0的工作表
		List list = new ArrayList();
		// 总行数 
		int count = readExcel.getRowCount();
		//验证
		if(validate(count,name.length))
		{
			System.err.println("配置文件的列大于Excel的列数，请检查");
		}
		for (int i = 0; i <= count; i++)
		{
			if(i<sRow)
			{
				continue;
			}
			Map map = new HashMap();
			String[] rows = readExcel.readExcelLine(i);
			for (int j = 0; j < name.length; j++) 
			{
				if(j>rows.length)//防止配置的列数大于可以输入的列数
				{
					break;
				}
				map.put(name[j], rows[j]);
				System.out.print(rows[j] + "  ");
			}
			list.add(map);
			System.out.print("\n");
		}
		return list;
	}
	
	private boolean validate(int m,int n)
	{
		boolean f = false;
		if(m>n)
		{
			f = true;
		}
		return f;
	}
}
