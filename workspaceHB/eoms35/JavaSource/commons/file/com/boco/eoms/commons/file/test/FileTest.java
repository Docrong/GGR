package com.boco.eoms.commons.file.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.file.exception.FMException;
import com.boco.eoms.commons.file.sample.FMExportSample;
import com.boco.eoms.commons.file.sample.FMImportSample;
import com.boco.eoms.commons.file.service.IFMExportFileManager;
import com.boco.eoms.commons.file.service.IFMImportFileManager;

/**
 * <p>
 * Title:对外接口测试用例
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 11, 2007 11:26:10 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class FileTest extends ConsoleTestCase {
	/**
	 * 测试导出csv
	 *  
	 */
	public void testExportCSV() {
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
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * excel的导出
	 *  
	 */
	public void testExportExcel() {

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
				.getBean("FMExportExcelFileManagerImpl");
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
									+ "com/boco/eoms/commons/file/sample/FMExportSample.xls");

		} catch (FMException e) {
			logger.error(e);
			fail();
		}
	}

	/**
	 * excel的导入
	 *  
	 */
	public void testImptExcel() {
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
			e.printStackTrace();
			fail();
		}
		assertNotNull(map);
		// 取第一页
		List page1 = (List) map.get(new Integer(0));
		assertNotNull(page1);
		for (int i = 0; i < page1.size(); i++) {
			FMImportSample fm = (FMImportSample) page1.get(i);
			assertNotNull(fm);
			// logger.debug("page1=memo====" + fm.getMemo());
			// logger.debug("page1=name====" + fm.getName());
			// logger.debug("memo+(i+1)====" + "memo" + (i + 1));
			// logger.debug("name+(i+1)====" + "name" + (i + 1));
			// assertEquals(fm.getMemo(), "memo" + (i + 1));
			// assertEquals(fm.getName(), "name" + (i + 1));

		}

		// 取第二页
		List page2 = (List) map.get(new Integer(1));
		assertNotNull(page2);

		for (int j = 0; j < page2.size(); j++) {
			FMImportSample fm = (FMImportSample) page2.get(j);
			assertNotNull(fm);
			// logger.debug("page2=memo====" + fm.getMemo());
			// logger.debug("page2=name====" + fm.getName());
			// logger.debug("memo+(i+1)====" + "memo" + (j + 1));
			// logger.debug("name+(i+1)====" + "name" + (j + 1));
			// assertEquals(fm.getMemo(), "memo" + (j + 1));
			// assertEquals(fm.getName(), "name" + (j + 1));
		}
	}

}
