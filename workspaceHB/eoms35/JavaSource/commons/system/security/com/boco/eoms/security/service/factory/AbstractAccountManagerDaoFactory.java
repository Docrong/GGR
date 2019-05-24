/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   weis            2003-08-15         created
 * V1.0.0.0   Wang Zhuo Wei   2003-08-19         amend
 */

package com.boco.eoms.security.service.factory;

import com.boco.eoms.security.exception.SecurityManagerDaoException;
import com.boco.eoms.security.service.dao.DeptDAO;
import com.boco.eoms.security.service.dao.PermissionDAO;
import com.boco.eoms.security.service.dao.RangeDAO;
import com.boco.eoms.security.service.dao.RegionDAO;
import com.boco.eoms.security.service.dao.RoleDAO;
import com.boco.eoms.security.service.dao.UserDAO;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Department Data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 *
 * User Manager Dao Factory instance
 *
 * @author weis
 * @version 1.0
 */

public interface AbstractAccountManagerDaoFactory {

  /** get dao class name */
  public abstract String getUserDAOClass();
  public abstract String getDepartmentDAOClass();
  public abstract String getRoleDAOClass();
  public abstract String getPermissionDAOClass();
  public abstract String getRegionDAOClass();
  public abstract String getRangeDAOClass();

  // create the user data access object
  public abstract UserDAO createUserDao();
  // create the group data access object
  public abstract RangeDAO createRangeDao();
  // create the region data access object
  public abstract RegionDAO createRegionDao();
  // create the department data access object
  public abstract DeptDAO createDepartmentDao();
  // create the roles data access object
  public abstract RoleDAO createRolesDao();
  // create the roles data access object
  public abstract PermissionDAO createPermissionDao();

  public abstract void begin() throws SecurityManagerDaoException;
  public abstract void commit();
  public abstract void abort();
}
