package com.boco.eoms.commons.fileconfig.test.service;

import java.util.Iterator;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.commons.fileconfig.exception.ParseXMLException;
import com.boco.eoms.commons.fileconfig.sample.FMImportSheet;
import com.boco.eoms.commons.fileconfig.sample.FMImportSheets;
import com.boco.eoms.commons.fileconfig.service.impl.ParseXmlService;

/**
 * <p>
 * Title:castor mapping测试 依赖文件管理
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 27, 2007 5:23:50 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class ParseXmlManagerImplTest extends ConsoleTestCase {
	FMImportSheets sheets;

	public void testXml2objectWithImport() {

		try {
			sheets = (FMImportSheets) ParseXmlService
					.create()
					.xml2object(FMImportSheets.class, "FMImportMappingSample",
							"classpath:com/boco/eoms/commons/fileconfig/sample/FMImportSample.xml");
			assertNotNull(sheets);
			for (Iterator it = sheets.getSheet().iterator(); it.hasNext();) {

				FMImportSheet sheet = (FMImportSheet) it.next();
				if (!new Integer(1).equals(sheet.getNum())
						&& !new Integer(0).equals(sheet.getNum())) {
					fail("sample xml page元素的num属性只有1,0");
				}

			}

		} catch (ParseXMLException e) {
			e.printStackTrace();
			logger.error(e);

			fail("读取配置文件出错");
		}
	}

	public void testObject2xml() {
		testXml2objectWithImport();
		FMImportSheet sheet = (FMImportSheet) sheets.getSheet().iterator()
				.next();
		sheet.setClassName("modified");
		try {
			ParseXmlService
					.create()
					.object2xml(sheets, "FMImportMappingSample",
							"classpath:com/boco/eoms/commons/fileconfig/sample/FMImportSample.xml");
		} catch (ParseXMLException e) {
			fail();
		}
	}
}
