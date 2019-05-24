package com.boco.eoms.autosheet.servletoper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.*;
import java.io.PrintWriter;
import com.boco.eoms.common.util.*;
import com.boco.eoms.db.util.*;
import com.boco.eoms.autosheet.util.*;

public class SheetValueOperator {
  /*****************************
   定义变量
   */
  private String vName = "", align = "", valign = "", color = "", nowrap = "",
      vStoreType = "",defaultVal = "";
  private int isattach, index, newLine, isQuery, attrID;
  private int width, height, colspan, rowspan;
  private int showType, formWidth, formHeight, typeID;
  private int isDefault, vCtrl;
  private int sheetID;
  private SheetValue shValue;

  // private HttpSession session;
  private RecordSet rs;

  /*******************
   定义数据库封装对象
   */
  private ConnStatement cstmt = new ConnStatement();
  private LogMan log = LogMan.getInstance();

  /*******************
   构造参数
   */
  public SheetValueOperator() {
  }

  /**********获取请求参数
     @param request http请求
     @throws Exception newLine
   */
  public void doInit(HttpServletRequest request) throws Exception {
    vName = request.getParameter("vName");
    isattach = StaticMethod.getIntValue(request.getParameter("isattach"), 0);
    index = StaticMethod.getIntValue(request.getParameter("index"), 0);
    newLine = StaticMethod.getIntValue(request.getParameter("newLine"), 0);
    isQuery = StaticMethod.getIntValue(request.getParameter("isQuery"), 0);
    attrID = StaticMethod.getIntValue(request.getParameter("attrID"), 0);
    width = StaticMethod.getIntValue(request.getParameter("width"), 0);
    height = StaticMethod.getIntValue(request.getParameter("height"), 0);
    colspan = StaticMethod.getIntValue(request.getParameter("colspan"), 0);
    rowspan = StaticMethod.getIntValue(request.getParameter("rowspan"), 0);
    align = StaticMethod.null2String(request.getParameter("align"), "");
    valign = StaticMethod.null2String(request.getParameter("valign"), "");
    nowrap = StaticMethod.null2String(request.getParameter("nowrap"), "nowrap");
    color = StaticMethod.null2String(request.getParameter("color"), "");

    showType = StaticMethod.getIntValue(request.getParameter("showtype"), 0);
    formWidth = StaticMethod.getIntValue(request.getParameter("formWidth"), 0);
    formHeight = StaticMethod.getIntValue(request.getParameter("formHeight"), 0);
    typeID = StaticMethod.getIntValue(request.getParameter("typeID"), 0);

    vStoreType = StaticMethod.null2String(request.getParameter("vStoreType"),
                                          "varchar(255)");
    isDefault = StaticMethod.getIntValue(request.getParameter("isDefault"), 0);
    defaultVal = StaticMethod.null2String(request.getParameter("defaultval"), "");
    vCtrl = StaticMethod.getIntValue(request.getParameter("vCtrl"), 0);

    sheetID = StaticMethod.getIntValue( (String) request.getSession().
                                       getAttribute("sheetID"),
                                       0);
    shValue = new SheetValue(sheetID + "");
  }

  /*************************************************************
   处理录入数据,
   @throws Exception
   @return int
   */
  public boolean doInsert() throws Exception {

    try {
      //插入taw_sheetanmne
      cstmt = new ConnStatement();
      String sql = "insert into taw_sheetvalue(sheet_id,v_name,isattach,index1,newLine,isQuery,attr_id," +
          "align,valign,colspan,rowspan,width,height,nowrap,color," +
          "showtype,formWidth,formHeight,typeid," +
          " v_storetype,isDefault,defaultval,v_ctrl) " +
          "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

      cstmt.setStatementSql(sql);
      cstmt.setInt(1, sheetID);
      cstmt.setString(2, StaticMethod.dbNStrRev(vName));
      cstmt.setInt(3, isattach);
      cstmt.setInt(4, index);
      cstmt.setInt(5, newLine);
      cstmt.setInt(6, isQuery);
      cstmt.setInt(7, attrID);
      cstmt.setString(8, align);
      cstmt.setString(9, valign);
      cstmt.setInt(10, colspan);
      cstmt.setInt(11, rowspan);
      cstmt.setInt(12, width);
      cstmt.setInt(13, height);
      cstmt.setString(14, nowrap);
      cstmt.setString(15, color);
      cstmt.setInt(16, showType);
      cstmt.setInt(17, formWidth);
      cstmt.setInt(18, formHeight);
      cstmt.setInt(19, typeID);
      cstmt.setString(20, vStoreType);
      cstmt.setInt(21, isDefault);
      cstmt.setString(22, defaultVal);
      cstmt.setInt(23, vCtrl);
      cstmt.executeUpdate();
      cstmt.commit();
      System.out.println("sql=" + sql);
    }
    catch (Exception e) {
      try {
        cstmt.rollback();
      }
      catch (Exception ex) {}

      e.printStackTrace();
      log.writeLog("SheetValueOperator", e);
      throw new Exception("数据库操作失败");
    }
    finally {
      cstmt.close();
    }
    return true;
  }

