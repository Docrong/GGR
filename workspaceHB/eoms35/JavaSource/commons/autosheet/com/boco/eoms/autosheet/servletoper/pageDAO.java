package com.boco.eoms.autosheet.servletoper;
import javax.sql.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.common.controller.*;
import com.boco.eoms.db.util.*;
import com.boco.eoms.common.dao.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class pageDAO extends DAO{
  private String sql = "";
  private int length = 15;
  public pageDAO(com.boco.eoms.db.util.ConnectionPool ds) {
    super(ds);
  }

  //根据从页面获取的参数拼写SQL语句，并将取出的数据存放到ListData这个List中
  public ArrayList getData(int length,int DeptId,String UserId,int offset){
    ArrayList ListData = new ArrayList();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rsPageData = null;
    //得到连接池的连接
    conn = ds.getConnection();
    if (UserId.equals(StaticVariable.ADMIN)) {
      sql="select sheet_id,sh_cname,para3,module_id from taw_sheetname order by module_id";
    }
    else
      sql="select sheet_id,sh_cname,para3,module_id from taw_sheetname where dept_id = "+DeptId+" or dept_id = 1 order by module_id";
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
      rsPageData = pstmt.executeQuery();

      if (offset > 0){
        rsPageData.absolute(offset);
      }
      int recCount = 0;
      while ( (recCount++ < length) && rsPageData.next()) {
        //将取出的数据拼成字符串，存放到LIST中
        String Data = rsPageData.getString(1) + "-" + rsPageData.getString(2) + "-" + rsPageData.getString(3) + "-" + rsPageData.getString(4);
        ListData.add(Data);
      }
    }
    catch (SQLException e) {
      close(rsPageData);
      close(pstmt);
      rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return ListData;
  }

  //计算记录集内的数据总数,同时计算出总页数，并将总页数、总记录数和每页的记录数拼成字符串返回
  public String getSize(String Table){
    com.boco.eoms.db.util.BocoConnection conn=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    String size = "";
    int MaxRec = 0;
    int MaxPage = 0;
    ResultSet rsPageSize = null;
    conn = ds.getConnection();
    sql="select count(*) from " + Table;
    try{
      conn = ds.getConnection();
      pstmt=conn.prepareStatement(sql);
      rsPageSize=pstmt.executeQuery();
      while(rsPageSize.next()){
        MaxRec = rsPageSize.getInt(1);
      }
      if (MaxRec % length == 0){
        MaxPage = MaxRec/length;
      }
      else{
        MaxPage = MaxRec/length + 1;
      }
      size = MaxRec + "_" + MaxPage + "_" + length;
    }
    catch (SQLException e){
      close(rsPageSize);
      close(pstmt);
      rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return size;
  }

}
