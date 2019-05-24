/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   weis            2003-08-22         creation
 * V1.0.0.0   Wang Zhuo Wei   2003-09-11         Enhancement
 */

package com.boco.eoms.security.service.manager;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Permission Data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author weis
 * @version 1.0
 */
import java.util.Vector;

import com.boco.eoms.security.config.SystemConfig;
import com.boco.eoms.security.exception.ObjectAlreadyExistException;
import com.boco.eoms.security.exception.ObjectNotExistException;
import com.boco.eoms.security.exception.SecurityManagerDaoException;
import com.boco.eoms.security.service.dao.PermissionDAO;
import com.boco.eoms.security.service.model.PermissionDO;

public class PermissionManager {

  private static SystemConfig sc = SystemConfig.getInstance();

  private static PermissionDAO createDao()
      throws SecurityManagerDaoException {
    try {
      //System.out.println(sc.permissionDAOClass);
      return (PermissionDAO) Class.forName(sc.permissionDAOClass).newInstance();
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

  //------------------------------------------------------------
  //  Permission management
  //------------------------------------------------------------

  /**
   * create the permission with a permission object
   * @param permission    permission object
   * @return  permissionDO    permission data object
   * @throws SecurityManagerDaoException
   * @throws ObjectAlreadyExistException
   */
  public static PermissionDO createPermission(PermissionDO permission)
      throws SecurityManagerDaoException, ObjectAlreadyExistException {
    try {
      validation(permission);
    }
    catch (SecurityManagerDaoException ex) {
      throw ex;
    }
    PermissionDO pdo = null;
    try {
      pdo = createDao().createPermission(permission);
    }
    catch (SecurityManagerDaoException ex1) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex1);
    }
    catch (ObjectAlreadyExistException ex1) {
      throw new ObjectAlreadyExistException("Permission ID " + permission.getPermissionID() + " is already exist.");
    }
    return pdo;
  }

  /**
   * create the permission with the given id,name,category and description
   * @param id    permission ID
   * @param name  permission Name
   * @param category  permission's category
   * @param desc  the description of permission
   * @return  PermissionDO permission data object
   * @throws ObjectAlreadyExistException
   * @throws SecurityManagerDaoException
   */
  public static PermissionDO createPermission(String id, String name, String category, String desc)
      throws ObjectAlreadyExistException, SecurityManagerDaoException {
    PermissionDO pdo = null;
    try {
      pdo = createDao().createPermission(id, name, category, desc);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectAlreadyExistException ex) {
      throw new ObjectAlreadyExistException("Permission ID " + id + " is already exist.");
    }
    return pdo;
  }

  /**
   * delete the permission according to the permission ID
   * @param pid   permission ID
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static void deletePermission(String pid)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      createDao().deletePermission(pid);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("Permission ID " + pid + " is not exist.");
    }
  }

  /**
   * lookup the permission according to the permission pid
   * @param pid permission ID
   * @return  permissionDO permission data object
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static PermissionDO lookupPermission(String pid)
      throws ObjectNotExistException, SecurityManagerDaoException {

    PermissionDO pdo = null;
    try {
      pdo = createDao().lookupPermission(pid);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("Permission ID " + pid + " is not exist.");
    }
    return pdo;

  }

  /**
   * get all permissions
   * @return  vector vector of all permissions
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static Vector getAllPermissions()
      throws ObjectNotExistException, SecurityManagerDaoException {
    Vector vec = null;
    try {
      vec = createDao().getAllPermissions();
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("");
    }
    return vec;
  }

  /**
   * update the permission data
   * @param pdo - permission data object
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static void updatePermission(PermissionDO pdo)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      createDao().updatePermission(pdo);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("Permission ID " + pdo.getPermissionID() + " is not exist.");
    }
  }

  //--------------------------------------------------------------
  //  Role management
  //--------------------------------------------------------------

  /**
   * return role list
   * @param pid - permission ID
   * @return  vector - the roles list
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static Vector getAssignedRoles(String pid)
      throws ObjectNotExistException, SecurityManagerDaoException {
    Vector vec = null;
    try {
      vec = createDao().getAssignedRoles(pid);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("Permission ID " + pid + " is not exist.");
    }
    return vec;
  }

  /**
   * assign role to the permission
   * @param pid - permission ID
   * @param roles - the role vector
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static void assignRolePermission(String pid, Vector roles)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      createDao().assignRolePermission(pid, roles);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("Permission ID " + pid + " is not exist.");
    }
  }

  /**
   * remove permission a role
   * @param permID
   * @param roles
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */

