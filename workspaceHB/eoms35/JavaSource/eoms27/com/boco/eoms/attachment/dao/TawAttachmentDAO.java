package com.boco.eoms.attachment.dao;


import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;

import com.boco.eoms.attachment.model.TawAttachment;
import com.boco.eoms.common.dao.*;

public class TawAttachmentDAO extends DAO {

  public TawAttachmentDAO(com.boco.eoms.db.util.ConnectionPool ds) {
    super(ds);
  }
  public TawAttachmentDAO() {
     super();
   }
  public synchronized int insert(String _name, String _filename,int _size, String _cruser,
      String _crtime, int _appId) throws SQLException {
    String sql;
    int attachmentId = 0;
    sql = "INSERT INTO taw_attachment (attachmentname, attachmentfilename,"
          + "attachmentsize,cruser,crtime,appid) VALUES (?,?,?,?,?,?)";

    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, _name);
      pstmt.setString(2, _filename);
      pstmt.setInt(3, _size);
      pstmt.setString(4, _cruser);
      pstmt.setString(5, _crtime);
      pstmt.setInt(6, _appId);
      pstmt.executeUpdate();
      pstmt.close();
      conn.commit();
    }
    catch (SQLException sqle) {
      close(pstmt);
    }
    sql = "SELECT attachmentid FROM taw_attachment order by attachmentid desc";
    try
    {
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      if (rs.next())
      {
        attachmentId = rs.getInt(1);
      }
    }
    catch (SQLException sqle)
    {
      sqle.printStackTrace();
    }
    finally {
      close(conn);
      return attachmentId;
    }
  }

  public void delete(int _attachmentId) throws SQLException
  {
    com.boco.eoms.db.util.BocoConnection conn = null;
    conn = ds.getConnection();
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    String sql = "delete from taw_attachment where attachmentid = ?";
    try
    {
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1,_attachmentId);
      pstmt.executeUpdate();
      conn.commit();
    }
    catch (SQLException sqle)
    {
      sqle.printStackTrace();
    }
    finally
    {
      close(pstmt);
      close(conn);
    }
  }

  public List list(String _idList) throws SQLException {
    ArrayList list = new ArrayList();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql = "SELECT attachmentid,attachmentname, attachmentfilename, "
                   + "attachmentsize,cruser,crtime,appid FROM taw_attachment ";
               if(!_idList.equals("")){
                 sql += "where attachmentid in (" + _idList + ")";
               }else{
                 sql += "where attachmentid in ('" + _idList + "')";
               }
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        TawAttachment tawAttachment = new TawAttachment();

        tawAttachment.setAttachmentId(rs.getInt("attachmentid"));
        tawAttachment.setAttachmentName(rs.getString("attachmentname"));
        tawAttachment.setAttachmentFilename(rs.getString("attachmentfilename"));
        tawAttachment.setSize(rs.getInt("attachmentsize"));
        tawAttachment.setCrtime(rs.getString("crtime"));
        tawAttachment.setCruser(rs.getString("cruser"));
        tawAttachment.setAppId(rs.getInt("appid"));

        list.add(tawAttachment);
        tawAttachment=null;
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
    return list;
  }

  public TawAttachment retrieve(String _attid) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    TawAttachment tawAttachment = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql = "SELECT attachmentid,attachmentname, attachmentfilename, "
                   + "attachmentsize,cruser,crtime,appid FROM taw_attachment "
                   + "where attachmentid = " + _attid;
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      if(rs.next()) {
        tawAttachment = new TawAttachment();
        tawAttachment.setAttachmentId(rs.getInt("attachmentid"));
        tawAttachment.setAttachmentName(rs.getString("attachmentname"));
        tawAttachment.setAttachmentFilename(rs.getString("attachmentfilename"));
        tawAttachment.setSize(rs.getInt("attachmentsize"));
        tawAttachment.setCrtime(rs.getString("crtime"));
        tawAttachment.setCruser(rs.getString("cruser"));
        tawAttachment.setAppId(rs.getInt("appid"));
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
    return tawAttachment;
  }

}
