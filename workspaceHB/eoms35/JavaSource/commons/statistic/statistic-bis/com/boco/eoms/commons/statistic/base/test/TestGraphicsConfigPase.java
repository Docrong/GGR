package com.boco.eoms.commons.statistic.base.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.boco.eoms.commons.statistic.base.config.graphic.GraphicConfig;
import com.boco.eoms.commons.statistic.base.reference.ConsoleTestCase;
import com.boco.eoms.commons.statistic.base.util.ExcelConverterUtil;
import com.boco.eoms.commons.statistic.base.util.GraphicsReportUtil;

public class TestGraphicsConfigPase extends ConsoleTestCase{
	private GraphicConfig graphicConfig = null;

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public static void main(String[] args) throws Exception, FileNotFoundException
	{
		String path = "E:/work/EOMSV3.5R1B2/source/EOMS/eoms35/JavaSource/commons/statistic/statistic-bis/com/boco/eoms/commons/statistic/base/test/GraphicConfig.xml";
		
		String gconfig = ExcelConverterUtil.InputStreamToString(new FileInputStream(path));
		
//		Object obj = new ParseXmlManagerImpl().xml2object(GraphicConfig.class, path);
		Object obj = GraphicsReportUtil.xml2object(GraphicConfig.class, gconfig);
		System.out.print("obj :" + obj);
	}
	
	public void testXml2objectWithImport() {
	}
	

}
