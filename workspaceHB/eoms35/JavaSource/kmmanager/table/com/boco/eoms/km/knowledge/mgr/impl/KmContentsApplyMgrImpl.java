package com.boco.eoms.km.knowledge.mgr.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.km.knowledge.model.KmContentsApply;
import com.boco.eoms.km.knowledge.mgr.KmContentsApplyMgr;
import com.boco.eoms.km.knowledge.dao.KmContentsApplyDao;
import com.boco.eoms.km.statics.util.StatisticMethod;

/**
 * <p>
 * Title:知识申请
 * </p>
 * <p>
 * Description:知识申请
 * </p>
 * <p>
 * Tue Jul 14 10:27:17 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public class KmContentsApplyMgrImpl implements KmContentsApplyMgr {

    private KmContentsApplyDao kmContentsApplyDao;

    public KmContentsApplyDao getKmContentsApplyDao() {
        return this.kmContentsApplyDao;
    }

    public void setKmContentsApplyDao(KmContentsApplyDao kmContentsApplyDao) {
        this.kmContentsApplyDao = kmContentsApplyDao;
    }

    public List getKmContentsApplys() {
        return kmContentsApplyDao.getKmContentsApplys();
    }

    public KmContentsApply getKmContentsApply(final String id) {
        return kmContentsApplyDao.getKmContentsApply(id);
    }

    public void saveKmContentsApply(KmContentsApply kmContentsApply) {
        kmContentsApplyDao.saveKmContentsApply(kmContentsApply);
    }

    public void removeKmContentsApply(final String id) {
        kmContentsApplyDao.removeKmContentsApply(id);
    }

    public Map getKmContentsApplys(final Integer curPage, final Integer pageSize,
                                   final String whereStr, final String orderStr) {
        return kmContentsApplyDao.getKmContentsApplys(curPage, pageSize, whereStr, orderStr);
    }

    public Map getKmContentsApplyRanks(final String startDate, final String endDate,
                                       final String userId, final String deptId, final String themeId, final String maxSize) {
        // TODO Auto-generated method stub
        Map map = kmContentsApplyDao.getKmContentsApplyRanks(startDate, endDate, userId, deptId, themeId, maxSize);
        List list = (List) map.get("result");
        List list_result = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Object[] objs = (Object[]) list.get(i);
            KmContentsApply kmContentsApply = new KmContentsApply();
            kmContentsApply.setId(StatisticMethod.objectToString(objs[0]));
            kmContentsApply.setApplyTitle(StatisticMethod.objectToString(objs[1]));
            kmContentsApply.setApplyCount(StatisticMethod.objectToString(objs[2])); //申请次数
            list_result.add(kmContentsApply);
        }
        map.put("result", list_result);

        return map;
    }

    public Map getKmContentsApplys(Integer curPage, Integer pageSize,
                                   String applyTheme, String startDate, String endDate, String orderStr) {
        return kmContentsApplyDao.getKmContentsApplys(curPage, pageSize, applyTheme, startDate, endDate, orderStr);
    }

}