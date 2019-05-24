/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-18         created
 */

package com.boco.eoms.security.service.factory;

import com.boco.eoms.security.config.SystemConfig;
import com.boco.eoms.security.service.dao.DeptDAO;
import com.boco.eoms.security.service.dao.PermissionDAO;
import com.boco.eoms.security.service.dao.RangeDAO;
import com.boco.eoms.security.service.dao.RegionDAO;
import com.boco.eoms.security.service.dao.RoleDAO;
import com.boco.eoms.security.service.dao.UserDAO;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: create the DAO instance of LDAP </p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class LdapDaoFactory
    implements AbstractAccountManagerDaoFactory {

  public static SystemConfig sc = SystemConfig.getInstance();
  private static LdapDaoFactory instance = null;

  public synchronized static LdapDaoFactory getInstance() {
    if (instance == null) {
      try {
        instance = (LdapDaoFactory) Class.forName(sc.daoFactory).newInstance();
        return instance;
      }
      catch (Exception ex) {
        ex.printStackTrace();
        return null;
      }
    }
    else {
      return instance;
    }
  }

  public String getUserDAOClass() {
    return sc.userDAOClass;
  }

  public String getDepartmentDAOClass() {
    return sc.departmentDAOClass;
  }

  public String getRoleDAOClass() {
    return sc.roleDAOClass;
  }

  public String getPermissionDAOClass() {
    return sc.permissionDAOClass;
  }

  public String getRegionDAOClass() {
    return sc.regionDAOClass;
  }

  public String getRangeDAOClass() {
    return sc.rangeDAOClass;
  }

  // create the user data access object
  public UserDAO createUserDao() {
    UserDAO dao = null;
    try {
      dao = (UserDAO) Class.forName(getUserDAOClass()).newInstance();
    }
    catch (Exception e) {
      dao = null;
    }
    return dao;
  }

  // create the group data access object
  public RangeDAO createRangeDao() {
    RangeDAO dao = null;
    try {
      dao = (RangeDAO) Class.forName(getRangeDAOClass()).newInstance();
    }
    catch (Exception e) {
      dao = null;
    }
    return dao;
  }

  // create the region data access object
  public RegionDAO createRegionDao() {
    RegionDAO dao = null;
    try {
      dao = (RegionDAO) Class.forName(getRegionDAOClass()).newInstance();
    }
    catch (Exception e) {
      dao = null;
    }
    return dao;
  }

  // create the department data access object
  public DeptDAO createDepartmentDao() {
    DeptDAO dao = null;
    try {
      dao = (DeptDAO) Class.forName(getDepartmentDAOClass()).newInstance();
    }
    catch (Exception e) {
      dao = null;
    }
    return dao;
  }

  // create the roles data access object
  public RoleDAO createRolesDao() {
    RoleDAO dao = null;
    try {
      dao = (RoleDAO) Class.forName(getRoleDAOClass()).newInstance();
    }
    catch (Exception e) {
      dao = null;
    }
    return dao;
  }

  // create the roles data access object
  public PermissionDAO createPermissionDao() {
    PermissionDAO dao = null;
    try {
      dao = (PermissionDAO) Class.forName(getPermissionDAOClass()).newInstance();
    }
    catch (Exception e) {
      dao = null;
    }
    return dao;
  }

  /** transaction support */
  public void begin() {}

  /** commit change */
  public void commit() {}

  /** abort change */
  public void abort() {}
}
