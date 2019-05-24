package com.boco.eoms.duty.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.duty.dao.TawRmRecordDAO;
import com.boco.eoms.duty.model.TawRmRecord;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class DutyAutoScheduler
    implements Job {
  private ConnectionPool ds = ConnectionPool.getInstance();
  public DutyAutoScheduler() {
  }

  public void execute(JobExecutionContext jobExecutionContext) throws
      JobExecutionException {
    BocoLog.info(this, 0, "行政班自动合并开始");
    Coalition();
    BocoLog.info(this, 0, "行政班自动合并完成");
  }

  /**
   * Coalition
   */
  private void Coalition() {

    TawRmRecordDAO dao = new TawRmRecordDAO(ds);
    TawRmRecord rmRecord = new TawRmRecord();
    List list = null;
    int workserial = 0;
    String rmContent = "";
    try {
      list = dao.listAutoMerge();
      for (int i = 0; i < list.size(); i++) {
        workserial = ( (Integer) list.get(i)).intValue();
        rmRecord = dao.retrieve(workserial);
        if (rmRecord != null) {
          rmContent = dao.coalitionSub(workserial);
          rmContent = StaticMethod.strFromPageToDB(rmContent);
          rmRecord.setDutyrecord(rmContent);
          rmRecord.setFlag(1);
          update(rmRecord);
        }
      }
    }
    catch (SQLException ex) {
      BocoLog.info(this, 0, "行政班自动合并异常");
    }

  }

  public void update(TawRmRecord tawRmRecord) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    String sql = null;

    try {
      conn = ds.getConnection();

      if (StaticMethod.getDbType().equals(StaticVariable.ORACLE)) {
        String sql1 =
            "alter session set NLS_DATE_FORMAT='YYYY-MM-DD HH24:MI:SS'";
        pstmt = conn.prepareStatement(sql1);
        pstmt.executeUpdate();
      }

      String starttime = StaticMethod.getTimeBeginString(-1);
      String endtime = StaticMethod.getTimeBeginString(0);

      sql = "UPDATE taw_rm_record SET  flag=?, dutyrecord=?, starttime=?, endtime=?  WHERE id=?";
      pstmt = conn.prepareStatement(sql);

      pstmt.setInt(1, tawRmRecord.getFlag());
      pstmt.setString(2, tawRmRecord.getDutyrecord());
      pstmt.setString(3, starttime);
      pstmt.setString(4, endtime);
      pstmt.setInt(5, tawRmRecord.getId());
      pstmt.executeUpdate();
      pstmt.close();
      conn.commit();
    }
    catch (SQLException e) {
      pstmt.close();
      conn.rollback();
      e.printStackTrace();
    }
    finally {
      conn.close();
    }
  }

}
