/**
 * @author 赵川
 * @version 1.0
 * @see <p>功能描述：封装机房用户对应关系的业务逻辑类。</p>
 * <p>使用举例：首先实例化该类，然后通过实例化该类的对象，调用相应方法。</p>
 */

package com.boco.eoms.duty.bo;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.*;
import org.apache.struts.util.*;

import com.boco.eoms.common.bo.BO;
import com.boco.eoms.duty.model.*;
import com.boco.eoms.duty.controller.*;
import com.boco.eoms.duty.dao.*;
import com.boco.eoms.common.util.*;


public class TawUserRoomBO extends BO {
    public TawUserRoomBO(com.boco.eoms.db.util.ConnectionPool ds) {
        super(ds);
    }

    /**
     * @param roomId
     * @param user_true值班用户string,（以,分割user_id）
     * @param user_false非值班用户string,（以,分割user_id）
     * @see 根据机房ID和值班和当前非值班用户string,（以,分割user_id），配置机房用户
     */
    public void updateUserRoom(int roomId, String user_true, String user_false) {
        TawUserRoomDAO tawUserRoomDAO = null;
        Vector vector_true = null;
        String element = null;
        TawUserRoom tawUserRoom = null;
        Vector vector_false = null;

        try {
            tawUserRoomDAO = new TawUserRoomDAO(ds);
            //delete from tawUserRoom
            tawUserRoomDAO.delete(roomId);
            //add user_true
            vector_true = StaticMethod.getVector(user_true, ",");
            for (int i = 0; i < vector_true.size(); i++) {
                element = String.valueOf(vector_true.elementAt(i));
                if (!element.trim().equals("")) {
                    tawUserRoom = new TawUserRoom();
                    tawUserRoom.setRoomId(roomId);
                    tawUserRoom.setUserId(String.valueOf(vector_true.elementAt(i)));
                    tawUserRoom.setOrdercode(i + 1);
                    tawUserRoom.setFlag(0);
                    tawUserRoomDAO.insert(tawUserRoom);
                }
            }
            //add user_false
            vector_false = StaticMethod.getVector(user_false, ",");
            for (int i = 0; i < vector_false.size(); i++) {
                element = String.valueOf(vector_false.elementAt(i));
                if (!element.trim().equals("")) {
                    tawUserRoom = new TawUserRoom();
                    tawUserRoom.setRoomId(roomId);
                    tawUserRoom.setUserId(String.valueOf(vector_false.elementAt(i)));
                    tawUserRoom.setOrdercode(i + 1001);
                    tawUserRoom.setFlag(1);
                    tawUserRoomDAO.insert(tawUserRoom);
                }
            }
        } catch (SQLException e) {
        } finally {
            tawUserRoomDAO = null;
            vector_true = null;
            element = null;
            tawUserRoom = null;
            vector_false = null;
        }
    }

    /**
     * @param dept_id
     * @return 返回vector, 内容为user_id和user_name
     * @throws SQLException
     * @see 根据部门查询非删除的用户，如dept_id为-1，查询所有，否则查询该部门
     */
    public Vector getDeptUser(int dept_id) throws SQLException {
        Vector vectorDeptUser = new Vector();
        String sql = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            if (dept_id == -1) {
                sql = "SELECT userid,username FROM taw_system_user WHERE deleted=? order by username ";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, 0);
                rs = pstmt.executeQuery();
            } else {
                sql = "SELECT userid,username FROM taw_system_user WHERE deptid in " + dept_id + " and deleted=0 ";
                pstmt = conn.prepareStatement(sql);

                rs = pstmt.executeQuery();
            }
            while (rs.next()) {
                vectorDeptUser.add(/*StaticMethod.dbNull2String*/(rs.getString(1)));
                vectorDeptUser.add(/*StaticMethod.dbNull2String*/(rs.getString(2)));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            rs.close();
            pstmt.close();
            conn.rollback();
            e.printStackTrace();
        } finally {
            conn.close();
            //null
            sql = null;
        }
        return vectorDeptUser;
    }


    /**
     * @param dept_id
     * @return 返回vector, 内容为user_id和user_name
     * @throws SQLException
     * @see 根据部门查询非删除的用户，如dept_id为-1，查询所有，否则查询该部门 值班用
     */
    public Vector getDeptUser(String dept_id) throws SQLException {
        Vector vectorDeptUser = new Vector();
        String sql = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            if (dept_id.equals("-1")) {
                sql = "SELECT userid,username FROM taw_system_user WHERE deleted=? order by username ";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, 0);
                rs = pstmt.executeQuery();
            } else {
                sql = "SELECT userid,username FROM taw_system_user WHERE deptid in ('" + dept_id + "') and deleted=0 ";
                pstmt = conn.prepareStatement(sql);

                rs = pstmt.executeQuery();
            }
            while (rs.next()) {
                vectorDeptUser.add(/*StaticMethod.dbNull2String*/(rs.getString(1)));
                vectorDeptUser.add(/*StaticMethod.dbNull2String*/(rs.getString(2)));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            rs.close();
            pstmt.close();
            conn.rollback();
            e.printStackTrace();
        } finally {
            conn.close();
            //null
            sql = null;
        }
        return vectorDeptUser;
    }


}