  /*************************************************************
   处理录入数据,
   @throws Exception
   @return int
   */
  public boolean doUpdate(HttpServletRequest request) throws Exception {
    cstmt = new ConnStatement();
    int value_id = StaticMethod.getIntValue(request.getParameter("value_id"), 0);
    int sheet_id = StaticMethod.getIntValue(request.getParameter("sheet_id"), 0);
    try {
      //插入taw_sheetanmne
      String sql =
          "update taw_sheetvalue set v_name=?,isattach=?,index1=?,newLine=" +
          "?,isquery=?,attr_id=?,align=?,valign=?,colspan=?,rowspan=?,width=" +
          "?,height=?,nowrap=?,color=?,showtype=?" +
          ",formWidth=?,formHeight=?,typeid=?," +
          " v_storetype=?,isDefault=?,defaultval=?,v_ctrl=? " +
          "where value_id=?";

      cstmt.setStatementSql(sql);
      cstmt.setString(1, vName);
      cstmt.setInt(2, isattach);
      cstmt.setInt(3, index);
      cstmt.setInt(4, newLine);
      cstmt.setInt(5, isQuery);
      cstmt.setInt(6, attrID);
      cstmt.setString(7, align);
      cstmt.setString(8, valign);
      cstmt.setInt(9, colspan);
      cstmt.setInt(10, rowspan);
      cstmt.setInt(11, width);
      cstmt.setInt(12, height);
      cstmt.setString(13, nowrap);
      cstmt.setString(14, color);
      cstmt.setInt(15, showType);
      cstmt.setInt(16, formWidth);
      cstmt.setInt(17, formHeight);
      cstmt.setInt(18, typeID);
      cstmt.setString(19, vStoreType);
      cstmt.setInt(20, isDefault);
      cstmt.setString(21, defaultVal);
      cstmt.setInt(22, vCtrl);
      cstmt.setInt(23, value_id);
      cstmt.executeUpdate();
      cstmt.commit();

      if (vStoreType.equals("date"))
        vStoreType = "varchar(30)";
      else if (vStoreType.equals("datetime"))
        vStoreType = "varchar(50)";

        //数值的不同处理
        //   else if (vStoreType.equals("integer"))
        //    vStoreType = "varchar(10)";
        //   else if (vStoreType.equals("float"))
        //    vStoreType = "varchar(20)";
      if (vStoreType.equals("integer") || vStoreType.equals("float")) {
        sql = "delete from boco_" + sheet_id;
        cstmt.setStatementSql(sql);
        cstmt.executeUpdate();
        cstmt.commit();
      }

      sql = "alter table boco_" + sheet_id + " modify " + vName + " " +
          vStoreType;
      cstmt.setStatementSql(sql);
      cstmt.executeUpdate();
      cstmt.commit();
    }
    catch (Exception e) {
      try {
        cstmt.rollback();
      }
      catch (Exception ex) {}

      log.writeLog("SheetValueUpdateOperator", e);
      e.printStackTrace();
      throw new Exception("数据库修改操作失败");
    }
    finally {
      cstmt.close();
    }
    return true;
  }

  public boolean doDelete(HttpServletRequest request,
                          HttpServletResponse response) {
    boolean flag = false;
    String sheet_id = request.getParameter("sheet_id");
    String value_id = request.getParameter("value_id");
    String sql = "delete from taw_sheetvalue where sheet_id=? and value_id=?";
    PrintWriter out = null;
    try {
      cstmt = new ConnStatement();
      cstmt.setStatementSql(sql);
      cstmt.setString(1, sheet_id);
      cstmt.setString(2, value_id);
      cstmt.executeUpdate();
      cstmt.commit();
      flag = true;
    }
    catch (Exception e) {
      try {
        cstmt.rollback();
      }
      catch (Exception ex) {}
    }
    finally {
      cstmt.close();
    }
    return flag;
  }
}
