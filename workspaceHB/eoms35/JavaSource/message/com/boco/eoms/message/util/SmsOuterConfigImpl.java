// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   SmsOuterConfigImpl.java

package com.boco.eoms.message.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.message.dao.ISmsOuterConfig;
import com.boco.eoms.message.mgr.impl.SmsMonitorManagerImpl;

// Referenced classes of package com.boco.eoms.message.util:
//            MsgHelp

public class SmsOuterConfigImpl
    implements ISmsOuterConfig
{

	public boolean sendSms(String mobiles, String content) {
		DataSource db = (DataSource)ApplicationContextHolder.getInstance().getBean("msgdatasource");
		boolean returnStr = true;
		Statement stmt = null;
		Connection conn = null;
		try {			
			conn = db.getConnection();
			stmt = conn.createStatement();
//			content = new String(content.getBytes("GB2312"),"ISO-8859-1");
			String sql = "insert into sp_submit (desttermid,msgcontent) values ('"+ mobiles + "','" + content + "')";
			
			stmt.execute(sql);
			
		} catch (Exception e) {
			returnStr = false;
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				BocoLog.error(SmsMonitorManagerImpl.class, e.getMessage());
			}
		}
		return returnStr;
	}

	public boolean sendVoice(String t_no, String t_alloc_time, String t_finish_time, String t_content, String t_tel_num, String t_tel_num2, String dispatch_tel) {
		// TODO Auto-generated method stub
		return false;
	}

}
