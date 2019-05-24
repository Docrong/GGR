package com.boco.eoms.testcard.sms;

import java.util.TimerTask;
import java.sql.*;
import java.util.*;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.db.util.BocoConnection;
import com.boco.eoms.db.util.ConnectionPool;
//import com.boco.eoms.interfaces.sms.shortMsg;
//import com.boco.eoms.faultsheet.dao.TawWrfMonitorDAO;
public class TestcardSms extends TimerTask {
  ConnectionPool ds = ConnectionPool.getInstance();
  public void run(){
    String condition = "";
    String sql =  "select cardid,contect,leantime,renewlimit from taw_testcard_manager where returntime is null";
    BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    //shortMsg sms = new shortMsg();
    Calendar cal=Calendar.getInstance();
    int dayNow=cal.get(cal.DAY_OF_MONTH);
    int hourNow=cal.get(cal.HOUR_OF_DAY);
    int month=cal.get(cal.MONTH);
    System.out.println(dayNow);
    if(hourNow==9){
      try {
        conn = ds.getConnection();
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        while (rs.next()) {
          String leantime=rs.getString(3);
          String limit=leantime.substring(11,13);
          int LIMIT=Integer.parseInt(rs.getString(3).substring(8,10));
          int lim=Integer.parseInt(limit)+rs.getInt(4);
          String mess="��ã���������һ�Ų��Կ������ţ�"+rs.getString(1)+" �ѵ��ڣ��뾡��黹";
          if (hourNow<lim){
            if(month==1||month==5||month==7||month==8||month==10||month==12)
            {
              dayNow=dayNow+30;
            }
            else if(month==3)
            {
              dayNow=dayNow+28;
            }
            else{
              dayNow=dayNow+31;
            }
          }
          dayNow=dayNow+lim;
          if(dayNow-LIMIT<=7)
          {
            System.out.println("smsԿ���ʼ�߻�");
            //sms.sendSms(rs.getString(2),mess);
          }
        }
        rs.close();
        pstmt.close();
        conn.commit();
        conn.close();
      }
      catch (SQLException e) {
        conn.close();
        e.printStackTrace();
      }
      catch (Exception es) {
        es.printStackTrace();
      }
    }
  }
  /**
   *
   * @param toTarget
   * @param targetClass
   * @return
   */
  private String getMobile(int toTarget,int targetClass)
  {
    String sql = "";
    String ret = "";
    BocoConnection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    if (targetClass == 0)
    {
      sql = "select mobile from taw_rm_user where dept_id=" + toTarget;
    }
    else if (targetClass == 3)
    {
      sql = "select mobile from taw_rm_user where deleted=0 and id=" + toTarget;
    }
    try
    {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while (rs.next())
      {
        ret = ret + rs.getString("mobile") + ",";
      }
      rs.close();
      pstmt.close();
      conn.close();
      if (!ret.equals("") && ret.length()>2)
      {
        ret = ret.substring(0,ret.length()-1);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      conn.close();
    }
    return ret;
  }
}

