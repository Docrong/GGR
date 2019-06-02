package com.boco.eoms.commons.file.test.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.file.exception.FMException;
import com.boco.eoms.commons.file.sample.FMImportSample;
import com.boco.eoms.commons.file.service.IFMImportFileManager;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 28, 2007 5:11:46 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class FMImportExcelFileManagerImplTest extends ConsoleTestCase {


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
			// BocoLog.debug(this,"page1=memo====" + fm.getMemo());
			// BocoLog.debug(this,"page1=name====" + fm.getName());
			// BocoLog.debug(this,"memo+(i+1)====" + "memo" + (i + 1));
			// BocoLog.debug(this,"name+(i+1)====" + "name" + (i + 1));
			// assertEquals(fm.getMemo(), "memo" + (i + 1));
			// assertEquals(fm.getName(), "name" + (i + 1));

		}

		// 取第二页
		List page2 = (List) map.get(new Integer(1));
		assertNotNull(page2);

		for (int j = 0; j < page2.size(); j++) {
			FMImportSample fm = (FMImportSample) page2.get(j);
			assertNotNull(fm);
			// BocoLog.debug(this,"page2=memo====" + fm.getMemo());
			// BocoLog.debug(this,"page2=name====" + fm.getName());
			// BocoLog.debug(this,"memo+(i+1)====" + "memo" + (j + 1));
			// BocoLog.debug(this,"name+(i+1)====" + "name" + (j + 1));
			// assertEquals(fm.getMemo(), "memo" + (j + 1));
			// assertEquals(fm.getName(), "name" + (j + 1));
		}
	}
}
