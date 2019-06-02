package com.boco.eoms.duty.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import org.apache.struts.util.LabelValueBean;

import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.common.util.StaticMethod;

/**
 * <p>Title: TawAutoSheetDAO</p>
 * <p>Description:自动表单 </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Boco</p>
 * @author: Lihong Qi
 * @version 1.0
 */

public class TawAutoSheetDAO extends DAO{
  public TawAutoSheetDAO(com.boco.eoms.db.util.ConnectionPool ds) {
     super(ds);
  }

  /**
   * @see 取出指定模块的表单名
   * @param moduleId
   * @return
   */
  public Vector getAutoSheetSelect(int moduleId){
    Vector retVector = new Vector();
    try{
     com.boco.eoms.db.util.BocoConnection conn = null;
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     String sql = "";

     conn = ds.getConnection();
     sql = "SELECT sheet_id,sh_cname FROM taw_sheetname WHERE module_id =? ORDER BY sheet_id";
     pstmt = conn.prepareStatement(sql);
     pstmt.setInt(1,moduleId);
     rs = pstmt.executeQuery();
     String labelString = "";
     String labelValue = "";

     retVector.add(new LabelValueBean("- 选择 -",""));
     while (rs.next()){
       labelString = StaticMethod.dbNull2String(rs.getString("sh_cname"));
       labelValue = String.valueOf(rs.getInt("sheet_id"));
       retVector.add(new LabelValueBean(labelString,labelValue));
     }

     //释放对象
     close(conn);
     pstmt = null;
     rs = null;
     sql = "";
    }catch(Exception e){
      e.printStackTrace();
    }
    return retVector;
  }

}
