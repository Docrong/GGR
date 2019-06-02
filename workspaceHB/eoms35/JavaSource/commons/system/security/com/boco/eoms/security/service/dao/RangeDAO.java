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
import com.boco.eoms.security.service.model.RangeDO;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Department Data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author weis
 * @version 1.0
 */

public interface RangeDAO {

  //------------------------------------------------------
  //  Data Range management
  //------------------------------------------------------

  /**
   * create Date Range object
   *
   * @param id    range id, should take the format
   * @param name
   * @param category
   * @param desc
   * @return
   * @throws ObjectAlreadyExistException
   * @throws SecurityManagerDaoException
   */
  public RangeDO createRange(String id, String name, String category, String desc,String parentID)
      throws ObjectAlreadyExistException, SecurityManagerDaoException;

  /**
   * create the data range according to the range data object
   * @param rangedo   data range object
   * @return  rangedo data Range object
   * @throws ObjectAlreadyExistException
   * @throws SecurityManagerDaoException
   */
  public RangeDO createRange(RangeDO rangedo)
      throws ObjectAlreadyExistException, SecurityManagerDaoException;

  /**
   * create data range category
   *
   * @param name  category name
   * @param desc    category description
   * @throws ObjectAlreadyExistException
   * @throws SecurityManagerDaoException
   */
  public void createCategory(String name, String desc)
      throws ObjectAlreadyExistException, SecurityManagerDaoException;

  /**
   * delete Data Range
   * @param name
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public void deleteRange(String name)
      throws ObjectNotExistException, SecurityManagerDaoException;

  /**
   * delete Date Range according to the Range data object
   * @param range
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public void deleteRange(RangeDO range)
      throws ObjectNotExistException, SecurityManagerDaoException;

  /**
   *update a Data Range object
   */
  public void updateRange(RangeDO range)
      throws ObjectNotExistException, SecurityManagerDaoException;

  /**
   *lookup a Data Range object
   */
  public RangeDO lookupRange(String name)
      throws ObjectNotExistException, SecurityManagerDaoException;

  /**
   * get all range.
   * */
  public Vector getAllRange()
      throws ObjectNotExistException, SecurityManagerDaoException;

  public Vector getRangeByCategory(Object category)
      throws ObjectNotExistException, SecurityManagerDaoException;

  public Vector getAllCategory()
      throws ObjectNotExistException, SecurityManagerDaoException;

  public void assignRoleRange(String rangeID, Vector roles)
      throws ObjectNotExistException, SecurityManagerDaoException;

  public void removeRoleRange(String rangeID, Vector roles)
      throws ObjectNotExistException, SecurityManagerDaoException ;
  public Vector getRoleRanges(String role)
      throws ObjectNotExistException, SecurityManagerDaoException ;

  public Vector getSubRanges(RangeDO range)
      throws ObjectNotExistException, SecurityManagerDaoException ;
  public RangeDO getParentRange(RangeDO range)
      throws ObjectNotExistException, SecurityManagerDaoException ;
  public boolean isSubRange(RangeDO p, RangeDO c)
      throws ObjectNotExistException, SecurityManagerDaoException ;
  public Vector removeSubRangesInVector(RangeDO p, Vector v)
      throws ObjectNotExistException, SecurityManagerDaoException ;
}
