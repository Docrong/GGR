
package com.boco.eoms.commons.system.area.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.system.area.model.TawSystemArea;

public interface ITawSystemAreaManager extends Manager {
    /**
     * Retrieves all of the tawSystemAreas
     */
    public List getTawSystemAreas(TawSystemArea tawSystemArea);

    /**
     * Gets tawSystemArea's information based on id.
     * @param id the tawSystemArea's id
     * @return tawSystemArea populated tawSystemArea object
     */
    public TawSystemArea getTawSystemArea(final String id);

    /**
     * Saves a tawSystemArea's information
     * @param tawSystemArea the object to be saved
     */
    public void saveTawSystemArea(TawSystemArea tawSystemArea);

    /**
     * Removes a tawSystemArea from the database by id
     * @param id the tawSystemArea's id
     */
    public void removeTawSystemArea(final String id);
    public Map getTawSystemAreas(final Integer curPage, final Integer pageSize);
    public Map getTawSystemAreas(final Integer curPage, final Integer pageSize, final String whereStr);

    /**
     * 根据地域ID查询地域信息
     * @param areaid
     * @return
     */
    public TawSystemArea getAreaByAreaId(String areaid);
    /**
     * 查询某地域的下一级地域信息
     * @param areaid
     * @return
     */
    public List getSonAreaByAreaId(String areaid);
    /**
     * 查询同级地域信息
     * @param parentareaid
     * @param ordercode
     * @return
     */
    public List getSameLevelArea(String parentareaid,Integer ordercode);
    /**
     * 查询某地域名称是否存在
     * @param areaname
     * @return
     */
    public boolean isExitAreaName(String areaname);
    /**
     * 查询某地域的所有子地域信息
     * @param areaid
     * @return
     */
    public List getAllSonAreaByAreaid(String areaid);
    
    /**
	 * 根据地域ID得到最大的子地域ID
	 * 
	 * @param deptid
	 * @return
	 */
	public String getNewAreaid(String parareaid) ;
	/**
	 * 查询某地域根据 areacode
	 * 
	 * @param areacode
	 * @return
	 */
	public TawSystemArea getAreaByCode(String areacode);
	 
	/**
	 * 查询某地域根据 areacode parentAreaid
	 * @param parentAreaid
	 * @param areacode
	 * @return
	 */
	public TawSystemArea getAreaByCode(String areacode,String parentAreaid) ;
	
	/*
	 * 根据地域名称判断这个地域在同一个上级下有没有同名的
	 */
	
	public boolean getAreaNameByName(String areaName,String parentAreaid);
}

