package com.boco.eoms.commons.statistic.base.test;

import com.boco.eoms.commons.statistic.base.config.model.KpiConfig;
import com.boco.eoms.commons.statistic.base.reference.ConsoleTestCase;
import com.boco.eoms.commons.statistic.base.reference.ParseXmlManagerImpl;
import com.boco.eoms.commons.statistic.base.reference.ParseXmlService;

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
	KpiConfig KpiConfig = null;

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public static void main(String[] args) throws Exception
	{
		String path = "classpath:config/statistic/commonfault-config/oracle/statistic-config-query-commonfault_T_resolve_KPI4_oracle.xml";
		Object obj = new ParseXmlManagerImpl().xml2object(KpiConfig.class, path);
		System.out.print("obj :" + obj);
	}
	
	public void testXml2objectWithImport() {

		try {
			KpiConfig = (KpiConfig) ParseXmlService
					.create().xml2object(KpiConfig.class, "classpath:config/statistic/commonfault-config/oracle/statistic-config-query-commonfault_T_resolve_KPI4_oracle.xml");
//					.xml2object(FMImportSheets.class, "FMImportMappingSample",
//							"classpath:com/boco/eoms/commons/fileconfig/sample/FMImportSample.xml");
			assertNotNull(KpiConfig);
			System.out.println(KpiConfig+"8888888888888888888888");
//			for (Iterator it = KpiConfig.getSheet().iterator(); it.hasNext();) {
//
//				FMImportSheet sheet = (FMImportSheet) it.next();
//				if (!new Integer(1).equals(sheet.getNum())
//						&& !new Integer(0).equals(sheet.getNum())) {
//					fail("sample xml page元素的num属性只有1,0");
//				}
//
//			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

			fail("读取配置文件出错");
		}
	}

	public void testObject2xml() {
//		testXml2objectWithImport();
//		KpiConfig sheet = (KpiConfig) sheets.getSheet().iterator()
//				.next();
//		sheet.setClassName("modified");
//		try {
//			ParseXmlService
//					.create()
//					.object2xml(sheets, "FMImportMappingSample",
//							"classpath:com/boco/eoms/commons/fileconfig/sample/FMImportSample.xml");
//		} catch (ParseXMLException e) {
//			fail();
//		}
	}
}
