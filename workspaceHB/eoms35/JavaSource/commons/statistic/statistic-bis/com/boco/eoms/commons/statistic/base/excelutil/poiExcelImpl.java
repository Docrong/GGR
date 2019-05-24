package com.boco.eoms.commons.statistic.base.excelutil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * @author 李振友
 * 
 */
public class poiExcelImpl implements IpoiExcel {
	/**
	 * 对Vector数据源将其里面的数据导入到excel表单
	 * 
	 * @param ObjectArray
	 *            数据源 用Vector对象
	 * @param fieldName
	 *            导出到excel文件里的表头名
	 * @param sheetName
	 *            工作表的名称
	 * @param output
	 *            java输出流
	 */
	public void ExportExcel(Vector ObjectArray, String[] fieldName,
			String sheetName, OutputStream output) {
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
		for (int i = 0; i < ObjectArray.size(); i++) {
			Vector tmp = (Vector) ObjectArray.get(i);
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
			output.flush();
			workbook.write(output);

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Output is closed");
		}

	}

	/*
	 * 把数据集rs中的数据导出至Excel工作表中。 传入参数：数据集rs，Excel文件名称xlsName，工作表名称sheetName。
	 */

	public static void resultSetToExcel(ResultSet rs, String xlsName,
			String sheetName) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, sheetName, HSSFWorkbook.ENCODING_UTF_16);
		HSSFRow row = sheet.createRow((short) 0);
		;
		HSSFCell cell;
		ResultSetMetaData md = rs.getMetaData();
		int nColumn = md.getColumnCount();
		// 写入各个字段的名称
		for (int i = 1; i <= nColumn; i++) {
			cell = row.createCell((short) (i - 1));
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(md.getColumnLabel(i));
		}

		int iRow = 1;
		// 写入各条记录，每条记录对应Excel中的一行
		while (rs.next()) {
			row = sheet.createRow((short) iRow);
			;
			for (int j = 1; j <= nColumn; j++) {
				cell = row.createCell((short) (j - 1));
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(rs.getObject(j).toString());
			}
			iRow++;
		}
		FileOutputStream fOut = new FileOutputStream(xlsName);
		workbook.write(fOut);
		fOut.flush();
		fOut.close();
		JOptionPane.showMessageDialog(null, "导出数据成功！");
	}
	
	 private static boolean readExcelToDB2() {
		  POIFSFileSystem fs = null;
		  HSSFWorkbook wb = null;
		  try {
		   fs = new POIFSFileSystem(new FileInputStream("d:\\poitest.xls"));
		   wb = new HSSFWorkbook(fs);
		  } catch (IOException e) {
		   e.printStackTrace();
		   return false;
		  }
		  HSSFSheet sheet = wb.getSheetAt(0);
		  HSSFRow row = null;
		  HSSFCell cell = null;
		  String name = "";
		  String id = "";
		  int rowNum, cellNum;
		  int i;
		  rowNum = sheet.getLastRowNum();
		  for (i = 0; i <= rowNum; i++) {
		   row = sheet.getRow(i);
		   //cellNum = row.getLastCellNum();
		   cell = row.getCell((short) 0);
		   name = cell.getStringCellValue();
		   cell = row.getCell((short) 1);
		   id =  cell.getRichStringCellValue().toString();
		   String sql = "insert into TEST(ID, NAME) values(" + id + ",'" + name + "')";
//		   try {
//		    stmt.executeUpdate(sql);
//		   } catch (SQLException e1) {
//		    e1.printStackTrace();
//		    return false;
//		   }
		   System.out.println(sql);
		  }
		  return true;
		 }
	 
	 public static void main(String[] args)
	 {
		 readExcelToDB2();
	 }
}
