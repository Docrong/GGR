package com.boco.eoms.commons.file.test.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.file.exception.FMException;
import com.boco.eoms.commons.file.sample.FMExportSample;
import com.boco.eoms.commons.file.service.IFMExportFileManager;

/**
 * <p>
 * Title:csv导出测试
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 30, 2007 12:41:30 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class FMExportCSVFileManagerImplTest extends ConsoleTestCase {



	public void testExport() {
		List list0 = new ArrayList();
		List list1 = new ArrayList();
		for (int i = 0; i < 11; i++) {

			FMExportSample fme = new FMExportSample();
			fme.setMemo("memo" + i);
			fme.setName("name" + i);
			list0.add(fme);
			list1.add(fme);
		}
		IFMExportFileManager efm = (IFMExportFileManager) this
				.getBean("FMExportCSVFileManagerImpl");
		// 模拟数据
		Map map = new HashMap();
		map.put(new Integer(0), list0);
		map.put(new Integer(1), list1);
		try {
			efm
					.export(
							map,
							StaticMethod.CLASSPATH_FLAG
									+ "com/boco/eoms/commons/file/sample/FMExportSample.xml",
							StaticMethod.CLASSPATH_FLAG
									+ "com/boco/eoms/commons/file/sample/FMExportSample.csv");

		} catch (FMException e) {
			logger.error(e);
			fail();
		}
	}

}
