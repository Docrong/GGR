package readme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.message.dao.ISmsOuterConfig;
import com.boco.eoms.message.mgr.impl.SmsMonitorManagerImpl;

public class SmsOuterConfigImpl_HuBei implements ISmsOuterConfig {
    public boolean sendSms(String mobiles, String content) {
        DataSource db = (DataSource) ApplicationContextHolder.getInstance().getBean("msgdatasource");
        boolean returnStr = true;
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            String sql = "insert into sp_submit (desttermid,msgcontent) values(?,?)";
            conn = db.getConnection();
            pstmt = conn.prepareStatement(sql);
            content = new String(content);
            pstmt.setString(1, mobiles);
            pstmt.setString(2, content);
            pstmt.execute(sql);

        } catch (Exception e) {
            returnStr = false;
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
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
        // TODO 自动生成方法存根
        return false;
    }
}

