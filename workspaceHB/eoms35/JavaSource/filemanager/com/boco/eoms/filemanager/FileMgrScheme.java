package com.boco.eoms.filemanager;

import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.resource.Util;



import java.sql.*;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2005-9-15
 * Time: 10:26:01
 * Boco Corporation
 * 部门：亿阳信通软件研究院 EOMS
 * 地址：北京市海淀区亮甲店130号亿阳大厦 12层3室
 * To change this template use File | Settings | File Templates.
 */
public class FileMgrScheme  implements Job {

     Connection conn = null;
    Statement stat = null;
    private String schemeId="-1";
    private java.util.Date firedate;
    private int cycleType=SchemeMgrDAO.SCHEME_CYCLE_TYPE_TEMP;
    public FileMgrScheme() {
        try {
            conn = ConnectionPool.getInstance().getConnection();
            stat = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    public static String selectSubId="select remark from st_subscription  where sub_id=?";
    public static String getSchemeId(String subId){
        String remarkId ="";
        Connection connection=ConnectionPool.getInstance().getConnection();
        PreparedStatement pmts = null;
        try {
            pmts=connection.prepareStatement(selectSubId);
            pmts.setString(1,subId);
            ResultSet resultSet=pmts.executeQuery();
            if(resultSet.next())
               remarkId=resultSet.getString("remark");
            if(resultSet!=null)resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        try {
            if(pmts!=null)pmts.close();
            if(connection!=null)connection.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return remarkId;
    }
    boolean success=false;
    static final int restartCount=0;
    static private  String schemeSql="select * from taw_file_mgr_scheme where file_mgr_scheme_id=?";
     public void execute(JobExecutionContext context) throws org.quartz.
      JobExecutionException {               //计划调度定期生成报表
         System.out.println("-------------*******************---------------------------");
         System.out.println("-------------FileMgrScheme start...-----------------");
         String cycle="";
         String isAudit = "";
         String auditUserId = "";
         String auditUserName = "";
         try {
             boolean isAuto = conn.getAutoCommit();
             schemeId=getSchemeId(context.getJobDetail().getName());
             conn.setAutoCommit(false);
             String reportId = "-1";
             PreparedStatement schemePstm = conn.prepareStatement(schemeSql);
             ResultSet schemeRs=null;
             schemePstm.setString(1, schemeId);
             schemeRs=schemePstm.executeQuery();
             if(!schemeRs.next()) return;
             java.util.Date date = context.getFireTime();
//             TawHieMonitorBO tawHieMonitorBO = new TawHieMonitorBO();
             String msgContent=""; //短信内容
             String sendDeptName=""; //短信内容
             firedate=date;
//             java.sql.Date curr = new java.sql.Date(date.getTime());
             Timestamp stamp=new Timestamp(date.getTime());
             cycle=schemeRs.getString("scheme_cycle");
             cycleType=schemeRs.getInt("scheme_cycle_type");
             sendDeptName=StaticMethod.dbNull2String(schemeRs.getString("send_dept_name"));
             
             isAudit = schemeRs.getString("is_audit");
             auditUserId = schemeRs.getString("audit_user_id");
             if(auditUserId==null||"".equals(auditUserId)){
            	 auditUserName = "[需要审核]";
             }else{
            	 auditUserId = auditUserId.trim();
            	 auditUserName =schemeRs.getString("audit_user_name").trim();
             }
             

             System.out.println("date = " + date);
             System.out.println("周期类型 = " +cycleType );
             System.out.println("周期 = " + cycle);
             String insertSql = "insert into taw_file_mgr_report(topic_id,file_mgr_scheme_id," +
                     "create_time,report_name,mgr_dept,deadline,report_id,combintype) values(?,?,?,?,?,?,?,?)";
             try {
                 ResultSet rs = conn.createStatement().executeQuery("select max(report_id)+1 from taw_file_mgr_report");
                 if (rs.next()) {
                     if(rs.getString(1)==null)
                        reportId="1";
                     else
                         reportId = rs.getString(1);
                 } else {
                     reportId = "-1";
                 }
                 try {
                     if (rs != null) rs.close();
                 } catch (SQLException e) {
                     e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                 }
                 PreparedStatement pmts = conn.prepareStatement(insertSql);
                 pmts.setString(1, schemeRs.getString("topic_id"));
                 pmts.setString(2, schemeRs.getString("file_mgr_scheme_id"));
//                 pmts.setDate(3, curr);                 //informix
                 String title=getReportName(schemeRs.getString("title"));     //报表名称
                 Timestamp deadline=getDeadline(schemeRs.getInt("scheme_ahead"),date); //处理时限
                 pmts.setTimestamp(3,stamp);
                 pmts.setString(4, title);
                 pmts.setString(5, schemeRs.getString("send_dept_id"));
                 pmts.setTimestamp(6,deadline);
                 pmts.setString(7, Util.getSequenceId(conn, "taw_file_mgr_report", "report_id"));
                 pmts.setString(8, schemeRs.getString("combintype"));
                 pmts.executeUpdate();
                 DateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                 msgContent=getMsgContent(sendDeptName,title,format.format(deadline));
                 System.out.println("-------------生成汇总报表成功！-----------------");
                 //拆分accept_dept_id accept_dept_name
                 String acceptDeptIds=schemeRs.getString("accept_dept_id");
                 acceptDeptIds=acceptDeptIds.trim();
                 String acceptDeptNames=schemeRs.getString("accept_dept_name");   //无须编码
                 acceptDeptNames=acceptDeptNames.trim();
                 String[] deptIdArr=acceptDeptIds.split(",");
                 String[] deptNameArr=acceptDeptNames.split(",");
                 if(deptIdArr.length!=deptNameArr.length) throw new Exception("部门配置错误！部门id与名称不匹配！");
                 insertSql = "insert into taw_file_mgr_list(report_id ,topic_id,acccept_dept_id ," +
                         "acccept_dept_name,send_time,flow_id,is_audit,audit_user_id,audit_user_name,status) values(?,?,?,?,?,?,?,?,?,?)";
                 try {
                     if (pmts != null) pmts.close();
                 } catch (SQLException e) {
                     e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                 }
                 pmts = conn.prepareStatement(insertSql);
                 for (int i = 0; i < deptIdArr.length; i++) {
                     pmts.setString(1, reportId);
                     pmts.setString(2, schemeRs.getString("topic_id"));
                     pmts.setString(3, deptIdArr[i]);
                     pmts.setString(4, deptNameArr[i]);
                     pmts.setTimestamp(5, stamp);
                     pmts.setString(6, Util.getSequenceId(conn,"taw_file_mgr_list","flow_id"));
                     pmts.setString(7, isAudit);
                     pmts.setString(8, auditUserId);
                     pmts.setString(9, auditUserName);
                     pmts.setInt(10, 0);
                     pmts.executeUpdate();
                     try {
//                         tawHieMonitorBO.sendMessage(deptIdArr[i], MesStaticVariable.REC_TPYE_DEPT, msgContent,0);
                     } catch (Exception e) {
                         e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                     }
                 }
                 try {
                     if (pmts != null) pmts.close();
                 } catch (SQLException e) {
                     e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                 }
                 System.out.println("-------------派发子报表成功！-----------------");
                 // 如果是临时报表 置删除标志 写错误日至列表
                if(schemeRs.getInt("scheme_cycle_type")==SchemeMgrDAO.SCHEME_CYCLE_TYPE_TEMP){
                     String updateSubscription="update st_subscription set deleted=1 where sub_id='"+context.getJobDetail().getName()+"'";
                     stat.executeUpdate(updateSubscription);
                    System.out.println("-------------临时任务删除成功！-----------------");
                }
                 conn.commit();
                 success=true;
                 System.out.println("任务执行成功！"+" FileMgrScheme:周期类型 ："+cycleType+" 周期："+cycle);
                 System.out.println("-------------*******************---------------------------");
             } catch (Exception e) {
                 conn.rollback();
                 System.out.println(StaticMethod.dbNull2String(e.toString()));
                 e.printStackTrace();
                 if(restartCount<3){
                     try {
                         System.out.println("-------500毫秒后重试！----------------");
                         Thread.sleep(500);
                     } catch (InterruptedException e1) {
                         e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                     }
                     this.execute(context);
                 }
                 if(!success)System.out.println("FileMgrScheme:任务执行失败！");
             }
             try {
                 schemeRs.close();
                 schemePstm.close();
             } catch (SQLException e) {
                 e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
             }
             conn.setAutoCommit(isAuto);
             release();
         } catch (SQLException e) {
             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         }
     }

    private String getReportName(String title){
        if(cycleType!=SchemeMgrDAO.SCHEME_CYCLE_TYPE_TEMP){
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
            return title+"("+dateFormat.format(firedate)+")";
        }
        else return title;
    }
    private java.sql.Timestamp getDeadline(int afterDay,java.util.Date date ){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        while(afterDay>0){  //按工作日计算
         calendar.add(Calendar.DAY_OF_YEAR,1);
         int day=calendar.get(Calendar.DAY_OF_WEEK);
         if(day!=Calendar.SATURDAY&&day!=Calendar.SUNDAY) afterDay--;
        }
       return new java.sql.Timestamp(calendar.getTimeInMillis());
    }
   private String getMsgContent(String sendDeptName,String reportName,String deadline){
       return "["+sendDeptName+"]通知您在"+deadline+"之前上报["+reportName+"]报表。";
   }
    public void release() {
        if (stat != null) {
            try {
                stat.close();
            } catch (Exception e) {
                System.out.println("release Error");
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("release Error");
            }
        }
    }

}
