package com.boco.eoms.infmanage.dao;

import java.sql.*;
import java.util.*;

import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.infmanage.model.*;
import com.boco.eoms.common.util.StaticMethod;

public class TawInfSortDAO
    extends DAO
{
    public Connection con = null;

    public TawInfSortDAO(com.boco.eoms.db.util.ConnectionPool ds)
    {
        super(ds);
    }

    /**
     * 在命令库表中插入新的记录
     * @param _tawInfMaintainer 命令库信息对象
     * @throws SQLException
     */
    public void insert(TawInfSort tawInfSort) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "";
        sql = "INSERT INTO taw_inf_sort (inf_sort_name)"
            + " VALUES (?)";

        try
        {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, tawInfSort.getInfSortName());
            pstmt.executeUpdate();
            close(pstmt);
            conn.setAutoCommit(false);
        }
        catch (Exception sqle)
        {
           // conn.rollback();
          //  sqle.printStackTrace();
        }
        finally
        {
           close(conn);
        }
    }

    /**
     * 根据记录ID删除命令库资料信息
     * @param id　记录ID
     * @throws SQLException
     */
    public void delete(int sortId) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;

        try
        {
            String sql = "";
            sql = "DELETE FROM taw_inf_sort WHERE inf_sort_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sortId);

            pstmt.executeUpdate();
            conn.commit();
        }
        catch (Exception e)
        {
          //  conn.rollback();
          //  e.printStackTrace();
        }
        finally
        {
            close(conn);
        }
    }

    /**
     * 获得命令库资料信息列表
     * @param condition　查询条件
     * @param offset　
     * @param length
     * @return　信息列表
     * @throws SQLException
     */
    public List getList(int offset, int length) throws
        SQLException
    {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        try
        {
            sql = "select * from taw_inf_sort";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (offset > 0)
            {
                int i = 0;
                while (i++ < offset)
                {
                    rs.next();
                }
            }

            int recCount = 0;
            while ( (recCount++ < length) && rs.next())
            {
                TawInfSort tawInfSort = new TawInfSort();
                populate(tawInfSort, rs);

                list.add(tawInfSort);
            }
        }
        catch (SQLException e)
        {
          //  e.printStackTrace();
        }
        finally
        {
            close(rs);
            close(pstmt);
            close(conn);
        }
        return list;
    }

    /**
     * 获取查询到的记录的长度
     * @param tableName
     * @param condition
     * @return　size
     * @throws SQLException
     */
    public int getSize(String tableName) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int size = 0;

        try
        {
            String sql = "";
            sql = "select count(*) from " + tableName;
            System.out.println("sql===" + sql);
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next())
            {size = rs.getInt(1);}
        }
        catch (SQLException se)
        {
           // se.printStackTrace();
        }
        finally
        {
           close(conn);
        }
        return size;
    }

    /**
     * 根据记录id获取资料类别信息对象
     * @param id　记录ID
     * @return
     * @throws SQLException
     */
    public TawInfSort getById(int sortId) throws
        SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        TawInfSort tawInfSort = new TawInfSort();
        try
        {
            String sql = "";
            sql = "select * from taw_inf_sort where inf_sort_id=?";

            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sortId);
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                populate(tawInfSort, rs);
            }
        }
        catch (Exception e)
        {
          //  e.printStackTrace();
        }
        finally
        {
           close(conn);
        }
        return tawInfSort;
    }

    /**
     * 根据记录ID修改命令库资料信息
     * @param tawInfSort
     * @throws SQLException
     */
    public void update(TawInfSort tawInfSort) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;

        try
        {
            String sql = "";
            sql =
                "UPDATE taw_inf_sort SET inf_sort_name=?" +
                " WHERE inf_sort_id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tawInfSort.getInfSortName());
            pstmt.setInt(2, tawInfSort.getInfSortId());

            pstmt.executeUpdate();
            conn.commit();
        }
        catch (Exception e)
        {
           //// conn.rollback();
           // e.printStackTrace();
        }
        finally
        {
           close(conn);
        }
    }

    public void insertTree(String sortName,int sortId) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String id = "";

        String sql = "";
        sql = "select * from taw_rm_tree where id like '16%'";

        try
        {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                id = String.valueOf(Integer.parseInt(rs.getString("id")) + 2);
            }

            String sqltree = "";
            sqltree =
                "insert into taw_rm_tree (id,parent_id,name,leaf,url,hide)"
                + "values ('" + id + "','" + 16 + "','" +
                sortName + "'," + 1 +
                ",'/infmanage/TawInfAppu/query.do?sortId=" +
                sortId + "'," + 0 + ")";
            pstmt = conn.prepareStatement(sqltree);
            pstmt.executeUpdate();
            close(pstmt);
            conn.setAutoCommit(false);
        }
        catch (Exception sqle)
        {
          //  conn.rollback();
          //  sqle.printStackTrace();
        }
        finally
        {
        close(conn);
        }
    }

    public int getSortId(String sortName) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int sortId = 0;

        String sql = "";
        sql = "select * from taw_inf_sort where inf_sort_name='" + sortName + "'";
        try
        {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next())
            {sortId = rs.getInt("inf_sort_id");}
            close(pstmt);
            conn.setAutoCommit(false);
        }
        catch(Exception sqle)
        {
         ////   conn.rollback();
          //  sqle.printStackTrace();
        }
        finally
        {
      close(conn);
        }
        return sortId;
    }

    public void updateTree(String sortName1,String sortName2) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;

        try
        {
            String sql = "";
            sql =
                "UPDATE taw_rm_tree SET name=?" +
                " WHERE name=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,sortName2);
            pstmt.setString(2,sortName1);
            pstmt.executeUpdate();
            conn.commit();
        }
        catch (Exception e)
        {
          //  conn.rollback();
           // e.printStackTrace();
        }
        finally
        {
            close(conn);
        }
    }

    public String getSortName(int sortId) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sortName = "";

        String sql = "";
        sql = "select * from taw_inf_sort where inf_sort_id=" + sortId;

        try
        {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next())
           { sortName = StaticMethod.dbNull2String(rs.getString("inf_sort_name"));}
            close(pstmt);
            conn.setAutoCommit(false);
        }
        catch(Exception sqle)
        {
          //  conn.rollback();
           // sqle.printStackTrace();
        }
        finally
        {
            close(conn);
        }
        return sortName;
    }

    public void deleteTree(String sortName) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;

        try
        {
            String sql = "";
            sql = "DELETE FROM taw_rm_tree WHERE name='" + sortName + "'";
            pstmt = conn.prepareStatement(sql);

            pstmt.executeUpdate();
            conn.commit();
        }
        catch (Exception e)
        {
           // conn.rollback();
          //  e.printStackTrace();
        }
        finally
        {
            close(conn);
        }
    }
}