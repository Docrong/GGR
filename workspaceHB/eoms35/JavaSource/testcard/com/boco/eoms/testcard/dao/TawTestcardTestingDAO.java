package com.boco.eoms.testcard.dao;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.commons.system.dict.service.impl.ID2NameServiceImpl;
import com.boco.eoms.db.util.ConnectionPool;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.boco.eoms.testcard.model.TawTestcardTesting;

public class TawTestcardTestingDAO extends DAO {
  public TawTestcardTestingDAO(ConnectionPool ds) {
    super(ds);
  }

  public List getTestedResult(int offset, int limit,String condition) throws SQLException {
  ArrayList list = new ArrayList();
  com.boco.eoms.db.util.BocoConnection conn = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  TawTestcardTesting tawTestcardTesting =null;
  TawTestcardDAO tawTestcardDAO=new TawTestcardDAO();
  try {
    conn = ds.getConnection();
    String sql = "SELECT iccid,msisdn,testtime,outcome,conner,leave,accessories from taw_testcard_testing "+condition+" order by leave,testtime";
    pstmt = conn.prepareStatement(sql);
    rs = pstmt.executeQuery();
    if(offset > 0) {
      int i=0;
      while(i++ < offset){
        rs.next();
      }
    }
    int recCount = 0;
    while((recCount++ < limit) && rs.next()) {
      tawTestcardTesting = new TawTestcardTesting();
      populate(tawTestcardTesting, rs);
     try {
    	 ID2NameServiceImpl mgr = (ID2NameServiceImpl) ApplicationContextHolder
			.getInstance().getBean("id2nameService");
    	 tawTestcardTesting.setLeave(mgr.id2Name(tawTestcardTesting.getLeave(),
			"ItawSystemDictTypeDao"));
//       tawTestcardTesting.setLeave(tawTestcardDAO.getStorage(tawTestcardTesting.getLeave()));
     }
     catch (Exception ex) {
       ex.printStackTrace();
     }
      list.add(tawTestcardTesting);
      tawTestcardTesting =null;
    }
    close(rs);
    close(pstmt);
  } catch (SQLException e) {
    close(rs);
    close(pstmt);
    e.printStackTrace();
  } finally {
    tawTestcardTesting=null;
    close(conn);
  }
  return list;
}

public int  getLengh(String condition) throws SQLException {
  int length=0;
  com.boco.eoms.db.util.BocoConnection conn = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  try {
    conn = ds.getConnection();
    String sql = "SELECT count(*) from taw_testcard_testing "+condition;
    System.out.println(sql);
    pstmt = conn.prepareStatement(sql);
    rs = pstmt.executeQuery();
    while( rs.next()) {
      length=rs.getInt(1);
      System.out.println(length);
    }
    close(rs);
    close(pstmt);
  } catch (SQLException e) {
    close(rs);
    close(pstmt);
    e.printStackTrace();
  } finally {
    close(conn);
  }
  return length;
}



}
