package readme;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.message.dao.ISmsOuterConfig;
import com.boco.eoms.message.mgr.impl.SmsMonitorManagerImpl;

public class SmsOuterConfigImpl_JiLin implements ISmsOuterConfig {
	public boolean sendSms(String mobiles, String content) {
		content="卓越运维："+content.trim();
		boolean returnStr = true;
		if("13800000000".equals(mobiles)) {
			BocoLog.info(SmsMonitorManagerImpl.class, "13800000000不发送短信");
		} else {

			DataSource db = (DataSource)ApplicationContextHolder.getInstance().getBean("msgdatasource");
			Statement stmt = null;
			Connection conn = null;
			try {			
				conn = db.getConnection();
				stmt = conn.createStatement();
				content = new String(content.getBytes("GB2312"),"ISO-8859-1");
				String sql = "insert into sendsms (mobile,content) values ('"+ mobiles + "','" + content + "')";
				
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
		}
		return returnStr;
	}

	public boolean sendVoice(String t_no, String t_alloc_time, String t_finish_time, String t_content, String t_tel_num, String t_tel_num2, String dispatch_tel) {
		// TODO 自动生成方法存根
		return false;
	}
}
