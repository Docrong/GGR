/**
 * @see
 * <p>功能描述：封装值班记录的业务逻辑类。</p>
 * <p>使用举例：首先实例化该类，然后通过实例化该类的对象，调用相应方法。</p>
 * @author 赵川
 * @version 1.0
 */

package com.boco.eoms.km.duty.bo;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.*;
import org.apache.struts.util.*;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.bo.BO;
import com.boco.eoms.duty.model.*;
import com.boco.eoms.duty.controller.*;
import com.boco.eoms.duty.dao.*;
import com.boco.eoms.workbench.contact.util.ContactAttriubuteLocator;
import com.boco.eoms.common.util.*;
import java.io.*;
import java.sql.*;
import java.util.*;

import javax.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.struts.action.*;
import org.apache.struts.util.*;

import com.boco.eoms.common.bo.BO;
import com.boco.eoms.duty.model.*;
import com.boco.eoms.duty.util.DutyMgrLocator;
import com.boco.eoms.duty.controller.*;
import com.boco.eoms.duty.dao.*;
import com.boco.eoms.km.duty.dao.KmrecordDAO;
import com.boco.eoms.km.duty.model.Kmrecord;
import com.boco.eoms.km.duty.webapp.form.KmaddonsTableForm;
import com.boco.eoms.workplan.util.TawwpStaticVariable;
import com.boco.eoms.workplan.util.TawwpUtil;

public class KmrecordBO extends BO {
	public KmrecordBO(com.boco.eoms.db.util.ConnectionPool ds) {
		super(ds);
	}

	/**
	 * @see 删除ID对应的值班记录以及子记录
	 * @param id
	 */
	public void deleteRmRecord(int id) {
		TawRmRecordDAO tawRmRecordDAO = null;
		TawRmRecordSubDAO tawRmRecordSubDAO = null;
		TawRmRecordPerDAO tawRmRecordPerDAO = null;
		// TawRmDefinemainDAO tawRmDefinemainDAO = null;
		try {
			tawRmRecordDAO = new TawRmRecordDAO(ds);
			tawRmRecordSubDAO = new TawRmRecordSubDAO(ds);
			tawRmRecordDAO.delete(id);
			tawRmRecordSubDAO.delete_workserial(id);
			tawRmRecordPerDAO = new TawRmRecordPerDAO(ds);
			tawRmRecordPerDAO.delete_workserial(id);

			// tawRmDefinemainDAO = new TawRmDefinemainDAO(ds);
			// tawRmDefinemainDAO.deleteWorkserial(id);
		} catch (SQLException e) {
		} finally {
			tawRmRecordDAO = null;
			tawRmRecordSubDAO = null;
			tawRmRecordPerDAO = null;
			// tawRmDefinemainDAO = null;
		}
	}

