package com.boco.eoms.km.table.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.table.model.KmTableDict;
import com.boco.eoms.km.table.mgr.KmTableDictMgr;
import com.boco.eoms.km.table.dao.KmTableDictDao;

/**
 * <p>
 * Title:知识字段字典
 * </p>
 * <p>
 * Description:知识字段字典
 * </p>
 * <p>
 * Mon Mar 02 14:55:43 CST 2009
 * </p>
 *
 * @author 吕卫华
 * @version 1.0
 */
public class KmTableDictMgrImpl implements KmTableDictMgr {

    private KmTableDictDao kmTableDictDao;

    public KmTableDictDao getKmTableDictDao() {
        return this.kmTableDictDao;
    }

    public void setKmTableDictDao(KmTableDictDao kmTableDictDao) {
        this.kmTableDictDao = kmTableDictDao;
    }

    public List getKmTableDicts() {
        return kmTableDictDao.getKmTableDicts();
    }

    public KmTableDict getKmTableDict(final String id) {
        return kmTableDictDao.getKmTableDict(id);
    }

    public void saveKmTableDict(KmTableDict kmTableDict) {
        kmTableDictDao.saveKmTableDict(kmTableDict);
    }

    public void removeKmTableDict(final String id) {
        kmTableDictDao.removeKmTableDict(id);
    }

    public Map getKmTableDicts(final Integer curPage, final Integer pageSize,
                               final String whereStr) {
        return kmTableDictDao.getKmTableDicts(curPage, pageSize, whereStr);
    }

}