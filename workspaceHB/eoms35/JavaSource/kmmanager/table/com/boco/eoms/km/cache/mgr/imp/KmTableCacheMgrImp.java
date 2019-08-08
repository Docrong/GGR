package com.boco.eoms.km.cache.mgr.imp;

import com.boco.eoms.km.cache.mgr.KmTableCacheMgr;
import com.boco.eoms.km.cache.dao.jdbc.KmTableCacheDaoJdbc;

import com.boco.eoms.km.core.dataservice.map.EntityMap;
import com.boco.eoms.km.table.dao.KmTableGeneralDao;
import com.boco.eoms.km.table.model.KmTableGeneral;

public class KmTableCacheMgrImp implements KmTableCacheMgr {

    private KmTableGeneralDao kmTableGeneralDao;
    private KmTableCacheDaoJdbc kmTableCacheDaoJdbc;

    public KmTableGeneralDao getKmTableGeneralDao() {
        return kmTableGeneralDao;
    }

    public void setKmTableGeneralDao(KmTableGeneralDao kmTableGeneralDao) {
        this.kmTableGeneralDao = kmTableGeneralDao;
    }

    public KmTableCacheDaoJdbc getKmTableCacheDaoJdbc() {
        return kmTableCacheDaoJdbc;
    }

    public void setKmTableCacheDaoJdbc(KmTableCacheDaoJdbc kmTableCacheDaoJdbc) {
        this.kmTableCacheDaoJdbc = kmTableCacheDaoJdbc;
    }

    /**
     * 根据主键查询模型信息表
     *
     * @param id 主键
     * @return 返回某id的对象
     * @author zhangxb
     */
    public EntityMap getKmEntityMapByTableId(final String TABLE_ID) {
        EntityMap entityMap = kmTableCacheDaoJdbc.getKmEntityMap(TABLE_ID);
        return entityMap;
    }

    /**
     * 根据模型分类themeId查询模型信息表
     *
     * @param themeId 模型分类
     * @return 返回某模型分类themeId的对象
     * @author zhangxb
     */
    public EntityMap getKmEntityMapByThemeId(final String THEME_ID) {
        String rootThemeId = THEME_ID.substring(0, 3);
        KmTableGeneral kmTableGeneral = kmTableGeneralDao.getKmTableGeneralByThemeId(rootThemeId);
        if (kmTableGeneral != null && kmTableGeneral.getId() != null) {
            return kmTableCacheDaoJdbc.getKmEntityMap(kmTableGeneral.getId());
        }
        return null;
    }
}