
package com.boco.eoms.commons.system.area.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.model.util.TawSystemAreaUtil;
import com.boco.eoms.commons.system.area.dao.TawSystemAreaDao;
import com.boco.eoms.commons.system.area.dao.TawSystemAreaDaoJdbc;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;

public class TawSystemAreaManagerImpl extends BaseManager implements ITawSystemAreaManager {
    private TawSystemAreaDao dao;
    private TawSystemAreaDaoJdbc daojdbc;

    /**
     * Set the Dao for communication with the data layer.
     *
     * @param dao
     */
    public void setTawSystemAreaDao(TawSystemAreaDao dao) {
        this.dao = dao;
    }


    public TawSystemAreaDaoJdbc getDaojdbc() {
        return daojdbc;
    }


    public void setDaojdbc(TawSystemAreaDaoJdbc daojdbc) {
        this.daojdbc = daojdbc;
    }


    /**
     * @see com.boco.eoms.commons.system.area.service.ITawSystemAreaManager#getTawSystemAreas(com.boco.eoms.commons.system.area.model.TawSystemArea)
     */
    public List getTawSystemAreas(final TawSystemArea tawSystemArea) {
        return dao.getTawSystemAreas(tawSystemArea);
    }

    /**
     * @see com.boco.eoms.commons.system.area.service.ITawSystemAreaManager#getTawSystemArea(String id)
     */
    public TawSystemArea getTawSystemArea(final String id) {
        return dao.getTawSystemArea(new Integer(id));
    }

    /**
     * @see com.boco.eoms.commons.system.area.service.ITawSystemAreaManager#saveTawSystemArea(TawSystemArea tawSystemArea)
     */
    public void saveTawSystemArea(TawSystemArea tawSystemArea) {
        dao.saveTawSystemArea(tawSystemArea);
    }

    /**
     * @see com.boco.eoms.commons.system.area.service.ITawSystemAreaManager#removeTawSystemArea(String id)
     */
    public void removeTawSystemArea(final String id) {
        dao.removeTawSystemArea(new Integer(id));
    }

    /**
     *
     */
    public Map getTawSystemAreas(final Integer curPage, final Integer pageSize) {
        return dao.getTawSystemAreas(curPage, pageSize, null);
    }

    public Map getTawSystemAreas(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawSystemAreas(curPage, pageSize, whereStr);
    }

    /**
     * 根据地域ID查询地域信息
     *
     * @param areaid
     * @return
     */
    public TawSystemArea getAreaByAreaId(String areaid) {
        return dao.getAreaByAreaId(areaid);
    }

    /**
     * 查询某地域的下一级地域信息
     *
     * @param areaid
     * @return
     */
    public List getSonAreaByAreaId(String areaid) {
        return dao.getSonAreaByAreaId(areaid);
    }

    /**
     * 查询同级地域信息
     *
     * @param parentareaid
     * @param ordercode
     * @return
     */
    public List getSameLevelArea(String parentareaid, Integer ordercode) {
        return dao.getSameLevelArea(parentareaid, ordercode);
    }

    /**
     * 查询某地域名称是否存在
     *
     * @param areaname
     * @return
     */
    public boolean isExitAreaName(String areaname) {
        return dao.isExitAreaName(areaname);
    }

    /**
     * 查询某地域的所有子地域信息
     *
     * @param areaid
     * @return
     */
    public List getAllSonAreaByAreaid(String areaid) {
        return dao.getAllSonAreaByAreaid(areaid);
    }

    /**
     * 根据地域ID得到最大的子地域ID
     *
     * @param deptid
     * @return
     */
    public String getNewAreaid(String parareaid) {
        String newareaid = TawSystemAreaUtil.AREA_DEFAULT_STRVAL;
        long areaidvar = TawSystemAreaUtil.AREA_DEFAULT_LONGVAL;

        newareaid = daojdbc.getMaxAreaId(parareaid, parareaid.length() + TawSystemAreaUtil.AREAID_BETWEEN_LENGTH);
        if (newareaid.equals(parareaid)) {
            newareaid = parareaid + TawSystemAreaUtil.AREAID_NOSON;
        } else {
            areaidvar = Long.valueOf(newareaid).longValue();
            if (newareaid.compareTo(parareaid + TawSystemAreaUtil.AREAID_IF_MAXID) < TawSystemAreaUtil.AREA_DEFAULT_INTVAL) {
                newareaid = String.valueOf(areaidvar + TawSystemAreaUtil.AREA_DEFAULT_INTHVAL);
            } else {
                newareaid = TawSystemAreaUtil.AREAID_MAXID;
            }
        }
        return newareaid;
    }

    /**
     * 查询某地域根据 areacode
     *
     * @param areacode
     * @return
     */
    public TawSystemArea getAreaByCode(String areacode) {

        return dao.getAreaByCode(areacode);
    }

    /**
     * 查询某地域根据 areacode parentAreaid
     *
     * @param parentAreaid
     * @param areacode
     * @return
     */
    public TawSystemArea getAreaByCode(String areacode, String parentAreaid) {

        return dao.getAreaByCode(areacode, parentAreaid);
    }

    /*
     * 根据地域名称判断这个地域在同一个上级下有没有同名的
     */

    public boolean getAreaNameByName(String areaName, String parentAreaid) {
        return dao.getAreaNameByName(areaName, parentAreaid);
    }
}
