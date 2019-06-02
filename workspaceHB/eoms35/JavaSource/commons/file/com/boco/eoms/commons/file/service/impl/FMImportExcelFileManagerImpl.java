package com.boco.eoms.commons.file.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.file.config.model.FMImportColumn;
import com.boco.eoms.commons.file.config.model.FMImportHeader;
import com.boco.eoms.commons.file.config.model.FMImportSheet;
import com.boco.eoms.commons.file.config.model.FMImportSheets;
import com.boco.eoms.commons.file.exception.FMException;
import com.boco.eoms.commons.file.exception.FMImportExcelFileException;
import com.boco.eoms.commons.file.service.FMImportFileManagerAdpter;
import com.boco.eoms.commons.file.util.FileUtil;
import com.boco.eoms.commons.fileconfig.exception.ParseXMLException;

/**
 * <p>
 * Title:导入操作,由excel为源读入
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 27, 2007 9:07:19 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class FMImportExcelFileManagerImpl extends FMImportFileManagerAdpter {

	/**
	 * xml中定义false
	 */
	private final static String FALSE = "false";

	private Logger logger = Logger
			.getLogger(FMImportExcelFileManagerImpl.class);

	/**
	 * 导入操作,由excel为源读入
	 * 
	 * @param xmlPath
	 *            导入所需的xml配置文件,如FMImportSample.xml文件
	 * @param filePath
	 *            excel(pdf,word)文件的路径
	 * @return map <string,list>,string为xml所配的sheet num做为key,list为className的列表
	 */
	public Map impt(String xmlPath, String filePath) throws FMException {

		Map map = new HashMap();
		// 取filePath xml配的对象
		// 构建Workbook对象, 只读Workbook对象
		// 直接从本地文件创建Workbook
		// 从输入流创建Workbook
		Workbook workbook = this.filePathOpenWorkbook(filePath);
		// 通过fileconfig(文件配置）获取castor的mapping对象
		FMImportSheets fmSheets;
		try {
			// 解析配置文件异常
			fmSheets = (FMImportSheets) this.fmParseXmlManager.xml2object(
					FMImportSheets.class, "FMImportMapping", xmlPath);
		} catch (ParseXMLException e) {
			logger.error(e);
			throw new FMImportExcelFileException("配置文件" + filePath + "异常");
		}

		if (null != fmSheets) {
			// 遍历 xml 中的sheet
			for (Iterator sheetIt = fmSheets.getSheet().iterator(); sheetIt
					.hasNext();) {
				// 取xml 中的sheet
				FMImportSheet fmSheet = (FMImportSheet) sheetIt.next();
				List list = new ArrayList();
				// 根据 xml中所配的页面取excel中的sheet
				Sheet sheet = workbook.getSheet(fmSheet.getNum().intValue());

				// fmSheet.getClassName();
				// 遍历xml 中的column列表
				for (Iterator columnIt = fmSheet.getColumn().iterator(); columnIt
						.hasNext();) {
					FMImportColumn column = (FMImportColumn) columnIt.next();

					// 若xml中配置的startRow小于endRow，则只读取startRow到endRow中的内容
					if (column.getStartRow().intValue() < column.getEndRow()
							.intValue()) {
						// 由column配的strartRow,endRow,循环读取sheet cell创建对象
						for (int i = column.getStartRow().intValue(); i < column
								.getEndRow().intValue(); i++) {
							// 按header.getCol(列）及行（i)取cell.getContent，逐一赋给header所配置的filedname,obj.setFiledname，值为cell.getContent
							list.add(this.cellContent2filedName(fmSheet
									.getClassName(), column.getHeaderMapping()
									.getHeader().iterator(), i, sheet));
						}
					}
					// 若xml中配置的startRow大于endRow，则读取startRow到第一个<isNull>false</isNull>且sheet.getCell(header.getCol(),
					// i)==null则结束
					else {
						for (int i = column.getStartRow().intValue();; i++) {
							// 判断若遇到<isNull>false</isNull>且sheet.getCell(header.getCol()).getContents等于null，则返回的object为null
							Object object = this
									.cellContent2filedNameForNullReturn(fmSheet
											.getClassName(), column
											.getHeaderMapping().getHeader()
											.iterator(), i, sheet);
							if (null == object) {
								break;
							}
							list.add(object);
						}
					}

				}
				map.put(fmSheet.getNum(), list);
			}
		}
		workbook.close();
		return map;
	}

	/**
	 * 获取实例化对象的方法并调用
	 * 
	 * @param cls
	 *            class
	 * @param obj
	 *            class实例化对象
	 * @param header
	 *            xml mapping对象( <header/>标签)的对象
	 * @param cell
	 *            单元格
	 * @throws FMImportExcelFileException
	 */
	private void getMethodAndInvokeMethod(Class cls, Object obj,
			FMImportHeader header, Cell cell) throws FMImportExcelFileException {
		try {

			Method method = cls.getDeclaredMethod(FileUtil
					.getSetMethodName(header.getFieldName()),
					new Class[] { FileUtil.getClass(header.getType()) });

			// 通过type类型取content对应的实例对象
			// 将content string转为iso
			method.invoke(obj, new Object[] { FileUtil.getObjectByType(cell
					.getContents(), header.getType()) });

		} catch (Exception e) {
			logger.error(e);
			throw new FMImportExcelFileException(header.getFieldName()
					+ "没有对应get/set方法或" + cell.getContents() + "不可以转换为"
					+ header.getType() + "类型");
		}

	}

	/**
	 * 通过filePath取workbook
	 * 
	 * @param filePath
	 *            excel文件路径
	 * @return
	 * @throws FMImportExcelFileException
	 */
	private Workbook filePathOpenWorkbook(String filePath)
			throws FMImportExcelFileException {

		try {
			// TODO
			return Workbook.getWorkbook(StaticMethod
					.getFileInputStream(filePath));
		} catch (Exception e) {
			throw new FMImportExcelFileException(filePath + "文件并不存在或文件不合法");
		}
	}

	/**
	 * className转为class
	 * 
	 * @param className
	 *            包名类名
	 * @return class
	 * @throws FMImportExcelFileException
	 */
	private Class className2Class(String className)
			throws FMImportExcelFileException {
		Class cls = null;
		try {
			cls = Class.forName(className);
		} catch (Exception e) {
			logger.error(e);
			throw new FMImportExcelFileException(e);
		}
		return cls;
	}

	/**
	 * 转Class实例化为对象
	 * 
	 * @param cls
	 *            某class
	 * @return cls的实例对象
	 * @throws FMImportExcelFileException
	 */
	private Object class2Object(Class cls) throws FMImportExcelFileException {
		try {

			return cls.newInstance();
		} catch (Exception e) {
			logger.error(e);
			throw new FMImportExcelFileException(e);
		}
	}

	/**
	 * 按header.getCol(列）及行（i)取cell.getContent，逐一赋给header所配置的filedname,obj.setFiledname，值为cell.getContent
	 * 
	 * @param className
	 *            类名
	 * @param headerIt
	 *            xml中配置的header标签的Iterator，其中含每列对应配置
	 * @param i
	 *            行数
	 * @param sheet
	 *            workSheet
	 * @return 根据xml定义被赋值的对象
	 * @throws FMImportExcelFileException
	 */
	private Object cellContent2filedName(String className, Iterator headerIt,
			int i, Sheet sheet) throws FMImportExcelFileException {
		Class cls = this.className2Class(className);
		Object obj = this.class2Object(cls);

		// 遍历xml 中所配的header
		for (; headerIt.hasNext();) {
			// 取xml 中配置的header
			FMImportHeader header = (FMImportHeader) headerIt.next();

			// i为行,header.getCol为列
			Cell cell = sheet.getCell(header.getCol().intValue(), i);

			// 获取class实例化的object对象，并解析xml每个单元格，将xml配置单元格的filedName相应调用object.setFiledName，值为cell.getContent
			this.getMethodAndInvokeMethod(cls, obj, header, cell);

		}
		return obj;
	}

	/**
	 * 按header.getCol(列）及行（i)取cell.getContent，逐一赋给header所配置的filedname,obj.setFiledname，值为cell.getContent
	 * 判断若遇到 <isNull>false
	 * </isNull>且sheet.getCell(header.getCol()).getContents等于null，则返回的object为null
	 * 
	 * @param className
	 *            类名
	 * @param headerIt
	 *            xml中配置的header标签的Iterator，其中含每列对应配置
	 * @param i
	 *            行数
	 * @param sheet
	 *            workSheet
	 * @return 根据xml定义被赋值的对象
	 * @throws FMImportExcelFileException
	 */
	private Object cellContent2filedNameForNullReturn(String className,
			Iterator headerIt, int i, Sheet sheet)
			throws FMImportExcelFileException {
		Class cls = this.className2Class(className);
		Object obj = this.class2Object(cls);

		// 遍历xml 中所配的header
		for (; headerIt.hasNext();) {
			// 取xml 中配置的header
			FMImportHeader header = (FMImportHeader) headerIt.next();

			// i为行,header.getCol为列
			Cell cell = null;
			try {
				cell = sheet.getCell(header.getCol().intValue(), i);
			} catch (RuntimeException e) {
				// logger.error(e);
				return null;
			}

			if (FALSE.equals(header.getIsNull())
					&& (cell == null || cell.getContents() == null || ""
							.equals(cell.getContents()))) {
				return null;
			}
			// 获取class实例化的object对象，并解析xml每个单元格，将xml配置单元格的filedName相应调用object.setFiledName，值为cell.getContent
			this.getMethodAndInvokeMethod(cls, obj, header, cell);

		}
		return obj;
	}
}
