package com.boco.eoms.commons.file.service.impl;

import java.awt.Color;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.file.config.model.FMExportHeaders;
import com.boco.eoms.commons.file.config.model.FMExportPage;
import com.boco.eoms.commons.file.config.model.FMExportPages;
import com.boco.eoms.commons.file.exception.FMException;
import com.boco.eoms.commons.file.exception.FMExportPdfFileException;
import com.boco.eoms.commons.file.service.FMExportFileManagerAdpter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * <p>
 * Title:pdf 导出
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 29, 2007 8:38:43 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class FMExportPdfFileManagerImpl extends FMExportFileManagerAdpter {
	// TODO 目前未使用
	private final Logger logger = Logger.getLogger(this.getClass());

	public void export(Map map, String xmlPath, String filePath)
			throws FMException {
		xmlPath = StaticMethod.getFilePath(xmlPath);
		filePath = StaticMethod.getFilePath(filePath);
		FMExportPages pages = null;
		Document document = new Document();
		try {
			pages = (FMExportPages) this.fmParseXmlManager.xml2object(
					FMExportPages.class, "FMExportMapping", xmlPath);
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			// 添加标题
			document.addTitle("title");
			document.addSubject("SUBJECT");

			document.addKeywords("keywords");
			document.addAuthor("author");
			document.addCreator("creator");
			// 生产者
			document.addProducer();
			document.addCreationDate();
			document.addHeader("name", "content");

			document.open();
			Chunk chunk1 = new Chunk("This text is underlined", FontFactory
					.getFont(FontFactory.HELVETICA, 12, Font.UNDERLINE));
			Phrase phrase = new Phrase();
			phrase.add(chunk1);
			// 页面
			for (Iterator pageIt = pages.getPage().iterator(); pageIt.hasNext();) {
				FMExportPage page = (FMExportPage) pageIt.next();
				document.add(new Paragraph("Hello World"));

				// ------------------------------------------
				// 创建一个有3列的表格
				PdfPTable tlb = new PdfPTable(3);
				// 定义一个表格单元
				PdfPCell cell = new PdfPCell(new Paragraph(
						"header with colspan 3"));
				// 定义一个表格单元的跨度
				cell.setColspan(3);
				// 把单元加到表格中
				tlb.addCell(cell);

				// 把下面这9项顺次的加入到表格中，当一行充满时候自动折行到下一行
				tlb.addCell("1.1");
				tlb.addCell("2.1");
				tlb.addCell("3.1");
				tlb.addCell("1.2");
				tlb.addCell("2.2");
				tlb.addCell("3.2");
				tlb.addCell("1.3");
				tlb.addCell("2.3");
				tlb.addCell("3.3");
				// 重新定义单元格
				cell = new PdfPCell(new Paragraph("cell test1"));
				// 定义单元格的框颜色
				cell.setBorderColor(new Color(255, 0, 0));
				// 把单元格加到表格上，默认为一个单元
				tlb.addCell(cell);
				// 重新定义单元格
				cell = new PdfPCell(new Paragraph("cell test2"));
				// 定义单元格的跨度
				cell.setColspan(2);
				// 定义单元格的背景颜色
				cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				// 增加到表格上
				tlb.addCell(cell);
				// 增加到文档中
				document.add(tlb);
				// -----------------------------------------------
				// TODO 要修改标题位置

				float[] widths = { 0.05f, 0.29f, 0.05f, 0.05f, 0.14f, 0.03f,
						0.04f, 0.04f, 0.05f, 0.05f, 0.08f, 0.08f, 0.05f };
				// new 一个13列的table
				PdfPTable table = new PdfPTable(13);
				// 设置table每一列的宽度，widths里写的是百分比，他们加和需要是1
				table.setWidths(widths);
				// 设置表格在页面上的宽度，设成100表示可以表格填满页面，但是要去掉页面margin
				table.setWidthPercentage(100);
				// 设置表格上端的空白距离，类似css中的margin-top:xxpx;这样在给表格加上标题后，标题就不会跟表格重叠在一起了。
				table.setSpacingBefore(3f);
				// 向表格里填数据
				for (int i = 0; i < 26; i++) {
					table.addCell(i + "dddddddddddd");
				}
				// 标题和表格组合
				document.add(new Paragraph("fkdakfkdsafkakfd", FontFactory
						.getFont(FontFactory.HELVETICA, 12, Font.BOLD,
								new Color(0, 0, 0))));
				// 由于设置了table.setSpacingBefore(3f);所以table跟标题不会重合。
				document.add(table);

				// 标题
				for (Iterator titleIt = page.getTitles().getTitle().iterator(); titleIt
						.hasNext();) {
					// FMExportTitle title = (FMExportTitle) titleIt.next();

				}

				// 书写列
				for (Iterator headersIt = page.getHeaders().iterator(); headersIt
						.hasNext();) {
					FMExportHeaders headers = (FMExportHeaders) headersIt
							.next();

					// 看作一个对象
					for (Iterator headerIt = headers.getHeader().iterator(); headerIt
							.hasNext();) {
						// FMExportHeader header = (FMExportHeader) headerIt
						// .next();

					}
				}
				// document.setPageSize(PageSize.A3);
				document.newPage();

			}

		} catch (Exception e) {
			logger.error(e);
			throw new FMExportPdfFileException(e);
		}

		// step 5: we close the document
		document.close();
	}
}
