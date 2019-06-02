package com.boco.eoms.commons.file.service.impl;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.file.config.model.FMExportHeader;
import com.boco.eoms.commons.file.config.model.FMExportHeaders;
import com.boco.eoms.commons.file.config.model.FMExportPage;
import com.boco.eoms.commons.file.config.model.FMExportPages;
import com.boco.eoms.commons.file.exception.FMException;
import com.boco.eoms.commons.file.exception.FMExportCSVFileException;
import com.boco.eoms.commons.file.service.FMExportFileManagerAdpter;
import com.boco.eoms.commons.file.util.FileUtil;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 30, 2007 12:13:52 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class FMExportCSVFileManagerImpl extends FMExportFileManagerAdpter {
	private final Logger logger = Logger.getLogger(this.getClass());

	/**
	 * csv,单元格以逗号分隔
	 */
	public final static String CSV_CELL_SPLIT = ",";

	/**
	 * @see 
	 */
	public void export(Map map, String xmlPath, String filePath)
			throws FMException {

		if (map == null) {
			throw new FMExportCSVFileException("map 值为空");
		}

		// 构建Workbook对象, 只读Workbook对象
		// 直接从本地文件创建Workbook
		// 从输入流创建Workbook
		// 取filePath xml配的对象
		FMExportPages pages = null;
		PrintWriter pw = null;
		// 取xml中 header 属性上className的对象列表
		List list = null;

		// TODO 需要重构
		try {

			pages = (FMExportPages) this.fmParseXmlManager.xml2object(
					FMExportPages.class, "FMExportMapping", xmlPath);
			java.io.File file = new java.io.File(StaticMethod
					.getFilePath(filePath));
			// 如果文件不存，则建立
			if (!file.exists()) {
				file.createNewFile();
			}
			pw = new PrintWriter(new FileOutputStream(StaticMethod
					.getFilePath(filePath)));
			// 写内容
			// 遍历page,由于导入csv格式,xml 的page信息是无用的
			for (Iterator pageIt = pages.getPage().iterator(); pageIt.hasNext();) {
				FMExportPage page = (FMExportPage) pageIt.next();
				list = (List) map.get(page.getNum());
				pw.println();
				pw.println("页码:" + page.getNum() + "名称:" + page.getName());
				pw.println();
				// 遍历headers是为了取header
				for (Iterator headersIt = page.getHeaders().iterator(); headersIt
						.hasNext();) {
					FMExportHeaders headers = (FMExportHeaders) headersIt
							.next();
					// 写入表头,只写一行
					for (Iterator headerTitleIt = headers.getHeader()
							.iterator(); headerTitleIt.hasNext();) {
						FMExportHeader header = (FMExportHeader) headerTitleIt
								.next();
						// 写入xml所配置与字段名称对应的名称
						pw.print(header.getHeaderName());
						// 下一列
						pw.print(CSV_CELL_SPLIT);

					}
					// 换行
					pw.println();
					for (Iterator listIt = list.iterator(); listIt.hasNext();) {
						Object obj = listIt.next();
						// 遍历字段
						for (Iterator headerIt = headers.getHeader().iterator(); headerIt
								.hasNext();) {
							FMExportHeader header = (FMExportHeader) headerIt
									.next();

							Method method = null;
							// 通过反射调用get方法返回的cell对象
							Object robj = null;

							// 调用方法，通过调用getGetMethodName取得对象中的方法
							method = obj.getClass().getDeclaredMethod(
									FileUtil.getGetMethodName(header
											.getFieldName()), new Class[] {});
							robj = method.invoke(obj, new Object[] {});
							pw.print(robj);
							pw.print(",");
						}
						pw.println();
					}
				}
			}

		} catch (Exception e) {
			logger.error(e);
			throw new FMExportCSVFileException(e);
		} finally {
			pw.close();

		}

	}
}
