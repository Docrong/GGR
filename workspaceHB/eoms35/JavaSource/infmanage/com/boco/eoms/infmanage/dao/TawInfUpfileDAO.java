package com.boco.eoms.infmanage.dao;

import com.boco.eoms.common.dao.DAO;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.infmanage.model.*;
//import com.boco.eoms.jbzl.model.*;
import com.boco.eoms.common.util.CacheManager;
import com.boco.eoms.common.dao.*;

public class TawInfUpfileDAO
    extends DAO
{
    public TawInfUpfileDAO(com.boco.eoms.db.util.ConnectionPool ds)
    {
        super(ds);
    }

    public void insert(TawInfUpfile tawInfUpfile) throws SQLException
    {
        String sql;
        sql = "INSERT INTO taw_inf_upfile"
            + "(info_id, inf_upfile_name, encodename)"
            + " VALUES (?, ?, ?)";
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tawInfUpfile.getInfoId());
            pstmt.setString(2, tawInfUpfile.getInfUpfileName());
            pstmt.setString(3, tawInfUpfile.getEncodename());
            pstmt.executeUpdate();
            pstmt.close();
            conn.commit();
        }
        catch (SQLException sqle)
        {
          //  close(rs);
        //    close(pstmt);
        //    rollback(conn);
         //   sqle.printStackTrace();
         //   throw sqle;
        }
        finally
        {
            close(conn);
        }
    }

    /*public void insertReply(TawInfUpfile tawInfUpfile) throws
        SQLException
    {
        String sql;
        sql = "INSERT INTO taw_ws_upfile"
            + "(task_id, filename, encodename,status,replyid)"
            + " VALUES (?, ?, ?, ?,?)";
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tawWorksheetFile.getTaskid());
            pstmt.setString(2, tawWorksheetFile.getFilename());
            pstmt.setString(3, tawWorksheetFile.getEncodename());
            pstmt.setInt(4, tawWorksheetFile.getStatus());
            pstmt.setInt(5, tawWorksheetFile.getReplyid());
            pstmt.executeUpdate();
            pstmt.close();
            conn.commit();
        }
        catch (SQLException sqle)
        {
            close(rs);
            close(pstmt);
            rollback(conn);
            sqle.printStackTrace();
            throw sqle;
        }
        finally
        {
            close(conn);
        }
    }*/

    public void update(TawInfUpfile tawInfUpfile) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            conn = ds.getConnection();
            String sql =
                "UPDATE taw_inf_upfile SET inf_upfile_name=?, encodename=? WHERE info_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tawInfUpfile.getInfUpfileName());
            pstmt.setString(2, tawInfUpfile.getEncodename());
            pstmt.setInt(3, tawInfUpfile.getInfoId());
            pstmt.executeUpdate();
            close(pstmt);
            conn.commit();
        }
        catch (SQLException e)
        {
         //   close(pstmt);
          //  rollback(conn);
         //   e.printStackTrace();
        }
        finally
        {
            close(conn);
        }
    }

    /*public void updateReply(TawInfUpfile tawInfUpfile) throws
        SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            conn = ds.getConnection();
            String sql = "UPDATE taw_ws_upfile SET filename=?, encodename=? WHERE task_id=? and replyid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tawInfUpfile.getFilename());
            pstmt.setString(2, tawInfUpfile.getEncodename());
            pstmt.setString(3, tawInfUpfile.getTaskid());
            pstmt.setInt(4, tawInfUpfile.getReplyid());
            pstmt.executeUpdate();
            close(pstmt);
            conn.commit();
        }
        catch (SQLException e)
        {
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        }
        finally
        {
            close(conn);
        }
    }*/

    public void delete(int infoId) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            conn = ds.getConnection();
            String sql =
                "DELETE FROM taw_inf_upfile WHERE info_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, infoId);
            pstmt.executeUpdate();
            close(pstmt);
            conn.commit();
        }
        catch (SQLException e)
        {
            close(pstmt);
           // rollback(conn);
          //  e.printStackTrace();
        }
        finally
        {
            close(conn);
        }
    }

    public String getFilename(int infoId) throws SQLException
    {
        String filename = "";
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            conn = ds.getConnection();
            String sql =
                "SELECT * FROM taw_inf_upfile WHERE info_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, infoId);
            rs = pstmt.executeQuery();
           if(rs.next())
            {filename = StaticMethod.dbNull2String(rs.getString("inf_upfile_name"));}
            close(rs);
            close(pstmt);
        }
        catch (SQLException e)
        {
            close(rs);
            close(pstmt);
          //  rollback(conn);
          //  e.printStackTrace();
        }
        finally
        {
            close(conn);
        }
        return filename;
    }

    /*public void deleteReply(String taskId, int replyid) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            conn = ds.getConnection();
            String sql =
                "DELETE FROM taw_ws_upfile WHERE task_id=? and replyid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, taskId);
            pstmt.setInt(2, replyid);
            pstmt.executeUpdate();
            close(pstmt);
            conn.commit();
        }
        catch (SQLException e)
        {
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        }
        finally
        {
            close(conn);
        }
    }*/

    public TawInfUpfile retrieve(int infoId) throws
        SQLException
    {
        TawInfUpfile tawInfUpfile = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            conn = ds.getConnection();
            String sql =
                "SELECT * FROM taw_inf_upfile WHERE info_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, infoId);
            rs = pstmt.executeQuery();
            if (rs.next())
            {
                tawInfUpfile = new TawInfUpfile();
                tawInfUpfile.setInfoId(rs.getInt("info_id"));
                tawInfUpfile.setInfUpfileName(StaticMethod.dbNull2String(rs.
                    getString("inf_upfile_name")));
                tawInfUpfile.setEncodename(StaticMethod.dbNull2String(rs.
                    getString("encodename")));
            }
            close(rs);
            close(pstmt);
        }
        catch (SQLException e)
        {
            close(rs);
            close(pstmt);
          //  rollback(conn);
           // e.printStackTrace();
        }
        finally
        {
            close(conn);
        }
        return tawInfUpfile;
    }

    /*public TawInfUpfile retrieveReply(int infoId, int replyid) throws
        SQLException
    {
        TawInfUpfile tawInfUpfile = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            conn = ds.getConnection();
            String sql =
                "SELECT * FROM taw_inf_upfile WHERE inf0_id=? and replyid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, infoId);
            pstmt.setInt(2, replyid);
            rs = pstmt.executeQuery();
            if (rs.next())
            {
                tawInfUpfile = new TawInfUpfile();
                tawInfUpfile.setInfoId(rs.getInt("info_id"));
                tawInfUpfile.setInfUpfileName(StaticMethod.dbNull2String(rs.
                    getString("inf_upfile_name")));
                tawInfUpfile.setEncodename(StaticMethod.dbNull2String(rs.
                    getString("encodename")));
                //tawWorksheetFile = null;
            }
            close(rs);
            close(pstmt);
        }
        catch (SQLException e)
        {
            close(rs);
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        }
        finally
        {
            close(conn);
        }
        return tawInfUpfile;
    }*/

}