package com.boco.eoms.km.log.mgr.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.km.log.dao.KmOperateLogDao;
import com.boco.eoms.km.log.mgr.KmOperateLogMgr;
import com.boco.eoms.km.log.model.KmOperateLog;
import com.boco.eoms.km.statics.mgr.KmOperateScoreMgr;
import com.boco.eoms.km.statics.model.KmOperateScore;

/**
 * <p>
 * Title:知识操作历史记录表
 * </p>
 * <p>
 * Description:知识操作历史记录表
 * </p>
 * <p>
 * Tue Mar 24 16:04:02 CST 2009
 * </p>
 *
 * @author ljt
 * @version 0.1
 */
public class KmOperateLogMgrImpl implements KmOperateLogMgr {

    private KmOperateLogDao kmOperateLogDao;
    private KmOperateScoreMgr kmOperateScoreMgr;


    public KmOperateLogDao getKmOperateLogDao() {
        return this.kmOperateLogDao;
    }

    public void setKmOperateLogDao(KmOperateLogDao kmOperateLogDao) {
        this.kmOperateLogDao = kmOperateLogDao;
    }

    public KmOperateScoreMgr getKmOperateScoreMgr() {
        return kmOperateScoreMgr;
    }

    public void setKmOperateScoreMgr(KmOperateScoreMgr kmOperateScoreMgr) {
        this.kmOperateScoreMgr = kmOperateScoreMgr;
    }

    public List getKmOperateLogs() {
        return kmOperateLogDao.getKmOperateLogs();
    }

    public KmOperateLog getKmOperateLog(final String id) {
        return kmOperateLogDao.getKmOperateLog(id);
    }

    public void saveKmOperateLog(KmOperateLog kmOperateLog) {
        kmOperateLogDao.saveKmOperateLog(kmOperateLog);
    }

    public void removeKmOperateLog(final String id) {
        kmOperateLogDao.removeKmOperateLog(id);
    }

    public Map getKmOperateLogs(final Integer curPage, final Integer pageSize,
                                final String whereStr) {
        return kmOperateLogDao.getKmOperateLogs(curPage, pageSize, whereStr);
    }


    /**
     * 添加知识操作历史记录接口,提供给其他模块操作
     * params:
     * themeId 知识分类id
     * tableId 表id
     * contentId 知识id
     * operateId 操作id
     * operateUser 操作人
     * operateDept 操作部门
     * toUser 目标人
     */
    public void saveKmOperateLog(String themeId, String tableId, String contentId, Integer operateId, String operateUser, String deptId, String toUser) {
        KmOperateLog kmOperateLog = new KmOperateLog();
        kmOperateLog.setThemeId(themeId);
        kmOperateLog.setTableId(tableId);
        kmOperateLog.setContentId(contentId);
        kmOperateLog.setOperateUser(operateUser);
        kmOperateLog.setOperateDept(deptId);
        kmOperateLog.setOperateId(operateId);

        KmOperateScore kmOperateScore = this.kmOperateScoreMgr.getKmOperateScore(operateId);
        if (kmOperateScore != null) {
            kmOperateLog.setScore(kmOperateScore.getScore());
        }

        kmOperateLog.setToUser(toUser);
        kmOperateLog.setOperateTime(new Date());

        //保存
        this.saveKmOperateLog(kmOperateLog);
    }
}