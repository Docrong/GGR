/**
 * @see
 * <p>���������������õ�DAO��</p>
 * @author �Դ�
 * @version 2.0
 */


package com.boco.eoms.duty.dao;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;

import com.boco.eoms.duty.model.*;
import com.boco.eoms.common.util.CacheManager;
import com.boco.eoms.common.dao.*;
//import com.boco.eoms.jbzl.dao.TawApparatusroomDAO;
//import com.boco.eoms.jbzl.model.TawApparatusroom;
//import com.boco.eoms.wsdict.model.TawWsDictRel;

public class TawRmSysteminfoSpeDAO extends DAO {

  public TawRmSysteminfoSpeDAO(com.boco.eoms.db.util.ConnectionPool ds) {
    super(ds);
  }

  /**
   * @see ����������Ϣ����
   * @param tawRmSysteminfo
   * @throws SQLException
   */
  public void insert(TawRmSysteminfoSpe tawRmSysteminfoSpe) throws SQLException {
     String sql="";
    sql = "INSERT INTO taw_rm_systeminfo_spe (ROOM_ID,SPECIALTY_ID,SPECIALTY_TYPE) VALUES (?, ?, ?)";
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, tawRmSysteminfoSpe.getRoomId());
      pstmt.setInt(2, tawRmSysteminfoSpe.getSpecialtyId());
      pstmt.setInt(3, tawRmSysteminfoSpe.getSpecialtyType());

