package com.boco.eoms.autosheet.servletoper;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author eastbear
 * @version 1.0
 */
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import java.io.*;

import com.boco.eoms.common.util.*;
import com.boco.eoms.db.util.*;
import com.boco.eoms.autosheet.util.*;
//import com.boco.eoms.log.bo.logBO;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.dao.*;
//import com.boco.eoms.cache.CacheDataLoad;


public class SheetOperator extends DAO{

 private char Separator = 2;
 private SheetName shName;
 private SheetValue shValue;

 private PropertyFile prop = PropertyFile.getInstance();
 //private logBO log;

  public SheetOperator(com.boco.eoms.db.util.ConnectionPool ds) {
    super(ds);
  }

  public boolean createSheet(HttpServletRequest request) {
    ConnStatement cst=new ConnStatement();
  //  HttpSession session;
    boolean flag = false;
    String sheetID = (String) request.getSession().getAttribute("sheetID");
    String sql =null;
    SheetName.removeInstance();
    shName = SheetName.getInstance();
    try {
      sql=this.produceDDL(sheetID);
      cst.setStatementSql(sql);
      cst.executeUpdate();

       if (StaticMethod.getDbType().equals(StaticVariable.ORACLE)) {
      sql=this.getSeqSql(sheetID);
      cst.setStatementSql(sql);
      cst.executeUpdate();

      sql=this.getTrigerSql(sheetID);
      cst.setStatementSql(sql);
      cst.executeUpdate();
       }
      cst.commit();
      flag = true;
    }
    catch (Exception e) {
      try {
        cst.rollback();
      }
      catch (Exception ex) {}
      e.printStackTrace();
    }finally {
      cst.close();
    }
    /*if (!flag) { //�½���boco_���ɹ�,ɾ����Ӧ��sheetֵ
      sql = "delete taw_sheetvalue where sheet_id=" + sheetID;
      try {
        cst.setStatementSql(sql);
        cst.executeUpdate();
        sql = "delete taw_attrvalue where sheet_id=" + sheetID;
        cst.setStatementSql(sql);
        cst.executeUpdate();
        sql = "delete taw_sheetname where sheet_id=" + sheetID;
        cst.setStatementSql(sql);
        cst.executeUpdate();
      }
      catch (Exception e) {}
      finally{
       cst.close();
      }
         }*/
     /*������boco_�ɹ�����ִ����صĲ���*/
    /*if (flag) {
      String s1 = appID + "" + Separator + "" + sheetID + "" + Separator +
          StaticMethod.toBaseEncoding(shName.getSh_cname(sheetID));
      rs.execute("produceTable", s1);
      if (rs.next()) {
        if (rs.getString(1).equals("1")) {
          flag = true;
        }
        else {
          flag = false;
        }
      }
    }
    if (flag) {
      log = new logBO(ds);
      try {
        log.insertLogToDB("admin", "", "");
      }
      catch (Exception e) {}

    }*/

    return flag;
  }

  public boolean reCreateSheet(String sheetID) {
    ConnStatement cst=new ConnStatement();
    boolean flag = false;
    String sql =null;
    SheetName.removeInstance();
    shName = SheetName.getInstance();
    try {
      sql=this.produceDDL(sheetID);
      cst.setStatementSql(sql);
      cst.executeUpdate();
      if (StaticMethod.getDbType().equals(StaticVariable.ORACLE)) {
        sql = this.getSeqSql(sheetID);
        cst.setStatementSql(sql);
        cst.executeUpdate();

        sql = this.getTrigerSql(sheetID);
        cst.setStatementSql(sql);
        cst.executeUpdate();
      }

      cst.commit();

      flag = true;
    }
    catch (Exception e) {
      try {
        cst.rollback();
      }
      catch (Exception ex) {}
      e.printStackTrace();
    }finally {
      cst.close();
    }
    /*if (!flag) { //�½���boco_���ɹ�,ɾ����Ӧ��sheetֵ
      sql = "delete taw_sheetvalue where sheet_id=" + sheetID;
      try {
        cst.setStatementSql(sql);
        cst.executeUpdate();
        sql = "delete taw_attrvalue where sheet_id=" + sheetID;
        cst.setStatementSql(sql);
        cst.executeUpdate();
        sql = "delete taw_sheetname where sheet_id=" + sheetID;
        cst.setStatementSql(sql);
        cst.executeUpdate();
      }
      catch (Exception e) {}
      finally{
       cst.close();
      }
         }*/
     /*������boco_�ɹ�����ִ����صĲ���*/
    /*if (flag) {
      String s1 = appID + "" + Separator + "" + sheetID + "" + Separator +
          StaticMethod.toBaseEncoding(shName.getSh_cname(sheetID));
      rs.execute("produceTable", s1);
      if (rs.next()) {
        if (rs.getString(1).equals("1")) {
          flag = true;
        }
        else {
          flag = false;
        }
      }
    }
    if (flag) {
      log = new logBO(ds);
      try {
        log.insertLogToDB("admin", "", "");
      }
      catch (Exception e) {}

    }*/

    return flag;
  }

