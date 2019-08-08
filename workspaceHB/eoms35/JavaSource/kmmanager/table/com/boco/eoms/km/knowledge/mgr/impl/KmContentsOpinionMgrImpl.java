package com.boco.eoms.km.knowledge.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.knowledge.model.KmContentsOpinion;
import com.boco.eoms.km.knowledge.mgr.KmContentsOpinionMgr;
import com.boco.eoms.km.knowledge.dao.KmContentsOpinionDao;

/**
 * <p>
 * Title:知识管理
 * </p>
 * <p>
 * Description:知识管理
 * </p>
 * <p>
 * Tue Mar 24 10:33:01 CST 2009
 * </p>
 *
 * @author eoms
 * @version 1.0
 */
public class KmContentsOpinionMgrImpl implements KmContentsOpinionMgr {

    private KmContentsOpinionDao kmContentsOpinionDao;

    public KmContentsOpinionDao getKmContentsOpinionDao() {
        return this.kmContentsOpinionDao;
    }

    public void setKmContentsOpinionDao(KmContentsOpinionDao kmContentsOpinionDao) {
        this.kmContentsOpinionDao = kmContentsOpinionDao;
    }

    public List getKmContentsOpinions() {
        return kmContentsOpinionDao.getKmContentsOpinions();
    }

    public KmContentsOpinion getKmContentsOpinion(final String id) {
        return kmContentsOpinionDao.getKmContentsOpinion(id);
    }

    public void saveKmContentsOpinion(KmContentsOpinion kmContentsOpinion) {
        kmContentsOpinionDao.saveKmContentsOpinion(kmContentsOpinion);
    }

    public void removeKmContentsOpinion(final String id) {
        kmContentsOpinionDao.removeKmContentsOpinion(id);
    }

    /**
     * 根据知识id删除所用对应的知识评论记录
     *
     * @param contentId
     */
    public void removeKmContentsOpinionList(final String contentId) {
        kmContentsOpinionDao.removeKmContentsOpinionList(contentId);
    }

    public Map getKmContentsOpinions(final Integer curPage, final Integer pageSize,
                                     final String whereStr) {
        return kmContentsOpinionDao.getKmContentsOpinions(curPage, pageSize, whereStr);
    }

}