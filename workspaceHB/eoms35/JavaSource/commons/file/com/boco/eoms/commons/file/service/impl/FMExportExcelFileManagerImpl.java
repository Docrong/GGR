package com.boco.eoms.commons.file.service.impl;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.file.config.model.FMExportHeader;
import com.boco.eoms.commons.file.config.model.FMExportHeaders;
import com.boco.eoms.commons.file.config.model.FMExportPage;
import com.boco.eoms.commons.file.config.model.FMExportPages;
import com.boco.eoms.commons.file.config.model.FMExportTitle;
import com.boco.eoms.commons.file.excel.JxlExcelFontStyle;
import com.boco.eoms.commons.file.excel.JxlExcelUtil;
import com.boco.eoms.commons.file.exception.FMException;
import com.boco.eoms.commons.file.exception.FMExportExcelFileException;
import com.boco.eoms.commons.file.service.FMExportFileManagerAdpter;
import com.boco.eoms.commons.file.util.FileUtil;

/**
 * <p>
 * Title:excel excel导出
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 27, 2007 8:51:30 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class FMExportExcelFileManagerImpl extends FMExportFileManagerAdpter {
	private Logger logger = Logger
			.getLogger(FMImportExcelFileManagerImpl.class);

	public void export(Map map, String xmlPath, String filePath)
			throws FMException {

		// 构建Workbook对象, 只读Workbook对象
		// 直接从本地文件创建Workbook
		// 从输入流创建Workbook
		// 取filePath xml配的对象
		FMExportPages pages = null;
		WritableWorkbook wwb = null;
		// 取xml中 header 属性上className的对象列表
		List list = null;
		// TODO 需要重构
		try {

			pages = (FMExportPages) this.fmParseXmlManager.xml2object(
					FMExportPages.class, "FMExportMapping", xmlPath);
			wwb = Workbook.createWorkbook(new File(StaticMethod
					.getFilePath(filePath)));

			if (pages != null) {
				// 遍历page
				for (Iterator pageIt = pages.getPage().iterator(); pageIt
						.hasNext();) {

					FMExportPage page = (FMExportPage) pageIt.next();
					list = (List) map.get(page.getNum());
					if (list == null) {
						// TODO 国际化exception msg
						throw new FMExportExcelFileException("xml配置的<page num="
								+ page.getNum() + ">所对应map.put("
								+ page.getNum() + ",list)的list为空");
					}
					WritableSheet ws = wwb.createSheet(page.getName(), page
							.getNum().intValue());
					if (page.getTitles() != null
							&& page.getTitles().getTitle() != null) {
						// 遍历title
						for (Iterator titleIt = page.getTitles().getTitle()
								.iterator(); titleIt.hasNext();) {

							FMExportTitle title = (FMExportTitle) titleIt
									.next();

							// 根据格式书写cell
							WritableCellFormat wcf = new jxl.write.WritableCellFormat(
									JxlExcelFontStyle.getWritableFont(title));
							// 书写title
							Label labelC = new Label(title.getStartCol()
									.intValue(),
									title.getStartRow().intValue(), title
											.getFont().getText(), wcf);
							ws.addCell(labelC);
							// 合并title单元格
							ws.mergeCells(title.getStartCol().intValue(), title
									.getStartRow().intValue(), title
									.getEndCol().intValue(), title.getEndRow()
									.intValue());
						}
					}

					// 遍历headers 准备写入传入对象中的数据
					for (Iterator headersIt = page.getHeaders().iterator(); headersIt
							.hasNext();) {
						FMExportHeaders headers = (FMExportHeaders) headersIt
								.next();
						// for (Iterator listIt = list.iterator(); listIt
						// .hasNext();) {
						for (int i = 0; i < list.size(); i++) {
							// Object obj = listIt.next();
							Object obj = list.get(i);
							/*
							 * // 计算结束行数 int endRow = 0; //
							 * 若map中对象列表的个数大于xml中配置由开始行到结束行显示个数,则将显示现有对象列表所有对象(设置行尾),否则使用xml配置的endRow
							 * if (list.size() > (headers.getEndRow().intValue() -
							 * headers .getStartRow().intValue())) {
							 * 
							 * endRow = headers.getStartRow().intValue() +
							 * list.size(); } else { endRow =
							 * headers.getEndRow().intValue(); } //
							 * if(list.size()) // 按行写入excel for (int i =
							 * headers.getStartRow().intValue(); i < endRow;
							 * i++) {
							 */
							// 遍历header，每个header一个对象
							for (Iterator headerIt = headers.getHeader()
									.iterator(); headerIt.hasNext();) {

								FMExportHeader header = (FMExportHeader) headerIt
										.next();

								Method method = null;
								// 通过反射调用get方法返回的cell对象
								Object robj = null;
								try {
									// 调用方法，通过调用getGetMethodName取得对象中的方法
									method = obj.getClass().getDeclaredMethod(
											FileUtil.getGetMethodName(header
													.getFieldName()),
											new Class[] {});
									robj = method.invoke(obj, new Object[] {});
								} catch (Exception e) {
									e.printStackTrace();
								}

								// 根据格式书写cell
								// WritableCellFormat wcf = new
								// jxl.write.WritableCellFormat(
								// JxlExcelFontStyle
								// .getWritableFont(title));
								// 书写title
								WritableCell cell = JxlExcelUtil.getCellByType(
										robj, null, header.getCol(),
										new Integer(i));
							

								ws.addCell(cell);

							}
							// }

						}
					}
				}
			}

		} catch (Exception e) {
			logger.error(e);
			throw new FMExportExcelFileException(e);
		}

		finally {
			try {
				wwb.write();
				wwb.close();
			} catch (Exception e) {
				throw new FMExportExcelFileException(e);
			}
		}

	}
}
