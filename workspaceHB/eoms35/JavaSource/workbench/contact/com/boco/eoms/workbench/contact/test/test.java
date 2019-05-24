package com.boco.eoms.workbench.contact.test;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class test {
	public static String xlsFile = "e://new2008_07_24_162718.xls"; // 产生的Excel文件的名称

	public static void main(String args[]) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(); // 产生工作簿对象
			HSSFSheet sheet = workbook.createSheet(); // 产生工作表对象
			// 设置第一个工作表的名称为firstSheet
			// 为了工作表能支持中文，设置字符编码为UTF_16
			workbook
					.setSheetName(0, "firstSheet", HSSFWorkbook.ENCODING_UTF_16);
		 
			// 以下语句读取生成的Excel文件内容
			 
			FileInputStream fIn = new FileInputStream(xlsFile);
			HSSFWorkbook readWorkBook = new HSSFWorkbook(fIn);
			// HSSFSheet readSheet = readWorkBook.getSheet("firstSheet");
			HSSFSheet readSheet = readWorkBook.getSheetAt(0);
			// HSSFRow readRow = readSheet.getRow(0);
			System.out.println(readSheet.getPhysicalNumberOfRows());
			System.out.println(readSheet.getDefaultColumnWidth());
			int maxrow = readSheet.getPhysicalNumberOfRows();
			int col = readSheet.getDefaultColumnWidth();
			for (int i = 0; i < maxrow; i++) {
				HSSFRow readRow = readSheet.getRow(i);

				for (int j = 0; j < col - 2; j++) {
					HSSFCell readCell = readRow.getCell((short) j);
					System.out
							.println(readRow.getCell((short) j).getCellType());
					if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
						System.out.print("空格");
					} else {
						System.out.print(readCell.toString());
					}

				}
				System.out.println();
			}
			HSSFRow readRow = readSheet.getRow(0);
			// System.out.println(readRow.getRowNum());
			HSSFCell readCell = readRow.getCell((short) 0);
			 
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
