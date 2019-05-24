/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   weis              2003-08-15       created
 */

package com.boco.eoms.security.service.dao;

import java.util.Vector;

import com.boco.eoms.security.exception.ObjectAlreadyExistException;
import com.boco.eoms.security.exception.ObjectNotExistException;
import com.boco.eoms.security.exception.SecurityManagerDaoException;
import com.boco.eoms.security.service.model.RoleDO;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Department Data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author weis
 * @version 1.0
 */
public interface RoleDAO {
  /**
   * create a role with given name
   * @param role role object to create
   */
  public RoleDO createRole(RoleDO role)
      throws ObjectAlreadyExistException, SecurityManagerDaoException;

  /**
   * delete the role
   * @param role the role to delete
   */
  public void deleteRole(RoleDO role)
      throws ObjectNotExistException, SecurityManagerDaoException;

  /**
   * update the role
   * @param role role to update
   */
  public void updateRole(RoleDO role)
      throws ObjectNotExistException, SecurityManagerDaoException;

  /**
   * retrieve the role with the given name
   */
  public RoleDO lookupRole(String name)
      throws ObjectNotExistException, SecurityManagerDaoException;

  /**
   * @return total number of roles
   */
  public int getCount()
      throws SecurityManagerDaoException;

  /**
   * @return all the role names
   */
  public Vector getRoleNames()
      throws SecurityManagerDaoException;

  /**
   * @param index start index
   * @param nCount count of names
   * @return a range of role names
   */
  public Vector getRoleNames(int index, int nCount)
      throws SecurityManagerDaoException;

  /**
   * return all the roles
   */
  public Vector getRoles()
      throws SecurityManagerDaoException;
}
