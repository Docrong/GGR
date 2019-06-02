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
import com.boco.eoms.security.service.dao.RoleDAO;
import com.boco.eoms.security.service.model.RoleDO;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: all operation to role data object </p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class RoleManager {

  private static SystemConfig sc = SystemConfig.getInstance();

  /**
   * Desc : create the corresponding DAO according to the configuration file
   * @return RoldDAO - The data access object of Role
   * @throws SecurityManagerDaoException
   */
  private static RoleDAO createDao()
      throws SecurityManagerDaoException {
    try {
      //System.out.println(sc.roleDAOClass);
      return (RoleDAO) Class.forName(sc.roleDAOClass).newInstance();
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

  /**
   * Desc :
   * @param role - the role information to create
   * @return RoleDO - the created role data object
   * @throws ObjectAlreadyExistException
   * @throws SecurityManagerDaoException
   */
  public static RoleDO createRole(RoleDO role)
      throws ObjectAlreadyExistException, SecurityManagerDaoException {
    try {
      validation(role);
    }
    catch (SecurityManagerDaoException ex) {
      throw ex;
    }

    RoleDO tmp = null;
    try {
      tmp = createDao().createRole(role);
    }
    catch (SecurityManagerDaoException ex1) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex1);
    }
    catch (ObjectAlreadyExistException ex1) {
      throw new ObjectAlreadyExistException("Role ID " + role.getRoleID() + " is already exist.");
    }
    return tmp;
  }

  /**
   * Desc :
   * @param role - the role you want to delete
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static void deleteRole(RoleDO role)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      validation(role);
    }
    catch (SecurityManagerDaoException ex) {
      throw ex;
    }

    try {
      createDao().deleteRole(role);
    }
    catch (SecurityManagerDaoException ex1) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex1);
    }
    catch (ObjectNotExistException ex1) {
      throw new ObjectNotExistException("Role ID " + role.getRoleID() + " is not exist.");
    }
  }

  /**
   * Desc :
   * @param role - the role you want to update
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static void updateRole(RoleDO role)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      validation(role);
    }
    catch (SecurityManagerDaoException ex) {
      throw ex;
    }

    try {
      createDao().updateRole(role);
    }
    catch (SecurityManagerDaoException ex1) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex1);
    }
    catch (ObjectNotExistException ex1) {
      throw new ObjectNotExistException("Role ID " + role.getRoleID() + " is not exist.");
    }
  }

  /**
   * Desc :
   * @param roleName - the role name you want to search for
   * @return RoleDO - The corresponding role data object according to the name
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static RoleDO lookupRole(String roleName)
      throws ObjectNotExistException, SecurityManagerDaoException {
    RoleDO tmp = null;
    try {
      tmp = createDao().lookupRole(roleName);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("Role ID " + roleName + " is not exist.");
    }
    return tmp;
  }

  public static Vector getRoleList()
      throws ObjectNotExistException, SecurityManagerDaoException {
    Vector tmp = null;
    try {
      tmp = createDao().getRoleNames();
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    return tmp;
  }

  /**
   * Desc : check the mandatory fields of RoleDO
   * @param role - the role data object which want to be validated
   * @throws SecurityManagerDaoException
   */
  private static void validation(RoleDO role)
      throws SecurityManagerDaoException {
    if (role.getRoleID() == null || role.getRoleID().equals("")) {
      throw new SecurityManagerDaoException("Invalid Role ID.", new Exception("Invalid Role ID."));
    }
  }

  public static void main(String[] args) {
    RoleDO role = new RoleDO();
    //insert operation test
    role.setRoleID("webRole010");
    role.setName("Test Role");
    role.setComment("Comment for test role!!");
    try {
      RoleManager.createRole(role);
//      RoleManager.updateRole(role);
//      RoleManager.deleteRole(role);
//      RoleDO a = RoleManager.lookupRole(role.getRoleID());
//      System.out.println(a.getRoleID());
//      System.out.println(a.getName());
//      System.out.println(a.getComment());
      /* Vector a = RoleManager.getRoleList();
       Iterator it = a.iterator();
       while (it.hasNext()){
         RoleDO qq = (RoleDO)it.next();
         System.out.println(qq.toString());
       }*/

      System.out.println("successfully");

    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}