  public static void removeRolePermission(String permID, Vector roles)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      createDao().removeRolePermission(permID, roles);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("Permission ID " + permID + " is not exist.");
    }
  }

  /**
   * create the permission Category
   * @param name - the name of the Category
   * @param desc - the description of the Category
   * @throws ObjectAlreadyExistException
   * @throws SecurityManagerDaoException
   */
  public static void createCategory(String name, String desc)
      throws ObjectAlreadyExistException, SecurityManagerDaoException {
    try {
      createDao().createCategory(name, desc);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
  }

  /**
   * get the given role's permission
   * @param rid - rold ID
   * @return vector - permission's vector
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static Vector getRolePermissions(String rid)
      throws ObjectNotExistException, SecurityManagerDaoException {
    Vector vec = null;
    try {
      vec = createDao().getRolePermissions(rid);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("Role ID " + rid + " is not exist.");
    }
    return vec;
  }

  public static Vector getAllCategory()
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      return createDao().getAllCategory();
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("");
    }
  }

  public static Vector getRangeByCategory(Object category)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      return createDao().getRangeByCategory(category);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("");
    }
  }

  /**
   * test the input data
   * @param perm - permission Data object
   * @throws SecurityManagerDaoException
   */
  private static void validation(PermissionDO perm)
      throws SecurityManagerDaoException {
    if (perm.getPermissionID() == null || perm.getPermissionID().equals("")) {
      throw new SecurityManagerDaoException("Invalid Permission ID.", new Exception("Invalid Permission ID."));
    }
  }

  public static void main(String[] args) {
    /*PermissionDO pdo = new PermissionDO();
             pdo.setPermissionID("testPerManager");
             pdo.setCategory("testPerManagerCat");
             pdo.setName("testPerManagerName2");
             pdo.setComment("3This is test for Per manager!");*/

    try {
      //PermissionManager.createPermission(pdo);
      //PermissionManager.deletePermission("testPerManager2");
      //PermissionManager.updatePermission(pdo);

      //Vector pm = PermissionManager.getAllPermissions();
      Vector pm = PermissionManager.getRangeByCategory("test");
      for (int i = 0; i < pm.size(); i++) {
        PermissionDO p = (PermissionDO) pm.elementAt(i);
        System.out.println("ID : " + p.getPermissionID());
        System.out.println("Name: " + p.getName());
        System.out.println("Description: " + p.getComment());
        System.out.println("Category: " + p.getCategory());
      }
      /*PermissionDO pdo = PermissionManager.lookupPermission("permissionTest1");
                   System.out.println("ID: " + pdo.getPermissionID());
                   System.out.println("Name: " + pdo.getName());
                   System.out.println("Category: " + pdo.getCategory());
                   System.out.println("Description: " + pdo.getComment());*/
      /*Vector roles = new Vector();
                   roles.addElement("Role001");
                   roles.addElement("Role002");
                   roles.addElement("Role003");
                   roles.addElement("Role004");
                   PermissionManager.assignRolePermission("testPerManager",roles);*/
      //Vector roles = new Vector();
      /*Vector roles = PermissionManager.getAssignedRoles("testPerManager");
                   for(int i=0;i<roles.size();i++)
                   {
          RoleDO r = (RoleDO) roles.elementAt(i);
          System.out.println("the role id: " + r.getRoleID());
          System.out.println("the role name is: " + r.getName());
          System.out.println("----------------");
                   } */
      /*Vector role = new Vector();
                   role.addElement("Role004");
                   PermissionManager.removeRolePermission("testPerManager",role);*/

      /*Vector category = PermissionManager.getAllCategory();
                   for(int i=0; i<category.size(); i++)
                   {
          Map cat = (HashMap)category.elementAt(i);
          System.out.println("The category name: " + cat.get("categoryName"));
          System.out.println("The description: " + cat.get("description"));
                   } */

      System.out.println("Congratulations!");
    }
    catch (Exception ex) {
      System.out.println("There is something wrong with your programs !");
    }
  }
}