  public boolean deleteSheet(String sheetID) {
    ConnStatement cst = new ConnStatement();
    boolean flag = false;
    String sql = null;
    try {
       if (StaticMethod.getDbType().equals(StaticVariable.ORACLE)) {
      try {
        sql = "drop trigger tr_taw_boco_" + sheetID + "_id";
        cst.setStatementSql(sql);
        cst.executeUpdate();
        cst.commit();
      }
      catch (Exception e) {
        try {
          cst.rollback();
        }
        catch (Exception ex) {}
       // e.printStackTrace();
        flag = false;
      }
      try {
        sql = "drop sequence q_taw_boco_" + sheetID + "_id";
        cst.setStatementSql(sql);
        cst.executeUpdate();
        cst.commit();
      }
      catch (Exception e) {
        try {
          cst.rollback();
        }
        catch (Exception ex) {}
      //  e.printStackTrace();
        flag = false;
      }
      }

      try {
        sql = "drop table " +
            StaticMethod.null2String(prop.getProperty("pretable"), "boco_") +
            sheetID;
        cst.setStatementSql(sql);
        cst.executeUpdate();
        cst.commit();
      }
      catch (Exception e) {
        try {
          cst.rollback();
        }
        catch (Exception ex) {}
     //   e.printStackTrace();
        flag = false;
      }
      flag = true;
    }
    catch (Exception e) {
      try {
        cst.rollback();
      }
      catch (Exception ex) {}
      e.printStackTrace();
      flag = false;
    }
    finally {
      cst.close();
    }
    /*������־*/
    if (flag) {
      //log = new logBO(ds);
      try {
        //log.insertLogToDB("admin", "", "");
      }
      catch (Exception e) {}
    }
    return flag;
  }

  public boolean reNewSheet(String sheetID) {
    ConnStatement cst = new ConnStatement();
    boolean flag = false;
    String sql = null;
    /*try {
        sql = "delete from taw_rm_tree where ordercode = " + sheetID;
        cst.setStatementSql(sql);
        cst.executeUpdate();
        cst.commit();
      }
      catch (Exception e) {
        try {
          cst.rollback();
        }
        catch (Exception ex) {}
     //   e.printStackTrace();
        flag = false;
      }

      try {
         sql = "update taw_sheetname  set para3 = 1 where sheet_id = " + sheetID;
         cst.setStatementSql(sql);
         cst.executeUpdate();
         cst.commit();
       }
       catch (Exception e) {
         try {
           cst.rollback();
         }
         catch (Exception ex) {}
      //   e.printStackTrace();
         flag = false;
       }*/

    try {
      deleteSheet(sheetID);
      reCreateSheet(sheetID);
      flag = true;
    }
    catch (Exception e) {
      e.printStackTrace();
      flag = false;
    }

    /*������־*/
    if (flag) {
      //log = new logBO(ds);
      try {
        //log.insertLogToDB("admin", "", "");
      }
      catch (Exception e) {}
    }
    return flag;
  }

