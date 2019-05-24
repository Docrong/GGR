package com.boco.eoms.workbench.contact.bo;

import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.Region;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.workbench.contact.sample.FMExportSample;
import com.boco.eoms.workbench.contact.util.ContactAttriubuteLocator;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContact;
import java.util.*;

public class WorkbenchContactBO {

	/**
	 *   
	 */
	public String exportModelToExcel(List list) {

		String timeTag = StaticMethod.getCurrentDateTime("yyyy_MM_dd_HHmmss");
		String uploadPath = ContactAttriubuteLocator.getNetDiskAttributes()
				.getContactRootPath();// 取当前系统路径
		String filePath = uploadPath + timeTag + ".xls";
		try {

			// 建立输出流

			FileOutputStream fos = new FileOutputStream(filePath);
			// 建立一个新的工作表
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet();

			HSSFRow row = null; // 建立新行
			HSSFCell cell = null; // 建立新cell
			HSSFCellStyle cellStyle = wb.createCellStyle();

			cellStyle.setWrapText(true);
			cellStyle.setVerticalAlignment((short) 1); // 设置垂直居中
			cellStyle.setAlignment((short) 2); // 设置水平居中
			TawWorkbenchContact fme = null;

			try {
  
				/** ********************配置常量单元格信息 start************************ */
				// 常量值
				String[] valueOfLine = { "人员名称", "联系人所属部门名称	", "联系人职务",
						"联系人电话号码", " 联系人地址", "联系人电子邮件地址" }; // gong
				// 对应的行
				int[] rowOfLine = { 0, 0, 0, 0, 0, 0 };
				// 对应的列
				int[] colOfLine = { 0, 1, 2, 3, 4, 5 };
				// xy
				// 没用上
				/*
				 * int[][] xyOfLine = { { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 }, {
				 * 1, 1 }, { 1, 1 } };
				 */
				for (int i = 0; i < valueOfLine.length; i++) {
					row = sheet.createRow((short) rowOfLine[i]); // 建立新行
					cell = row.createCell((short) colOfLine[i]); // 建立新cell
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(valueOfLine[i]);

				}
				int y = 1;
				/** ********************配置常量单元格信息 end************************ */
				// 循环处理执行内容
				for (int i = 0; i < list.size(); i++) {

					fme = (TawWorkbenchContact) list.get(i);
					row = sheet.createRow((short) y);
					cell = row.createCell((short) 0);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(StaticMethod.null2String(fme
							.getContactName()));

					cell = row.createCell((short) 1);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(fme.getDeptName());

					cell = row.createCell((short) 2);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(StaticMethod.null2String(fme
							.getPosition()));

					cell = row.createCell((short) 3);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(StaticMethod.null2String(fme.getTele()));

					cell = row.createCell((short) 4);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(StaticMethod
							.null2String(fme.getAddress()));

					cell = row.createCell((short) 5);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(StaticMethod.null2String(fme.getEmail()));

					// 定义单元格
					y++;

				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

			// 写信息－end
			// 写入输出流
			wb.write(fos);
			fos.close(); // 关闭输出流
			return filePath;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
