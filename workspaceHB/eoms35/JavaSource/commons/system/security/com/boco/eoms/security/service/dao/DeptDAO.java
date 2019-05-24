/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-15         created
 */

package com.boco.eoms.security.service.dao;

import java.util.Vector;

import com.boco.eoms.security.exception.ObjectAlreadyExistException;
import com.boco.eoms.security.exception.ObjectNotExistException;
import com.boco.eoms.security.exception.SecurityManagerDaoException;
import com.boco.eoms.security.service.model.DeptDO;
import com.boco.eoms.security.service.model.RangeDO;
import com.boco.eoms.security.service.model.RegionDO;
import com.boco.eoms.security.service.model.RoleDO;
import com.boco.eoms.security.service.model.UserDO;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Department Data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author weis
 * @version 1.0
 */

public interface DeptDAO
    extends BaseDAO {
  // -------------------------------------------------------------------------
  //  Department Object management
  // -------------------------------------------------------------------------

  /**
   * name of the department
   * @param dpt department object to persist
   * @throws ObjectAlreadyExistException , SecurityManagerDaoException;
   */
  public DeptDO createDepartment(DeptDO dept, RegionDO region)
      throws ObjectAlreadyExistException, SecurityManagerDaoException;

  /**
   * remove department object
   * @param dpt department object to remove , the parameter may be Department Object or id of the region
   * @throws ObjectNotExistException , SecurityManagerDaoException;
   */
  public void deleteDepartment(DeptDO dept, RegionDO region)
      throws ObjectNotExistException, SecurityManagerDaoException;

  /**
   * update department information
   * @param dpt department object to update
   * @throws ObjectNotExistException , SecurityManagerDaoException;
   */
  public void updateDepartment(DeptDO dept, RegionDO region)
      throws ObjectNotExistException, SecurityManagerDaoException;

  /**
   * retrieve the department with the given name
   *
   * @param id id of the department
   * @throws ObjectNotExistException ,SecurityManagerDaoException
   */
  public DeptDO lookupDepartment(DeptDO dept, RegionDO region)
      throws ObjectNotExistException, SecurityManagerDaoException;

  /**
   * retrieve the department with the given name
   *
   * @param id id of the department
   * @throws ObjectNotExistException ,SecurityManagerDaoException
   */
  public DeptDO lookupDepartment(int deptID)
  throws ObjectNotExistException, SecurityManagerDaoException;

  /**
   * @return total number of departments
   */
  public int getCount()
      throws SecurityManagerDaoException;

  // -------------------------------------------------------------------------
  //  User management
  // -------------------------------------------------------------------------
  /**
   * add user to group
   * @param department department object to add the user
   * @param usr user to add
   * @throws ObjectAlreadyExistException , SecurityManagerDaoException
   */
  public void addUser(DeptDO department, RegionDO region, UserDO user)
      throws ObjectAlreadyExistException, SecurityManagerDaoException;

  /**
   * remove user from department
   * @param department department object to operate
   * @param usr user to remove
   * @throws ObjectNotExistException , SecurityManagerDaoException
   */
  public void removeUser(DeptDO department, RegionDO region, UserDO user)
      throws ObjectNotExistException, SecurityManagerDaoException;

  /**
   * get members belong to a department
   * @param department department to retrive
   * @return vector containing group members
   * @throws SecurityManagerDaoException
   */
  public Vector getUserList(DeptDO department, RegionDO region)
      throws SecurityManagerDaoException;

  /**
   * get members belong to a department
   * @param department department to retrive
   * @return vector containing group members
   * @throws SecurityManagerDaoException
   * @author shenwei
   */
  public Vector getUserList(String deptID)
  throws SecurityManagerDaoException;

  /**
   * get members belong to a department and sub department
   * @param department department to retrive
   * @return vector containing group members
   * @throws SecurityManagerDaoException
   * @author shenwei
   */
  public Vector getAllUserList(String deptID)
  throws SecurityManagerDaoException ;
  /**
   * remove all user in the department
   * @param grp group object
   */
  public void clearUsers(DeptDO department, RegionDO region)
      throws SecurityManagerDaoException;

  /**
   * Check if user is a memeber of the department with the name , this is a shotcut
   * method of previous for convienient.
   *
   * @param usr user to judge
   * @param department department object
   *
   * @throws SecurityManagerDaoException
   */
  public boolean isMemberOf(DeptDO department, RegionDO region, UserDO user)
      throws SecurityManagerDaoException;

  // ------------------------------------------------
  //  role management
  // ------------------------------------------------

  /**
   * set role to group
   * @param dept Department Object
   * @param role role to add
   * @throws ObjectAlreadyExistException , SecurityManagerDaoException
   */
  public void setRole(DeptDO dept, RegionDO region, RoleDO role)
      throws ObjectNotExistException, ObjectAlreadyExistException, SecurityManagerDaoException;

  /**
   * remove role from group
   * @param dept department object
   * @param role role to remove
   * @throws ObjectNotExistException , SecurityManagerDaoException
   */
  public void removeRole(DeptDO dept, RegionDO region, RoleDO role)
      throws ObjectNotExistException, SecurityManagerDaoException;


  /**
   * get roles belong to the group
   * @param dept dept object
   * @return vector containg roles assigned to the department
   * @throws SecurityManagerDaoException
   */
  public Vector getRoleList(DeptDO dept,RegionDO region)
      throws SecurityManagerDaoException;

  /**
   * clear all roles
   * @param dept dept to clear roles
   * @throws SecurityManagerDaoException
   */
  public void clearRoles(DeptDO dept,RegionDO region)
      throws SecurityManagerDaoException;

  // ------------------------------------------------
  //  DataRange management
  // ------------------------------------------------

  /**
   * set Range to group
   * @param dept Department Object
   * @param range role to add
   * @throws ObjectAlreadyExistException , SecurityManagerDaoException
   */
  public void setRange(DeptDO department, RegionDO region, RangeDO range)
      throws ObjectNotExistException, ObjectAlreadyExistException, SecurityManagerDaoException;

  /**
   * remove Range from group
   * @param dept department object
   * @param range range to remove
   * @throws ObjectNotExistException , SecurityManagerDaoException
   */
  public void removeRange(DeptDO department, RegionDO region, RangeDO range)
      throws ObjectNotExistException, SecurityManagerDaoException;

  /**
   * get range belong to the group
   * @param dept dept object
   * @return vector containg ranges assigned to the department
   * @throws SecurityManagerDaoException
   */
  public Vector getRangeList(DeptDO department, RegionDO region)
      throws SecurityManagerDaoException;

  /**
   * clear all range
   * @param dept dept to clear range
   * @throws SecurityManagerDaoException
   */
  public void clearRange(DeptDO department, RegionDO region)
      throws SecurityManagerDaoException;

  /**
   *
   * @param deptID
   * @param up if get the parent department
   * @return All roles of the department(parent)
   * @throws SecurityManagerDaoException
   * @throws ObjectNotExistException
   */
  public Vector getRolesOfDepartment(String deptID, String regionID, boolean up)
      throws SecurityManagerDaoException, ObjectNotExistException;

  /**
   *
   * @param deptID
   * @param up if get the parent department
   * @return All ranges of the department(parent)
   * @throws SecurityManagerDaoException
   * @throws ObjectNotExistException
   */
  public Vector getRangesOfDepartment(String deptID, String regionID, boolean up)
      throws SecurityManagerDaoException, ObjectNotExistException;

  public Vector getSubDepartment(String deptID, String regionID)
      throws SecurityManagerDaoException, ObjectNotExistException;

  public Vector getSubDepartment(String deptID) throws SecurityManagerDaoException, ObjectNotExistException; //add by shenwei 2004-02-04

  public Vector getSubDepartment()throws SecurityManagerDaoException, ObjectNotExistException;


}
