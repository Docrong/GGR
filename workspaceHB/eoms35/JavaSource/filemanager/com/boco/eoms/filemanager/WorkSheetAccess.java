package com.boco.eoms.filemanager;

import com.boco.eoms.common.resource.Util;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.filemanager.extra.fileupload.FileInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2005-9-21
 * Time: 16:47:09
 * Boco Corporation
 * 部门：亿阳信通软件研究院 EOMS
 * 地址：北京市海淀区亮甲店130号亿阳大厦 12层3室
 * To change this template use File | Settings | File Templates.
 */
public class WorkSheetAccess {
    Connection conn = null;
    Statement stat = null;

    public WorkSheetAccess() {
        try {
            conn = ConnectionPool.getInstance().getConnection();
            stat = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
   public WorkSheetAccess(Connection conn) {
        try {
            this.conn = conn;
            stat = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
   
   //一审驳回
    public String rejectReport(String userId,String flowId,String auditInfo) throws Exception{
        String resultXml="";
        //派发驳回工单
        //更新报表状态
        //taw_file_mgr_list中status为是否已提交，reject为是否被驳回
        String updateReportSql="update taw_file_mgr_report set report_status=? where report_id in (select report_id from taw_file_mgr_list where flow_id=?)";
        String updateSql="update taw_file_mgr_list set status=?,reply_info=? where flow_id=?";
        String insertSql="insert into taw_file_mgr_audit(audit_id,flow_id,status_old,status_new,audit_user_id,audit_time,audit_info) values(?,?,?,?,?,?,?)";
        String searchSql="select status from taw_file_mgr_list where flow_id='"+flowId+"'";
        boolean isAuto = conn.getAutoCommit();
        int status_old = 0;
        conn.setAutoCommit(false);
        try {
            //查原状态
            Statement listStat = conn.createStatement();
            ResultSet listRs = null;
            listRs = listStat.executeQuery(searchSql);
            if (listRs.next()) {
            	status_old = listRs.getInt("status");
            }
            try {
                if (listRs != null) 
                	{
                		listRs.close();
                		listStat.close();
                	}
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            
            //改汇总表report
            PreparedStatement pmts = conn.prepareStatement(updateReportSql);
            pmts.setInt(1, 1);     //置为不合格,一审驳回���ϸ�
            pmts.setString(2, flowId);
            pmts.executeUpdate();
            try {
                if(pmts!=null)pmts.close();
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            //改任务表list
            pmts=conn.prepareStatement(updateSql);
             pmts.setInt(1, 2);    //置为不合格,一审驳回���ϸ�
             pmts.setString(2, auditInfo);
             pmts.setString(3, flowId);
             pmts.executeUpdate();
             try {
                 if(pmts!=null)pmts.close();
             } catch (SQLException e) {
                 e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
             }
             //插入审核流程信息到audit表
             Date date = new Date();
             Timestamp stamp=new Timestamp(date.getTime());
             pmts=conn.prepareStatement(insertSql);
             pmts.setString(1, Util.getSequenceId(conn, "taw_file_mgr_audit", "audit_id"));
             pmts.setString(2, flowId);
             pmts.setInt(3, status_old);
             pmts.setInt(4, 2);
             pmts.setString(5, userId); 
             pmts.setTimestamp(6, stamp);
             pmts.setString(7, auditInfo);
             pmts.executeUpdate();
             
             conn.commit();
             resultXml="<test><return err='2' userId='"+userId+"' flowId='"+flowId+"' msg='审批驳回工单成功！'/></test>";
         } catch (SQLException e) {
              resultXml="<test><return err='-1' userId='"+userId+"' flowId='"+flowId+"' msg='"+e.getMessage()+"'/></test>";
             conn.rollback();
             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         }
         conn.setAutoCommit(isAuto);
         return resultXml;
     }
    //一审通过
    public String passReport(String userId,String flowId,String auditInfo) throws Exception{
        String resultXml="";
        //派发驳回工单
        //更新报表状态
        //taw_file_mgr_list中status为是否已提交，reject为是否被驳回
        String updateReportSql="update taw_file_mgr_report set report_status=? where report_id in (select report_id from taw_file_mgr_list where flow_id=?)";
        String updateSql="update taw_file_mgr_list set status=?,reply_info=? where flow_id=?";
        String insertSql="insert into taw_file_mgr_audit(audit_id,flow_id,status_old,status_new,audit_user_id,audit_time,audit_info) values(?,?,?,?,?,?,?)";
        String searchSql="select status from taw_file_mgr_list where flow_id='"+flowId+"'";
        boolean isAuto = conn.getAutoCommit();
        int status_old = 0;
        conn.setAutoCommit(false);
        try {
            //查原状态
            Statement listStat = conn.createStatement();
            ResultSet listRs = null;
            listRs = listStat.executeQuery(searchSql);
            if (listRs.next()) {
            	status_old = listRs.getInt("status");
            }
            try {
                if (listRs != null) 
                	{
                		listRs.close();
                		listStat.close();
                	}
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            
            //改汇总表report
            PreparedStatement pmts = conn.prepareStatement(updateReportSql);
            pmts.setInt(1, 1);     //置为合格,一审通过ϸ�
            pmts.setString(2, flowId);
            pmts.executeUpdate();
            try {
                if(pmts!=null)pmts.close();
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            //改任务表list
            pmts=conn.prepareStatement(updateSql);
             pmts.setInt(1, 3);    //置为合格,一审通过���ϸ�
             pmts.setString(2, auditInfo);
             pmts.setString(3, flowId);
             pmts.executeUpdate();
             try {
                 if(pmts!=null)pmts.close();
             } catch (SQLException e) {
                 e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
             }
             //插入审核流程信息到audit表
             Date date = new Date();
             Timestamp stamp=new Timestamp(date.getTime());
             pmts=conn.prepareStatement(insertSql);
             pmts.setString(1, Util.getSequenceId(conn, "taw_file_mgr_audit", "audit_id"));
             pmts.setString(2, flowId);
             pmts.setInt(3, status_old);
             pmts.setInt(4, 3);
             pmts.setString(5, userId); 
             pmts.setTimestamp(6, stamp);
             pmts.setString(7, auditInfo);
             pmts.executeUpdate();
             
             conn.commit();
             resultXml="<test><return err='3' userId='"+userId+"' flowId='"+flowId+"' msg='审批通过工单成功！'/></test>";
         } catch (SQLException e) {
              resultXml="<test><return err='-1' userId='"+userId+"' flowId='"+flowId+"' msg='"+e.getMessage()+"'/></test>";
             conn.rollback();
             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         }
         conn.setAutoCommit(isAuto);
         return resultXml;
     }
//  //二审驳回
    public String rejectReport2(String userId,String flowId,String auditInfo) throws Exception{
    	String resultXml="";
        //派发驳回工单
        //更新报表状态
        //taw_file_mgr_list中status为是否已提交，reject为是否被驳回
        String updateReportSql="update taw_file_mgr_report set report_status=? where report_id in (select report_id from taw_file_mgr_list where flow_id=?)";
        String updateSql="update taw_file_mgr_list set status=?,reply_info=? where flow_id=?";
        String insertSql="insert into taw_file_mgr_audit(audit_id,flow_id,status_old,status_new,audit_user_id,audit_time,audit_info) values(?,?,?,?,?,?,?)";
        String searchSql="select status from taw_file_mgr_list where flow_id='"+flowId+"'";
        boolean isAuto = conn.getAutoCommit();
        int status_old = 0;
        conn.setAutoCommit(false);
        try {
            //查原状态
            Statement listStat = conn.createStatement();
            ResultSet listRs = null;
            listRs = listStat.executeQuery(searchSql);
            if (listRs.next()) {
            	status_old = listRs.getInt("status");
            }
            try {
                if (listRs != null) 
                	{
                		listRs.close();
                		listStat.close();
                	}
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            
            //改汇总表report
            PreparedStatement pmts = conn.prepareStatement(updateReportSql);
            pmts.setInt(1, 1);     //置为不合格,二审驳回���ϸ�
            pmts.setString(2, flowId);
            pmts.executeUpdate();
            try {
                if(pmts!=null)pmts.close();
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            //改任务表list
            pmts=conn.prepareStatement(updateSql);
             pmts.setInt(1, 4);    //置为不合格,二审驳回���ϸ�
             pmts.setString(2, auditInfo);
             pmts.setString(3, flowId);
             pmts.executeUpdate();
             try {
                 if(pmts!=null)pmts.close();
             } catch (SQLException e) {
                 e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
             }
             //插入审核流程信息到audit表
             Date date = new Date();
             Timestamp stamp=new Timestamp(date.getTime());
             pmts=conn.prepareStatement(insertSql);
             pmts.setString(1, Util.getSequenceId(conn, "taw_file_mgr_audit", "audit_id"));
             pmts.setString(2, flowId);
             pmts.setInt(3, status_old);
             pmts.setInt(4, 4);
             pmts.setString(5, userId); 
             pmts.setTimestamp(6, stamp);
             pmts.setString(7, auditInfo);
             pmts.executeUpdate();
             
             conn.commit();
             resultXml="<test><return err='4' userId='"+userId+"' flowId='"+flowId+"' msg='审核驳回工单成功！'/></test>";
         } catch (SQLException e) {
              resultXml="<test><return err='-1' userId='"+userId+"' flowId='"+flowId+"' msg='"+e.getMessage()+"'/></test>";
             conn.rollback();
             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         }
         conn.setAutoCommit(isAuto);
         return resultXml;
     }
    //二审通过
    public String passReport2(String userId,String flowId,String auditInfo) throws Exception{
    	String resultXml="";
        //派发驳回工单
        //更新报表状态
        //taw_file_mgr_list中status为是否已提交，reject为是否被驳回
        String updateReportSql="update taw_file_mgr_report set report_status=? where report_id in (select report_id from taw_file_mgr_list where flow_id=?)";
        String updateSql="update taw_file_mgr_list set status=?,reply_info=? where flow_id=?";
        String insertSql="insert into taw_file_mgr_audit(audit_id,flow_id,status_old,status_new,audit_user_id,audit_time,audit_info) values(?,?,?,?,?,?,?)";
        String searchSql="select status from taw_file_mgr_list where flow_id='"+flowId+"'";
        boolean isAuto = conn.getAutoCommit();
        int status_old = 0;
        conn.setAutoCommit(false);
        try {
            //查原状态
            Statement listStat = conn.createStatement();
            ResultSet listRs = null;
            listRs = listStat.executeQuery(searchSql);
            if (listRs.next()) {
            	status_old = listRs.getInt("status");
            }
            try {
                if (listRs != null) 
                	{
                		listRs.close();
                		listStat.close();
                	}
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            
            //改汇总表report
            PreparedStatement pmts = conn.prepareStatement(updateReportSql);
            pmts.setInt(1, 1);     //置为不合格,2审通过���ϸ�
            pmts.setString(2, flowId);
            pmts.executeUpdate();
            try {
                if(pmts!=null)pmts.close();
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            //改任务表list
            pmts=conn.prepareStatement(updateSql);
             pmts.setInt(1, 5);    //置为不合格,2审通过���ϸ�
             pmts.setString(2, auditInfo);
             pmts.setString(3, flowId);
             pmts.executeUpdate();
             try {
                 if(pmts!=null)pmts.close();
             } catch (SQLException e) {
                 e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
             }
             //插入审核流程信息到audit表
             Date date = new Date();
             Timestamp stamp=new Timestamp(date.getTime());
             pmts=conn.prepareStatement(insertSql);
             pmts.setString(1, Util.getSequenceId(conn, "taw_file_mgr_audit", "audit_id"));
             pmts.setString(2, flowId);
             pmts.setInt(3, status_old);
             pmts.setInt(4, 5);
             pmts.setString(5, userId); 
             pmts.setTimestamp(6, stamp);
             pmts.setString(7, auditInfo);
             pmts.executeUpdate();
             
             conn.commit();
             resultXml="<test><return err='5' userId='"+userId+"' flowId='"+flowId+"' msg='审核通过工单成功！'/></test>";
         } catch (SQLException e) {
              resultXml="<test><return err='-1' userId='"+userId+"' flowId='"+flowId+"' msg='"+e.getMessage()+"'/></test>";
             conn.rollback();
             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         }
         conn.setAutoCommit(isAuto);
         return resultXml;
     }
    
//  审核驳回
    public String rejectReport3(String userId,String flowId,String auditInfo) throws Exception{
        String resultXml="";
        //派发驳回工单
        //更新报表状态
        //taw_file_mgr_list中status为是否已提交，reject为是否被驳回
        String updateReportSql="update taw_file_mgr_report set report_status=? where report_id in (select report_id from taw_file_mgr_list where flow_id=?)";
        String updateSql="update taw_file_mgr_list set status=?,reply_info=? where flow_id=?";
        String insertSql="insert into taw_file_mgr_audit(audit_id,flow_id,status_old,status_new,audit_user_id,audit_time,audit_info) values(?,?,?,?,?,?,?)";
        String searchSql="select status from taw_file_mgr_list where flow_id='"+flowId+"'";
        boolean isAuto = conn.getAutoCommit();
        int status_old = 0;
        conn.setAutoCommit(false);
        try {
            //查原状态
            Statement listStat = conn.createStatement();
            ResultSet listRs = null;
            listRs = listStat.executeQuery(searchSql);
            if (listRs.next()) {
            	status_old = listRs.getInt("status");
            }
            try {
                if (listRs != null) 
                	{
                		listRs.close();
                		listStat.close();
                	}
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            
            //改汇总表report
            PreparedStatement pmts = conn.prepareStatement(updateReportSql);
            pmts.setInt(1, 1);     //置为不合格,一审驳回���ϸ�
            pmts.setString(2, flowId);
            pmts.executeUpdate();
            try {
                if(pmts!=null)pmts.close();
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            //改任务表list
            pmts=conn.prepareStatement(updateSql);
             pmts.setInt(1, 6);    //置为不合格,一审驳回���ϸ�
             pmts.setString(2, auditInfo);
             pmts.setString(3, flowId);
             pmts.executeUpdate();
             try {
                 if(pmts!=null)pmts.close();
             } catch (SQLException e) {
                 e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
             }
             //插入审核流程信息到audit表
             Date date = new Date();
             Timestamp stamp=new Timestamp(date.getTime());
             pmts=conn.prepareStatement(insertSql);
             pmts.setString(1, Util.getSequenceId(conn, "taw_file_mgr_audit", "audit_id"));
             pmts.setString(2, flowId);
             pmts.setInt(3, status_old);
             pmts.setInt(4, 6);
             pmts.setString(5, userId); 
             pmts.setTimestamp(6, stamp);
             pmts.setString(7, auditInfo);
             pmts.executeUpdate();
             
             conn.commit();
             resultXml="<test><return err='6' userId='"+userId+"' flowId='"+flowId+"' msg='审批驳回工单成功！'/></test>";
         } catch (SQLException e) {
              resultXml="<test><return err='-1' userId='"+userId+"' flowId='"+flowId+"' msg='"+e.getMessage()+"'/></test>";
             conn.rollback();
             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         }
         conn.setAutoCommit(isAuto);
         return resultXml;
     }
    //审核通过
    public String passReport3(String userId,String flowId,String auditInfo) throws Exception{
        String resultXml="";
        //派发驳回工单
        //更新报表状态
        //taw_file_mgr_list中status为是否已提交，reject为是否被驳回
        String updateReportSql="update taw_file_mgr_report set report_status=? where report_id in (select report_id from taw_file_mgr_list where flow_id=?)";
        String updateSql="update taw_file_mgr_list set status=?,reply_info=? where flow_id=?";
        String insertSql="insert into taw_file_mgr_audit(audit_id,flow_id,status_old,status_new,audit_user_id,audit_time,audit_info) values(?,?,?,?,?,?,?)";
        String searchSql="select status from taw_file_mgr_list where flow_id='"+flowId+"'";
        boolean isAuto = conn.getAutoCommit();
        int status_old = 0;
        conn.setAutoCommit(false);
        try {
            //查原状态
            Statement listStat = conn.createStatement();
            ResultSet listRs = null;
            listRs = listStat.executeQuery(searchSql);
            if (listRs.next()) {
            	status_old = listRs.getInt("status");
            }
            try {
                if (listRs != null) 
                	{
                		listRs.close();
                		listStat.close();
                	}
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            
            //改汇总表report
            PreparedStatement pmts = conn.prepareStatement(updateReportSql);
            pmts.setInt(1, 1);     //置为合格,一审通过ϸ�
            pmts.setString(2, flowId);
            pmts.executeUpdate();
            try {
                if(pmts!=null)pmts.close();
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            //改任务表list
            pmts=conn.prepareStatement(updateSql);
             pmts.setInt(1, 7);    //置为合格,一审通过���ϸ�
             pmts.setString(2, auditInfo);
             pmts.setString(3, flowId);
             pmts.executeUpdate();
             try {
                 if(pmts!=null)pmts.close();
             } catch (SQLException e) {
                 e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
             }
             //插入审核流程信息到audit表
             Date date = new Date();
             Timestamp stamp=new Timestamp(date.getTime());
             pmts=conn.prepareStatement(insertSql);
             pmts.setString(1, Util.getSequenceId(conn, "taw_file_mgr_audit", "audit_id"));
             pmts.setString(2, flowId);
             pmts.setInt(3, status_old);
             pmts.setInt(4, 7);
             pmts.setString(5, userId); 
             pmts.setTimestamp(6, stamp);
             pmts.setString(7, auditInfo);
             pmts.executeUpdate();
             
             conn.commit();
             resultXml="<test><return err='7' userId='"+userId+"' flowId='"+flowId+"' msg='审批通过工单成功！'/></test>";
         } catch (SQLException e) {
              resultXml="<test><return err='-1' userId='"+userId+"' flowId='"+flowId+"' msg='"+e.getMessage()+"'/></test>";
             conn.rollback();
             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         }
         conn.setAutoCommit(isAuto);
         return resultXml;
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
