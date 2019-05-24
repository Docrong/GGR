package com.boco.eoms.workbench.contact.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.file.exception.FMException;
import com.boco.eoms.workbench.contact.sample.FMExportSample;
import com.boco.eoms.workbench.contact.sample.FMImportSample;
import com.boco.eoms.commons.file.service.IFMExportFileManager;
import com.boco.eoms.commons.file.service.IFMImportFileManager;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 26, 2007 11:11:00 PM
 * </p>
 * 
 * @author 龚玉峰
 * 
 * 
 */
public class ImportExcel extends ConsoleTestCase {

	public List contactImpt(String path) {
		FMImportSample fm = null;
		IFMImportFileManager ifm = (IFMImportFileManager) this
				.getBean("FMImportExcelFileManagerImpl");
		Map map = null;
		try {
			map = ifm
					.impt(
							StaticMethod.CLASSPATH_FLAG
									+ "com/boco/eoms/workbench/contact/sample/FMImportSample.xml",
							path);
		} catch (FMException e) {

			logger.error(e);
			fail();
		}
		assertNotNull(map);
		// 取第一页
		List page1 = (List) map.get(new Integer(0));
		assertNotNull(page1);
		for (int i = 0; i < page1.size(); i++) {
			fm = (FMImportSample) page1.get(i);
			assertNotNull(fm);
		}

		return page1;
	}

	public void testExport(List list, String filePath) {
		List list0 = new ArrayList();
		FMExportSample fme = null;
		for (int i = 0; i < list.size(); i++) {

			  fme = new FMExportSample();
			fme.setAddress("address" + i);
			fme.setContactName("contactName" + i);
			fme.setDeptName("contactName" + i);
			fme.setEmail("email" + i);
			fme.setPosition("position" + i);
			fme.setTele("tele" + i);
			list0.add(fme);

		}
		IFMExportFileManager efm = (IFMExportFileManager) this
				.getBean("FMExportExcelFileManagerImpl");
		// 模拟数据
		Map map = new HashMap();
		map.put(new Integer(0), list);

		try {
			efm.export(map, StaticMethod.CLASSPATH_FLAG

			+ "com/boco/eoms/workbench/contact/sample/FMExportSample.xml",
					filePath);

		} catch (FMException e) {
			logger.error(e);
			fail();

		}

	}

}
