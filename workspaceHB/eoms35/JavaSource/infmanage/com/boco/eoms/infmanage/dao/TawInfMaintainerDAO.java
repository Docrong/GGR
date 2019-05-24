package com.boco.eoms.infmanage.dao;

import java.sql.*;
import java.util.*;

import com.boco.eoms.common.dao.*;
import com.boco.eoms.infmanage.model.*;

public class TawInfMaintainerDAO
    extends DAO
{
    public Connection con = null;

    public TawInfMaintainerDAO(com.boco.eoms.db.util.ConnectionPool ds)
    {
        super(ds);
    }

    /**
     * 在维护人员信息表中插入新的记录
     * @param _tawInfMaintainer 维护人员信息对象
     * @throws SQLException
     */
    public void insert(TawInfMaintainer _tawInfMaintainer) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "";
        sql = "INSERT INTO taw_inf_maintainer (maintainer_name,maintainer_sex,dept_id,dep_name,tele,tele_mobile,tele_home,email,special)"
            + " VALUES (?,?,?,?,?,?,?,?,?)";

        try
        {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, _tawInfMaintainer.getMaintainerName());
            pstmt.setString(2, _tawInfMaintainer.getMaintainerSex());
            pstmt.setInt(3, _tawInfMaintainer.getDeptId());
            pstmt.setString(4, _tawInfMaintainer.getDeptName());
            pstmt.setString(5, _tawInfMaintainer.getTele());
            pstmt.setString(6, _tawInfMaintainer.getTeleMobile());
            pstmt.setString(7, _tawInfMaintainer.getTeleHome());
            pstmt.setString(8, _tawInfMaintainer.getEmail());
            pstmt.setString(9, _tawInfMaintainer.getSpecial());
            pstmt.executeUpdate();
            close(pstmt);
            conn.setAutoCommit(false);
        }
        catch (Exception sqle)
        {
            conn.rollback();
            sqle.printStackTrace();
        }
        finally
        {
            if (pstmt != null)
            {
                pstmt = null;
            }
            if (conn != null)
            {
                conn = null;
            }
        }
    }

    /**
     * 根据维护人员编号删除维护人员信息
     * @param _maintainerId　维护人员编号
     * @throws SQLException
     */
    public void delete(int maintainerId) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;

        try
        {
            String sql = "";
            sql = "DELETE FROM taw_inf_maintainer WHERE maintainer_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, maintainerId);

            pstmt.executeUpdate();
            conn.commit();
        }
        catch (Exception e)
        {
            conn.rollback();
            e.printStackTrace();
        }
        finally
        {
            if (conn != null)
            {
                conn = null;
            }
            if (pstmt != null)
            {
                pstmt = null;
            }
        }
    }

    /**
     * 获得维护人员信息列表
     * @param _deptId　部门编号
     * @return list 维护人员信息列表
     * @throws SQLException
     */
    public List getList(String condition, int offset, int length) throws
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
            sql = "select * from taw_inf_maintainer";
            sql = sql + condition;
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
                TawInfMaintainer tawInfMaintainer = new TawInfMaintainer();
                populate(tawInfMaintainer, rs);

                list.add(tawInfMaintainer);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
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
    public int getSize(String tableName, String condition) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int size = 0;

        try
        {
            String sql = "";
            sql = "select count(*) from " + tableName + " " + condition;
            System.out.println("sql===" + sql);
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();
            size = rs.getInt(1);
        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }
        finally
        {
            if (rs != null)
            {
                rs = null;
            }
            if (pstmt != null)
            {
                pstmt = null;
            }
            if (conn != null)
            {
                conn = null;
            }
        }
        return size;
    }

    /**
     * 获取维护人员信息对象
     * @param maintainerId
     * @return
     * @throws SQLException
     */
    public TawInfMaintainer getByMaintainerId(int maintainerId) throws
        SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        TawInfMaintainer tawInfMaintainer = new TawInfMaintainer();
        try
        {
            String sql = "";
            sql = "select * from taw_inf_maintainer where maintainer_id=?";

            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, maintainerId);
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                populate(tawInfMaintainer, rs);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (conn != null)
            {
                conn = null;
            }
            if (pstmt != null)
            {
                pstmt = null;
            }
            if (rs != null)
            {
                rs = null;
            }
        }
        return tawInfMaintainer;
    }

    /**
     * 根据维护人员ID修改维护人员资料
     * @param tawInfMaintainer
     * @throws SQLException
     */
    public void update(TawInfMaintainer tawInfMaintainer) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;

        try
        {
            String sql = "";
            sql =
                "UPDATE taw_inf_maintainer SET maintainer_name=?,maintainer_sex=?,dept_id=?,dep_name=?,tele=?,tele_mobile=?,tele_home=?,email=?,special=?" +
                " WHERE maintainer_id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tawInfMaintainer.getMaintainerName());
            pstmt.setString(2, tawInfMaintainer.getMaintainerSex());
            pstmt.setInt(3, tawInfMaintainer.getDeptId());
            pstmt.setString(4, tawInfMaintainer.getDeptName());
            pstmt.setString(5, tawInfMaintainer.getTele());
            pstmt.setString(6, tawInfMaintainer.getTeleMobile());
            pstmt.setString(7, tawInfMaintainer.getTeleHome());
            pstmt.setString(8, tawInfMaintainer.getEmail());
            pstmt.setString(9, tawInfMaintainer.getSpecial());
            pstmt.setInt(10, tawInfMaintainer.getMaintainerId());

            pstmt.executeUpdate();
            conn.commit();
        }
        catch (Exception e)
        {
            conn.rollback();
            e.printStackTrace();
        }
        finally
        {
            if (conn != null)
            {
                conn = null;
            }
            if (pstmt != null)
            {
                pstmt = null;
            }
        }
    }
}