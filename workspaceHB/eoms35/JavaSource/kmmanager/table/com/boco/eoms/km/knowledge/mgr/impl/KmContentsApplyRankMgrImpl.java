package com.boco.eoms.km.knowledge.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.knowledge.dao.KmContentsApplyRankDao;
import com.boco.eoms.km.knowledge.mgr.KmContentsApplyRankMgr;
import com.boco.eoms.km.knowledge.util.CreateChartUtil;

/**
 * <p>
 * Title:知识申请排行
 * </p>
 * <p>
 * Description:知识申请排行
 * </p>
 * <p>
 * Wed Aug 19 15:53:50 CST 2009
 * </p>
 *
 * @author wangzhiyong
 * @version 1.0
 */
public class KmContentsApplyRankMgrImpl implements KmContentsApplyRankMgr {

    private KmContentsApplyRankDao kmContentsApplyRankDao;

    public KmContentsApplyRankDao getKmContentsApplyRankDao() {
        return this.kmContentsApplyRankDao;
    }

    public void setKmContentsApplyRankDao(KmContentsApplyRankDao kmContentsApplyRankDao) {
        this.kmContentsApplyRankDao = kmContentsApplyRankDao;
    }


    public String getKmContentsApplyRankDetail(final String id, final String startDate, final String endDate) {
        Map map = kmContentsApplyRankDao.getKmContentsApplyRankDetail(id, startDate, endDate);
        CreateChartUtil c = new CreateChartUtil();
        List list = (List) map.get("result");
        int listSize = list.size();
        double[][] data = new double[listSize][1];
        String[] rowKeys = new String[listSize];
        String[] columnKeys = {"申请次数"};

        for (int i = 0; i < listSize; i++) {
            Object[] objs = (Object[]) list.get(i);
            data[i][0] = ((Integer) objs[0]).doubleValue();
            rowKeys[i] = (String) objs[1];
        }
        String imageName = "";
        if (listSize > 0) {
            imageName = id + startDate + endDate + ".png";
            c.createBarChart(data, rowKeys, columnKeys, "申请日期", "日申请量", "每日申请量(次/日)", imageName);
        }

        return imageName;
    }


}