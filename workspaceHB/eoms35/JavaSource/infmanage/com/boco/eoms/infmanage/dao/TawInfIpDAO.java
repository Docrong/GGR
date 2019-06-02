package com.boco.eoms.infmanage.dao;

import java.sql.*;
import java.util.*;

import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.common.util.*;
import com.boco.eoms.infmanage.model.*;

public class TawInfIpDAO extends DAO
{
    public Connection con = null;

    public TawInfIpDAO(com.boco.eoms.db.util.ConnectionPool ds)
    {
        super(ds);
    }

    /**
     * 在用户IP地址表中插入记录
     * @param tawInfIp 用户IP地址信息对象
     * @throws SQLException
     */
    public void insert(TawInfIp tawInfIp) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "";
        sql = "INSERT INTO taw_inf_ip (user_id,user_name,dept_id,dept_name,user_address,user_tel,user_type,dev_port,dev_id,user_logic,logicport,remark)"
            + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try
        {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, tawInfIp.getUserId());
            pstmt.setString(2, tawInfIp.getUserName());
            pstmt.setInt(3, tawInfIp.getDeptId());
            pstmt.setString(4, tawInfIp.getDeptName());
            pstmt.setString(5, tawInfIp.getUserAddress());
            pstmt.setString(6, tawInfIp.getUserTel());
            pstmt.setString(7, tawInfIp.getUserType());
            pstmt.setString(8, tawInfIp.getDevPort());
            pstmt.setString(9, tawInfIp.getDevId());
            pstmt.setString(10, tawInfIp.getUserLogic());
            pstmt.setString(11, tawInfIp.getLogicPort());
            pstmt.setString(12, tawInfIp.getRemark());
            pstmt.executeUpdate();
            close(pstmt);
            conn.setAutoCommit(false);
        }
        catch (Exception sqle)
        {
          //  conn.rollback();
           // sqle.printStackTrace();
        }
        finally
        {
           close(conn);
        }
    }

    /**
     * 根据记录ID删除用户IP地址资料信息
     * @param id 记录ID
     * @throws SQLException
     */
    public void delete(int id) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;

        try
        {
            String sql = "";
            sql = "DELETE FROM taw_inf_ip WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            conn.commit();
        }
        catch (Exception e)
        {
          //  conn.rollback();
         //   e.printStackTrace();
        }
        finally
        {
            close(conn);
        }
    }

    /**
     * 获得用户IP地址资料信息列表
     * @param condition　查询条件
     * @param offset　
     * @param length
     * @return　信息列表
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
            sql = "select * from taw_inf_ip";
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
                TawInfIp tawInfIp = new TawInfIp();
                populate(tawInfIp, rs);
                tawInfIp.setLogicPort(StaticMethod.dbNull2String(rs.getString(11)));
                list.add(tawInfIp);
                System.out.println(tawInfIp.getLogicPort());
            }
        }
        catch (SQLException e)
        {
         //   e.printStackTrace();
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
            if(rs.next())
            {size = rs.getInt(1);}
        }
        catch (SQLException se)
        {
          //  se.printStackTrace();
        }
        finally
        {
            close(conn);
        }
        return size;
    }

    /**
     * 根据记录id获取用户IP地址资料信息对象
     * @param id　记录ID
     * @return
     * @throws SQLException
     */
    public TawInfIp getById(int id) throws
        SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        TawInfIp tawInfIp = new TawInfIp();
        try
        {
            String sql = "";
            sql = "select * from taw_inf_ip where id=?";

            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                populate(tawInfIp, rs);
                tawInfIp.setLogicPort(StaticMethod.dbNull2String(rs.getString(11)));
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
        return tawInfIp;
    }

    /**
     * 根据记录ID修改用户IP地址资料信息
     * @param tawInfIp 用户IP地址资料信息对象
     * @throws SQLException
     */
    public void update(TawInfIp tawInfIp) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;

        try
        {
            String sql = "";
            sql =
                "UPDATE taw_inf_ip SET user_id=?,user_name=?,dept_name=?,user_address=?,user_tel=?,user_type=?,dev_port=?,dev_id=?,user_logic=?,logicport=?,remark=?" +
                " WHERE id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tawInfIp.getUserId());
            pstmt.setString(2, tawInfIp.getUserName());
            pstmt.setString(3, tawInfIp.getDeptName());
            pstmt.setString(4, tawInfIp.getUserAddress());
            pstmt.setString(5, tawInfIp.getUserTel());
            pstmt.setString(6, tawInfIp.getUserType());
            pstmt.setString(7, tawInfIp.getDevPort());
            pstmt.setString(8, tawInfIp.getDevId());
            pstmt.setString(9, tawInfIp.getUserLogic());
            pstmt.setString(10, tawInfIp.getLogicPort());
            pstmt.setString(11, tawInfIp.getRemark());
            pstmt.setInt(12, tawInfIp.getId());

            pstmt.executeUpdate();
            conn.commit();
        }
        catch (Exception e)
        {
          //  conn.rollback();
         //   e.printStackTrace();
        }
        finally
        {
            close(conn);
        }
    }
}
