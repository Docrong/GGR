package com.ggr.excel;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	private static String username = "eoms35";
	private static String password = "hbeoms35boco";
	private static String driver = "oracle.jdbc.OracleDriver";
	private static String url = "jdbc:oracle:thin:@133.0.252.4:1521:eoms10g";
	public static void main(String[] args) throws Exception {
		List excelList = read();

	}

	public static List read() {
//		String excelPath = "外包人员信息反馈.xlsx";
		String excelPath = "售后日报表20190412汽贸-OK.xlsx";
		List excelList = new ArrayList();
		try {
			// String encoding = "GBK";
			File excel = new File(excelPath);
			if (excel.isFile() && excel.exists()) { // 判断文件是否存在

				String[] split = excel.getName().split("\\."); // .是特殊字符，需要转义！！！！！
				Workbook wb;
				// 根据文件后缀（xls/xlsx）进行判断
				if ("xls".equals(split[1])) {
					FileInputStream fis = new FileInputStream(excel); // 文件流对象
					wb = new HSSFWorkbook(fis);
				} else if ("xlsx".equals(split[1])) {
					wb = new XSSFWorkbook(excel);
				} else {
					System.out.println("文件类型错误!");
					return null;
				}

				// 开始解析
				Sheet sheet = wb.getSheetAt(0); // 读取sheet 0
				for(int i=0;i<wb.getNumberOfSheets();i++) {
					System.out.println(wb.getSheetName(i));
				}
				
				System.out.println(sheet.getSheetName());
				int firstRowIndex = sheet.getFirstRowNum() + 0; // 第一行是列名，所以不读
				int lastRowIndex = sheet.getLastRowNum();
				System.out.println("$$nfirstRowIndex: " + firstRowIndex);
				System.out.println("^^lastRowIndex: " + lastRowIndex);

				for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) { // 遍历行
					 System.out.println("rIndex: " + rIndex);
					Row row = sheet.getRow(rIndex);
					if (row != null) {
						int firstCellIndex = row.getFirstCellNum();
						List list = new ArrayList();
						int lastCellIndex = row.getLastCellNum();
						for (int cIndex = 0; cIndex < 8; cIndex++) { // 遍历列
							Cell cell = row.getCell(cIndex);
							list.add(cell);
                         if (cell != null) {
                             System.out.print(cell.toString()+"--");
                         }
						}
						excelList.add(list);
					}
				}
				System.out.println(excelList.size());

			} else {
				System.out.println("找不到指定的文件");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return excelList;
	}

	public static List analysisList(List excelList) {
		List wblist = new ArrayList();
		for (int i = 0; i < excelList.size(); i++) {
			List list = (List) excelList.get(i);
			String wb = list.get(5).toString();
			if (wb.equals("外包") || wb.equals("代维")) {
				if (list.get(6) != null)
					wblist.add(list);
			}
		}
		System.out.println(excelList.size());
		for (int i = 0; i < wblist.size(); i++) {
			List list = (List) wblist.get(i);
			if (list.get(3).toString().indexOf("E") > 0) {
				String userid = list.get(3).toString();
				userid = userid.replace(".", "");
				userid = userid.substring(0, userid.indexOf("E"));
				if(userid.length()==10) {
					userid=userid+"0";
//					System.out.print(userid);
//					System.out.print("-->"+userid+"\n");
				}
				list.set(3, userid);
			}
			// System.out.println(list);
		}
		System.out.println(wblist.size());
		return wblist;

	}
}

	
	