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
import com.boco.eoms.security.service.dao.RegionDAO;
import com.boco.eoms.security.service.model.RangeDO;
import com.boco.eoms.security.service.model.RegionDO;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: all operation to region data object </p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class RegionManager {

  private static SystemConfig sc = SystemConfig.getInstance();

  /**
   * Desc : create the corresponding DAO according to the configuration file
   * @return RegionDAO - The data access object of Region
   * @throws SecurityManagerDaoException
   */
  private static RegionDAO createDao()
      throws SecurityManagerDaoException {
    try {
      //System.out.println(sc.regionDAOClass);
      return (RegionDAO) Class.forName(sc.regionDAOClass).newInstance();
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
   * @param region - the region information to create
   * @return RegionDO - the created region data object
   * @throws ObjectAlreadyExistException
   * @throws SecurityManagerDaoException
   */
  public static RegionDO createRegion(RegionDO region)
      throws ObjectAlreadyExistException, SecurityManagerDaoException {
    try {
      validation(region);
    }
    catch (SecurityManagerDaoException ex) {
      throw ex;
    }

    RegionDO tmp = null;
    try {
      tmp = createDao().createRegion(region);
    }
    catch (SecurityManagerDaoException ex1) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex1);
    }
    catch (ObjectAlreadyExistException ex1) {
      throw new ObjectAlreadyExistException("Region ID " + region.getRegionID() + " is already exist.");
    }
    return tmp;
  }

  /**
   * Desc :
   * @param region - the region you want to delete
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static void deleteRegion(RegionDO region)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      validation(region);
    }
    catch (SecurityManagerDaoException ex) {
      throw ex;
    }

    try {
      createDao().deleteRegion(region);
    }
    catch (SecurityManagerDaoException ex1) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex1);
    }
    catch (ObjectNotExistException ex1) {
      throw new ObjectNotExistException("Region ID " + region.getRegionID() + " is not exist.");
    }
  }

  /**
   * Desc :
   * @param region - the region you want to update
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static void updateRegion(RegionDO region)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      validation(region);
    }
    catch (SecurityManagerDaoException ex) {
      throw ex;
    }

    try {
      createDao().updateRegion(region);
    }
    catch (SecurityManagerDaoException ex1) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex1);
    }
    catch (ObjectNotExistException ex1) {
      throw new ObjectNotExistException("Region ID " + region.getRegionID() + " is not exist.");
    }
  }

  /**
   * Desc :
   * @param regionName - the region name you want to search for
   * @return RegionDO - The corresponding region data object according to the name
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static RegionDO lookupRegion(RegionDO region)
      throws ObjectNotExistException, SecurityManagerDaoException {
    RegionDO tmp = null;
    try {
      tmp = createDao().lookupRegion(region);
    }
    catch (SecurityManagerDaoException ex1) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex1);
    }
    catch (ObjectNotExistException ex1) {
      throw new ObjectNotExistException("Region ID " + region.getRegionID() + " is not exist.");
    }
    return tmp;
  }

  /**
   * Desc :
   * @param regionName - the current region name
   * @return RegionDO - the parent region information
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static RegionDO getParentRegion(RegionDO regionName)
      throws ObjectNotExistException, SecurityManagerDaoException {
    RegionDO tmp = null;
    try {
      tmp = createDao().getParentRegion(regionName);
    }
    catch (SecurityManagerDaoException ex1) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex1);
    }
    catch (ObjectNotExistException ex1) {
      throw new ObjectNotExistException("Region ID " + regionName.getRegionID() + " is not exist.");
    }
    return tmp;
  }

  /**
   * Desc :
   * @param regionName - the current region name
   * @return Vector - the sub-region list
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static Vector getSubRegions(RegionDO regionName)
      throws ObjectNotExistException, SecurityManagerDaoException {
    Vector tmp = null;
    try {
      tmp = createDao().getSubRegions(regionName);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("Region ID " + regionName.getRegionID() + " is not exist.");
    }
    return tmp;
  }

  /**
   * Desc :
   * @param regionName - the current region name
   * @return Vector - the department list
   * @throws SecurityManagerDaoException
   */
  public static Vector getDepartmentList(RegionDO regionName)
      throws SecurityManagerDaoException {
    Vector tmp = null;
    try {
      tmp = createDao().getDepartmentList(regionName);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    return tmp;
  }

  /**
   * Desc :
   * @return Vector - the all regions
   * @throws SecurityManagerDaoException
   */
  public static Vector getRegionList()
      throws SecurityManagerDaoException {
    Vector tmp = null;
    try {
      tmp = createDao().getRegionList();
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
    return tmp;
  }

  public static void addRange(RegionDO region, RangeDO range)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      createDao().addRange(region, range);
    }
    catch (ObjectNotExistException ex){
      throw new SecurityManagerDaoException("ָ���ĵ��������򲻴��ڡ�", ex);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
  }

  public static void removeRange(RegionDO region, RangeDO range)
      throws ObjectNotExistException, SecurityManagerDaoException {
    try {
      createDao().removeRange(region, range);
    }
    catch (ObjectNotExistException ex){
      throw new SecurityManagerDaoException("ָ���ĵ��������򲻴��ڡ�", ex);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("����ʧ�ܣ������Ƿ����㹻��Ȩ�ޡ�", ex);
    }
  }

  /**
   * Desc : check the mandatory fields of RegionDO
   * @param role - the region data object which want to be validated
   * @throws SecurityManagerDaoException
   */
  private static void validation(RegionDO region)
      throws SecurityManagerDaoException {
    if (region.getRegionID() == null || region.getRegionID().equals("")) {
      throw new SecurityManagerDaoException("Invalid Region ID.", new Exception("Invalid Region ID."));
    }
  }

  /*only for self-testing*/
  public static void main(String[] args) {
    RegionDO region = new RegionDO();
    region.setRegionID("bbb");
//    region.setName("aaa");
//    region.setComment("aaa");
//    region.addParentHierarchy("aaa");

    try {
//      createRegion(region);
      System.out.println(getParentRegion(region));
//      region = RegionManager.lookupRegion(region);
//      RangeDO rdo1 = RangeManager.lookupRange("3");
//      RangeDO rdo2 = RangeManager.lookupRange("2");
//
//      removeRange(region,rdo1);
//      addRange(region,rdo2);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}