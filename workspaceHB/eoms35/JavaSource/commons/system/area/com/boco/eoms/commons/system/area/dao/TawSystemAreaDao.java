package com.boco.eoms.commons.system.area.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.area.model.TawSystemArea;

public interface TawSystemAreaDao extends Dao {

    /**
     * Retrieves all of the tawSystemAreas
     */
    public List getTawSystemAreas(TawSystemArea tawSystemArea);

    /**
     * Gets tawSystemArea's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
     * found.
     *
     * @param id the tawSystemArea's id
     * @return tawSystemArea populated tawSystemArea object
     */
    public TawSystemArea getTawSystemArea(final Integer id);

    /**
     * Saves a tawSystemArea's information
     *
     * @param tawSystemArea the object to be saved
     */
    public void saveTawSystemArea(TawSystemArea tawSystemArea);

    /**
     * Removes a tawSystemArea from the database by id
     *
     * @param id the tawSystemArea's id
     */
    public void removeTawSystemArea(final Integer id);

    /**
     * 用于分页显示 curPage 当前页数 pageSize 每页显示数
     */
    public Map getTawSystemAreas(final Integer curPage, final Integer pageSize);

    public Map getTawSystemAreas(final Integer curPage, final Integer pageSize,
                                 final String whereStr);

    /**
     * 根据地域ID查询地域信息
     *
     * @param areaid
     * @return
     */
    public TawSystemArea getAreaByAreaId(String areaid);

    /**
     * 查询某地域的下一级地域信息
     *
     * @param areaid
     * @return
     */
    public List getSonAreaByAreaId(String areaid);

    /**
     * 查询同级地域信息
     *
     * @param parentareaid
     * @param ordercode
     * @return
     */
    public List getSameLevelArea(String parentareaid, Integer ordercode);

    /**
     * 查询某地域名称是否存在
     *
     * @param areaname
     * @return
     */
    public boolean isExitAreaName(String areaname);

    /**
     * 查询某地域的所有子地域信息
     *
     * @param areaid
     * @return
     */
    public List getAllSonAreaByAreaid(String areaid);

    /**
     * 查询某地域根据 areacode
     *
     * @param areacode
     * @return
     */
    public TawSystemArea getAreaByCode(String areacode);

    /**
     * 查询某地域根据 areacode parentAreaid
     *
     * @param parentAreaid
     * @param areacode
     * @return
     */
    public TawSystemArea getAreaByCode(String areacode, String parentAreaid);

    public boolean getAreaNameByName(String areaName, String parentAreaid);

    /**
     * 通过用户取其所在地
     *
     * @param userId 用户id
     * @return 某用户其所在地
     */
    public TawSystemArea getAreaOfUser(String userId);

    /**
     * 取部门所在地
     *
     * @param deptId 部门id
     * @return 某部门所在地
     */
    public TawSystemArea getAreaOfDept(String deptId);

    /**
     * @param areaId
     * @return
     */
    public List getChildAreas(String areaId);

    /**
     * 取所有地域
     *
     * @return 返回地域列表
     */
    public List getAreas();
}