      pstmt.executeUpdate();
      pstmt.close();
      conn.commit();
    } catch (SQLException sqle) {
      close(pstmt);
      rollback(conn);
      sqle.printStackTrace();
      throw sqle;
    } finally {
    	close(conn);
    }
  }



  /**
   * @see  ��ݻ�ID��ɾ���רҵ��¼
   * @param roomId
   * @throws SQLException
   */
  public void delete(int roomId) throws SQLException {
  	com.boco.eoms.db.util.BocoConnection conn = null;
  	PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      String sql = "DELETE FROM taw_rm_systeminfo_spe WHERE room_id=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, roomId);
      pstmt.executeUpdate();
      close(pstmt);
      conn.commit();
    } catch (SQLException e) {
      close(pstmt);
      rollback(conn);
      e.printStackTrace();
    } finally {
    	close(conn);
    }
  }

  //add by xudongsuo 20071220 start
  /**
   * @see  ��ݻ�ID��ɾ���רҵ��¼
   * @param roomId
   * @throws SQLException
   */
  public void delete(int roomId,int systype) throws SQLException {
  	com.boco.eoms.db.util.BocoConnection conn = null;
  	PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      String sql = "DELETE FROM taw_rm_systeminfo_spe WHERE room_id=? and specialty_type='"+systype+"'";
      System.out.println("sql" + sql);
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, roomId);
      pstmt.executeUpdate();
      close(pstmt);
      conn.commit();
    } catch (SQLException e) {
      close(pstmt);
      rollback(conn);
      e.printStackTrace();
    } finally {
    	close(conn);
    }
  }


  /**
   * @see �õ�����רҵ�б� ��רҵ�ֵ�������Ϊ406203
   * @return
   * @throws SQLException
   */
  public List getSublist(int roomId) throws SQLException {
    ArrayList list = new ArrayList();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql = "select room_id,specialty_id,specialty_type from taw_rm_systeminfo_spe  where room_id=? and specialty_type=406203";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, roomId);
      rs = pstmt.executeQuery();
      while(rs.next()) {
    	TawRmSysteminfoSpe tawRmSysteminfoSpe = new TawRmSysteminfoSpe();
        populate(tawRmSysteminfoSpe, rs);
        list.add(tawRmSysteminfoSpe);
        tawRmSysteminfoSpe=null;
      }
      close(rs);
      close(pstmt);
    } catch (SQLException e) {
      close(rs);
      close(pstmt);
    //  rollback(conn); liquan modify
      e.printStackTrace();
    } finally {
    	close(conn);
    }
    return list;
  }

  /**
   * @see �õ���רҵ�б� רҵ�ֵ�������Ϊ406202
   * @return
   * @throws SQLException
   */
  public List getlist(int roomId) throws SQLException {
	  ArrayList list = new ArrayList();
	    com.boco.eoms.db.util.BocoConnection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	      conn = ds.getConnection();
	      String sql = "select room_id,specialty_id,specialty_type from taw_rm_systeminfo_spe  where room_id=? and specialty_type=406202";
	      pstmt = conn.prepareStatement(sql);
	      pstmt.setInt(1, roomId);
	      rs = pstmt.executeQuery();

	      while( rs.next()) {
	    	  TawRmSysteminfoSpe tawRmSysteminfoSpe = new TawRmSysteminfoSpe();
	          populate(tawRmSysteminfoSpe, rs);
	          list.add(tawRmSysteminfoSpe);
	          tawRmSysteminfoSpe=null;
	      }
	      close(rs);
	      close(pstmt);
	    } catch (SQLException e) {
	      close(rs);
	      close(pstmt);
	     //  rollback(conn); liquan modify
	      e.printStackTrace();
	    } finally {
	    	close(conn);
	    }
    return list;
  }
  /**
   * @see �õ���רҵ�б� רҵ�ֵ�������Ϊ406202 ��רҵ�ֵ�������Ϊ406203
   * @return
   * @throws SQLException
   */
  public HashMap getMap() throws SQLException {
	  HashMap map = new HashMap();
	    com.boco.eoms.db.util.BocoConnection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	      conn = ds.getConnection();
	      String sql = "select t.dict_id||','||t.dict_type as dict_id ,t.dict_name as  dict_name from Taw_System_DictType t where t.dict_type=406203 or t.dict_type=406202 ";
	      pstmt = conn.prepareStatement(sql);

	      rs = pstmt.executeQuery();

	      while( rs.next()) {
	    	   map.put(rs.getString("dict_id"), rs.getString("dict_name"));
	      }
	      close(rs);
	      close(pstmt);
	    } catch (SQLException e) {
	      close(rs);
	      close(pstmt);
	      //  rollback(conn); liquan modify
	      e.printStackTrace();
	    } finally {
	    	close(conn);
	    }
    return map;
  }
  public HashMap getMap(String parentdictid) throws SQLException {
	  HashMap map = new HashMap();
	    com.boco.eoms.db.util.BocoConnection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	      conn = ds.getConnection();
	      String sql = "select t.dictname,t.dictid from Taw_System_DictType t where t.parentdictid like '"+parentdictid+"%'";
	      pstmt = conn.prepareStatement(sql);

	      rs = pstmt.executeQuery();
	     	      while( rs.next()) {
	    	   map.put(rs.getString("dictid"), rs.getString("dictname"));
	      }
	      close(rs);
	      close(pstmt);
	    } catch (SQLException e) {
	      close(rs);
	      close(pstmt);
	      //  rollback(conn); liquan modify
	      e.printStackTrace();
	    } finally {
	    	close(conn);
	    }
    return map;
  }
//  public ArrayList getDicRel()throws SQLException {
//	    ArrayList list=new ArrayList();
//	    com.boco.eoms.db.util.BocoConnection conn = null;
//	    PreparedStatement pstmt = null;
//	    ResultSet rs = null;
//	    try {
//	      conn = ds.getConnection();
//	      String sql = "select dict_id,dict_type,dict_id_rel,dict_type_rel from taw_ws_dict_rel r where r.dict_type=406202 and deleted=0 ";
//	      pstmt = conn.prepareStatement(sql);
//
//	      rs = pstmt.executeQuery();
//
//	      while( rs.next()) {
//	    	  TawWsDictRel dictRle=new TawWsDictRel();
//	    	  populate(dictRle, rs);
//	    	  list.add(dictRle);
//	      }
//	      close(rs);
//	      close(pstmt);
//	    } catch (SQLException e) {
//	      close(rs);
//	      close(pstmt);
//	     //  rollback(conn); liquan modify
//	      e.printStackTrace();
//	    } finally {
//	    	close(conn);
//	    }
//       return list;
//  }

}
