/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   weis            2003-08-22         creation
 * V1.0.0.0   Wang Zhuo Wei   2003-09-11         Enhancement
 */

package com.boco.eoms.security.service.manager;

import java.util.Vector;

import com.boco.eoms.security.config.SystemConfig;
import com.boco.eoms.security.exception.ObjectAlreadyExistException;
import com.boco.eoms.security.exception.ObjectNotExistException;
import com.boco.eoms.security.exception.SecurityManagerDaoException;
import com.boco.eoms.security.service.dao.RangeDAO;
import com.boco.eoms.security.service.model.RangeDO;

public class RangeManager {

  private static SystemConfig sc = SystemConfig.getInstance();

  /**
   * create the Data access Object
   * @return RangeDAO Range data access object
   * @throws SecurityManagerDaoException
   */
  private static RangeDAO createDao()
      throws SecurityManagerDaoException {
    try {
      return (RangeDAO) Class.forName(sc.rangeDAOClass).newInstance();
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

  //-----------------------------------------------------------
  // Data Range Management
  //-----------------------------------------------------------

  /**
   * create Range use RangeDO
   * @param range - Range Data Object
   * @return RangeDO - Range Data Object
   * @throws ObjectAlreadyExistException
   * @throws SecurityManagerDaoException
   */
  public static RangeDO createRange(RangeDO range)
      throws ObjectAlreadyExistException, SecurityManagerDaoException {
    try {
      validation(range);
    }
    catch (SecurityManagerDaoException ex) {
      throw ex;
    }
    RangeDO rdo = null;
    try {
      rdo = createDao().createRange(range);
    }
    catch (SecurityManagerDaoException ex1) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex1);
    }
    catch (ObjectAlreadyExistException ex1) {
      throw new ObjectAlreadyExistException("Range ID " + range.getRangeID() + " is already exist.");
    }
    return rdo;
  }

  /**
   * create range use id, name, category, description
   * @param id - Data Range ID
   * @param name - Data Range name
   * @param cat - Data Range category
   * @param desc - Data Range description
   * @return RangeDO - Data Range Data Object
   * @throws ObjectAlreadyExistException
   * @throws SecurityManagerDaoException
   */
  public static RangeDO createRange(String id, String name, String cat, String desc, String parentID)
      throws ObjectAlreadyExistException, SecurityManagerDaoException {
    RangeDO rdo = null;
    try {
      rdo = createDao().createRange(id, name, cat, desc, parentID);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectAlreadyExistException ex) {
      throw new ObjectAlreadyExistException("Range ID " + id + " is already exist.");
    }
    return rdo;
  }

  /**
   * create the data range Category
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
    catch (ObjectAlreadyExistException ex) {
      throw new ObjectAlreadyExistException("");
    }
  }

  /**
   * delete the data range according to the name
   * @param name - data range name
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static void deleteRange(String name)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      createDao().deleteRange(name);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("Range ID " + name + " is not exist.");
    }
  }

  public static void deleteRange(RangeDO range)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      createDao().deleteRange(range);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("Range ID " + range.getRangeID() + " is not exist.");
    }
  }

  /**
   * update the data range according to the given Range Data Object
   * @param range - Data Range Object
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static void updateRange(RangeDO range)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      createDao().updateRange(range);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("Range ID " + range.getRangeID() + " is not exist.");
    }
  }

  /**
   * lookup the Data Range using the name
   * @param name - Data Range Name
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static RangeDO lookupRange(String name)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      return createDao().lookupRange(name);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("Range ID " + name + " is not exist.");
    }
  }

  /**
   * get all data range defined in Ldap
   * @return vector - the results of all data ranges
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static Vector getAllRanges()
      throws ObjectNotExistException, SecurityManagerDaoException {
    Vector vec = null;
    try {
      vec = createDao().getAllRange();
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("");
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
   *
   * @param pid
   * @param roles
   */
  public static void assignRoleRange(String rangeID, Vector roles)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      createDao().assignRoleRange(rangeID, roles);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("");
    }
  }

  /**
   *
   * @param permID
   * @param roles
   */
  public static void removeRoleRange(String rangeID, Vector roles)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      createDao().removeRoleRange(rangeID, roles);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("");
    }
  }

  public static Vector getRoleRanges(String role)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      return createDao().getRoleRanges(role);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("");
    }
  }

  public static Vector getSubRanges(RangeDO range)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      return createDao().getSubRanges(range);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("");
    }
  }

  public static RangeDO getParentRange(RangeDO range)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      return createDao().getParentRange(range);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("");
    }
  }

  public static boolean isSubRange(RangeDO p, RangeDO c)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      return createDao().isSubRange(p,c);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("");
    }
  }

  public static Vector removeSubRangesInVector(RangeDO p, Vector v)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      return createDao().removeSubRangesInVector(p,v);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("");
    }
  }

  /**
   * The helper function to vertify the validation
   * @param rangeDO - Range data object
   * @throws SecurityManagerDaoException
   */
  private static void validation(RangeDO rangeDO)
      throws SecurityManagerDaoException {
    if (rangeDO.getRangeID() == null || rangeDO.getRangeID().equals("")) {
      throw new SecurityManagerDaoException("Invalid Range ID.", new Exception("Invalid Range ID"));
    }
  }

  public static void main(String[] args) {
    RangeDO range = new RangeDO();
    range.setRangeID("5");

    RangeDO range1 = new RangeDO();
    range1.setRangeID("9");

    RangeDO parent = new RangeDO();
    parent.setRangeID("4");

    Vector vec = new Vector();
    vec.add(range);
    vec.add(range1);

    try {
//      System.out.println(getParentRange(range));
//      System.out.println(getSubRanges(range));
//      System.out.println(isSubRange(range,parent));
      System.out.println(removeSubRangesInVector(parent,vec));
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