	/**
	 * @author gongyufeng
	 * @see 值班日志的导出功能
	 * @param strCondition
	 */
	public String exportToExcel(String strCondition) {

		/*
		 * String timeTag =
		 * StaticMethod.getCurrentDateTime("yyyy_MM_dd_HHmmss"); String
		 * uploadPath = ContactAttriubuteLocator.getNetDiskAttributes()
		 * .getContactRootPath();// 取当前系统路径 String filePath = uploadPath +
		 * timeTag + ".xls";
		 */
		String url = "";

		try {
			String timeTag = StaticMethod.getCurrentDateTime("yyyyMMddHH");
			String uploadPath = DutyMgrLocator.getAttributes()
					.getDutyRootPath()
					+ "/";

			String sysTemPaht = StaticMethod.getWebPath();

			url = sysTemPaht + "/" + uploadPath + timeTag + ".xls";
			// String path = sysTemPaht + url;

			// 建立输出流
			FileOutputStream fos = new FileOutputStream(url);
			// 建立一个新的工作表
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet();

			HSSFRow row = null; // 建立新行
			HSSFCell cell = null; // 建立新cell
			HSSFCellStyle cellStyle = wb.createCellStyle();
			; // 样式
			cellStyle.setWrapText(true);
			cellStyle.setVerticalAlignment((short) 1); // 设置垂直居中
			cellStyle.setAlignment((short) 2); // 设置水平居中

			// 写信息－start
			KmrecordDAO tawRmRecordDAO = new KmrecordDAO(ds);
			int y = 0;
			Kmrecord tawRmRecord = null;

			List tawRmRecords = null;

			/** ********************配置常量单元格信息 start************************ */
			// 常量值
			String[] valueOfLine = { "值班编号", "完成状态", "接班时间", "交班时间", "值班人员",
					"审核人", "审核时间", "值班记录" };
			// 对应的行
			int[] rowOfLine = { 0, 0, 0, 0, 0, 0, 0, 0 };
			// 对应的列
			int[] colOfLine = { 0, 1, 2, 3, 4, 5, 6, 7 };
			// xy
			int[][] xyOfLine = { { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 },
					{ 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 } };

			/** ********************配置常量单元格信息 end************************ */

			tawRmRecords = tawRmRecordDAO.searchAll(strCondition);

			wb.setSheetName(0, "值班记录查询结果", HSSFWorkbook.ENCODING_UTF_16);

			// 定义常量单元格
			for (int i = 0; i < valueOfLine.length; i++) {
				row = sheet.createRow((short) rowOfLine[i]); // 建立新行
				cell = row.createCell((short) colOfLine[i]); // 建立新cell
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(valueOfLine[i]);
				if (i == 7) {

					sheet.addMergedRegion(new Region((short) 0, (short) 7,
							(short) 0, (short) 8));

				} else {
					sheet.addMergedRegion(new Region((short) rowOfLine[i],
							(short) (colOfLine[i]), (short) (rowOfLine[i]
									+ xyOfLine[i][0] - 1),
							(short) (colOfLine[i] + xyOfLine[i][1] - 1)));
				}

			}

			y = 1;
			// 循环处理执行内容
			for (int i = 0; i < tawRmRecords.size(); i++) {// tawRmRecords.size();

				tawRmRecord = (Kmrecord) tawRmRecords.get(i);

				// 新的行
				row = sheet.createRow((short) ((i * 5) + y));
				sheet.addMergedRegion(new Region((short) ((i * 5) + y),
						(short) 0, (short) ((i * 5) + y) + 4, (short) 0));
				// HSSFCellStyle style = wb.createCellStyle();
				// style.setAlignment(HSSFCellStyle.ALIGN_CENTER );
				cell = row.createCell((short) 0);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(tawRmRecord.getId());

				String strFlag = "";
				if (tawRmRecord.getFlag() == 0) {
					strFlag = "未合并";
				} else {
					strFlag = "已合并";
				}
				sheet.addMergedRegion(new Region((short) ((i * 5) + y),
						(short) 1, (short) ((i * 5) + y) + 4, (short) 1));
				cell = row.createCell((short) 1);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(strFlag);

				sheet.addMergedRegion(new Region((short) ((i * 5) + y),
						(short) 2, (short) ((i * 5) + y) + 4, (short) 2));
				cell = row.createCell((short) 2);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(tawRmRecord.getStarttime());

				sheet.addMergedRegion(new Region((short) ((i * 5) + y),
						(short) 3, (short) ((i * 5) + y) + 4, (short) 3));
				cell = row.createCell((short) 3);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(tawRmRecord.getEndtime());

				sheet.addMergedRegion(new Region((short) ((i * 5) + y),
						(short) 4, (short) ((i * 5) + y) + 4, (short) 4));
				cell = row.createCell((short) 4);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(tawRmRecord.getDutyman());

				sheet.addMergedRegion(new Region((short) ((i * 5) + y),
						(short) 5, (short) ((i * 5) + y) + 4, (short) 5));
				cell = row.createCell((short) 5);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(tawRmRecord.getAuditor());

				sheet.addMergedRegion(new Region((short) ((i * 5) + y),
						(short) 6, (short) ((i * 5) + y) + 4, (short) 6));
				cell = row.createCell((short) 6);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(tawRmRecord.getAuTime());

				sheet.addMergedRegion(new Region((short) ((i * 5) + y),
						(short) 7, (short) ((i * 5) + y) + 4, (short) 7));
				cell = row.createCell((short) 7);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(tawRmRecord.getDutyrecord());
				/*
				 * row = sheet.createRow((short) i * 5 + y); cell =
				 * row.createCell((short) 7);
				 * cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				 * cell.setCellValue("传输");
				 * 
				 * row = sheet.createRow((short) i * 5 + y); cell =
				 * row.createCell((short) 8);
				 * cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				 * cell.setCellValue("");
				 * 
				 * row = sheet.createRow((short) i * 5 + y + 1); cell =
				 * row.createCell((short) 7);
				 * cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				 * cell.setCellValue("数据");
				 * 
				 * row = sheet.createRow((short) i * 5 + y + 1); cell =
				 * row.createCell((short) 8);
				 * cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				 * cell.setCellValue("");
				 * 
				 * row = sheet.createRow((short) i * 5 + y + 2); cell =
				 * row.createCell((short) 7);
				 * cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				 * cell.setCellValue("系统");
				 * 
				 * row = sheet.createRow((short) i * 5 + y + 2); cell =
				 * row.createCell((short) 8);
				 * cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				 * cell.setCellValue("");
				 * 
				 * row = sheet.createRow((short) i * 5 + y + 3); cell =
				 * row.createCell((short) 7);
				 * cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				 * cell.setCellValue("交换");
				 * 
				 * row = sheet.createRow((short) i * 5 + y + 3); cell =
				 * row.createCell((short) 8);
				 * cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				 * cell.setCellValue("");
				 * 
				 * row = sheet.createRow((short) i * 5 + y + 4); cell =
				 * row.createCell((short) 7);
				 * cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				 * cell.setCellValue("其他");
				 * 
				 * row = sheet.createRow((short) i * 5 + y + 4); cell =
				 * row.createCell((short) 8);
				 * cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				 * cell.setCellValue("");
				 */
				y++;

			}
			wb.write(fos);
			fos.close(); // 关闭输出流

		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	public void insert(KmaddonsTableForm tawRmAddonsTableForm) {
		TawRmRecordDAO tawRmRecordDAO = null;
		TawRmAddonsTable tawRmAddonsTable = null;
		try {
			tawRmRecordDAO = new TawRmRecordDAO(ds);
			tawRmAddonsTable = new TawRmAddonsTable();
			tawRmAddonsTable.setModel(tawRmAddonsTableForm.getModel());
			tawRmAddonsTable.setCreatTime(tawRmAddonsTableForm.getCreatTime());
			tawRmAddonsTable.setCreatUser(tawRmAddonsTableForm.getCreatUser());
			tawRmAddonsTable.setName(tawRmAddonsTableForm.getName());
			tawRmAddonsTable.setRemark(tawRmAddonsTableForm.getRemark());
			tawRmAddonsTable.setRoomId(tawRmAddonsTableForm.getRoomId());
			tawRmAddonsTable.setUrl(tawRmAddonsTableForm.getUrl());

			tawRmRecordDAO.insetAddons(tawRmAddonsTable);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 取得指定日期的所处月份的最后一天
	 * 
	 * @param date
	 *            指定日期。
	 * @return 指定日期的所处月份的最后一天
	 */
	public java.util.Date getLastDayOfMonth(java.util.Date date) {
		/**
		 * 详细设计： 1.如果date在1月，则为31日 2.如果date在2月，则为28日 3.如果date在3月，则为31日
		 * 4.如果date在4月，则为30日 5.如果date在5月，则为31日 6.如果date在6月，则为30日
		 * 7.如果date在7月，则为31日 8.如果date在8月，则为31日 9.如果date在9月，则为30日
		 * 10.如果date在10月，则为31日 11.如果date在11月，则为30日 12.如果date在12月，则为31日
		 * 1.如果date在闰年的2月，则为29日
		 */
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(Calendar.MONTH)) {
		case 0:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 1:
			gc.set(Calendar.DAY_OF_MONTH, 28);
			break;
		case 2:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 3:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 4:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 5:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 6:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 7:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 8:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 9:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 10:
			gc.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 11:
			gc.set(Calendar.DAY_OF_MONTH, 31);
			break;
		}
		// 检查闰年
		if ((gc.get(Calendar.MONTH) == Calendar.FEBRUARY)
				&& (isLeapYear(gc.get(Calendar.YEAR)))) {
			gc.set(Calendar.DAY_OF_MONTH, 29);
		}
		return gc.getTime();
	}

	// 检查输入的年是否为闰年
	public boolean isLeapYear(int year) {
		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}

	/**
	 * 将输入的字符串按照时间格式转换为DATE型
	 * @param strDate
	 * @param pattern
	 * @return java.util.Date
	 */
	public java.util.Date parseDateFormat(String strDate,String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		java.util.Date date = null;
		sdf.applyPattern(pattern);
		try {
			date = sdf.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
}