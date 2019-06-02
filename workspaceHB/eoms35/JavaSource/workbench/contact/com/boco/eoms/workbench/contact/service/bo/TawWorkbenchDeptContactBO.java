package com.boco.eoms.workbench.contact.service.bo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.workbench.contact.model.TawWorkbenchDeptContact;
import com.boco.eoms.workbench.contact.sample.FMImportSample;
import com.boco.eoms.workbench.contact.service.ITawWorkbenchDeptContactGroupManager;
import com.boco.eoms.workbench.contact.service.ITawWorkbenchDeptContactManager;
import com.boco.eoms.workbench.contact.util.ContactAttriubuteLocator;
 
public class TawWorkbenchDeptContactBO {
	private TawWorkbenchDeptContactBO() {

	}

	private static TawWorkbenchDeptContactBO instance = null;

	public static TawWorkbenchDeptContactBO getInstance() {
		if (instance == null) {
			instance = init();
		}  
		return instance;
	}

	private static TawWorkbenchDeptContactBO init() {
		instance = new TawWorkbenchDeptContactBO();
		return instance;
	}
	
	// 根据用户名称和删除标志得到用户所有的分组

	public List getNextLevecGroups(String nodeId,String userid, String deleted) {
		List list = null;
		try {
			ITawWorkbenchDeptContactGroupManager mgr = (ITawWorkbenchDeptContactGroupManager) ApplicationContextHolder.getInstance().getBean("ItawWorkbenchDeptContactGroupManager");
			list = new ArrayList();
			list = mgr.getNextLevecGroups(nodeId,userid, deleted);
		} catch (Exception ex) {
			BocoLog.error(TawWorkbenchDeptContactBO.class, " 查询用户" + userid
					+ " 的所有分组报�cuo cccc       错"+ ex.getMessage());
		}
		return list;
	}
	
	// 根据分组id得到分组下的人员列表

	public List getNextLevecContact(String userId,String group_id, String deleted) {
		List list = null;
		try {
			ITawWorkbenchDeptContactManager mgr = (ITawWorkbenchDeptContactManager) ApplicationContextHolder.getInstance().getBean("ItawWorkbenchDeptContactManager");
			list = new ArrayList();
			list = mgr.getNextLevecContacts(userId,group_id, deleted);
		} catch (Exception ex) {
			BocoLog.error(TawWorkbenchDeptContactBO.class, " 查询组" + group_id
					+ " 的所有人员报错" + ex.getMessage());
		}
		return list;
	}
	

	/**
	 * 将模版信息导出成Excel
	 * 
	 * @param _modelId
	 *            String 模版标识
	 * @return String 存储地址
	 */
	public String exportModelToExcel(List list, String url) {

		String timeTag = StaticMethod.getCurrentDateTime("yyyy_MM_dd_HHmmss");
		String uploadPath = ContactAttriubuteLocator.getNetDiskAttributes()
				.getContactRootPath();// 取当前系统路�?
		String filePath = url + "/" + uploadPath + timeTag + ".xls";

		try {

			// 建立输出�?

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
			TawWorkbenchDeptContact fme = null;

			try {

				/** ********************配置常量单元格信�?start************************ */
//				 常量值
				String[] valueOfLine = { "人员名称 ", "联系人所属部门名称	", "联系人职务",
						"联系人电话号码", " 联系人地址", "联系人电子邮件地址" }; // gong
				// 对应的行
				int[] rowOfLine = { 0, 0, 0, 0, 0, 0 };
				// 对应的列
				int[] colOfLine = { 0, 1, 2, 3, 4, 5 };
				// xy
				// 没用�?
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
				/** ********************配置常量单元格信�?end************************ */
				// 循环处理执行内容
				for (int i = 0; i < list.size(); i++) {

					fme = (TawWorkbenchDeptContact) list.get(i);
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

					// 定义单元�?
					y++;

				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

			// 写信息－end
			// 写入输出�?
			wb.write(fos);
			fos.close(); // 关闭输出�?
			return filePath;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List getDateFromExcel(String url) {
		List list = new ArrayList();
		FMImportSample tawWorkbenchDeptContact =null;
		try {

			// 以下语句读取生成的Excel文件内容
			FileInputStream fIn = new FileInputStream(url);
			HSSFWorkbook readWorkBook = new HSSFWorkbook(fIn);
			// HSSFSheet readSheet = readWorkBook.getSheet("firstSheet");
			HSSFSheet readSheet = readWorkBook.getSheetAt(0);
			// HSSFRow readRow = readSheet.getRow(0);

			int maxrow = readSheet.getPhysicalNumberOfRows();
			int col = readSheet.getDefaultColumnWidth();
			for (int i = 1; i < maxrow; i++) {
				HSSFRow readRow = readSheet.getRow(i);
				tawWorkbenchDeptContact = new FMImportSample();
				for (int j = 0; j < col - 2; j++) {
					HSSFCell readCell = readRow.getCell((short) j);
					if (j == 0) {
						String str = "";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str="";
						} else {
							str = readCell.toString();
						}
						tawWorkbenchDeptContact.setContactName(str);
					}
					if (j == 1) {
						String str = "";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str="";
						} else {
							str = readCell.toString();
						}
						tawWorkbenchDeptContact.setDeptName(str);
					}
					if (j == 2) {
						String str = "";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str="";
						} else {
							str = readCell.toString();
						}
						tawWorkbenchDeptContact.setPosition(str);
					}
					if (j == 3) {
						String str = "";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str="";
						} else {
							str = readCell.toString();
						}
						tawWorkbenchDeptContact.setTele(str);
					}
					if (j == 4) {
						String str = "";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str="";
						} else {
							str = readCell.toString();
						}
						tawWorkbenchDeptContact.setAddress(str);
					}
					if (j == 5) {
						String str = "";
						if (readRow.getCell((short) j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							str="";
						} else {
							str = readCell.toString();
						}
						tawWorkbenchDeptContact.setEmail(str);
					}
				 

				}
				list.add(tawWorkbenchDeptContact);

			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
