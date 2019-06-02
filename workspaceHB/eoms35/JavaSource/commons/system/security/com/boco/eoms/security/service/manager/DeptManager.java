/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-18         created
 */

package com.boco.eoms.security.service.manager;

import java.util.Vector;

import com.boco.eoms.security.config.SystemConfig;
import com.boco.eoms.security.exception.ObjectAlreadyExistException;
import com.boco.eoms.security.exception.ObjectNotExistException;
import com.boco.eoms.security.exception.SecurityManagerDaoException;
import com.boco.eoms.security.service.dao.DeptDAO;
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
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class DeptManager {

  private static SystemConfig sc = SystemConfig.getInstance();

  public static DeptDAO createDao()
      throws SecurityManagerDaoException {
    try {
      //System.out.println(sc.departmentDAOClass);
      return (DeptDAO) Class.forName(sc.departmentDAOClass).newInstance();
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

  private DeptDAO dao;
  /**
   * Desc : create department informaiton
   * @param department
   * @return
   * @throws ObjectAlreadyExistException
   * @throws SecurityManagerDaoException
   */
  public static DeptDO createDepartment(DeptDO department, RegionDO region)
      throws ObjectAlreadyExistException, SecurityManagerDaoException {
    try {
      validation(department);
    }
    catch (SecurityManagerDaoException ex) {
      throw ex;
    }

    DeptDO result = new DeptDO();
    try {
      result = createDao().createDepartment(department, region);
    }
    catch (ObjectAlreadyExistException ex) {
      throw new ObjectAlreadyExistException("�˲���ID���Ѿ����ڣ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

    return result;
  }

  /**
   * Desc : delete department information
   * @param department
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static void deleteDepartment(DeptDO department, RegionDO region)
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
      throw new ObjectNotExistException("�ò��Ų����ڣ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

  }

  /**
   * Desc : update department information
   * @param department
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static void updateDepartment(DeptDO department, RegionDO region)
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
      throw new ObjectNotExistException("�ò��Ų����ڣ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

  }

  /**
   * Desc : find the corresponding department
   * @param DepartmentDO
   * @return
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static DeptDO lookupDepartment(DeptDO department, RegionDO region)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      validation(department);
    }
    catch (SecurityManagerDaoException ex) {
      throw ex;
    }

    DeptDO result = new DeptDO();
    try {
      result = createDao().lookupDepartment(department, region);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("�ò��Ų����ڣ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
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
  public static DeptDO lookupDepartment(int deptID)
  throws ObjectNotExistException, SecurityManagerDaoException {
//  	try {
//  		validation(department);
//  	}
//  	catch (SecurityManagerDaoException ex) {
//  		throw ex;
//  	}

  	DeptDO result = new DeptDO();
  	try {
  		result = createDao().lookupDepartment(deptID);
  	}
  	catch (ObjectNotExistException ex) {
  		throw new ObjectNotExistException("�ò��Ų����ڣ�");
  	}
  	catch (SecurityManagerDaoException ex) {
  		throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
  	}

  	return result;
  }

  // ------------------------------------------------
  //  User management
  // ------------------------------------------------

  public static void addUser(DeptDO department, RegionDO region, UserDO user)
      throws ObjectAlreadyExistException, SecurityManagerDaoException {

    try {
      createDao().addUser(department, region, user);
    }
    catch (ObjectAlreadyExistException ex) {
      throw new ObjectAlreadyExistException("���û��Ѿ������ڸò��ţ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

  }

  public static void removeUser(DeptDO department, RegionDO region, UserDO user)
      throws ObjectNotExistException, SecurityManagerDaoException {

    try {
      createDao().removeUser(department, region, user);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("���û������ڣ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

  }

  public static Vector getUserList(DeptDO department, RegionDO region)
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

  public static void clearUsers(DeptDO department, RegionDO region)
      throws SecurityManagerDaoException {
    createDao().clearUsers(department, region);
  }


  // ------------------------------------------------
  //  Role management
  // ------------------------------------------------

  public static void setRole(DeptDO department, RegionDO region, RoleDO role)
      throws ObjectNotExistException, ObjectAlreadyExistException, SecurityManagerDaoException {
    try {
      createDao().setRole(department, region, role);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("�˶��󲻴��ڣ�");
    }
    catch (ObjectAlreadyExistException ex) {
      throw new ObjectAlreadyExistException("�ý�ɫ�Ѿ����ڴ˲��ţ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

  }

  public static void removeRole(DeptDO department, RegionDO region, RoleDO role)
      throws ObjectNotExistException, SecurityManagerDaoException {

    try {
      createDao().removeRole(department, region, role);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("�˶��󲻴��ڣ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }
  }

//  public static Vector getRoleList(DepartmentDO department, RegionDO region)
//      throws SecurityManagerDaoException{
//    return createDao().getRoleList(department, region);
//  }

  public static void clearRoles(DeptDO department, RegionDO region)
      throws SecurityManagerDaoException {
    createDao().clearRoles(department, region);
  }

  // ------------------------------------------------
  //  DataRange management
  // ------------------------------------------------

  public static void setRange(DeptDO department, RegionDO region, RangeDO range)
      throws ObjectNotExistException, ObjectAlreadyExistException, SecurityManagerDaoException {

    try {
      createDao().setRange(department, region, range);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("�˶��󲻴��ڣ�");
    }
    catch (ObjectAlreadyExistException ex) {
      throw new ObjectAlreadyExistException("�ù������Ѿ����ڴ˲��ţ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

  }

  public static void removeRange(DeptDO department, RegionDO region, RangeDO range)
      throws ObjectNotExistException, SecurityManagerDaoException {

    try {
      createDao().removeRange(department, region, range);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("�˶��󲻴��ڣ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

  }

//  public static Vector getRangeList(DepartmentDO department, RegionDO region)
//      throws SecurityManagerDaoException{
//    return createDao().getRangeList(department, region);
//  }

  public static void clearRange(DeptDO department, RegionDO region)
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
      throw new ObjectNotExistException("�˶��󲻴��ڣ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
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
      throw new ObjectNotExistException("�˶��󲻴��ڣ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
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
      throw new ObjectNotExistException("�˶��󲻴��ڣ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
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
  		throw new ObjectNotExistException("�˶��󲻴��ڣ�");
  	}
  	catch (SecurityManagerDaoException ex) {
  		throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
  	}
  	return result;

  }

  public static Vector getSubDepartment()
  throws SecurityManagerDaoException, ObjectNotExistException{
  	Vector result = new Vector();
  	try {
  		result = createDao().getSubDepartment();
  	}
  	catch (ObjectNotExistException ex) {
  		throw new ObjectNotExistException();
  	}
  	catch (SecurityManagerDaoException ex) {
  		throw new SecurityManagerDaoException("������˴������Ժ�����", ex);
  	}
  	return result;

  }
  /**
   * Desc : check the mandatory fields of DepartmentDO
   * @param role - the department data object which want to be validated
   * @throws SecurityManagerDaoException
   */
  private static void validation(DeptDO department)
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

    	vec = DeptManager.getAllUserList("Department_30");
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
