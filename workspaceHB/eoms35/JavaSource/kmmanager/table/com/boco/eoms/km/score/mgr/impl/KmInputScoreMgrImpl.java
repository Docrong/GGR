package com.boco.eoms.km.score.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.score.dao.KmInputScoreDao;
import com.boco.eoms.km.score.mgr.KmInputScoreMgr;


/**
 * <p>
 * Title:知识导入积分管理
 * </p>
 * <p>
 * Description:知识导入积分管理
 * </p>
 * <p>
 * 2009-08-04
 * </p>
 *
 * @author daizhigang
 * @version 1.0
 */

public class KmInputScoreMgrImpl implements KmInputScoreMgr {

    private KmInputScoreDao kmInputScoreDao;

    public KmInputScoreDao getKmInputScoreDao() {
        return this.kmInputScoreDao;
    }

    public void setKmInputScoreDao(KmInputScoreDao kmInputScoreDao) {
        this.kmInputScoreDao = kmInputScoreDao;
    }

    /**
     * 根据条件分页查询知识导入积分
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回知识申请的分页列表
     */
    public Map getKmInputScores(final Integer curPage, final Integer pageSize,
                                final String whereStr) {
        return kmInputScoreDao.getKmInputScores(curPage, pageSize, whereStr);
    }

    /**
     * 根据条件和列表信息分页查询知识导入积分
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回知识申请的分页列表
     */
    public Map getKmInputScores(final Integer curPage, final Integer pageSize,
                                final String whereStr, final List column) {
        return kmInputScoreDao.getKmInputScores(curPage, pageSize, whereStr, column);

    }

    /**
     * 根据条件和列表信息分页查询知识导入积分
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回知识申请的分页列表
     */
    public Map getKmInputScoresHistory(final Integer curPage, final Integer pageSize,
                                       final String whereStr, final List column) {
        return kmInputScoreDao.getKmInputScoresHistory(curPage, pageSize, whereStr, column);

    }

    /**
     * 取出导入积分类型集合
     *
     * @return 返回导入积分类型集合
     */
    public List getKmInputScoreColumns() {
        return kmInputScoreDao.getKmInputScoreColumns();
    }
}