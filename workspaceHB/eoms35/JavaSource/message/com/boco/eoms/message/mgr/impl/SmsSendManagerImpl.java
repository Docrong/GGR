package com.boco.eoms.message.mgr.impl;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.util.PropertyFile;
import com.boco.eoms.common.util.StaticMethod;
import com.commerceware.cmpp.CMPP;
import com.commerceware.cmpp.cmppe_deliver_result;
import com.commerceware.cmpp.cmppe_login_result;
import com.commerceware.cmpp.cmppe_result;
import com.commerceware.cmpp.cmppe_submit;
import com.commerceware.cmpp.cmppe_submit_result;
import com.commerceware.cmpp.conn_desc;
public class SmsSendManagerImpl {
	  /**
	   *
	   * @param con
	   * @return
	   */
	  protected String readPa(conn_desc con) {
	    cmppe_result cr = null;
	    CMPP cmpp = new CMPP();
	    String ret = "";
	    try {
	      cr = cmpp.readResPack(con);
	      switch (cr.pack_id) {
	        case CMPP.CMPPE_NACK_RESP:
	          ret = "get nack pack";
	          break;
	        case CMPP.CMPPE_LOGIN_RESP:
	          cmppe_login_result cl;
	          cl = (cmppe_login_result) cr;
	          ret = "------------login resp----------: STAT = " +
	                             cl.stat;
	          break;

	        case CMPP.CMPPE_LOGOUT_RESP:
	          ret = "------------logout resp----------: STAT = " +
	                             cr.stat;
	          break;

	        case CMPP.CMPPE_SUBMIT_RESP:
	          cmppe_submit_result sr;
	          sr = (cmppe_submit_result) cr;
	          System.out.println("------------submit resp----------: STAT = " +
	                  sr.stat + " SEQ = " + sr.seq + "--us_count=" + sr.us_count + "---msg_id=" +
	                  new String(sr.msg_id));
	          ret = sr.stat+"";
	          break;

	        case CMPP.CMPPE_DELIVER:
	          cmppe_deliver_result cd = (cmppe_deliver_result) cr;
	          if (cd.status_rpt == 1)
	            System.out.println( "Rpt status = " + cd.status_from_rpt);
	          cmpp.cmpp_send_deliver_resp(con, cd.seq, cd.stat);
	          ret = "Rpt status = " + cd.status_from_rpt;
	          break;

	        case CMPP.CMPPE_CANCEL_RESP:
	          ret = "---------cancel-----------: STAT = " + cr.stat;
	          break;

	        case CMPP.CMPPE_ACTIVE_RESP:
	          ret = "---------active resp-----------: STAT " + cr.stat;
	          break;

	        default:
	          ret = "---------Error packet-----------";
	          break;

	      }

	    }
	    catch (Exception e) {
	      System.out.println(e.getMessage());
	      e.printStackTrace();
	      System.out.println("have a exception");
	      try {
	        System.in.read();
	      }
	      catch (Exception e1) {}

	    }
	    return ret;
	  }
	  /**
	   *
	   * @param str
	   * @return
	   */
	  private byte[] StrToByte(String str){
	          byte[] tmp = str.getBytes();
	          byte[] ret = new byte[tmp.length+1];
	          for (int i=0;i<tmp.length;i++)
	          {
	            ret[i]=tmp[i];
	          }
	          ret[tmp.length]=0x0;
	          return ret;
	  }
	  /**
	   *
	   * @param key
	   * @return
	   */
	  public String getProperty(String key){
	    PropertyFile prop=PropertyFile.getInstance();
	    String ret="";
	    try{
	      ret = prop.getProperty(key).toString();
	    }catch(Exception e){
	      System.out.println("配置文件："+prop.getFilePath()+"没有发现");
	    }

	    if (ret == null) ret = "";
	    return ret;

	  }
	  /**
	   *
	   * @param tel
	   * @param msg
	   * @return
	   */
	  private cmppe_submit initCMPP(String tel,String msg){
	    cmppe_submit sub = new cmppe_submit();
	    System.out.println(tel);
	    byte[] icp_id = StrToByte(getProperty("Interface.sms_icp_id"));
	    byte svc_type[] = StrToByte(getProperty("Interface.sms_icp_code"));
	    byte fee_type = 1;
	    byte info_fee = 0;
	    byte proto_id = 1;
	    byte msg_mode = 1; // 消息模式:0-不需要状态报告, 1-需要状态报告, 2-控制类型消息
	    byte priority = 0;
	    byte fee_utype = 0;
	    byte validate[] = {0};
	    byte schedule[] = {0};
	    //-----------去掉无用的手机号码------------
	    try {
	      System.out.println("----过滤前手机号码----------" + tel);
	      String[] tempMobile = tel.split(",");
	      tel = "";
	      for (int i = 0; i < tempMobile.length; i++) {
	        if (tempMobile[i].length() == 11) {
	          if (tel != null && !tel.equals("")) {
	            tel = tel + "," + tempMobile[i];
	          } else {
	            tel = tempMobile[i];
	          }
	        }
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    System.out.println("--过滤后手机号码----------" + tel);
	    //----------------------------------------

	    String mobile[] = tel.split(",");
	    System.out.println("mobile.length:" + mobile.length);
	    byte[][] dst_addr = new byte[mobile.length+1][CMPP.CMPPE_MAX_MSISDN_LEN];
	    //byte src_addr[] = new byte[CMPP.CMPPE_MAX_MSISDN_LEN];
	    byte src_addr[] = StrToByte(getProperty("Interface.sms_icp_code"));
	    for (int i=0;i<mobile.length;i++)
	    {
	      byte[] tmp = StrToByte(mobile[i]);
	      for (int j=0;j<tmp.length;j++)
	      {
	        dst_addr[i][j] =tmp[j];
	      }
	    }

	    byte du_count = (byte) mobile.length;
	    System.out.println("");
	    System.out.println(du_count);
	    byte data_coding = 0xf;
	    byte[] short_msg = StrToByte(msg);
	    int sm_len = short_msg.length;

	    System.out.println("short message length:" + sm_len);
	    System.out.println("msg:" + new String(short_msg));
	    try{
	    sub.set_icpid(icp_id);
	    sub.set_svctype(svc_type);
	    sub.set_feetype(fee_type);
	    sub.set_infofee(info_fee);
	    sub.set_protoid(proto_id);
	    sub.set_msgmode(msg_mode);
	    sub.set_priority(priority);
	    sub.set_validate(validate);
	    sub.set_schedule(schedule);
	    sub.set_feeutype(fee_utype);
	    //sub.set_feeuser(fee_user);
	    sub.set_srcaddr(src_addr);
	    sub.set_dstaddr(dst_addr);
	    sub.set_ducount(du_count);
	    sub.set_msg(data_coding, sm_len, short_msg);
	    }
	    catch(Exception e)
	    {
	      e.printStackTrace();
	      BocoLog.info(SmsSendManagerImpl.class, 1002, tel + "短信中心连接出错:" + msg);
	    }
	    return sub;
	  }
	  /**
	   *
	   * @param tel
	   * @param msg
	   * @return
	   */
	  public static String sendSms(String tel,String msg) {
	    int user_seq;
	    CMPP p = new CMPP();
	    SmsSendManagerImpl sms = new SmsSendManagerImpl();
	    Date date = new Date();
	    int s_min = (int) (date.getTime() / 1000);
	    String ret = "OK";
	    try {
	      if (! (StaticMethod.nullObject2String(tel).equals("") ||
	             StaticMethod.nullObject2String(msg).equals(""))) {
	        conn_desc con = new conn_desc();
	        String host = sms.getProperty("Interface.sms_host_ip");
	        int port = Integer.parseInt(sms.getProperty("Interface.sms_port"));
	        p.cmpp_connect_to_ismg(host, port, con);
	        System.out.println("Connected ISMG");
	        p.cmpp_login(con, sms.getProperty("Interface.sms_user"),
	                     sms.getProperty("Interface.sms_pass"), 2, 0x12, s_min);

	        System.out.println(sms.readPa(con));
	        cmppe_submit sub = sms.initCMPP(tel, msg);
	        for (int test_count = 0; test_count < 1; test_count++) {
	          user_seq = p.cmpp_submit(con, sub);
	          System.out.println("Submited SM Seq = " + user_seq);

	          sms.readPa(con);
	        }

	        p.cmpp_active_test(con);
	        System.out.println(sms.readPa(con));

	        p.cmpp_logout(con);
	        System.out.println(sms.readPa(con));
	      } else {
	        ret = "手机号或短信内容为空";
	      }
	      SmsSendManagerImpl.saveMsgLog("",0,"",tel,0,0,msg,0);
	      } catch (Exception e) {
	        ret = e.getMessage();
	        e.printStackTrace(); //异常处理
	        BocoLog.info(SmsSendManagerImpl.class, 1002, tel + "短信发送出错，请检查原因：" + msg + "时:" + ret);
	      }
	      BocoLog.info(SmsSendManagerImpl.class, 1002, tel + "手机号发送短信内容为：" + msg + "时:" + ret);
	      return ret;
	    }

	    /**
	   *
	   * @param tel
	   * @param msg
	   * @return
	   */
	  public static String sendSms(int monitorId,String tel,String msg) {
	    int user_seq;
	    CMPP p = new CMPP();
	    SmsSendManagerImpl sms = new SmsSendManagerImpl();
	    Date date = new Date();
	    int s_min = (int) (date.getTime() / 1000);
	    String ret = "OK";
	    try {
	      if (! (StaticMethod.nullObject2String(tel).equals("") ||
	             StaticMethod.nullObject2String(msg).equals(""))) {
	        conn_desc con = new conn_desc();
	        String host = sms.getProperty("Interface.sms_host_ip");
	        int port = Integer.parseInt(sms.getProperty("Interface.sms_port"));
	        p.cmpp_connect_to_ismg(host, port, con);
	        System.out.println("Connected ISMG");
	        p.cmpp_login(con, sms.getProperty("Interface.sms_user"),
	                     sms.getProperty("Interface.sms_pass"), 2, 0x12, s_min);

	        System.out.println(sms.readPa(con));
	        cmppe_submit sub = sms.initCMPP(tel, msg);
	        for (int test_count = 0; test_count < 1; test_count++) {
	          user_seq = p.cmpp_submit(con, sub);
	          System.out.println("Submited SM Seq = " + user_seq);

	          sms.readPa(con);
	        }

	        p.cmpp_active_test(con);
	        System.out.println(sms.readPa(con));

	        p.cmpp_logout(con);
	        System.out.println(sms.readPa(con));
	      } else {
	        ret = "手机号或短信内容为空";
	      }
	      SmsSendManagerImpl.saveMsgLog("",0,"",tel,0,0,msg,monitorId);
	      } catch (Exception e) {
	        ret = e.getMessage();
	        e.printStackTrace(); //异常处理
	        BocoLog.info(SmsSendManagerImpl.class, 1002, tel + "短信发送出错，请检查原因：" + msg + "时:" + ret);
	      }
	      BocoLog.info(SmsSendManagerImpl.class, 1002, tel + "手机号发送短信内容为：" + msg + "时:" + ret);
	      return ret;
	    }

	    /**
	     * @param tel
	     * @param msg
	     * @return
	     */
	    public static String sendSms(String tel,String msg,String senderName) {
	      int user_seq;
	      CMPP p = new CMPP();
	      SmsSendManagerImpl sms = new SmsSendManagerImpl();
	      Date date = new Date();
	      int s_min = (int) (date.getTime() / 1000);
	      String ret = "OK";
	      try {
	        if (! (StaticMethod.nullObject2String(tel).equals("") ||
	               StaticMethod.nullObject2String(msg).equals(""))) {
	          conn_desc con = new conn_desc();
	          String host = sms.getProperty("Interface.sms_host_ip");
	          int port = Integer.parseInt(sms.getProperty("Interface.sms_port"));
	          p.cmpp_connect_to_ismg(host, port, con);
	          System.out.println("Connected ISMG");
	          p.cmpp_login(con, sms.getProperty("Interface.sms_user"),
	                       sms.getProperty("Interface.sms_pass"), 2, 0x12, s_min);

	          System.out.println(sms.readPa(con));
	          cmppe_submit sub = sms.initCMPP(tel, msg);
	          for (int test_count = 0; test_count < 1; test_count++) {
	            user_seq = p.cmpp_submit(con, sub);
	            System.out.println("Submited SM Seq = " + user_seq);
	            sms.readPa(con);
	          }

	          p.cmpp_active_test(con);
	          System.out.println(sms.readPa(con));

	          p.cmpp_logout(con);
	          System.out.println(sms.readPa(con));
	        } else {
	          ret = "手机号或短信内容为空";
	        }
	        SmsSendManagerImpl.saveMsgLog("",0,senderName,tel,0,0,msg,0);
	        } catch (Exception e) {
	          ret = e.getMessage();
	          e.printStackTrace(); //异常处理
	          BocoLog.info(SmsSendManagerImpl.class, 1002, tel + "短信发送出错，请检查原因：" + msg + "时:" + ret);
	        }
	        BocoLog.info(SmsSendManagerImpl.class, 1002, tel + "手机号发送短信内容为：" + msg + "时:" + ret);
	        return ret;
	      }

	      /**
	       * @param tel
	       * @param msg
	       * @return
	       */
	      public static String testSendSms(String tel, String msg, String senderName) {
	        int user_seq;
	        CMPP p = new CMPP();
	        SmsSendManagerImpl sms = new SmsSendManagerImpl();
	        Date date = new Date();
	        int s_min = (int) (date.getTime() / 1000);
	        String ret = "OK";
	        try {
	          if (! (StaticMethod.nullObject2String(tel).equals("") ||
	                 StaticMethod.nullObject2String(msg).equals(""))) {
	            conn_desc con = new conn_desc();
	            String host = sms.getProperty("Interface.sms_host_ip");
	            int port = Integer.parseInt(sms.getProperty("Interface.sms_port"));
	            p.cmpp_connect_to_ismg(host, port, con);
	            System.out.println("Connected ISMG");
	            p.cmpp_login(con, sms.getProperty("Interface.sms_user"),
	                         sms.getProperty("Interface.sms_pass"), 2, 0x12, s_min);

	            System.out.println(sms.readPa(con));
	            cmppe_submit sub = sms.initCMPP(tel, msg);
	            for (int test_count = 0; test_count < 1; test_count++) {
	              user_seq = p.cmpp_submit(con, sub);
	              System.out.println("Submited SM Seq = " + user_seq);
	              sms.readPa(con);
	            }

	            p.cmpp_active_test(con);
	            System.out.println(sms.readPa(con));

	            p.cmpp_logout(con);
	            System.out.println(sms.readPa(con));
	          }
	          else {
	            ret = "手机号或短信内容为空";
	          }
	        }
	        catch (Exception e) {
	          ret = e.getMessage();
	          e.printStackTrace(); //异常处理
	          BocoLog.info(SmsSendManagerImpl.class, 1002,
	                       tel + "短信发送出错，请检查原因：" + msg + "时:" + ret);
	        }
	        BocoLog.info(SmsSendManagerImpl.class, 1002, tel + "手机号发送短信内容为：" + msg + "时:" + ret);
	        return ret;
	      }

	  /*
	   * 保存已经发送的短信日志
	   * @param worksheet_id String
	   * @param worksheet_class int
	   * @param filler String
	   * @param mobile String
	   * @param to_target int
	   * @param target_class int
	   * @param comment String
	   * @throws SQLException
	   */
	  public static void saveMsgLog(String worksheet_id, int worksheet_class,
	                           String filler,String mobile,int to_target,
	                           int target_class,String comment,int monitorId) throws SQLException{
	     com.boco.eoms.db.util.BocoConnection conn = null;
	     PreparedStatement pstmt = null;
	     String sql = null;
	     try {
	       sql = "INSERT INTO taw_wrf_monitor_log(worksheet_id,worksheet_class,filler,"
	           + "insert_time,mobile,to_target,target_class,comment,monitor_id) "
	           + "VALUES (?,?,?,?,?,?,?,?,?)";
	       conn = com.boco.eoms.db.util.ConnectionPool.getInstance().getConnection();
	       conn.setAutoCommit(true);
	       pstmt = conn.prepareStatement(sql);
	       pstmt.setString(1,worksheet_id);
	       pstmt.setInt(2,worksheet_class);
	       pstmt.setString(3,filler);
	       pstmt.setString(4,StaticMethod.getCurrentDateTime());
	       pstmt.setString(5,mobile);
	       pstmt.setInt(6,to_target);
	       pstmt.setInt(7,target_class);
	       pstmt.setString(8,StaticMethod.toBaseEncoding(comment));
	       pstmt.setInt(9,monitorId);

	       pstmt.executeUpdate();
	       pstmt.close();
	       conn.setAutoCommit(false);
	     }
	     catch (SQLException sqle) {
	       pstmt.close();
	       sqle.printStackTrace();
	     }
	     finally {
	       conn.close();
	       sql = null;
	     }
	   }

	}
