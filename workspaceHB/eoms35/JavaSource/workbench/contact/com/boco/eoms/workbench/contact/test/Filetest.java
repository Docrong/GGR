package com.boco.eoms.workbench.contact.test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.file.exception.FMException;
import com.boco.eoms.commons.file.sample.FMExportSample;
import com.boco.eoms.workbench.contact.sample.FMImportSample;
 
import com.boco.eoms.commons.file.service.IFMImportFileManager;

public class Filetest extends ConsoleTestCase{
	

	public void testImpt() {
			IFMImportFileManager ifm = (IFMImportFileManager) this
					.getBean("FMImportExcelFileManagerImpl");
			Map map = null;
			try {
				map = ifm
						.impt(
								StaticMethod.CLASSPATH_FLAG
										+ "com/boco/eoms/commons/file/sample/FMImportSample.xml",
								StaticMethod.CLASSPATH_FLAG
										+ "com/boco/eoms/commons/file/sample/FMImportSample.xls");
			} catch (FMException e) {
			 
				logger.error(e);
				fail();
			}
			assertNotNull(map);
			// 取第一页
			List page1 = (List) map.get(new Integer(0));
			assertNotNull(page1);
			for (int i = 0; i < page1.size(); i++) {
				FMImportSample fm = (FMImportSample) page1.get(i);
				assertNotNull(fm);
			}

			// 取第二页
			/*List page2 = (List) map.get(new Integer(1));
			assertNotNull(page2);

			for (int j = 0; j < page2.size(); j++) {
				FMImportSample fm = (FMImportSample) page2.get(j);
				assertNotNull(fm);
			}*/
		}
 
}