  //changed by songxuesong 2004-06-13
  public boolean dropSheet(String sheetID) {
    boolean flag = false;
    com.boco.eoms.db.util.BocoConnection conn = null;
    CallableStatement call = null;
    try {
      conn = ds.getConnection();
      //CacheDataLoad CDL = new CacheDataLoad();
      String sql = StaticMethod.getDBProcedure("dropTable(?)");
      call = conn.prepareCall(sql);
      call.setInt(1, new Integer(sheetID).intValue());
      try {
        call.executeUpdate();
        //CDL.initRmTreeCacheAll();
        flag = true;
      }
      catch (Exception e) {
        e.printStackTrace();
        flag = false;
      }
      call.close();

      conn.commit();
    }
    catch (Exception e) {
      // call.close();
      rollback(conn);
      BocoLog.error(this, 0, "���ñ?�洢��̱���!", e);
    }
    finally {
      close(conn);
    }

    if (flag) {
      StaticObject obj = StaticObject.getInstance();
      obj.removeObject("MODULETREE11");
      //log = new logBO(ds);
      try {
        //log.insertLogToDB("admin", "", "");
      }
      catch (Exception e) {}
    }

    if (deleteSheet(sheetID))
      flag = true;
    else flag =false;
    return flag;
  }

 //changed by songxuesong 2004-06-11
  public boolean publishSheet(String sheetID) {
    boolean flag = false;
    com.boco.eoms.db.util.BocoConnection conn = null;
    CallableStatement call = null;
    SheetName.removeInstance();
    shName = SheetName.getInstance();
    String sheetName = StaticMethod.dbNStrRev(shName.getSh_cname(sheetID));
    String appID = shName.getmodule_id(sheetID);

    try {
      conn = ds.getConnection();
      String sql = StaticMethod.getDBProcedure
          ("produceTable(?,?,?)");

        call = conn.prepareCall(sql);

        call.setString(1, sheetName);
        call.setInt(2, new Integer(appID).intValue());
        call.setInt(3, new Integer(sheetID).intValue());
        try {
          call.executeUpdate();
          flag = true;
        }
        catch (Exception e) {
          e.printStackTrace();
          flag = false;
        }
        call.close();

      conn.commit();
    }
    catch (Exception e) {
      // call.close();
      rollback(conn);
      BocoLog.error(this, 0, "���ñ?�洢��̱���!", e);
    }
    finally {
      close(conn);
    }
    if (flag) {
      StaticObject obj = StaticObject.getInstance();
      obj.removeObject("MODULETREE11");
      //log = new logBO(ds);
      try {
        //log.insertLogToDB("admin", "", "");
      }
      catch (Exception e) {}
    }
    return flag;
  }


  private String produceDDL(String sheetID) {
    String sql =null;
    shValue = new SheetValue(sheetID);
    shValue.reset();
    StringBuffer sqlBuffer = new StringBuffer("create table ");
    sqlBuffer.append(StaticMethod.null2String(prop.getProperty("pretable"),
                                              "boco_") + sheetID);
     if(StaticMethod.getDbType().equals(StaticVariable.ORACLE))
    sqlBuffer.append(
        " (id number,single_id varchar(80),attach_id varchar(200),user_id varchar(30),systime varchar(20),");
    else  if(StaticMethod.getDbType().equals(StaticVariable.INFORMIX))
      sqlBuffer.append(
        " (id serial,single_id varchar(80),attach_id varchar(200),user_id varchar(30),systime varchar(20),");
    //   " (id number,dept_id integer,room_id integer,user_id integer,");
    while (shValue.next()) {
      String buff = shValue.getVstoretypeStr();
      switch (StaticMethod.getIntValue(shValue.getVCtrl(), 1)) {
        case 0:
          buff += "not null";
          break;
        case 1:
          buff += "";
      }
      sqlBuffer.append(shValue.getVName() + " " + buff + ",");
    }
  //  sqlBuffer.append(
 //       "systime date,");
    sqlBuffer.append("primary key(id))");
    sql = sqlBuffer.toString();
    return sql;

  }
  // added by songxuesong 2004-06-11
  private String getTrigerSql(String sheetID) {
    String sql = null;
    sql = "create or replace trigger tr_taw_boco_"+sheetID+"_id "
        + "before insert on boco_"+sheetID
        + " for each row "
        + "declare "
        + "begin "
        + "if :new.id is null then "
        + "select q_taw_boco_"+sheetID+"_id.nextval into :new.id from dual; "
        + "end if; "
        +"end tr_taw_boco_"+sheetID+"_id; ";
    return sql;
  }

  // added by songxuesong 2004-06-11
 private String getSeqSql(String sheetID) {
   String sql = null;
   sql = "create sequence q_taw_boco_"+sheetID+"_id "
       + "minvalue 1 "
       + "maxvalue 99999999999999999999 "
       + "start with 1 "
       + "increment by 1 "
       + "cache 20 ";
   return sql;
 }

}
