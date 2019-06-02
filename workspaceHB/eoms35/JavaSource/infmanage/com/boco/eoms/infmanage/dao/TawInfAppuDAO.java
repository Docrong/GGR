package com.boco.eoms.infmanage.dao;

import java.sql.*;
import java.util.*;
//import com.boco.eoms.common.controller.SaveSessionBeanForm;
import com.boco.eoms.common.dao.*;
import com.boco.eoms.infmanage.model.*;
import com.boco.eoms.common.util.StaticMethod;

public class TawInfAppuDAO
    extends DAO
{
    public TawInfAppuDAO(com.boco.eoms.db.util.ConnectionPool ds)
    {
        super(ds);
    }

    /**
     * �������������
     * @param sortId
     * @return
     * @throws SQLException
     */
    public String getSortName(int sortId) throws SQLException
    {
        String sortName = "";
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "";
        sql = "select * from taw_inf_sort where inf_sort_id=" + sortId;

        try
        {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next())
            {sortName = StaticMethod.dbNull2String(rs.getString("inf_sort_name"));}
        }
        catch (SQLException sqle)
        {
         //   conn.rollback();
         //   sqle.printStackTrace();
        }
        finally
        {
          close(conn);
        }
        return sortName;
    }
    /**
     * �������������
     * @param sortId
     * @return
     * @throws SQLException
     */
    public String getName(int id) throws SQLException
    {
        String sortName = "";
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "";
        sql = "select * from taw_inf_info where id=" + id;

        try
        {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
              sortName = StaticMethod.dbNull2String(rs.getString("inf_info_name"));
            }
        }
        catch (SQLException sqle)
        {
            //conn.rollback();
           // sqle.printStackTrace();
        }
        finally
        {
          close(conn);
        }
        return sortName;
    }
    /**
     * �������������
     * @param sortId
     * @return
     * @throws SQLException
     */
    public int getUser_id(String id) throws SQLException
    {
        int sortName = 0;
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "";
        sql = "select * from taw_system_user where userid='" + id+"'";

        try
        {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next())
            {sortName = rs.getInt("id");}
        }
        catch (SQLException sqle)
        {
            //conn.rollback();
          //  sqle.printStackTrace();
        }
        finally
        {
          close(conn);
        }
        return sortName;
    }

    /**
     * ��������Ϣ���в����¼
     * @param tawInfInfo
     * @throws SQLException
     */
    public void insert(TawInfInfo tawInfInfo) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "";
        sql = "INSERT INTO taw_inf_info (inf_info_id,inf_info_name,inf_sort_id,dept_id,inf_up_id,inf_upfile_time)"
            + " VALUES (?,?,?,?,?,?)";

        try
        {
            pstmt = conn.prepareStatement(sql);
            System.out.println(sql);

            pstmt.setString(1, tawInfInfo.getInfInfoId());
            pstmt.setString(2, tawInfInfo.getInfInfoName());
            pstmt.setInt(3, tawInfInfo.getInfSortId());
            pstmt.setInt(4, tawInfInfo.getDeptId());
            pstmt.setString(5, tawInfInfo.getInfUpId());
            pstmt.setString(6, tawInfInfo.getInfUpTime());
            pstmt.executeUpdate();
            close(pstmt);
            conn.setAutoCommit(false);
        }
        catch (Exception sqle)
        {
          //  conn.rollback();
         //   sqle.printStackTrace();
        }
        finally
        {
            close(conn);
        }
    }
    /**
     * ����֪ͨ������
     * @param tawInfInfo
     * @throws SQLException
     */
    public void insertMsg(int id,int dept_id,String now) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String user_id="";
        String sql = "";
        String infoName=getName(id);
        String strComments =  " ���ĵ����� "+infoName+" �Ѿ����£��뼰ʱ�鿴��";
        sql="select *  from taw_inf_take where info_id="+id;
        try{

        pstmt = conn.prepareStatement(sql);
        rs=pstmt.executeQuery();
        if(rs.next()){
          user_id=rs.getString(3);
          int ID=getUser_id(user_id);
          //�������
          sql = "INSERT INTO taw_wrf_monitor (worksheet_id, worksheet_class, region_id, filler, ";
          sql = sql +
              "fill_time, to_target, target_class, hie_time_limit, hie_class, hie_style, ";
          sql = sql + "hie_arrive, hie_grade, hie_count, hie_comment, hie_close, hie_interval, hie_amount)";
          sql = sql +
              " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

          System.out.println(sql);
          pstmt = conn.prepareStatement(sql);
          pstmt.setString(1, "infomanager " + id);
          pstmt.setInt(2, 1);
          pstmt.setInt(3, dept_id);
          pstmt.setString(4, user_id);
          pstmt.setString(5, now);
          pstmt.setInt(6, ID);
          pstmt.setInt(7, 0);
          pstmt.setInt(8, 1);
          pstmt.setInt(9, 3);
          pstmt.setShort(10, (short) 0);
          pstmt.setShort(11, (short) 0);
          pstmt.setShort(12, (short) 0);
          pstmt.setInt(13, 0);
          pstmt.setString(14, StaticMethod.dbNStrRev(strComments));
          pstmt.setShort(15, (short) 0);
          pstmt.setShort(16, (short) 1);
          pstmt.setInt(17, (short) 1);

          pstmt.executeUpdate();
          close(pstmt);
          conn.setAutoCommit(false);
        }
        }
        catch (Exception sqle)
        {
           // conn.rollback();
           // sqle.printStackTrace();
        }
        finally
        {
            close(conn);
        }
    }

    /**
     * ��ݼ�¼IDɾ��������Ϣ
     * @param id ��¼ID
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
            sql = "DELETE FROM taw_inf_info WHERE id=?";
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
     * ɾ���Ѷ���������Ϣ
     * @param id ��¼ID
     * @throws SQLException
     */
    public void deletetaken(String id,String user) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;

        try
        {
            String sql = "";
            sql = "DELETE FROM taw_inf_take WHERE info_id in ("+id+") and taker_id='"+user+"'";
            pstmt = conn.prepareStatement(sql);
            //pstmt.setInt(1, id);

            pstmt.execute();
            conn.commit();
        }
        catch (Exception e)
        {
         //   conn.rollback();
         //   e.printStackTrace();
        }
        finally
        {
            close(conn);
        }
    }

    /**
     * ���������Ϣ�б�
     * @param condition����ѯ���
     * @param offset��
     * @param length
     * @return����Ϣ�б�
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
            sql = "select a.*,b.inf_sort_name,c.username,d.deptname from taw_inf_info a,taw_inf_sort b,taw_system_user c,taw_system_dept d";
            sql = sql + condition;
            pstmt = conn.prepareStatement(sql);
            System.out.println(sql);
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
                TawInfInfo tawInfInfo = new TawInfInfo();
                //populate(tawInfInfo, rs);
                tawInfInfo.setInfSortName(StaticMethod.dbNull2String(rs.getString("inf_sort_name")));
                tawInfInfo.setInfInfoId(StaticMethod.dbNull2String(rs.getString("inf_info_id")));
                tawInfInfo.setInfInfoName(StaticMethod.dbNull2String(rs.getString("inf_info_name")));
                tawInfInfo.setDeptName(StaticMethod.dbNull2String(rs.getString("deptname")));
                tawInfInfo.setInfUpName(StaticMethod.dbNull2String(rs.getString("username")));
                tawInfInfo.setInfUpTime(StaticMethod.dbNull2String(rs.getString("inf_upfile_time")));
                tawInfInfo.setId(rs.getInt("id"));
                tawInfInfo.setDeptId(rs.getInt("dept_id"));
                tawInfInfo.setInfSortId(rs.getInt("inf_sort_id"));

                list.add(tawInfInfo);
            }
        }
        catch (SQLException e)
        {
           // e.printStackTrace();
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
     * ��ȡ��ѯ���ļ�¼�ĳ���
     * @param tableName
     * @param condition
     * @return��size
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
     * ��ȡ������Ϣ
     * @param tableName
     * @param condition
     * @return��size
     * @throws SQLException
     */
    public int getIfTaken(String tableName, String condition) throws SQLException
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
           // se.printStackTrace();
        }
        finally
        {
            close(conn);
        }
        return size;
    }
    /**
     * ������Ϣ
     * @param tableName
     * @param condition
     * @return��size
     * @throws SQLException
     */
    public int Taken(int info_id,String taker_id) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int size = 0;

        try
        {
            String sql = "";
            sql = "INSERT INTO taw_inf_take (info_id,taker_id,deleted)"
            + " VALUES (?,?,?)";
            System.out.println("sql===" + sql);
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, info_id);
            pstmt.setString(2, taker_id);
            pstmt.setInt(3, 0);
            pstmt.execute();
            conn.commit();
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
     * ��ݼ�¼id��ȡ������Ϣ����
     * @param id����¼ID
     * @return
     * @throws SQLException
     */
    public TawInfInfo getById(int id) throws
        SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        TawInfInfo tawInfInfo = new TawInfInfo();
        try
        {
            String sql = "";
            sql = "select a.*,b.username,c.deptname from taw_inf_info a,taw_system_user b,taw_system_dept c,taw_inf_sort d where a.id=? and a.inf_up_id=b.userid and a.dept_id=c.deptid and a.inf_sort_id=d.inf_sort_id";

            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                //populate(tawInfInfo, rs);
                tawInfInfo.setInfInfoId(StaticMethod.dbNull2String(rs.getString("inf_info_id")));
                tawInfInfo.setInfInfoName(StaticMethod.dbNull2String(rs.getString("inf_info_name")));
                tawInfInfo.setDeptName(StaticMethod.dbNull2String(rs.getString("deptname")));
                tawInfInfo.setInfUpName(StaticMethod.dbNull2String(rs.getString("username")));
                tawInfInfo.setInfUpTime(StaticMethod.dbNull2String(rs.getString("inf_upfile_time")));
                tawInfInfo.setId(rs.getInt("id"));
                tawInfInfo.setDeptId(rs.getInt("dept_id"));
                tawInfInfo.setInfUpId(rs.getString("inf_up_id"));
                tawInfInfo.setInfSortId(rs.getInt("inf_sort_id"));
            }
        }
        catch (Exception e)
        {
           // e.printStackTrace();
        }
        finally
        {
            close(conn);
        }
        return tawInfInfo;
    }
    //��������
    public String getLocker(int id) throws
        SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String tawInfInfo = "";
        try
        {
            String sql = "";
            sql = "select editer from taw_inf_info where id="+id;

            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            //pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                //populate(tawInfInfo, rs);
                tawInfInfo=StaticMethod.dbNull2String(rs.getString(1));
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
        return tawInfInfo;
    }

    //�޸�ʱ��
    public void lock(String user_id,int id)
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            String sql = "";
            sql="update taw_inf_info set editer='"+user_id+"' where id="+id;
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            close(pstmt);
            conn.commit();
          }
          catch (SQLException e) {
           // close(pstmt);
            //rollback(conn);
          //  e.printStackTrace();
          }
          finally {
            close(conn);
          }

    }

    /**
     * ��ݼ�¼ID�޸�������Ϣ
     * @param tawInfIp ������Ϣ����
     * @throws SQLException
     */
    public void update(TawInfInfo tawInfInfo)
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;

        try
        {
            String sql = "";
            sql =
                "UPDATE taw_inf_info SET inf_info_id=?,inf_info_name=?,inf_sort_id=?,dept_id=?,inf_up_id=?,inf_upfile_time=?" +
                " WHERE id=?";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, tawInfInfo.getInfInfoId());
            pstmt.setString(2, tawInfInfo.getInfInfoName());
            pstmt.setInt(3, tawInfInfo.getInfSortId());
            pstmt.setInt(4, tawInfInfo.getDeptId());
            pstmt.setString(5, tawInfInfo.getInfUpId());
            pstmt.setString(6, tawInfInfo.getInfUpTime());
            pstmt.setInt(7, tawInfInfo.getId());
            pstmt.executeUpdate();
            close(pstmt);
            conn.commit();
         }
         catch (SQLException e) {
          // close(pstmt);
           //rollback(conn);
          // e.printStackTrace();
         }
         finally {
           close(conn);
         }

    }

    public int getId(String infUpTime) throws SQLException
    {
        int id = 0;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            String sql = "";
            sql =
                "select * from taw_inf_info where inf_upfile_time= '" +
                infUpTime + "'";

            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next())
            {id = rs.getInt("id");}

        }
        catch (Exception e)
        {
           // conn.rollback();
           // e.printStackTrace();
        }
        finally
        {
           close(conn);
        }
        return id;
    }

/*
    public String getName(String id) throws SQLException {
      String tawWeekly = null;
      com.boco.eoms.db.util.BocoConnection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
        conn = ds.getConnection();
        String SQL= "select user_name from taw_rm_user where user_id='"+id+"'";
        pstmt = conn.prepareStatement(SQL);
        rs = pstmt.executeQuery();
        if (rs.next()) {
          tawWeekly=rs.getString(1);
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
      return tawWeekly;
    }
*/
}
