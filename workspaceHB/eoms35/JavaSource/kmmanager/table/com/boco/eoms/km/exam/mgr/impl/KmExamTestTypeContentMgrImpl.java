package com.boco.eoms.km.exam.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.exam.model.KmExamTestTypeContent;
import com.boco.eoms.km.exam.mgr.KmExamTestTypeContentMgr;
import com.boco.eoms.km.exam.dao.KmExamTestTypeContentDao;

/**
 * <p>
 * Title:题型内容表
 * </p>
 * <p>
 * Description:题型内容表
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 *
 * @author hsl
 * @version 1.0
 */
public class KmExamTestTypeContentMgrImpl implements KmExamTestTypeContentMgr {

    private KmExamTestTypeContentDao kmExamTestTypeContentDao;

    public KmExamTestTypeContentDao getKmExamTestTypeContentDao() {
        return this.kmExamTestTypeContentDao;
    }

    public void setKmExamTestTypeContentDao(KmExamTestTypeContentDao kmExamTestTypeContentDao) {
        this.kmExamTestTypeContentDao = kmExamTestTypeContentDao;
    }

    public List getKmExamTestTypeContents() {
        return kmExamTestTypeContentDao.getKmExamTestTypeContents();
    }

    /**
     * 查询某类型下所有题目信息
     *
     * @param testTypeID
     * @return
     */
    public List getKmExamTestTypeContentByTestTypeID(final String testTypeID) {
        return kmExamTestTypeContentDao.getKmExamTestTypeContentByTestTypeID(testTypeID);
    }

    public KmExamTestTypeContent getKmExamTestTypeContent(final String id) {
        return kmExamTestTypeContentDao.getKmExamTestTypeContent(id);
    }

    public void saveKmExamTestTypeContent(KmExamTestTypeContent kmExamTestTypeContent) {
        kmExamTestTypeContentDao.saveKmExamTestTypeContent(kmExamTestTypeContent);
    }

    public void removeKmExamTestTypeContent(final String id) {
        kmExamTestTypeContentDao.removeKmExamTestTypeContent(id);
    }

    public Map getKmExamTestTypeContents(final Integer curPage, final Integer pageSize,
                                         final String whereStr) {
        return kmExamTestTypeContentDao.getKmExamTestTypeContents(curPage, pageSize, whereStr);
    }

}