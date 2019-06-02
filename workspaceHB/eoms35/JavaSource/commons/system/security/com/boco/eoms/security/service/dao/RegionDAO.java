/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   weis            2003-08-15         created
 * V1.0.0.0   Wang Zhuo Wei   2003-08-18         amend
 */


package com.boco.eoms.security.service.dao;

import java.util.Vector;

import com.boco.eoms.security.exception.ObjectAlreadyExistException;
import com.boco.eoms.security.exception.ObjectNotExistException;
import com.boco.eoms.security.exception.SecurityManagerDaoException;
import com.boco.eoms.security.service.model.RangeDO;
import com.boco.eoms.security.service.model.RegionDO;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Department Data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author weis
 * @version 1.0
 */
public interface RegionDAO {
     /**
     * name of the region
     * @param region region object to persist
     * @throws com.boco.common.security.exception.ObjectAlreadyExistException , SecurityManagerDaoException;
     */
    public RegionDO createRegion(RegionDO region) throws ObjectAlreadyExistException, SecurityManagerDaoException;

    /**
     * remove region object
     * @param region region object to remove , the parameter may be Region Object or id of the region
     * @throws com.boco.common.security.exception.ObjectNotExistException , SecurityManagerDaoException;
     */
    public void deleteRegion(RegionDO region) throws ObjectNotExistException, SecurityManagerDaoException;

    /**
     * update region information
     * @param region region object to update
     * @throws com.boco.common.security.exception.ObjectNotExistException , SecurityManagerDaoException;
     */
    public void updateRegion(RegionDO region) throws ObjectNotExistException, SecurityManagerDaoException;

    /**
     * get all regions
     * @return vector of regions
     * @throws com.boco.common.security.exception.SecurityManagerDaoException
     */
    public Vector getRegionList() throws SecurityManagerDaoException;

    /**
     * @return total number of regions
     */
    public int getCount() throws SecurityManagerDaoException;

    /**
     * retrieve the region with the given name
     *
     * @param id id of the region
     * @throws ObjectNotExistException ,SecurityManagerDaoException
     */
    public RegionDO lookupRegion(RegionDO region) throws ObjectNotExistException, SecurityManagerDaoException;


    // ------------------------------------------------
    //  Region hierachy management
    // ------------------------------------------------

    /**
     * get parent region
     * @param rgnid rgn id to retrieve
     * @return parent region object , null if no parent region found
     */
    public RegionDO getParentRegion(RegionDO region) throws ObjectNotExistException, SecurityManagerDaoException;

    /**
     * get sub region
     * @param id id of the region
     * @return sub region ids in hieracht order
     */
    public Vector getSubRegions(RegionDO region) throws ObjectNotExistException, SecurityManagerDaoException;

    // ------------------------------------------------
    //  Department Management
    // ------------------------------------------------

    /**
     * get departments belong to a region
     * @param region region to retrieve
     * @return vector containing department collections
     * @throws com.boco.common.security.exception.SecurityManagerDaoException
     */
    public Vector getDepartmentList(RegionDO region) throws SecurityManagerDaoException;

    public void addRange(RegionDO region, RangeDO range) throws ObjectNotExistException, SecurityManagerDaoException;
    public void removeRange(RegionDO region,RangeDO range) throws ObjectNotExistException, SecurityManagerDaoException;
}
