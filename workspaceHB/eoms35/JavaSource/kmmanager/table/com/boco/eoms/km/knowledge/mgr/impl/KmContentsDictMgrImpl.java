package com.boco.eoms.km.knowledge.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.knowledge.model.KmContentsDict;
import com.boco.eoms.km.knowledge.mgr.KmContentsDictMgr;
import com.boco.eoms.km.knowledge.dao.KmContentsDictDao;

/**
 * <p>
 * Title:知识管理
 * </p>
 * <p>
 * Description:知识管理
 * </p>
 * <p>
 * Tue Mar 24 10:33:19 CST 2009
 * </p>
 *
 * @author eoms
 * @version 1.0
 */
public class KmContentsDictMgrImpl implements KmContentsDictMgr {

    private KmContentsDictDao kmContentsDictDao;

    public KmContentsDictDao getKmContentsDictDao() {
        return this.kmContentsDictDao;
    }

    public void setKmContentsDictDao(KmContentsDictDao kmContentsDictDao) {
        this.kmContentsDictDao = kmContentsDictDao;
    }

    public List getKmContentsDicts() {
        return kmContentsDictDao.getKmContentsDicts();
    }

    public KmContentsDict getKmContentsDict(final String id) {
        return kmContentsDictDao.getKmContentsDict(id);
    }

    public void saveKmContentsDict(KmContentsDict kmContentsDict) {
        kmContentsDictDao.saveKmContentsDict(kmContentsDict);
    }

    public void removeKmContentsDict(final String id) {
        kmContentsDictDao.removeKmContentsDict(id);
    }

    public Map getKmContentsDicts(final Integer curPage, final Integer pageSize,
                                  final String whereStr) {
        return kmContentsDictDao.getKmContentsDicts(curPage, pageSize, whereStr);
    }

}