/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-18         created
 */

package com.boco.common.security.service.manager;

import java.util.Vector;

import com.boco.common.security.config.SystemConfig;
import com.boco.common.security.exception.ObjectAlreadyExistException;
import com.boco.common.security.exception.ObjectNotExistException;
import com.boco.common.security.exception.SecurityManagerDaoException;
import com.boco.common.security.service.dao.DepartmentDAO;
import com.boco.common.security.service.model.DepartmentDO;
import com.boco.common.security.service.model.RangeDO;
import com.boco.common.security.service.model.RegionDO;
import com.boco.common.security.service.model.RoleDO;
import com.boco.common.security.service.model.UserDO;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Department Data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class DepartmentManager {

  private static SystemConfig sc = SystemConfig.getInstance();

  public static DepartmentDAO createDao()
      throws SecurityManagerDaoException {
    try {
      //System.out.println(sc.departmentDAOClass);
      return (DepartmentDAO) Class.forName(sc.departmentDAOClass).newInstance();
    }
    catch (ClassNotFoundException ex) {
      ex.printStackTrace();
      return null;
    }
    catch (IllegalAccessException ex) {
      ex.printStackTrace();
      return null;
    }
    catch (InstantiationException ex) {
      ex.printStackTrace();
      return null;
    }
  }

  private DepartmentDAO dao;
  /**
   * Desc : create department informaiton
   * @param department
   * @return
   * @throws ObjectAlreadyExistException
   * @throws SecurityManagerDaoException
   */
  public static DepartmentDO createDepartment(DepartmentDO department, RegionDO region)
      throws ObjectAlreadyExistException, SecurityManagerDaoException {
    try {
      validation(department);
    }
    catch (SecurityManagerDaoException ex) {
      throw ex;
    }

    DepartmentDO result = new DepartmentDO();
    try {
      result = createDao().createDepartment(department, region);
    }
    catch (ObjectAlreadyExistException ex) {
      throw new ObjectAlreadyExistException("此部门ID号已经存在！");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
    }

    return result;
  }

  /**
   * Desc : delete department information
   * @param department
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static void deleteDepartment(DepartmentDO department, RegionDO region)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      validation(department);
    }
    catch (SecurityManagerDaoException ex) {
      throw ex;
    }

    try {
      createDao().deleteDepartment(department, region);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("该部门不存在！");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
    }

  }

  /**
   * Desc : update department information
   * @param department
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static void updateDepartment(DepartmentDO department, RegionDO region)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      validation(department);
    }
    catch (SecurityManagerDaoException ex) {
      throw ex;
    }
    try {
      createDao().updateDepartment(department, region);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("该部门不存在！");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
    }

  }

  /**
   * Desc : find the corresponding department
   * @param DepartmentDO
   * @return
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static DepartmentDO lookupDepartment(DepartmentDO department, RegionDO region)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      validation(department);
    }
    catch (SecurityManagerDaoException ex) {
      throw ex;
    }

    DepartmentDO result = new DepartmentDO();
    try {
      result = createDao().lookupDepartment(department, region);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("该部门不存在！");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
    }

    return result;
  }

  /**
   * Desc : find the corresponding department
   * @param DepartmentDO
   * @return
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   * @author shenwei
   */
  public static DepartmentDO lookupDepartment(String deptID)
  throws ObjectNotExistException, SecurityManagerDaoException {
//  	try {
//  		validation(department);
//  	}
//  	catch (SecurityManagerDaoException ex) {
//  		throw ex;
//  	}

  	DepartmentDO result = new DepartmentDO();
  	try {
  		result = createDao().lookupDepartment(deptID);
  	}
  	catch (ObjectNotExistException ex) {
  		throw new ObjectNotExistException("该部门不存在！");
  	}
  	catch (SecurityManagerDaoException ex) {
  		throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
  	}

  	return result;
  }

  // ------------------------------------------------
  //  User management
  // ------------------------------------------------

  public static void addUser(DepartmentDO department, RegionDO region, UserDO user)
      throws ObjectAlreadyExistException, SecurityManagerDaoException {

    try {
      createDao().addUser(department, region, user);
    }
    catch (ObjectAlreadyExistException ex) {
      throw new ObjectAlreadyExistException("此用户已经存在于该部门！");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
    }

  }

  public static void removeUser(DepartmentDO department, RegionDO region, UserDO user)
      throws ObjectNotExistException, SecurityManagerDaoException {

    try {
      createDao().removeUser(department, region, user);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("该用户不存在！");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
    }

  }

  public static Vector getUserList(DepartmentDO department, RegionDO region)
      throws SecurityManagerDaoException {
    return createDao().getUserList(department, region);
  }

  public static Vector getUserList(String deptID) //add by shenwei 2004-02-04
  throws SecurityManagerDaoException {
  	return createDao().getUserList(deptID);
  }

  public static Vector getAllUserList(String deptID) //add by shenwei 2004-02-04
  throws SecurityManagerDaoException {
  	return createDao().getAllUserList(deptID);
  }

  public static void clearUsers(DepartmentDO department, RegionDO region)
      throws SecurityManagerDaoException {
    createDao().clearUsers(department, region);
  }


  // ------------------------------------------------
  //  Role management
  // ------------------------------------------------

  public static void setRole(DepartmentDO department, RegionDO region, RoleDO role)
      throws ObjectNotExistException, ObjectAlreadyExistException, SecurityManagerDaoException {
    try {
      createDao().setRole(department, region, role);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("此对象不存在！");
    }
    catch (ObjectAlreadyExistException ex) {
      throw new ObjectAlreadyExistException("该角色已经属于此部门！");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
    }

  }

  public static void removeRole(DepartmentDO department, RegionDO region, RoleDO role)
      throws ObjectNotExistException, SecurityManagerDaoException {

    try {
      createDao().removeRole(department, region, role);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("此对象不存在！");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
    }
  }

//  public static Vector getRoleList(DepartmentDO department, RegionDO region)
//      throws SecurityManagerDaoException{
//    return createDao().getRoleList(department, region);
//  }

  public static void clearRoles(DepartmentDO department, RegionDO region)
      throws SecurityManagerDaoException {
    createDao().clearRoles(department, region);
  }

  // ------------------------------------------------
  //  DataRange management
  // ------------------------------------------------

  public static void setRange(DepartmentDO department, RegionDO region, RangeDO range)
      throws ObjectNotExistException, ObjectAlreadyExistException, SecurityManagerDaoException {

    try {
      createDao().setRange(department, region, range);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("此对象不存在！");
    }
    catch (ObjectAlreadyExistException ex) {
      throw new ObjectAlreadyExistException("该管理域已经属于此部门！");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
    }

  }

  public static void removeRange(DepartmentDO department, RegionDO region, RangeDO range)
      throws ObjectNotExistException, SecurityManagerDaoException {

    try {
      createDao().removeRange(department, region, range);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("此对象不存在！");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
    }

  }

//  public static Vector getRangeList(DepartmentDO department, RegionDO region)
//      throws SecurityManagerDaoException{
//    return createDao().getRangeList(department, region);
//  }

  public static void clearRange(DepartmentDO department, RegionDO region)
      throws SecurityManagerDaoException {
    createDao().clearRange(department, region);
  }

  public static boolean isMemberOf(Object obj, Object obj1)
      throws SecurityManagerDaoException {
    return true;
  }

  /**
   *
   * @param deptID
   * @param up if get the parent department
   * @return All roles of the department(parent)
   * @throws SecurityManagerDaoException
   * @throws ObjectNotExistException
   */
  public static Vector getRolesOfDepartment(String deptID, String regionID, boolean up)
      throws SecurityManagerDaoException, ObjectNotExistException {
    Vector result = new Vector();
    try {
      result = createDao().getRolesOfDepartment(deptID, regionID, up);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("此对象不存在！");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
    }
    return result;
  }

  /**
   *
   * @param deptID
   * @param up if get the parent department
   * @return All ranges of the department(parent)
   * @throws SecurityManagerDaoException
   * @throws ObjectNotExistException
   */
  public static Vector getRangesOfDepartment(String deptID, String regionID, boolean up)
      throws SecurityManagerDaoException, ObjectNotExistException {
    Vector result = new Vector();
    try {
      result = createDao().getRangesOfDepartment(deptID, regionID, up);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("此对象不存在！");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
    }
    return result;
  }

  public static Vector getSubDepartment(String deptID,String regionID)
      throws SecurityManagerDaoException, ObjectNotExistException{
    Vector result = new Vector();
    try {
      result = createDao().getSubDepartment(deptID, regionID);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("此对象不存在！");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
    }
    return result;

  }

  public static Vector getSubDepartment(String deptID) //add by shenwei 2004-02-05
  throws SecurityManagerDaoException, ObjectNotExistException{
  	Vector result = new Vector();
  	try {
  		result = createDao().getSubDepartment(deptID);
  	}
  	catch (ObjectNotExistException ex) {
  		throw new ObjectNotExistException("此对象不存在！");
  	}
  	catch (SecurityManagerDaoException ex) {
  		throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
  	}
  	return result;

  }

  /**
   * Desc : check the mandatory fields of DepartmentDO
   * @param role - the department data object which want to be validated
   * @throws SecurityManagerDaoException
   */
  private static void validation(DepartmentDO department)
      throws SecurityManagerDaoException {
    if (department.getDepartmentID() == null || department.getDepartmentID().equals("")) {
      throw new SecurityManagerDaoException("Invalid Department ID.", new Exception("Invalid Department ID."));
    }
  }

  /**
   * For self-testing only
   * @param args
   */
  public static void main(String[] args) {
    try {
      //Vector vec = DepartmentManager.getRolesOfDepartment("jl","jiangsu",false);
      //Vector vec = DepartmentManager.getRolesOfDepartment("jl","jiangsu",true);
      //Vector vec = DepartmentManager.getRolesOfDepartment("subjl","jiangsu",true);

      //Vector vec = DepartmentManager.getRangesOfDepartment("jl","jiangsu",true);
      //Vector vec = DepartmentManager.getRangesOfDepartment("MyDepartment","wuxi002",false);

      //Vector vec = DepartmentManager.getSubDepartment("jl","jiangsu");
    	UserDO udo;
    	Vector vec;

    	vec = DepartmentManager.getAllUserList("Department_30");
//      DepartmentDO ddo = new DepartmentDO();
//      ddo = DepartmentManager.lookupDepartment("Department_26");
//      ddo.setDepartmentID("jl");
//      RegionDO rdo = new RegionDO();
//      rdo.setRegionID("jiangsu");
//      rdo = RegionManager.lookupRegion(rdo);
//
//      Vector vec = DepartmentManager.getUserList(ddo,rdo);

      System.out.println("wzw:"+vec.size());
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
