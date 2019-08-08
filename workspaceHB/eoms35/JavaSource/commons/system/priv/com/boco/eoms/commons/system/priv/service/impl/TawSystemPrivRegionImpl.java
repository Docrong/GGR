package com.boco.eoms.commons.system.priv.service.impl;

import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuCommonDao;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivRegionDao;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivRegionManager;

public class TawSystemPrivRegionImpl extends BaseManager implements
        ITawSystemPrivRegionManager {

    private TawSystemPrivMenuCommonDao tawSystemPrivRegionJdbc;

    private TawSystemPrivRegionDao tawSystemPrivRegionDao;

    public TawSystemPrivMenuCommonDao getTawSystemPrivRegionJdbc() {
        return tawSystemPrivRegionJdbc;
    }

    public void setTawSystemPrivRegionJdbc(
            TawSystemPrivMenuCommonDao tawSystemPrivRegionJdbc) {
        this.tawSystemPrivRegionJdbc = tawSystemPrivRegionJdbc;
    }

    public TawSystemPrivRegionDao getTawSystemPrivRegionDao() {
        return tawSystemPrivRegionDao;
    }

    public void setTawSystemPrivRegionDao(
            TawSystemPrivRegionDao tawSystemPrivRegionDao) {
        this.tawSystemPrivRegionDao = tawSystemPrivRegionDao;
    }

    /**
     * 查询所有的域
     *
     * @param tawSytemPrivRegion
     * @return
     */
    public List getTawSytemPrivRegion(TawSystemPrivRegion tawSytemPrivRegion) {
        return tawSystemPrivRegionDao.getTawSytemPrivRegion(tawSytemPrivRegion);
    }

    /**
     * 查询某域
     *
     * @param tawSytemPrivRegion
     * @return
     */
    public TawSystemPrivRegion getTawSytemPrivRegion(final Integer id) {
        return tawSystemPrivRegionDao.getTawSytemPrivRegion(id);
    }

    /**
     * 保存域
     *
     * @param tawSytemPrivRegion
     */
    public void saveTawSytemPrivRegion(TawSystemPrivRegion tawSytemPrivRegion) {
        tawSystemPrivRegionDao.saveTawSytemPrivRegion(tawSytemPrivRegion);
    }

    /**
     * 删除某域
     *
     * @param id
     */
    public void removeTawSytemPrivRegion(final Integer id) {
        tawSystemPrivRegionDao.removeTawSytemPrivRegion(id);
    }

    /**
     * 查询某对象包含的所有域
     *
     * @param objectid
     * @return
     */
    public List getObjectRegion(String objectid) {
        return tawSystemPrivRegionDao.getObjectRegion(objectid);
    }

    /**
     * 查询某对象 某种类型的域
     *
     * @param objectid
     * @param type
     * @return
     */
    public List getObjectRegionByTypejdbc(String objectid, String objecttype,
                                          String type) {
        return tawSystemPrivRegionJdbc.getObjectRegionByType(objectid,
                objecttype, type);
    }

    /**
     * 查询某对象 某种类型的域
     *
     * @param objectid
     * @param type
     * @return
     */
    public List getObjectRegionByType(String objectid, String objecttype,
                                      String type) {
        return tawSystemPrivRegionDao.getObjectRegionByType(objectid,
                objecttype, type);
    }

    public List getObjectRegionByType(String objectid, String objecttype, String region,
                                      String type) {
        return tawSystemPrivRegionDao.getObjectRegionByType(objectid,
                objecttype, region, type);
    }

    /**
     * 根据URL查询某对象的部门域或者机房域 或者全部域
     *
     * @param objectid
     * @param url
     * @param type
     * @return
     */
    public List getObjectRegionByUrl(String objectid, String url, String type) {
        return tawSystemPrivRegionDao.getObjectRegionByUrl(objectid, url, type);
    }

    /**
     * @param objectid
     * @param type
     * @return
     */
    public List getObjectRegionBytype(String objectid, String reginids,
                                      String flag) {
        return tawSystemPrivRegionJdbc.getObjectRegionBytype(objectid,
                reginids, flag);
    }

    /**
     * 判断某菜单项是否存在于某些菜单方案
     *
     * @param menuids
     * @return
     */
    public boolean hasRegionBymenuids(String menuids, String code) {
        return tawSystemPrivRegionJdbc.hasRegionBymenuids(menuids, code);
    }

    /**
     * 删除某对象的权限域
     *
     * @param objectid
     * @param objecttype
     */
    public void removeOjbectregion(String objectid, String objecttype) {
        tawSystemPrivRegionJdbc.removeOjbectregion(objectid, objecttype);
    }

    public void removedeptregion(String objectid, String regionid) {
        tawSystemPrivRegionJdbc.removedeptregion(objectid, regionid);
    }
}
