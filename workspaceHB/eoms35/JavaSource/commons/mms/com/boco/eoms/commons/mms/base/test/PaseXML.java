package com.boco.eoms.commons.mms.base.test;

import java.io.FileNotFoundException;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.mms.base.config.Reports;
import com.boco.eoms.commons.mms.base.util.MMSConstants;
import com.boco.eoms.commons.statistic.base.reference.ParseXmlService;

public class PaseXML {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, Exception {
		// TODO Auto-generated method stub
		String configpath = MMSConstants.REPORT_CONFIG;
		Reports reports = (Reports) ParseXmlService.create().xml2object(
				Reports.class, StaticMethod.getFilePathForUrl(configpath));
	}

}
