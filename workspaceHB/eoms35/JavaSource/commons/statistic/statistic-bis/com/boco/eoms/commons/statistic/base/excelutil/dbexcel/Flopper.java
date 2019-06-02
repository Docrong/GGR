package com.boco.eoms.commons.statistic.base.excelutil.dbexcel;

import java.sql.Connection;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Title:        
 * Description:  
 * Copyright:    Copyright (c) 2002
 * Company:      UNC
 * @author      zhouj@unc.com.cn
 * @version     1.0
 */
public interface Flopper {
	String conversionOp(
		Connection connection,
		String arg,
		int index,
		Document doc,
		Element table,
		Element row)
		throws Exception;
	void setParameter(String[] para);
	Connection getConnection();
	void closeConnection(Connection conn);
	void beginConvert();
	void endOfConvert();
	void afterConnected(Connection conn);
	void beforeClosed(Connection conn);
}
