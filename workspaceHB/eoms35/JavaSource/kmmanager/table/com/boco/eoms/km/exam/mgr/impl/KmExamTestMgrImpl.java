package com.boco.eoms.km.exam.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.exam.model.KmExamTest;
import com.boco.eoms.km.exam.mgr.KmExamTestMgr;
import com.boco.eoms.km.exam.dao.KmExamTestDao;

/**
 * <p>
 * Title:试卷
 * </p>
 * <p>
 * Description:试卷
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 *
 * @author hsl
 * @version 1.0
 */
public class KmExamTestMgrImpl implements KmExamTestMgr {

    private KmExamTestDao kmExamTestDao;

    public KmExamTestDao getKmExamTestDao() {
        return this.kmExamTestDao;
    }

    public void setKmExamTestDao(KmExamTestDao kmExamTestDao) {
        this.kmExamTestDao = kmExamTestDao;
    }

    /**
     * 得到发布的考试试题
     *
     * @return
     */
    public List getKmExamPublicTests() {
        return kmExamTestDao.getKmExamPublicTests();
    }

    /**
     * 得到发布的随机考试试卷
     *
     * @return
     */
    public List getKmExamRandomTests() {
        return kmExamTestDao.getKmExamRandomTests();
    }

    public List getKmExamTests() {
        return kmExamTestDao.getKmExamTests();
    }

    public KmExamTest getKmExamTest(final String id) {
        return kmExamTestDao.getKmExamTest(id);
    }

    public void saveKmExamTest(KmExamTest kmExamTest) {
        kmExamTestDao.saveKmExamTest(kmExamTest);
    }

    public void removeKmExamTest(final String id) {
        kmExamTestDao.removeKmExamTest(id);
    }

    public Map getKmExamTests(final Integer curPage, final Integer pageSize,
                              final String whereStr) {
        return kmExamTestDao.getKmExamTests(curPage, pageSize, whereStr);
    }

}