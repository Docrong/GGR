/**
 * @see
 * <p>�������������DAO��</p>
 * @author ��ǿ
 * @version 1.0
 */
package com.boco.eoms.duty.dao;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import com.boco.eoms.common.util.CacheManager;
import com.boco.eoms.common.dao.*;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.duty.model.TawApparatusroom;

import org.apache.struts.util.LabelValueBean;

public class TawApparatusroomDAO
    extends DAO {
  /**
   * @see   ���췽��
   * @see  �����ݿ��l��
   * @param ds  DataSource ���Դ����Struts������ṩ��
   */
  public TawApparatusroomDAO(com.boco.eoms.db.util.ConnectionPool ds) {
    super(ds);
  }

  public TawApparatusroomDAO() {
    super();
  }

  /**
   * @see ��ݲ�����taw_apparatusroom���в���һ���¼��
   * @param tawApparatusroom  TawApparatusroom  �����Ϣ��Model����
   * @throws SQLException
   */
  public void insert(TawApparatusroom tawApparatusroom) throws SQLException {
    String sql;
    sql = "INSERT INTO taw_apparatusroom (room_name, manager, temp_manager, " +
        "dept_id, endtime, phone, mobile, fax, address, notes) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, tawApparatusroom.getRoomName());

      String man = tawApparatusroom.getManager();
      if (man.equals("")) {
        pstmt.setNull(2, Types.VARCHAR);
      }
      else {
        pstmt.setString(2, man);
      }

      String tep = tawApparatusroom.getTempManager();
      if (tep.equals("")) {
        pstmt.setNull(3, Types.VARCHAR);
      }
      else {
        pstmt.setString(3, tep);
      }

      int iTp = tawApparatusroom.getDeptId();
      if ( (iTp == 0) || (iTp == StaticVariable.defnull)) {
        pstmt.setNull(4, Types.INTEGER);
      }
      else {
        pstmt.setInt(4, iTp);
      }

      pstmt.setString(5, tawApparatusroom.getEndtime());
      pstmt.setString(6, tawApparatusroom.getPhone());
      pstmt.setString(7, tawApparatusroom.getMobile());
      pstmt.setString(8, tawApparatusroom.getFax());
      pstmt.setString(9, tawApparatusroom.getAddress());
      pstmt.setString(10, tawApparatusroom.getNotes());

      pstmt.executeUpdate();
      pstmt.close();
      conn.commit();
    }
    catch (SQLException sqle) {
      close(rs);
      close(pstmt);
      rollback(conn);
      sqle.printStackTrace();
      throw sqle;
    }
    finally {
      close(conn);
    }
  }

  /**
   * @see ��ݲ������taw_apparatusroom��Ķ�Ӧ��¼��
   * @param tawApparatusroom  TawApparatusroom  �����Ϣ��Model����
   * @throws SQLException
   */
  public void update(TawApparatusroom tawApparatusroom) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      String sql = "UPDATE taw_apparatusroom SET " +
          "room_name=?, manager=?, temp_manager=?, dept_id=?, endtime=?, " +
          "phone=?, mobile=?, fax=?, address=?, deleted=?, notes=? WHERE id=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, tawApparatusroom.getRoomName());
      pstmt.setString(2, tawApparatusroom.getManager());
      pstmt.setString(3, tawApparatusroom.getTempManager());
      pstmt.setInt(4, tawApparatusroom.getDeptId());
      pstmt.setString(5, tawApparatusroom.getEndtime());
      pstmt.setString(6, tawApparatusroom.getPhone());
      pstmt.setString(7, tawApparatusroom.getMobile());
      pstmt.setString(8, tawApparatusroom.getFax());
      pstmt.setString(9, tawApparatusroom.getAddress());
      pstmt.setInt(10, tawApparatusroom.getDeleted());
      pstmt.setString(11, tawApparatusroom.getNotes());
      pstmt.setInt(12, tawApparatusroom.getId());
      pstmt.executeUpdate();
      close(pstmt);
      conn.commit();
    }
    catch (SQLException e) {
      close(pstmt);
      rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
  }

  /**
   * @see ��ݻ�ID����taw_apparatusroom����deleteһ���¼��
   * @param id int ��ID
   * @throws SQLException
   */
  public void deleteForever(int id) throws SQLException {

    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();

      String sql = "delete from taw_apparatusroom where id=?";

      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);

      pstmt.executeUpdate();
      pstmt.close();
      conn.commit();
    }
    catch (SQLException e) {
      pstmt.close();
      rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
  }

  /**
   * @see ��ݻ�ID����taw_apparatusroom����ɾ��һ���¼����ɾ��deleted�ֶ���Ϊ"1"����
   * @param id int ��ID
   * @throws SQLException
   */
  public void delete(int id, int del, int undel, int domType, int privType) throws
      SQLException {

    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();

      String sql = "update taw_apparatusroom set deleted=? where id=?";

      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, del);
      pstmt.setInt(2, id);

      pstmt.executeUpdate();
      pstmt.close();
      conn.commit();
    }
    catch (SQLException e) {
      pstmt.close();
      rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
  }

  /**
   * @see ��ݻ�ID���������Ϣ��
   * @see �÷����ǵ������ֻ��taw_apparatusroom�����
   * @param id  int  ��ID
   * @return Model(TawApparatusroom)����ֵ��
   * @throws SQLException
   */
  public TawApparatusroom retrieve(int id) throws SQLException {
    TawApparatusroom tawAroom = null;
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql = "SELECT * FROM taw_apparatusroom WHERE id=? and deleted=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      pstmt.setInt(2, StaticVariable.UNDELETED);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        tawAroom = new TawApparatusroom();
        this.populate(tawAroom, rs);
      }
      close(rs);
      close(pstmt);
    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
    //  rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return tawAroom;
  }

  public String getAppName(int id) throws SQLException {
    String name = "";
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql = "SELECT roomname  FROM taw_system_cptroom WHERE id=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        name = StaticMethod.dbNull2String(StaticMethod.strFromDBToPage(rs.getString(1)));
      }
      close(rs);
      close(pstmt);
    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return name;
  }

  /**
   * @see ��ݻ���ơ�ID��ɾ���ǣ��������Ϣ��
   * @see �÷����ǵ������ֻ��taw_apparatusroom�����
   * @param id  int  ��ID
   * @param name  String  �����
   * @param deleted  int  ɾ����
   * @return Model(TawApparatusroom)����ֵ��
   * @throws SQLException
   */
  public TawApparatusroom retrieve(int id, String name, int deleted) throws
      SQLException {
    TawApparatusroom tawAroom = null;
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql =
          "SELECT id,room_name,manager,temp_manager,dept_id,endtime," +
          "phone,mobile,fax,address,notes  " +
          "FROM taw_apparatusroom WHERE room_name=? AND deleted =? AND id <> ?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, name);
      pstmt.setInt(2, deleted);
      pstmt.setInt(3, id);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        tawAroom = new TawApparatusroom();
        this.populate(tawAroom, rs);
      }
      close(rs);
      close(pstmt);
    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
   //   rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return tawAroom;
  }

  public TawApparatusroom retrieve(String name, int deleted) throws
      SQLException {
    TawApparatusroom tawApparatusroom = null;
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql =
          "SELECT * FROM taw_apparatusroom WHERE room_name=? AND deleted =?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, name);
      pstmt.setInt(2, deleted);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        tawApparatusroom = new TawApparatusroom();
      }
      close(rs);
      close(pstmt);
    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
     // rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return tawApparatusroom;
  }

  /**
   * @see ��ݻ�ID��ɾ���ǣ������Ӧ�Ļ���Ϣ��
   * @see �÷����Ƕ���ѯ�����ص�ֵ�а����ƣ�������ƣ�����Ա�������Ϣ��
   * @param id  int  ��ID
   * @param deleted  int  ɾ����
   * @return Model(TawApparatusroom)����ֵ��
   * @throws SQLException
   */
  public TawApparatusroom retrieve(int id, int deleted) throws SQLException {
    TawApparatusroom tawAroom = null;
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      StringBuffer sql = new StringBuffer();
      sql.append("SELECT a.room_name, a.dept_id, a.endtime, ");
      sql.append("a.phone, a.mobile, a.fax, a.address,a.notes,");
      sql.append("b.dept_name , a.manager, a.temp_manager ,a.id ");
      sql.append(" FROM taw_apparatusroom a, taw_dept b  ");
      sql.append("WHERE a.dept_id=b.dept_id   ");
      sql.append("AND a.deleted=? AND b.deleted=?  AND a.id=?");

      pstmt = conn.prepareStatement(sql.toString());
      pstmt.setInt(1, deleted);
      pstmt.setInt(2, deleted);
      pstmt.setInt(3, id);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        tawAroom = new TawApparatusroom();
        this.populate(tawAroom, rs);
      }
      close(rs);
      close(pstmt);
    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
    //  rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return tawAroom;
  }

  public String getId(String name) throws SQLException {
    StringBuffer id = new StringBuffer();
    String ret = null;
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql =
          " SELECT id  FROM taw_apparatusroom " +
          " WHERE room_name LIKE \'%" + name + "%\'";
      pstmt = conn.prepareStatement(sql);

      rs = pstmt.executeQuery();
      while (rs.next()) {
        id.append(rs.getInt(1) + ",");
      }
      close(rs);
      close(pstmt);

      ret = id.toString();
      if (ret.endsWith(",")) {
        ret = ret.substring(0, ret.length() - 1);
      }
    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
     // rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return ret;
  }

  /**
   * @see ���ɾ���ǣ������Ӧ�Ļ���Ϣ�б?�ϣ�
   * @see �÷����Ƕ���ѯ�����ص�ֵ�а����ƣ�������ƣ�����Ա�������Ϣ��
   * @param deleted  int  ɾ����
   * @return List���ϣ���Ԫ����TawApparatusroom����ֵ��
   * @throws SQLException
   */
  public List list(int deleted) throws SQLException {
    ArrayList list = new ArrayList();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      StringBuffer sql = new StringBuffer();
      sql.append("SELECT a.room_name, a.dept_id, a.endtime, ");
      sql.append(" a.phone, a.mobile, a.fax, a.address,a.notes,");
      sql.append(" b.dept_name, c.user_name as manager, ");
      sql.append(" d.user_name as temp_manager,a.id ");
      sql.append(" FROM taw_apparatusroom a, taw_dept b, taw_rm_user c ,");
      sql.append(" taw_rm_user d ");
      sql.append(" WHERE ");
      sql.append(" a.dept_id=b.dept_id AND a.manager=c.user_id AND ");
      sql.append(" a.temp_manager=d.user_id AND a.deleted=? ");
      sql.append(" AND b.deleted=? AND c.deleted=? AND d.deleted=? ");
      sql.append(" ORDER BY a.id desc ");
      pstmt = conn.prepareStatement(sql.toString());
      pstmt.setInt(1, deleted);
      pstmt.setInt(2, deleted);
      pstmt.setInt(3, deleted);
      pstmt.setInt(4, deleted);

      rs = pstmt.executeQuery();
      while (rs.next()) {
        TawApparatusroom tawAroom = new TawApparatusroom();
        this.populate(tawAroom, rs);
        list.add(tawAroom);
      }
      close(rs);
      close(pstmt);
    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
    //  rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return list;
  }

  /**
   * @see ���ɾ���Ǻͷ�ҳ��ǣ������Ӧ�Ļ���Ϣ�б?�ϣ�
   * @see �÷����Ƕ���ѯ�����ص�ֵ�а����ƣ�������ƣ�����Ա�������Ϣ��
   * @see �÷������ڷ�ҳ��ʾ��
   * @param deleted  int  ɾ����
   * @param limit  int  ÿҳ��ʾ��¼����
   * @param offset int  �Ӹü�¼����ʾ����
   * @return List���ϣ���Ԫ����TawApparatusroom����ֵ��
   * @throws SQLException
   */
  public List list(int offset, int limit, int deleted) throws SQLException {
    ArrayList list = new ArrayList();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      StringBuffer sql = new StringBuffer();
      sql.append("SELECT a.room_name, a.dept_id, a.endtime, ");
      sql.append(" a.phone, a.mobile, a.fax, a.address,a.notes,b.dept_name, ");
      sql.append(" c.user_name as manager,d.user_name as temp_manager,a.id");
      sql.append(" FROM taw_apparatusroom a, taw_dept b, taw_rm_user c ,");
      sql.append(" taw_rm_user d ");
      sql.append(" WHERE a.dept_id=b.dept_id AND a.manager=c.user_id AND ");
      sql.append(" a.temp_manager=d.user_id AND a.deleted=? ");
      sql.append(" AND b.deleted=? AND c.deleted=? AND d.deleted=? ");
      sql.append(" ORDER BY a.id desc ");
      pstmt = conn.prepareStatement(sql.toString());
      pstmt.setInt(1, deleted);
      pstmt.setInt(2, deleted);
      pstmt.setInt(3, deleted);
      pstmt.setInt(4, deleted);
      rs = pstmt.executeQuery();

      int i = 0;
      while (i < offset) {
        rs.next();
        i++;
      }

      int recCount = 0;
      while ( (recCount++ < limit) && rs.next()) {
        TawApparatusroom tawAroom = new TawApparatusroom();
        this.populate(tawAroom, rs);
        list.add(tawAroom);
      }
      close(rs);
      close(pstmt);
    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
     // rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return list;
  }

  /**
   * @see ͨ���������,��ѯ��,�����Ӧ�Ļ���Ϣ�б?�ϣ�
   * @see �÷����Ƕ���ѯ�����ص�ֵ�а����ƣ�������ƣ�����Ա�������Ϣ��
   * @see �÷������ڻ��ѯ��
   * @param deleted  int  ɾ����
   * @param limit  int  ÿҳ��ʾ��¼����
   * @param offset int  �Ӹü�¼����ʾ����
   * @param condition String  ��ѯ���
   * @return List���ϣ���Ԫ����TawApparatusroom����ֵ��
   * @throws SQLException
   */
  public List selectByCondition(int offset, int limit, String condition,
                                int deleted) throws SQLException {
    ArrayList list = new ArrayList();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql =
          "SELECT a.* FROM taw_apparatusroom a " + condition +
          " ORDER BY a.id desc";

      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      int i = 0;
      while (i < offset) {
        rs.next();
        i++;
      }

      int recCount = 0;
      while ( (recCount++ < limit) && rs.next()) {
        TawApparatusroom tawAroom = new TawApparatusroom();
        this.populate(tawAroom, rs);
        list.add(tawAroom);
      }
      close(rs);
      close(pstmt);
    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
    //  rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return list;
  }

  /**
   * @see ͨ���û��Ĳ��ű�ŵõ��ò��ŵĻ��б�
   * @author ����
   * @param deleted  int  ɾ����
   * @param  deptId  int  ��ѯ���
   * @return List���ϣ���Ԫ����TawApparatusroom���š�
   * @throws SQLException
   */

  public List getRoomNameSelect(int deptId) throws SQLException {
    ArrayList list = new ArrayList();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql = "SELECT room_name, id " +
          "FROM taw_apparatusroom where dept_id=? and deleted=? order by room_name";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, deptId);
      pstmt.setInt(2, StaticVariable.UNDELETED);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        list.add(
            new org.apache.struts.util.LabelValueBean(
            StaticMethod.dbNull2String(rs.getString(1)),
            StaticMethod.dbNull2String(String.valueOf(rs.getInt(2)))
            ));
      }
      close(rs);
      close(pstmt);
    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
   //   rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return list;
  }

  public List getRoomNameSelect(String depIds, int undel) throws SQLException {
    ArrayList list = new ArrayList();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql =
          "SELECT room_name, id FROM taw_apparatusroom " +
          " where dept_id in (" + depIds + ") " +
          " and deleted=" + undel + " order by room_name ";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        list.add(
            new org.apache.struts.util.LabelValueBean(
            StaticMethod.dbNull2String(rs.getString(1)),
            StaticMethod.dbNull2String(String.valueOf(rs.getInt(2)))
            ));
      }
      close(rs);
      close(pstmt);
    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
     // rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return list;
  }

  public Vector getRoomNameSelectV(String depIds, int undel) throws
      SQLException {
    Vector list = new Vector();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      //Modified by Lihong Qi on 2004/06/16:
      //���deptIds == "" ѡ��ȫ���Ļ��б�
      String sql = "";
      if (depIds.equals("")) {
        sql = "SELECT room_name, id FROM taw_apparatusroom " +
            " where  deleted=" + undel + " order by room_name ";

      }
      else {
        sql = "SELECT room_name, id FROM taw_apparatusroom " +
            " where dept_id in (" + depIds + ") " +
            " and deleted=" + undel + " order by room_name ";
      }

      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      list.add(new LabelValueBean("- ѡ�� -", ""));
      while (rs.next()) {
        list.add(
            new org.apache.struts.util.LabelValueBean(
            StaticMethod.dbNull2String(rs.getString(1)),
            StaticMethod.dbNull2String(String.valueOf(rs.getInt(2)))
            ));
      }
      close(rs);
      close(pstmt);
    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
     // rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return list;
  }

  public String getRoomNameSelString(String depIds, int undel) throws
      SQLException {
    String list = "";
    String temp = "";
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      conn = ds.getConnection();
      String sql = "SELECT room_name, id " +
          " FROM taw_apparatusroom where dept_id in (" + depIds +
          ") and deleted=" + undel + " order by room_name ";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while (rs.next()) {

        temp += "'" + StaticMethod.dbNull2String(rs.getString(1)) + "','" +
            StaticMethod.dbNull2String(rs.getString(2)) + "',";
      }
      close(rs);
      close(pstmt);

      if (temp.endsWith(",")) {
        temp = temp.substring(0, temp.length() - 1);
        list = "app=new Array(" + temp + ");";
      }

    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
   //   rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return list;
  }

  /**
   * @see �õ����б�
   * @author ����
   * @param deleted  int  ɾ����
   * @param  deptId  int  ��ѯ���
   * @return List���ϣ���Ԫ����TawApparatusroom���š�
   * @throws SQLException
   */

  public List getRoomNameSelect() throws SQLException {
    ArrayList list = new ArrayList();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql =
          "SELECT room_name,id FROM taw_apparatusroom " +
          " where deleted=? order by room_name ";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, StaticVariable.UNDELETED);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        list.add(
            new org.apache.struts.util.LabelValueBean(
            StaticMethod.dbNull2String(rs.getString(1)),
            StaticMethod.dbNull2String(String.valueOf(rs.getInt(2)))
            ));
      }
      close(rs);
      close(pstmt);
    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
    //  rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return list;
  }

  public String getRoomNameSelString() throws SQLException {
    String list = "";
    String temp = "";
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      conn = ds.getConnection();
      String sql =
          "SELECT room_name, id FROM taw_apparatusroom " +
          " where deleted=? order by room_name ";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, StaticVariable.UNDELETED);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        temp += "'" + StaticMethod.dbNull2String(rs.getString(1)) + "','" +
            StaticMethod.dbNull2String(rs.getString(2)) + "',";
      }
      close(rs);
      close(pstmt);

      if (temp.endsWith(",")) {
        temp = temp.substring(0, temp.length() - 1);
        list = "app=new Array(" + temp + ");";
      }

    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
   //   rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return list;
  }

  /**
   * ��ݲ��ű�Ż�ȡ�������󼯺�
   * @param deptId int ���ű��
   * @throws SQLException �쳣
   * @return List ����󼯺�
   */
  public List listRoom(int deptId) throws SQLException {
    ArrayList list = new ArrayList();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql =
          "SELECT * FROM taw_apparatusroom where dept_id =? and deleted =?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, deptId);
      pstmt.setInt(2, StaticVariable.UNDELETED);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        TawApparatusroom tawApparatusroom = new TawApparatusroom();
        populate(tawApparatusroom, rs);
        list.add(tawApparatusroom);
      }
      close(rs);
      close(pstmt);
    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return list;
  }

}
