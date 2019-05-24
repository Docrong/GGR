/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   ��ѩ��  2003-10-10         Created
 */

package com.boco.eoms.security.util;

/**
 * <p>
 * Title: Authentication and Authorization System via LDAP
 * </p>
 * <p>
 * Description: Authentication and Authorization System via LDAP
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: BOCO
 * </p>
 * 
 * @author Wang Zhuo Wei
 * @version 1.0
 */

import java.util.*;
import java.sql.*;

import javax.naming.NamingException;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import com.boco.eoms.security.service.dao.ldap.factory.LdapOperation;

// import com.boco.eoms.jbzl.dao.*;
// import com.boco.eoms.jbzl.model.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.commons.system.dept.dao.TawSystemDeptDao;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dept.service.impl.TawSystemDeptManagerImpl;
import com.boco.eoms.commons.system.user.dao.TawSystemUserDao;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;

public class TransferUtil extends com.boco.eoms.common.dao.DAO {

	TawSystemDeptDao tawSystemDeptDao;
	TawSystemUserDao tawSystemUserDao;

	public TransferUtil() {
	}

	private void Transfer() {
		// TawDeptDAO tawDeptDAO = new TawDeptDAO();
		// TawRmUserDAO tawRmUserDAO = new TawRmUserDAO();
		ArrayList list = null;
		try {
			list = (ArrayList) userList();
			for (int i = 0; i < list.size(); i++) {
				TawSystemUser tawRmUser = new TawSystemUser();
				TawSystemUser tawRmUser1 = new TawSystemUser();
				tawRmUser = (TawSystemUser) list.get(i);
				tawRmUser1 = tawSystemUserDao.getUserByuserid(tawRmUser.getUserid());
				if (tawRmUser1 == null)
					tawSystemUserDao.saveTawSystemUser(tawRmUser);
			}
			System.out.println("��Ա��ݵ�����ɣ�");
			list = (ArrayList) deptList();
			for (int i = 0; i < list.size(); i++) {
				TawSystemDept dept = new TawSystemDept();
				TawSystemDept dept1 = new TawSystemDept();
				dept = (TawSystemDept) list.get(i);
				dept1 = tawSystemDeptDao.getDeptinfobydeptid(dept.getDeptId(),"0");
				if (dept1 == null)
					tawSystemDeptDao.saveTawSystemDept(dept);
			}

			System.out.println("������ݵ�����ɣ�");

		} catch (Exception e) {

		}
	}

	public static void main(String[] args) {
		TransferUtil util = new TransferUtil();
		util.Transfer();
	}

	public List userList() throws SQLException {
		ArrayList list = new ArrayList();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TawSystemUser tawRmUser = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT *  FROM taw_system_user";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				tawRmUser = new TawSystemUser();
				populate(tawRmUser, rs);
				list.add(tawRmUser);
				tawRmUser = null;
			}
			close(rs);
			close(pstmt);
		} catch (SQLException e) {
			close(rs);
			close(pstmt);
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return list;
	}

	public List deptList() throws SQLException {
		ArrayList list = new ArrayList();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TawSystemDept dept = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT *  FROM taw_system_dept";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dept = new TawSystemDept();
				populate(dept, rs);
				list.add(dept);
				dept = null;
			}
			close(rs);
			close(pstmt);
		} catch (SQLException e) {
			close(rs);
			close(pstmt);
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return list;
	}



	public TawSystemUserDao getTawSystemUserDao() {
		return tawSystemUserDao;
	}

	public void setTawSystemUserDao(TawSystemUserDao tawSystemUserDao) {
		this.tawSystemUserDao = tawSystemUserDao;
	}

	public TawSystemDeptDao getTawSystemDeptDao() {
		return tawSystemDeptDao;
	}

	public void setTawSystemDeptDao(TawSystemDeptDao tawSystemDeptDao) {
		this.tawSystemDeptDao = tawSystemDeptDao;
	}

}